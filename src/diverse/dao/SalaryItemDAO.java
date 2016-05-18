package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.SalaryItem;

public class SalaryItemDAO 
{

	Connection connect;
	
	public SalaryItemDAO(Connection conn)
	{
		this.connect = conn;
	}
	
	public List<SalaryItem> getAllItems()throws SQLException
	{
		List<SalaryItem> tempList = new ArrayList<SalaryItem>();
		PreparedStatement statement = connect.prepareStatement("select * from SalaryItem");
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			SalaryItem item = new SalaryItem();
			item.setId(rs.getInt("id"));
			item.setCategory(rs.getString("category"));
			item.setName(rs.getString("name"));
			if(rs.getString("stype").equalsIgnoreCase("Y"))
				item.setType("increase");
			else if (rs.getString("stype").equalsIgnoreCase("N"))
				item.setType("increase");
			else
				item.setType("");
			if(rs.getString("displayed").equalsIgnoreCase("Y"))
				item.setDisplayed(true);
			else
				item.setDisplayed(false);
			item.setComment(rs.getString("comments"));
			item.setBaseId(rs.getInt("baseItemID"));
			item.setPrecedent(rs.getFloat("precedent"));
			item.setOperator(rs.getString("soperator"));
			
			tempList.add(item);
		}
		
		return tempList;
	}
	
	public void insertItem(SalaryItem item)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("insert into SalaryItem values(SALARYITEMID_SEQUENCE.nextval,?,?,?,?,?,?,?,?)");
		statement.setString(1, item.getCategory());
		statement.setString(2, item.getName());
		if(item.getType().equals("increase"))
			statement.setString(3, "Y");
		else if (item.getType().equals("decrease"))
			statement.setString(3, "N");
		else
			statement.setString(3, "E");
		if(item.isDisplayed())
			statement.setString(4, "Y");
		else
			statement.setString(4, "N");
		statement.setString(5, item.getComment());
		statement.setInt(6, item.getBaseId());
		statement.setFloat(7, item.getPrecedent());
		statement.setString(8, item.getOperator());
		
		statement.executeUpdate();
	}
	
	public void deleteItem(int id)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("delete from SalaryItem where id=?");
		statement.setInt(1, id);
		statement.executeUpdate();
		
	}
	
	public SalaryItem getItem(int id)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("select * from SalaryItem where id=?");
		statement.setInt(1, id);
		ResultSet rs=statement.executeQuery();
		rs.next();
		
		SalaryItem item = new SalaryItem();
		
		item.setId(rs.getInt("id"));
		item.setCategory(rs.getString("category"));
		item.setName(rs.getString("name"));
		if(rs.getString("stype").equalsIgnoreCase("Y"))
			item.setType("increase");
		else if (rs.getString("stype").equalsIgnoreCase("N"))
			item.setType("increase");
		else
			item.setType("");
		if(rs.getString("displayed").equalsIgnoreCase("Y"))
			item.setDisplayed(true);
		else
			item.setDisplayed(false);
		item.setComment(rs.getString("comments"));
		item.setBaseId(rs.getInt("baseItemID"));
		item.setPrecedent(rs.getFloat("precedent"));
		item.setOperator(rs.getString("soperator"));
		
		return item;
	}
	
	public void modifyItem(SalaryItem item)throws SQLException
	{
		PreparedStatement statement = connect.prepareStatement("update SalaryItem set category=?,name=?,stype=?, displayed=?," +
																"comments=?,baseItemID=?, precedent=?, soperator=? where id=?");
		statement.setString(1, item.getCategory());
		statement.setString(2, item.getName());
		if(item.getType().equals("increase"))
			statement.setString(3, "Y");
		else if (item.getType().equals("decrease"))
			statement.setString(3, "N");
		else
			statement.setString(3, "E");
		if(item.isDisplayed())
			statement.setString(4, "Y");
		else
			statement.setString(4, "N");
		statement.setString(5, item.getComment());
		statement.setInt(6, item.getBaseId());
		statement.setFloat(7, item.getPrecedent());
		statement.setString(8, item.getOperator());
		statement.setInt(9, item.getId());
		
		statement.executeUpdate();
	}
	
}

