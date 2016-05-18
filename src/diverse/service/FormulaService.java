package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.FormulaDAO;

import diverse.object.Formula;
import diverse.util.DBUtil;

public class FormulaService 
{
	public List getFormulas()
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		FormulaDAO dao = new FormulaDAO(connect);
		List<Formula> tempList=null;

		
		try {
			tempList=dao.getFormulas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return tempList;
	}

}
