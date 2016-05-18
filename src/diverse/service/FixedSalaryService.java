package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.FixedSalaryDAO;
import diverse.object.FixedSalary;
import diverse.util.DBUtil;

public class FixedSalaryService 
{
	public List getFixedSalaries(int departmentID)
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		FixedSalaryDAO dao=new FixedSalaryDAO(connect);
		List<FixedSalary> tempList=null;

		
		try {
			tempList=dao.getFixedSalaries(departmentID);
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
