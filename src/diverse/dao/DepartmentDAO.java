package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.Department;

public class DepartmentDAO 
{

	Connection connect;
	
	public DepartmentDAO(Connection conn)
	{
		this.connect = conn;
	}
	
	public List<Department> getAllDepartments()throws SQLException
	{
		List<Department> tempList = new ArrayList<Department>();
		PreparedStatement statement = connect.prepareStatement("select * from Department");
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			Department department = new Department();
			department.setId(rs.getInt("id"));
			department.setName(rs.getString("name"));
			tempList.add(department);
		}
		
		return tempList;
	}
	
	public void insertDepartment(Department department)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("insert into Department values(SALARYITEMID_SEQUENCE.nextval,?)");
		statement.setString(1, department.getName());
		statement.executeUpdate();
	}
	
	public void deleteDepartment(int id)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("delete from Department where id=?");
		statement.setInt(1, id);
		statement.executeUpdate();
		
	}
	
	public Department getDepartment(int id)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("select * from Department where id=?");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		rs.next();
		
		Department department = new Department();
		
		department.setId(rs.getInt("id"));
		department.setName(rs.getString("name"));
		
		return department;
	}
	
	public void modifyDepartment(Department department)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("update Department set name=? where id=?");
		
		statement.setString(1, department.getName());
		statement.setInt(2, department.getId());
		statement.executeUpdate();
	}
	
}


