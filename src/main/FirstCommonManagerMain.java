package main;
import tree.FirstCommonNode;
import tree.FirstCommonNode.Node;
import java.util.ArrayList;

/*
 * Company structure
 * 								ROOT
 * 				LeftS					RightM
 * 			Linda		Sil			Jac			Arby
 * 		A			  B			c		d	  		e
 */
public class FirstCommonManagerMain {
	public static void main(String[] args){
		FirstCommonNode<Integer> fcm = new FirstCommonNode<Integer>();
		FirstCommonNode<Integer>.Node a = fcm.new Node(30,null);
		FirstCommonNode<Integer>.Node b = fcm.new Node(31,null);
		FirstCommonNode<Integer>.Node c = fcm.new Node(32,null);
		FirstCommonNode<Integer>.Node d = fcm.new Node(33,null);
		FirstCommonNode<Integer>.Node e = fcm.new Node(34,null);
		
		ArrayList<FirstCommonNode<Integer>.Node> lindaArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		lindaArr.add(a);
		FirstCommonNode<Integer>.Node linda = fcm.new Node(20,lindaArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> silArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		silArr.add(b);
		FirstCommonNode<Integer>.Node sil = fcm.new Node(21,silArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> jacArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		jacArr.add(c);
		jacArr.add(d);
		FirstCommonNode<Integer>.Node jac = fcm.new Node(22,jacArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> arbyArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		arbyArr.add(e);
		FirstCommonNode<Integer>.Node arby = fcm.new Node(23,arbyArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> leftArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		leftArr.add(linda);
		leftArr.add(sil);
		FirstCommonNode<Integer>.Node leftS = fcm.new Node(11,leftArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> rightArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		rightArr.add(jac);
		rightArr.add(arby);
		FirstCommonNode<Integer>.Node rightM = fcm.new Node(12,rightArr);
		
		ArrayList<FirstCommonNode<Integer>.Node> ceoArr = new ArrayList<FirstCommonNode<Integer>.Node>();
		ceoArr.add(leftS);
		ceoArr.add(rightM);
		FirstCommonNode<Integer>.Node root = fcm.new Node(0,ceoArr);
		
		fcm.print(jac, c, d);
		fcm.print(root, c, d);
		fcm.print(root, a, d);
		fcm.print(root, c, e);
		fcm.print(e, e, e);
		fcm.print(root, root, d);
	}
}
