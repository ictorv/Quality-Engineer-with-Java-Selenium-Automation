import java.util.*;

public class Student {

	private List<String> studentList=new ArrayList<String>();

	public List<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}

	public void addStudentToList(String student)
	{
		//Fill the code here
		studentList.add(student);
	}
	
	public void sortStudentList()
	{
		//Fill the code here
		Collections.sort(studentList);
	}
	
	public void removeStudentFromList(String student)
	{
		//Fill the code here
		studentList.remove(student);
	}
}