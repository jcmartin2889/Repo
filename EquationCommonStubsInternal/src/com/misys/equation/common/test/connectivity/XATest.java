package com.misys.equation.common.test.connectivity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.ibm.as400.access.AS400JDBCXADataSource;
import com.ibm.as400.access.AS400JDBCXAResource;
import com.misys.equation.common.utilities.Toolbox;

public class XATest
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XATest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	String user1 = "WEDDELC1";
	String password1 = "ACHT0NG";
	String system1 = "SLOUGH1";
	String user2 = "WEDDELC1";
	String password2 = "APR2010";
	String system2 = "MNG";
	String library = "*LIBL";
	AS400JDBCXADataSource xaDataSource1 = null;
	XAConnection xaConnection1 = null;
	XAResource xaResource1 = null;
	XidImpl xid = null;
	Connection connection1 = null;
	Statement statement1 = null;
	AS400JDBCXADataSource xaDataSource2 = null;
	XAConnection xaConnection2 = null;
	XAResource xaResource2 = null;
	AS400JDBCXAResource a;
	// XidImpl xid2 = null;
	Connection connection2 = null;
	Statement statement2 = null;

	private XATest()
	{
	}

	public static void main(String[] args)
	{
		System.out.println("Proceeding with test...");
		XATest test = new XATest();
		test.test();
	}

	private void test()
	{
		try
		{
			xaDataSource1 = new AS400JDBCXADataSource();
			xaDataSource1.setServerName(system1);
			xaDataSource1.setUser(user1);
			xaDataSource1.setPassword(password1);
			xaDataSource1.setLibraries(library);
			xaDataSource1.setNaming("system");
			xaDataSource1.setTranslateBinary(false);
			xaDataSource1.setTrace(true);

			xaDataSource2 = new AS400JDBCXADataSource();
			xaDataSource2.setServerName(system2);
			xaDataSource2.setUser(user2);
			xaDataSource2.setPassword(password2);
			xaDataSource2.setLibraries(library);
			xaDataSource2.setNaming("system");
			xaDataSource2.setTranslateBinary(false);
			xaDataSource2.setTrace(true);

			xaConnection1 = xaDataSource1.getXAConnection();
			xaConnection2 = xaDataSource2.getXAConnection();

			xaResource1 = xaConnection1.getXAResource();
			xaResource2 = xaConnection2.getXAResource();

			xid = new XidImpl(new byte[] { 0x0d }, new byte[] { 0x0b });

			xaResource1.start(xid, XAResource.TMNOFLAGS);

			// Need to decide whether to join based on whether the resources have the same resource manager
			// if the are the same then the prepares, commits and rollbacks should only be executed on
			// the first resource
			if (xaResource1.isSameRM(xaResource2))
			{
				xaResource2.start(xid, XAResource.TMJOIN);
			}
			else
			{
				xaResource2.start(xid, XAResource.TMNOFLAGS);
			}

			// Do some SQL on connection 1
			connection1 = xaConnection1.getConnection();
			statement1 = connection1.createStatement();
			statement1.executeUpdate("INSERT INTO KFILEQ4/JVPF (JVHRC, JVHRD) VALUES('X01', 'X01D')");
			statement1.close();

			// Do some SQL on connection 2
			connection2 = xaConnection2.getConnection();
			statement2 = connection2.createStatement();
			statement2.executeUpdate("INSERT INTO KFILEQ4/JVPF (JVHRC, JVHRD) VALUES('X02', 'X02D')");
			statement2.close();

			// tell the resources that we have successfully finished doing the work,
			// need to consider what to do if the SQL had gone wrong!
			xaResource1.end(xid, XAResource.TMSUCCESS);
			xaResource2.end(xid, XAResource.TMSUCCESS);

			if (xaResource1.isSameRM(xaResource2))
			{
				int ret1 = xaResource1.prepare(xid);
				// int ret2 = xaResource2.prepare(xid);
				xaResource1.commit(xid, false);
				// xaResource2.commit(xid, false);
			}
			else
			{
				int ret1 = xaResource1.prepare(xid);
				int ret2 = xaResource2.prepare(xid);
				xaResource1.commit(xid, false);
				xaResource2.commit(xid, false);
			}
		}
		catch (XAException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				xaConnection1.close();
				xaConnection2.close();
			}
			catch (Exception e2)
			{
				// TODO: handle exception
			}
		}
	}
	public static class XidImpl implements Serializable, Xid
	{
		private static final long serialVersionUID = 1L;
		private static Random random = new Random();
		private final int formatId = 876;
		private final byte[] globalTransactionId;
		private final byte[] branchQualifier;
		private transient String cachedToString;
		private transient int cachedHashCode;

		public XidImpl()
		{
			globalTransactionId = new byte[10];
			random.nextBytes(globalTransactionId);
			branchQualifier = new byte[10];
			random.nextBytes(branchQualifier);
		}
		public XidImpl(byte[] globalTransactionId, byte[] branchQualifier)
		{
			this.globalTransactionId = globalTransactionId;
			this.branchQualifier = branchQualifier;
		}

		public int getFormatId()
		{
			return formatId;
		}

		public byte[] getGlobalTransactionId()
		{
			return globalTransactionId;
		}

		public byte[] getBranchQualifier()
		{
			return branchQualifier;
		}

		@Override
		public boolean equals(Object object)
		{
			if (object == this)
				return true;
			if (object == null || object instanceof Xid == false)
				return false;

			Xid other = (Xid) object;
			return (formatId == other.getFormatId() && Arrays.equals(globalTransactionId, other.getGlobalTransactionId()) && Arrays
							.equals(branchQualifier, other.getBranchQualifier()));
		}

		@Override
		public int hashCode()
		{
			if (cachedHashCode == 0)
			{
				cachedHashCode = formatId;
				for (int j = 0; j < globalTransactionId.length; ++j)
					cachedHashCode += globalTransactionId[j];
			}
			return cachedHashCode;
		}

		@Override
		public String toString()
		{
			if (cachedToString == null)
			{
				StringBuffer buffer = new StringBuffer();
				buffer.append("XidImpl[FormatId=").append(getFormatId());
				buffer.append(" GlobalId=x'").append(Toolbox.cvtBytesToHexString(getGlobalTransactionId()));
				buffer.append("' BranchQual=x'").append(Toolbox.cvtBytesToHexString(getBranchQualifier()));
				buffer.append("']");
				cachedToString = buffer.toString();
			}
			return cachedToString;
		}
	}
}
