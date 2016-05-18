package diverse.util;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

public class DBUtil {
	
	static DBUtil dbutil = null;
	String driverclass = "";
	String url = "";
	String username = "";
	String password = "";
	
		public DBUtil(){
			try {
				
				Properties pro = new Properties();
				pro.load(this.getClass().getResourceAsStream("/diverse/util/database.properties"));
				
				driverclass= pro.getProperty("driverclass");
				url = pro.getProperty("url");
				username = pro.getProperty("username");
				password = pro.getProperty("password");
				
				
				Class.forName(driverclass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static DBUtil getInstance(){
			if(dbutil==null){
				dbutil=new DBUtil();
			}
			return dbutil;
		}
		
		public Connection getConnection(){
			Connection connection=null;
			try {
				//connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ahmad", "orcl");
				connection=DriverManager.getConnection(url, username, password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
			
		}
	}

