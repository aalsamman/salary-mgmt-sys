package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;

import diverse.dao.LoginDAO;
import diverse.object.Login;
import diverse.util.DBUtil;

public class LoginService {
	
	public boolean getLogin(Login login) throws SQLException{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		LoginDAO log=new LoginDAO(connect);
		return log.getLogin(login);
		
	}

}
