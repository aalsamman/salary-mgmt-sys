package diverse.object;

import java.sql.Timestamp;

public class SalaryItemValue 
{
	private int employeeID;
	private int salaryItemID;
	private double value;
	private Timestamp date;
	private String editor;
	
	public int getEmployeeID() 
	{
		return employeeID;
	}
	public void setEmployeeID(int employeeID) 
	{
		this.employeeID = employeeID;
	}
	public int getSalaryItemID()
	{
		return salaryItemID;
	}
	public void setSalaryItemID(int salaryItemID)
	{
		this.salaryItemID = salaryItemID;
	}
	public double getValue() 
	{
		return value;
	}
	public void setValue(double value)
	{
		this.value = value;
	}
	public Timestamp getDate()
	{
		return date;
	}
	public void setDate(Timestamp date) 
	{
		this.date = date;
	}
	public String getEditor()
	{
		return editor;
	}
	public void setEditor(String editor) 
	{
		this.editor = editor;
	}
	
	
	
	

}
