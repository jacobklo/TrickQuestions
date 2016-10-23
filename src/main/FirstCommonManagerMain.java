package main;
import tree.FirstCommonManager;
import tree.FirstCommonManager.Employee;
import java.util.ArrayList;

/*
 * Company structure
 * 								CEO
 * 				LeftS					RightM
 * 			Linda		Sil			Jac			Arby
 * 		A			  B			c		d	  		e
 */
public class FirstCommonManagerMain {
	public static void main(String[] args){
		Employee ceo = getCEO();
		
	}
	
	public static Employee getCEO()
	{
		FirstCommonManager fcm = new FirstCommonManager();
		
		Employee a = fcm.new Employee(30,"A",null);
		Employee b = fcm.new Employee(31,"B",null);
		Employee c = fcm.new Employee(32,"C",null);
		Employee d = fcm.new Employee(33,"D",null);
		Employee e = fcm.new Employee(34,"E",null);
		
		ArrayList<Employee> lindaArr = new ArrayList<Employee>();
		lindaArr.add(a);
		Employee linda = fcm.new Employee(20,"Linda",lindaArr);
		
		ArrayList<Employee> silArr = new ArrayList<Employee>();
		silArr.add(b);
		Employee sil = fcm.new Employee(21,"Sil",silArr);
		
		ArrayList<Employee> jacArr = new ArrayList<Employee>();
		jacArr.add(c);
		jacArr.add(d);
		Employee jac = fcm.new Employee(22,"Jac",jacArr);
		
		ArrayList<Employee> arbyArr = new ArrayList<Employee>();
		arbyArr.add(e);
		Employee arby = fcm.new Employee(23,"Sil",arbyArr);
		
		ArrayList<Employee> leftArr = new ArrayList<Employee>();
		leftArr.add(linda);
		leftArr.add(sil);
		Employee leftS = fcm.new Employee(11,"LeftS",leftArr);
		
		ArrayList<Employee> rightArr = new ArrayList<Employee>();
		rightArr.add(jac);
		rightArr.add(arby);
		Employee rightM = fcm.new Employee(12,"RightM",rightArr);
		
		ArrayList<Employee> ceoArr = new ArrayList<Employee>();
		ceoArr.add(leftS);
		ceoArr.add(rightM);
		Employee ceo = fcm.new Employee(0,"CEO",ceoArr);
		
		return ceo;
	}
}
