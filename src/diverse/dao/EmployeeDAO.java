package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.Employee;
import diverse.object.FixedSalary;

public class EmployeeDAO {

	Connection connect;
	
	public EmployeeDAO(Connection conn){
		this.connect=conn;
	}
	
	public List<Employee> getEmployeesByDepartment(int departmentID)throws SQLException{
		List<Employee> tempList = new ArrayList<Employee>();

		PreparedStatement statement = connect.prepareStatement("select id as employeeid, name as employeename, gender as gender from employee where departmentid=?");
		statement.setInt(1, departmentID);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()){
			Employee temp=new Employee();
			temp.setDepartmentID(departmentID);
			temp.setId(rs.getInt("employeeid"));
			temp.setName(rs.getString("employeename"));
			temp.setGender(rs.getString("gender"));
			tempList.add(temp);			
		}
		return tempList;
	}
}
