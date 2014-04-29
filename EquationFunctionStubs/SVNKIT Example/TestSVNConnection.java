package svn;

import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.SVNAuthentication;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

public class TestSVNConnection
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			/**
			 * Test the SVN connection is valid. An exception will be thrown if the connection cannot be made.
			 * 
			 * @param url
			 * @throws SVNException
			 */
			String url = "http://manvlincm01/svn/repos/bfeq_src";
			String user = "williae1";
			String password = "#button201205x";
			SVNURL svnUrl = SVNURL.parseURIDecoded(url.toString());

			SVNRepository repository = SVNRepositoryFactory.create(svnUrl);

			// SVNRepository repository = clientManager.createRepository(SVNURL.parseURIEncoded(url), false);
			BasicAuthenticationManager authManager = new BasicAuthenticationManager(user, password);
			authManager.setAuthenticationForced(true);
			boolean flag = false;
			String s = "";
			String s1 = "";
			SVNErrorMessage svnerrormessage = null;
			SVNAuthentication svnauthentication = null;
			authManager.acknowledgeAuthentication(flag, s, s1, svnerrormessage, svnauthentication);
			repository.setAuthenticationManager(authManager);
			repository.testConnection();
			repository.getRepositoryUUID(true);
			repository.closeSession();
		}
		catch (SVNException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
