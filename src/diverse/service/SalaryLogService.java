package diverse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.dao.DepartmentDAO;
import diverse.dao.LogDAO;
import diverse.dao.SalaryItemDAO;
import diverse.object.*;
import diverse.util.DBUtil;

public class SalaryLogService

{
	public void save(SalaryLog entry) {
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		LogDAO dao = new LogDAO(connect);
		// LogManagerDAO dao2=new LogManagerDAO(conn);

		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			dao.save(entry);
			// dao2.insertLog("user: "+u.getUsername()+" is created...");
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
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void saveTotal(SalaryLog entry) {
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		LogDAO dao = new LogDAO(connect);
		// LogManagerDAO dao2=new LogManagerDAO(conn);

		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			dao.saveTotal(entry);
			// dao2.insertLog("user: "+u.getUsername()+" is created...");
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
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void pay(int departmentId, int month) {
		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();
		LogDAO dao = new LogDAO(connect);
		// LogManagerDAO dao2=new LogManagerDAO(conn);

		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			dao.pay(departmentId, month);
			// dao2.insertLog("user: "+u.getUsername()+" is created...");
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
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List getLog(int departmentId, int month) {

		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();

		LogDAO dao = new LogDAO(connect);
		List<SalaryLog> tempList = null;

		try {
			tempList = dao.getLog(departmentId, month);
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

	public List getLog() {

		DBUtil dbutil = DBUtil.getInstance();
		Connection connect = dbutil.getConnection();

		LogDAO dao = new LogDAO(connect);
		List<SalaryLog> tempList = null;

		try {
			tempList = dao.getLog();
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
