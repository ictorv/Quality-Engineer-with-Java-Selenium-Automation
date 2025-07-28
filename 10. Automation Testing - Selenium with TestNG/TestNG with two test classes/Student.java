
public class Student {
	private int studentId;
	private String studentName;
	private String department;
	private double cgpa;
		
	public Student(int studentId, String studentName, String department, double cgpa) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.department = department;
		this.cgpa = cgpa;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	
	public String generatePassword() {
		return studentId + department + studentName.substring(0, 3);
	}
	
	public String getResult() {
		if(cgpa>=9.0)
			return "Distinction";
		else if(cgpa>=8.0)
			return "First class";
		else if(cgpa>=7.0)
			return "Second class";
		else if(cgpa>=6.0)
			return "Third class";
		else
			return "Fail";
	}
}
