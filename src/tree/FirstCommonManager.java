package tree;
import java.util.ArrayList;

import tree.FirstCommonManager.Employee;

/*
 * Find the first common manager given two emplyees
 */
/*
 * For example, the firstCommonManager of C and D is Jac
 * the firstCommonManager of C and E is RightM
 * the firstCommonManager of A and D is CEO
 * the firstCommonManager of B and C is CEO
 * the firstCommonManager of CEO and CEO is CEO
 * the firstCommonManager of CEO and d is CEO
 * 
 * Company structure
 * 								CEO
 * 				LeftS					RightM
 * 			Linda		Sil			Jac			Arby
 * 		A			  B			c		d	  		e
 */
public class FirstCommonManager {
	
	public void print(Employee ceo, Employee e1, Employee e2){
		ArrayList<Employee> list = managerChain(new ArrayList<Employee>(), ceo, e1);
		for (Employee em : list)
			System.out.print(em + " ");
		System.out.println();
		ArrayList<Employee> list2 = managerChain(new ArrayList<Employee>(), ceo, e2);
		for (Employee em : list2)
			System.out.print(em + " ");
		System.out.println();
		System.out.println(firstCommonManager(ceo,e1,e2));
		System.out.println();
	}
	
	public Employee firstCommonManager(Employee ceo, Employee employee1, Employee employee2)
	{
		ArrayList<Employee> E1Chain = managerChain(new ArrayList<Employee>(), ceo, employee1);
		ArrayList<Employee> E2Chain = managerChain(new ArrayList<Employee>(), ceo, employee2);
		
		Employee currentManager = null;
		for (int i = 0 ; i < (E1Chain.size() > E2Chain.size() ? E1Chain.size() : E2Chain.size() ) ; i++){
			if (E1Chain.size() <= 0 || E2Chain.size() <= 0)	return currentManager;
			if (!(E1Chain.get(i).equals(E2Chain.get(i))))	return currentManager;
			currentManager = E1Chain.get(i);
			E1Chain.remove(i);
			E2Chain.remove(i);
		}
		return currentManager;
	}
	
	public ArrayList<Employee> managerChain(ArrayList<Employee> currentChain, Employee manager, Employee e){
		if (manager.equals(e)){
			currentChain.add(e);
			return currentChain;
		}
		ArrayList<Employee> result = new ArrayList<Employee>();
		for (Employee em : manager.unders){
			ArrayList<Employee> lower = managerChain(currentChain,em,e);
			if (lower != null && lower.size() > 0){
				result.add(manager);
				result.addAll(lower);
			}
		}
		return result;
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
		
		@Override
		public String toString(){
			return name;
		}
		
		@Override
		public boolean equals(Object o){
			if (o == null)	return false;
			if (!(o instanceof Employee))	return false;
			Employee other = (Employee ) o;
			return this.id == other.id && this.name.equals(other.name);
		}
	}
	
	///---Helper---///
	private class Tuple{
		public Employee e;
		public boolean foundAlready;
		
		public Tuple(Employee e, boolean foundAlready){
			this.e = e;
			this.foundAlready = foundAlready;
		}
	}
}
