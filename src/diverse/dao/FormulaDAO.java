package diverse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diverse.object.Formula;

public class FormulaDAO 
{
	Connection connect;
	
	public FormulaDAO(Connection conn)
	{
		this.connect = conn;
	}
	
	public List<Formula> getFormulas()throws SQLException
	{
		List<Formula> tempList = new ArrayList<Formula>();
			
		PreparedStatement statement = connect.prepareStatement("select id, baseitemid, precedent, soperator, stype, name from SalaryItem where category='calculated'");
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			Formula formula = new Formula();
			formula.setItemId(rs.getInt("id"));
			formula.setBaseItemId(rs.getInt("baseItemID"));
			formula.setSoperator(rs.getString("soperator"));
			formula.setName(rs.getString("name"));
			formula.setPrecedent(rs.getDouble("precedent"));
			formula.setType(rs.getString("stype"));
					
			tempList.add(formula);
		}
		/*
		for(int i=0; i<tempList.size(); i++)
		{
			Formula temp = new Formula();
			temp = tempList.get(i);
			System.out.println("formulaDAO#######################");
			System.out.println(temp.getItemId()+"\t"+temp.getName()+"\t"+temp.getType()+"\t"+temp.getBaseItemId());
			System.out.println("formulaDAO########################");
		}
		*/
		return tempList;
				
	}

}
