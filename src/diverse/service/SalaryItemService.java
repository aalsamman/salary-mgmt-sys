package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import diverse.dao.SalaryItemDAO;

import diverse.util.DBUtil;
//import DAO.LogManagerDAO;
import diverse.dao.SalaryItemDAO;
import diverse.object.SalaryItem;

public class SalaryItemService
{
	public List getAllItems()
	{
		
		DBUtil dbutil=DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		
		SalaryItemDAO dao=new SalaryItemDAO(connect);
		List<SalaryItem> tempList=null;

		
		try {
			tempList=dao.getAllItems();
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
	
	public void insertItem(SalaryItem item)
	{
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemDAO dao=new SalaryItemDAO(connect);
		//LogManagerDAO dao2=new LogManagerDAO(conn);
		
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			dao.insertItem(item);
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
	
	public void deleteItem(int id){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemDAO dao = new SalaryItemDAO(connect);
		
		try {
			dao.deleteItem(id);
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
	
	public SalaryItem getItem(int id){
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemDAO dao=new SalaryItemDAO(connect);
		SalaryItem item = new SalaryItem();
		
		try {
			item = dao.getItem(id);
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
		return item;
	}
	
	public void modifyItem(SalaryItem item){
		
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		SalaryItemDAO dao = new SalaryItemDAO(connect);
		
		try {
			dao.modifyItem(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}