package diverse.object;

import java.sql.Timestamp;

public class SalaryLog {

	int employeeID;
	int departmentID;
	int salaryItemID;
	int month;
	double salaryItemValue;
	Timestamp editDate;
	String editor;
	String itemName;
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getSalaryItemID() {
		return salaryItemID;
	}

	public void setSalaryItemID(int salaryItemID) {
		this.salaryItemID = salaryItemID;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getSalaryItemValue() {
		return salaryItemValue;
	}

	public void setSalaryItemValue(double salaryItemValue) {
		this.salaryItemValue = salaryItemValue;
	}

	public Timestamp getEditDate() {
		return editDate;
	}

	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}
