package tree;
import java.util.ArrayList;

import tree.FirstCommonNode.Node;

/*
 * Find the first common Node given two Nodes
 */
/*
 * For example, the firstCommonNode of C and D is Jac
 * the firstCommonNode of C and E is RightM
 * the firstCommonNode of A and D is ROOT
 * the firstCommonNode of B and C is ROOT
 * the firstCommonNode of ROOT and ROOT is ROOT
 * the firstCommonNode of ROOT and d is ROOT
 * 
 * Company structure
 * 								ROOT
 * 				LeftS					RightM
 * 			Linda		Sil			Jac			Arby
 * 		A			  B			c		d	  		e
 */
public class FirstCommonNode<T> {
	
	public void print(Node root, Node e1, Node e2){
		ArrayList<Node> list = managerChain(new ArrayList<Node>(), root, e1);
		for (Node em : list)
			System.out.print(em + " ");
		System.out.println();
		ArrayList<Node> list2 = managerChain(new ArrayList<Node>(), root, e2);
		for (Node em : list2)
			System.out.print(em + " ");
		System.out.println();
		System.out.println(firstCommonManager(root,e1,e2));
		System.out.println();
	}
	
	public Node firstCommonManager(Node root, Node employee1, Node employee2)
	{
		ArrayList<Node> E1Chain = managerChain(new ArrayList<Node>(), root, employee1);
		ArrayList<Node> E2Chain = managerChain(new ArrayList<Node>(), root, employee2);
		
		Node currentManager = null;
		for (int i = 0 ; i < (E1Chain.size() > E2Chain.size() ? E1Chain.size() : E2Chain.size() ) ; i++){
			if (E1Chain.size() <= 0 || E2Chain.size() <= 0)	return currentManager;
			if (!(E1Chain.get(i).equals(E2Chain.get(i))))	return currentManager;
			currentManager = E1Chain.get(i);
			E1Chain.remove(i);
			E2Chain.remove(i);
		}
		return currentManager;
	}
	
	public ArrayList<Node> managerChain(ArrayList<Node> currentChain, Node currentRoot, Node e){
		if (currentRoot.equals(e)){
			currentChain.add(e);
			return currentChain;
		}
		ArrayList<Node> result = new ArrayList<Node>();
		for (Node em : currentRoot.unders){
			ArrayList<Node> lower = managerChain(currentChain,em,e);
			if (lower != null && lower.size() > 0){
				result.add(currentRoot);
				result.addAll(lower);
			}
		}
		return result;
	}
	
	public class Node
	{
		T data;
		ArrayList<Node> unders;
		
		public Node(T data, ArrayList<Node> unders)
		{
			this.data = data;
			if (unders == null)
				this.unders = new ArrayList<Node>();
			else
				this.unders = unders;
		}
		
		@Override
		public String toString(){
			return this.data.toString();
		}
		
		@Override
		public boolean equals(Object o){
			if (o == null)	return false;
			if (!(o instanceof FirstCommonNode.Node))	return false;
			Node other = (Node ) o;
			return this.data.equals(other.data);
		}
	}
}
