package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.SalaryItemValue;

public class SalaryItemValueDAO {

	Connection connect;

	public SalaryItemValueDAO(Connection conn) {
		this.connect = conn;
	}

	public List<SalaryItemValue> getAllItemsValue() throws SQLException {
		List<SalaryItemValue> tempList = new ArrayList<SalaryItemValue>();
		PreparedStatement statement = connect
				.prepareStatement("select * from SalaryValue");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			SalaryItemValue value = new SalaryItemValue();
			value.setEmployeeID(rs.getInt("employeeID"));
			value.setSalaryItemID(rs.getInt("salaryItemID"));
			value.setEditor(rs.getString("editor"));
			value.setDate(rs.getTimestamp("editDate"));
			value.setValue(rs.getDouble("svalue"));

			tempList.add(value);
		}

		return tempList;
	}

	public void insertValue(SalaryItemValue value) throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("insert into SalaryValue values(?,?,?,?,?)");
		// statement.setInt(0, item.getId());
		statement.setInt(1, value.getEmployeeID());
		statement.setInt(2, value.getSalaryItemID());
		statement.setDouble(3, value.getValue());
		statement.setTimestamp(4, value.getDate());
		statement.setString(5, value.getEditor());

		statement.executeUpdate();
	}

	public void deleteValue(int employeeId, int salaryItemId)
			throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("delete from SalaryValue where employeeID=? and salaryItemID=?");
		statement.setInt(1, employeeId);
		statement.setInt(2, salaryItemId);
		statement.executeUpdate();

	}

	public SalaryItemValue getValue(int employeeId, int salaryItemId)
			throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("select * from SalaryValue where employeeID=? and salaryItemID=?");
		statement.setInt(1, employeeId);
		statement.setInt(2, salaryItemId);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {

			SalaryItemValue value = new SalaryItemValue();

			value.setEmployeeID(rs.getInt("employeeID"));
			value.setSalaryItemID(rs.getInt("salaryItemID"));
			value.setEditor(rs.getString("editor"));
			value.setDate(rs.getTimestamp("editDate"));
			value.setValue(rs.getDouble("svalue"));

			return value;
		} else
			return null;
	}

	public void modifyValue(SalaryItemValue value) throws SQLException {
		PreparedStatement statement = connect
				.prepareStatement("update SalaryValue set svalue=?, editDate=?,"
						+ "editor=? where employeeID=? and salaryItemID=?");
		statement.setDouble(1, value.getValue());
		statement.setTimestamp(2, value.getDate());
		statement.setString(3, value.getEditor());
		statement.setInt(4, value.getEmployeeID());
		statement.setInt(5, value.getSalaryItemID());

		statement.executeUpdate();
	}

}
