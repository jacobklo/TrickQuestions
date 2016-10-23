package hk.jlo;
import java.util.ArrayList;

public class FirstCommonManager {
	
	
	public class Employee
	{
		int id;
		String name;
		ArrayList<Employee> unders;
		
		public Employee(int id, String name, ArrayList<Employee> unders)
		{
			this.id = id;
			this.name = name;
			this.unders = unders;
		}
	}
}
