package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.ImportedSalaryDAO;
import diverse.object.ImportedSalary;
import diverse.util.DBUtil;

public class ImportedSalaryService 
{
	public List getImportedSalaries(int departmentID)
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		ImportedSalaryDAO dao=new ImportedSalaryDAO(connect);
		List<ImportedSalary> tempList=null;

		
		try {
			tempList=dao.getImportedSalaries(departmentID);
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
