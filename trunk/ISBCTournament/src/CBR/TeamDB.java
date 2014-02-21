package CBR;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import jcolibri.test.database.HSQLDBserver;
import jcolibri.test.database.SqlFile;
import jcolibri.util.FileIO;

import org.hsqldb.Server;

public class TeamDB {

	static boolean initialized = false;

    private static Server server;
    
    public static void init(){
    	if (initialized) return;
    	server = new Server();
    	server.setDatabaseName(0, "teamisbc");
    	server.setDatabasePath(0, "mem:team;sql.enforce_strict_size=true");
    	server.setLogWriter(null);
    	server.setErrWriter(null);
    	server.setSilent(true);
    	server.start();

    	initialized = true;
    	
    	try{
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/teamisbc", "sa", "");
    		SqlFile file = new SqlFile(new File(FileIO.findFile("src/CBR/teamx.sql").getFile()),false,new HashMap());
    		file.execute(conn, true);
    	}catch(Exception e){
    		org.apache.commons.logging.LogFactory.getLog(HSQLDBserver.class).error(e);
    	}
    }
    
    public static void shutDown(){
    	if (initialized)
    	{
    	    server.stop();
    	    initialized = false;
    	}
    }
	
}
