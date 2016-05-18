package diverse.object;

public class TotalSalary 
{
	int employeeID;
	String name;
	double fxSum;
	double icSum;
	double tax;
	
	
	
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFxSum() {
		return fxSum;
	}
	public void setFxSum(double fxSum) {
		this.fxSum = fxSum;
	}
	public double getIcSum() {
		return icSum;
	}
	public void setIcSum(double icSum) {
		this.icSum = icSum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeID;
		long temp;
		temp = Double.doubleToLongBits(fxSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(icSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(tax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TotalSalary other = (TotalSalary) obj;
		if (employeeID != other.employeeID)
			return false;
		if (Double.doubleToLongBits(fxSum) != Double
				.doubleToLongBits(other.fxSum))
			return false;
		if (Double.doubleToLongBits(icSum) != Double
				.doubleToLongBits(other.icSum))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(tax) != Double.doubleToLongBits(other.tax))
			return false;
		return true;
	}
	
	

}
