package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.SalaryItemValueDAO;

import diverse.util.DBUtil;
//import DAO.LogManagerDAO;
import diverse.object.SalaryItemValue;

public class SalaryItemValueService
{
	public List getAllItemsValue()
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		SalaryItemValueDAO dao=new SalaryItemValueDAO(connect);
		List<SalaryItemValue> tempList=null;

		
		try {
			tempList=dao.getAllItemsValue();
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
	
	public void insertValue(SalaryItemValue value)
	{
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemValueDAO dao=new SalaryItemValueDAO(connect);
		//LogManagerDAO dao2=new LogManagerDAO(conn);
		
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			dao.insertValue(value);
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
	
	public void deleteValue(int employeeId, int salaryItemId){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemValueDAO dao = new SalaryItemValueDAO(connect);
		
		try {
			dao.deleteValue(employeeId, salaryItemId);
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
	
	public SalaryItemValue getValue(int employeeId, int salaryItemId){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemValueDAO dao=new SalaryItemValueDAO(connect);
		SalaryItemValue value = new SalaryItemValue();
		
		try {
			value = dao.getValue(employeeId, salaryItemId);
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
		return value;
	}
	
	public void modifyValue(SalaryItemValue value){
		
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemValueDAO dao = new SalaryItemValueDAO(connect);
		
		try {
			dao.modifyValue(value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
