package tree;
import java.util.ArrayList;

/*
 * Find the first common manager given two emplyees
 */
public class FirstCommonManager {
	public Employee firstCommonManager(Employee ceo, Employee employee1, Employee employee2)
	{
		
	}
	
	public class Employee
	{
		int id;
		String name;
		ArrayList<Employee> unders;
		
		public Employee(int id, String name, ArrayList<Employee> unders)
		{
			this.id = id;
			this.name = name;
			if (unders == null)
				this.unders = new ArrayList<Employee>();
			else
				this.unders = unders;
		}
	}
}
