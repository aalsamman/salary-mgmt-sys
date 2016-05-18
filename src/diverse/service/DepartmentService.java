package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.DepartmentDAO;


import diverse.util.DBUtil;
//import DAO.LogManagerDAO;

import diverse.object.Department;


public class DepartmentService
{
	public List getAllDepartments()
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		DepartmentDAO dao=new DepartmentDAO(connect);
		List<Department> tempList=null;

		
		try {
			tempList = dao.getAllDepartments();
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
	
	public void insertDepartment(Department department)
	{
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		DepartmentDAO dao=new DepartmentDAO(connect);
		//LogManagerDAO dao2=new LogManagerDAO(conn);
		
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			dao.insertDepartment(department);
			//dao2.insertLog("user: "+u.getUsername()+" is created...");
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteDepartment(int id){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		DepartmentDAO dao = new DepartmentDAO(connect);
		
		try {
			dao.deleteDepartment(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Department getDepartment(int id){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		DepartmentDAO dao=new DepartmentDAO(connect);
		Department department = new Department();
		
		try {
			department = dao.getDepartment(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return department;
	}
	
	public void modifyDepartment(Department department){
		
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		DepartmentDAO dao = new DepartmentDAO(connect);
		
		try {
			dao.modifyDepartment(department);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
