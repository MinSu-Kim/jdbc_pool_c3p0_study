package jdbc_pool_c3p0.dto;

import java.util.Date;

public class Employee {
	private String empNo;
	private String empName;
	private Title title;
	private int salary;
	private boolean gender;
	private String hobby;
	private Department dept;
	private Date joinDate;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empNo) {
		this.empNo = empNo;
	}

	public Employee(String empNo, String empName, Title title, int salary, boolean gender, String hobby,
			Department dept, Date joinDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.gender = gender;
		this.hobby = hobby;
		this.dept = dept;
		this.joinDate = joinDate;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s, %s, %s, %s]", empNo, empName, title.getCode(), salary,
				gender ? "여" : "남", hobby, dept.getDeptNo(), joinDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empNo == null) ? 0 : empNo.hashCode());
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
		Employee other = (Employee) obj;
		if (empNo == null) {
			if (other.empNo != null)
				return false;
		} else if (!empNo.equals(other.empNo))
			return false;
		return true;
	}

}
