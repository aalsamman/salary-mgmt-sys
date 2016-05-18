package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.dao.EmployeeDAO;
import diverse.object.Employee;
import diverse.util.DBUtil;

public class EmployeeService {

	public List<Employee> getEmployeesByDepartment(int departmentID) {

		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();

		EmployeeDAO dao = new EmployeeDAO(connect);
		List<Employee> tempList = new ArrayList();

		try {
			tempList = dao.getEmployeesByDepartment(departmentID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
