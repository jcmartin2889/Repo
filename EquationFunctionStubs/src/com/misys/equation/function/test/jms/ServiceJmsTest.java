package com.misys.equation.function.test.jms;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

/**
 * 
 * Prerequisites:
 * 
 * WebSphere MQ installed, running with TCP listener running on local machine</br> Local Queues BF.SERVICE.REQUEST and
 * BF.SERVICE.REPLY must exist.
 * 
 * The ZPJ service needs to be created and exported.
 * 
 * The JMSTestMicroflow1 Microflow needs to be published. This uses the Equation ServiceRequest and ServiceResponse types, and
 * MQ_MSG_ServiceRequest/Response XML Messages
 * 
 * The Test system needs to have a suitable Camel-Context file which reads from BF.SERVICE.REQUEST and calls JMSTestMicroflow1
 * (which replies to BF.SERVICE.REPLY).
 * 
 */
public class ServiceJmsTest extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceJmsTest.java 17758 2014-01-07 14:49:14Z perkinj1 $";
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * This needs to ensure that:
	 * <ul>
	 * a) The send and reply queues are empty before starting the test b) the message can be sent successfully (doesn't prove much)
	 * c) A reply message appears (the contents are ignored for now d) Send a retrieve mode message e) Read the response to the
	 * retrieve mode request. Check the response contains the updated description
	 * 
	 * If all the above is OK, then the service is being processed OK.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void testService() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String unitId = "BR9";
		String systemId = "SLOUGH1";
		String sendQueueName = "BF.SERVICE.REQUEST";
		String replyQueueName = "BF.SERVICE.REPLY";
		String optionId = "ZPJ";

		// Use reflection to avoid the Ant build having to include MQ jar files.
		Class<?> mqConFactory = Class.forName("com.ibm.mq.jms.MQConnectionFactory");
		ConnectionFactory cf = (ConnectionFactory) mqConFactory.newInstance();
		Connection c = null;
		Session s = null;

		try
		{
			// Commented out (would need to call by reflection now)
			// cf.setPort(1414);
			// cf.setHostName("localhost");
			c = cf.createConnection();
			s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue q = s.createQueue(sendQueueName);

			if (!isQueueEmpty(s, q))
			{
				throw new RuntimeException("Send queue [" + q.getQueueName() + "] is not empty. Queue is not being read");
			}

			// Clear down the reply queue
			clearQueue(c, s, replyQueueName);

			MessageProducer p = s.createProducer(q);

			StringBuffer buffer = new StringBuffer();
			buffer
							.append("<eqfhdr:serviceRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:eqfhdr=\"http://www.misys.com/eqf/types\">");
			buffer.append("<header>");
			buffer.append(buildSimpleTag("optionId", optionId));
			buffer.append("<rqHeader>");
			buffer.append(buildSimpleTag("systemId", systemId));
			buffer.append(buildSimpleTag("unitId", unitId));
			buffer.append("</rqHeader>");
			buffer.append("</header>");

			buffer
							.append("<zpj:data xmlns:zpj=\"http://www.misys.com/equation/bankfusion/service/cmn\" xsi:type=\"zpj:EQ_CMN_holdCodeServiceForJmsZPJ_SRV\">");
			buffer.append(buildSimpleTag("A_HRC_holdCode", "JBP"));
			String newDescription = "JUnit test [" + Long.toString(new Date().getTime()) + "]";
			buffer.append(buildSimpleTag("A_HRD_holdDescription", newDescription));
			buffer.append("</zpj:data>");
			buffer.append("</eqfhdr:serviceRequest>");
			TextMessage msg = s.createTextMessage(buffer.toString());
			p.send(msg);

			// Now try to read from the reply queue:
			// Note that the first time this service is run, it will take a while.
			TextMessage reply = readFromQueue(c, s, replyQueueName, 20000);
			if (reply == null)
			{
				throw new RuntimeException("No reply message received ");
			}
			// Ignore the response...and assume the queue is cleared now

			// Build retrieve mode message
			buffer = new StringBuffer();
			buffer
							.append("<eqfhdr:serviceRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:eqfhdr=\"http://www.misys.com/eqf/types\">");
			buffer.append("<header>");
			buffer.append(buildSimpleTag("optionId", optionId));
			// Retrieve mode = B:
			buffer.append(buildSimpleTag("serviceMode", "B"));
			buffer.append("<rqHeader>");
			buffer.append(buildSimpleTag("systemId", systemId));
			buffer.append(buildSimpleTag("unitId", unitId));
			buffer.append("</rqHeader>");
			buffer.append("</header>");

			buffer
							.append("<zpj:data xmlns:zpj=\"http://www.misys.com/equation/bankfusion/service/cmn\" xsi:type=\"zpj:EQ_CMN_holdCodeServiceForJmsZPJ_SRV\">");
			buffer.append(buildSimpleTag("A_HRC_holdCode", "JBP"));
			buffer.append("</zpj:data>");
			buffer.append("</eqfhdr:serviceRequest>");
			msg = s.createTextMessage(buffer.toString());
			p.send(msg);

			reply = readFromQueue(c, s, replyQueueName, 5000);
			if (reply == null)
			{
				throw new RuntimeException("No reply message received ");
			}

			String replyDescription = extractTagContents(reply.getText(), "A_HRD_holdDescription");

			// The real test: Did the service update complete?
			assertEquals(newDescription, replyDescription);
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
		finally
		{
			safeClose(s);
			safeClose(c);
		}
	}

	private TextMessage readFromQueue(Connection c, Session s, String queueName, long timeout) throws JMSException
	{
		TextMessage result = null;
		MessageConsumer con = null;
		try
		{
			Queue q = s.createQueue(queueName);
			con = s.createConsumer(q);
			c.start();

			Message responseMsg = con.receive(timeout);
			if (responseMsg instanceof TextMessage)
			{
				result = (TextMessage) responseMsg;
				System.out.println(result.getText());
			}
		}
		finally
		{
			safeClose(con);
			try
			{
				c.stop();
			}
			catch (JMSException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Clear a JMS queue by repeatedly reading it
	 * 
	 * @param c
	 *            Connection
	 * @param s
	 *            Session
	 * @param queueName
	 *            JMS queue name
	 * @throws JMSException
	 */
	private void clearQueue(Connection c, Session s, String queueName) throws JMSException
	{
		MessageConsumer con = null;
		try
		{
			Queue q = s.createQueue(queueName);
			con = s.createConsumer(q);
			c.start();

			Message responseMsg = con.receiveNoWait();
			while (responseMsg != null)
			{
				responseMsg = con.receiveNoWait();
			}
		}
		finally
		{
			safeClose(con);
			try
			{
				c.stop();
			}
			catch (JMSException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Determines whether the queue is currently empty
	 * 
	 * @param session
	 * @param queue
	 * @return
	 * @throws JMSException
	 */
	private boolean isQueueEmpty(Session session, Queue queue) throws JMSException
	{
		QueueBrowser qb = session.createBrowser(queue);
		return !qb.getEnumeration().hasMoreElements();
	}

	private String buildSimpleTag(String tagName, String contents)
	{
		StringBuilder builder = new StringBuilder("<");
		builder.append(tagName);
		builder.append(">");
		builder.append(contents);
		builder.append("</");
		builder.append(tagName);
		builder.append(">");
		return builder.toString();
	}
	private void safeClose(MessageConsumer con)
	{
		if (con != null)
		{
			try
			{
				con.close();
			}
			catch (JMSException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void safeClose(Connection c)
	{
		if (c != null)
		{
			try
			{
				c.close();
			}
			catch (JMSException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void safeClose(Session s)
	{
		if (s != null)
		{
			try
			{
				s.close();
			}
			catch (JMSException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String extractTagContents(String xml, String tagName)
	{
		String result = null;
		String startTag = "<" + tagName + ">";
		String endTag = "</" + tagName + ">";
		int startIndex = xml.indexOf(startTag);
		int endIndex = xml.indexOf(endTag);

		if (startIndex > -1 && endIndex > -1 && endIndex > startIndex)
		{
			result = xml.substring(startIndex + startTag.length(), endIndex);
		}
		return result;
	}
}
