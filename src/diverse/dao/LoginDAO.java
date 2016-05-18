package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import diverse.object.Login;

public class LoginDAO {
	
	Connection connect;
	
	public LoginDAO(Connection conn){
		this.connect=conn;
	}
	
	public boolean getLogin(Login login) throws SQLException{
		PreparedStatement statement = connect.prepareStatement("select * from login where username=? and password=?");
		statement.setString(1,login.getUsername());
		statement.setString(2, login.getPassword());
		ResultSet rs = statement.executeQuery();
		if(rs.next())
			return true;
		return false;
	}

}
