package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.SalaryLog;

public class LogDAO {

	Connection connect;

	public LogDAO(Connection conn) {
		this.connect = conn;
	}

	public List<SalaryLog> getLog() throws SQLException {
		List<SalaryLog> tempList = new ArrayList<SalaryLog>();
		PreparedStatement statement = connect
				.prepareStatement("select * from HistoryLog");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			SalaryLog entry = new SalaryLog();
			entry.setDepartmentID(rs.getInt("departmentID"));
			entry.setEmployeeID(rs.getInt("employeeID"));
			entry.setSalaryItemID(rs.getInt("salaryItemID"));
			entry.setSalaryItemValue(rs.getDouble("salaryItemValue"));
			entry.setEditor(rs.getString("editor"));
			entry.setMonth(rs.getInt("dateMonth"));
			tempList.add(entry);
		}

		return tempList;
	}

	public List<SalaryLog> getLog(int departmentId, int month)
			throws SQLException {
		List<SalaryLog> tempList = new ArrayList<SalaryLog>();
		PreparedStatement statement = connect
				.prepareStatement("select h.*,e.name,s.name as itemname from historylog h,employee e,salaryitem s where h.employeeid = e.id and s.id = h.salaryitemid and h.departmentid=? and h.dateMonth=?");
		statement.setInt(1, departmentId);
		statement.setInt(2, month);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			SalaryLog entry = new SalaryLog();
			entry.setEmployeeID(rs.getInt("employeeID"));
			entry.setSalaryItemID(rs.getInt("salaryItemID"));
			entry.setSalaryItemValue(rs.getDouble("salaryItemValue"));
			entry.setName(rs.getString("name"));
			entry.setItemName(rs.getString("itemname"));

			/*
			 * PreparedStatement statement1 =
			 * connect.prepareStatement("select name from Employee where id=?");
			 * statement1.setInt(1, rs.getInt("employeeID")); ResultSet res =
			 * statement.executeQuery(); while(res.next()) {
			 * 
			 * }
			 */
			tempList.add(entry);
		}

		for (int i = 0; i < tempList.size(); i++) {
			SalaryLog log = new SalaryLog();
			log = tempList.get(i);
			System.out.println("LogDAO#################################");
			System.out.println(log.getEmployeeID() + "\t" + log.getName()
					+ "\t" + log.getItemName());
			System.out.println("LogDAO#################################");
		}

		return tempList;
	}

	public void save(SalaryLog entry) throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("insert into SalaryLog values(?,?,?,?,?,?)");
		statement.setInt(1, entry.getEmployeeID());
		statement.setInt(2, entry.getDepartmentID());
		statement.setInt(3, entry.getSalaryItemID());
		statement.setDouble(4, entry.getSalaryItemValue());
		statement.setTimestamp(5, entry.getEditDate());
		statement.setString(6, entry.getEditor());

		statement.executeUpdate();
	}

	public void saveTotal(SalaryLog entry) throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("insert into TotalSalaryLog values(?,?,?)");
		statement.setInt(1, entry.getEmployeeID());
		statement.setDouble(3, entry.getSalaryItemValue());
		statement.setInt(2, entry.getMonth());

		statement.executeUpdate();
	}

	public void pay(int departmentId, int month) throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("insert into historylog select employeeID,departmentID,salaryItemID,salaryItemValue,EXTRACT(month FROM editDate)from salarylog");
		statement.executeUpdate();
		PreparedStatement statement1 = connect
				.prepareStatement("insert into totalhistorylog select * from totalsalarylog");
		statement1.executeUpdate();
		PreparedStatement statement2 = connect
				.prepareStatement("delete from salarylog");
		statement2.executeUpdate();
		PreparedStatement statement3 = connect
				.prepareStatement("delete from totalsalarylog");
		statement3.executeUpdate();
		PreparedStatement statement4 = connect
				.prepareStatement("insert into departmentsalary values(?,?,?)");
		statement4.setInt(1, departmentId);
		statement4.setInt(2, month);
		statement4.setString(3, "Y");
		statement4.executeUpdate();
	}

	/*
	 * public void deleteItem(int id)throws SQLException { PreparedStatement
	 * statement =
	 * connect.prepareStatement("delete from SalaryItem where id=?");
	 * statement.setInt(1, id); statement.executeUpdate();
	 * 
	 * }
	 * 
	 * public SalaryItem getItem(int id)throws SQLException { PreparedStatement
	 * statement =
	 * connect.prepareStatement("select * from SalaryItem where id=?");
	 * statement.setInt(1, id); ResultSet rs=statement.executeQuery();
	 * rs.next();
	 * 
	 * SalaryItem item = new SalaryItem();
	 * 
	 * item.setId(rs.getInt("id")); item.setCategory(rs.getString("category"));
	 * item.setName(rs.getString("name"));
	 * if(rs.getString("stype").equalsIgnoreCase("Y")) item.setType("increase");
	 * else if (rs.getString("stype").equalsIgnoreCase("N"))
	 * item.setType("increase"); else item.setType("");
	 * if(rs.getString("displayed").equalsIgnoreCase("Y"))
	 * item.setDisplayed(true); else item.setDisplayed(false);
	 * item.setComment(rs.getString("comments"));
	 * item.setBaseId(rs.getInt("baseItemID"));
	 * item.setPrecedent(rs.getFloat("precedent"));
	 * item.setOperator(rs.getString("soperator"));
	 * 
	 * return item; }
	 * 
	 * public void modifyItem(SalaryItem item)throws SQLException {
	 * PreparedStatement statement =connect.prepareStatement(
	 * "update SalaryItem set category=?,name=?,stype=?, displayed=?," +
	 * "comments=?,baseItemID=?, precedent=?, soperator=? where id=?");
	 * statement.setString(1, item.getCategory()); statement.setString(2,
	 * item.getName()); if(item.getType().equals("increase"))
	 * statement.setString(3, "Y"); else if (item.getType().equals("decrease"))
	 * statement.setString(3, "N"); else statement.setString(3, "E");
	 * if(item.isDisplayed()) statement.setString(4, "Y"); else
	 * statement.setString(4, "N"); statement.setString(5, item.getComment());
	 * statement.setInt(6, item.getBaseId()); statement.setFloat(7,
	 * item.getPrecedent()); statement.setString(8, item.getOperator());
	 * statement.setInt(9, item.getId());
	 * 
	 * statement.executeUpdate(); }
	 */

}
