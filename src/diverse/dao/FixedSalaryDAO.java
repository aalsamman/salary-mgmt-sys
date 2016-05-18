package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import diverse.object.FixedSalary;
import diverse.object.SalaryItem;

public class FixedSalaryDAO {

	Connection connect;

	public FixedSalaryDAO(Connection conn) {
		this.connect = conn;
	}

	public List<FixedSalary> getFixedSalaries(int departmentID)
			throws SQLException {
		List<FixedSalary> tempList = new ArrayList<FixedSalary>();

		PreparedStatement statement = connect
				.prepareStatement("select id as employeeid, name as employeename from employee where departmentid=?");
		statement.setInt(1, departmentID);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			PreparedStatement SalaryStatement = connect
					.prepareStatement("select id, name from SalaryItem where category='fixed'");
			ResultSet SalaryRs = SalaryStatement.executeQuery();
			while (SalaryRs.next()) {
				PreparedStatement newstatement = connect
						.prepareStatement("select salaryitemid, svalue from salaryvalue where employeeid=? and salaryitemid= ?");
				newstatement.setInt(1, rs.getInt("employeeid"));
				newstatement.setInt(2, SalaryRs.getInt("id"));
				ResultSet res = newstatement.executeQuery();
				if (!res.isBeforeFirst()) {
					FixedSalary temp = new FixedSalary();
					temp.setEmployeeId(rs.getInt("employeeid"));
					temp.setEmployeeName(rs.getString("employeename"));
					temp.setSalaryItemName(SalaryRs.getString("name"));
					temp.setSalaryItemId(SalaryRs.getInt("id"));
					temp.setValue(0);
					tempList.add(temp);
				}
				while (res.next()) {
					FixedSalary fixedItem = new FixedSalary();
					fixedItem.setEmployeeId(rs.getInt("employeeid"));
					fixedItem.setEmployeeName(rs.getString("employeename"));
					fixedItem.setSalaryItemId(SalaryRs.getInt("id"));
					fixedItem.setSalaryItemName(SalaryRs.getString("name"));
					fixedItem.setValue(res.getDouble("svalue"));

					tempList.add(fixedItem);
				}

			}

		}
		return tempList;
	}
}
