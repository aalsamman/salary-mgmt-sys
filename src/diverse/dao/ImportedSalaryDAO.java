package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import diverse.object.ImportedSalary;
import diverse.object.SalaryItem;

public class ImportedSalaryDAO 
{
	
	Connection connect;
	
	public ImportedSalaryDAO(Connection conn)
	{
		this.connect = conn;
	}
	
	public List<ImportedSalary> getImportedSalaries(int departmentID)throws SQLException
	{
		List<ImportedSalary> tempList = new ArrayList<ImportedSalary>();
			
		PreparedStatement statement = connect.prepareStatement("select id as employeeid, name as employeename from employee where departmentid=?");
		statement.setInt(1, departmentID);
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			PreparedStatement SalaryStatement = connect.prepareStatement("select id, name from SalaryItem where category='imported'");
			ResultSet SalaryRs = SalaryStatement.executeQuery();
			while(SalaryRs.next())
			{
				PreparedStatement newstatement = connect.prepareStatement("select salaryitemid, svalue from salaryvalue where employeeid=? and salaryitemid= ?");
				newstatement.setInt(1, rs.getInt("employeeid"));
				newstatement.setInt(2, SalaryRs.getInt("id"));
				ResultSet res = newstatement.executeQuery();
				if(!res.isBeforeFirst())
				{
					ImportedSalary temp = new ImportedSalary();
					temp.setEmployeeId(rs.getInt("employeeid"));
					temp.setEmployeeName(rs.getString("employeename"));
					temp.setSalaryItemName(SalaryRs.getString("name"));
					temp.setSalaryItemId(SalaryRs.getInt("id"));
					temp.setValue(0);
					tempList.add(temp);
				}
				while(res.next())
				{
					ImportedSalary importedItem = new ImportedSalary();
					importedItem.setEmployeeId(rs.getInt("employeeid"));
					importedItem.setEmployeeName(rs.getString("employeename"));
					importedItem.setSalaryItemId(SalaryRs.getInt("id"));
					importedItem.setSalaryItemName(SalaryRs.getString("name"));
					importedItem.setValue(res.getDouble("svalue"));
					
					tempList.add(importedItem);
				}
				
			}
		
		}
		for(int i=0; i<tempList.size();i++)
		{
			ImportedSalary item = new ImportedSalary();
			item = tempList.get(i);
			System.out.println(item.getEmployeeId()+"\t"+item.getEmployeeName()+"\t"+item.getSalaryItemName()
					+"\t"+item.getValue());
		}
		return tempList;
	}
}
