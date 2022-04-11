package agent;

import javax.rad.server.SessionContext;

import com.sibvisions.rad.persist.jdbc.DBAccess;
import com.sibvisions.rad.persist.jdbc.IDBAccess;
import com.sibvisions.rad.server.security.DBSecurityManager;

/**
 * The LCO for the session.
 */
public class Session extends Application
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Returns access to the database.
	 * 
	 * @return the database access
	 * @throws Exception if a connection error occurs
	 */
	public IDBAccess getDBAccess() throws Exception
	{
		//TODO replace or use this connection creation 

		DBAccess dba = (DBAccess)get("dBAccess");
		
		if (dba == null)
		{
			dba = DBAccess.getDBAccess(DBSecurityManager.getCredentials(SessionContext.getCurrentSessionConfig()));
			dba.open();
					
			put("dBAccess", dba);
		}
		
		return dba;
	}	

}	// Session
