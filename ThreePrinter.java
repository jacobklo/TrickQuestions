package hk.jlo;
/**
 * Sample input:
 *
 *          1
 *         / \
 *        3   5
 *       /   / \
 *      2   4   7
 *     / \   \
 *    9   6   8
 
 *
 * Expected output:
 *    1
 *    3 5
 *    2 4 7
 *    9 6 8
 *    ==========
 */
import java.util.ArrayList;
import java.util.HashMap;

public class TreePrinter
{
	private Node root;
	private HashMap<Integer, ArrayList<Integer>> result;
	public TreePrinter()
	{
		root = initNode();
		result = new HashMap<Integer,ArrayList<Integer>>();
		convert(root,0);
	}

	public Node initNode()
	{
		Node n9 = new Node(9);
		Node n6 = new Node(6);
		Node n2 = new Node(2);
		n2.setLeft(n9);
		n2.setRight(n6);
		Node n8 = new Node(8);
		Node n4 = new Node(4);
		n4.setRight(n8);
		Node n3 = new Node(3);
		n3.setLeft(n2);
		Node n7 = new Node(7);
		Node n5 = new Node(5);
		n5.setLeft(n4);
		n5.setRight(n7);
		Node rootNode = new Node(1);
		rootNode.setLeft(n3);
		rootNode.setRight(n5);
		return rootNode;
	}
	
    public void convert(Node input, int onLevel)
    {
    	if ( input == null)	return;
    	ArrayList<Integer> inputNode = new ArrayList<Integer>();
    	inputNode.add(input.getValue());
    	if (!result.containsKey(new Integer(onLevel)))
    	{
    		result.put(new Integer(onLevel), inputNode);
    	}
    	else
    	{
    		result.get(new Integer(onLevel)).addAll(inputNode);
    	}
    	convert(input.getLeft(), onLevel+1);
    	convert(input.getRight(), onLevel+1);
    }
    
    public HashMap<Integer,ArrayList<Integer>> getResult()
    {
    	return this.result;
    }
	
	public class Node
	{
		int value;
		Node left;
		Node right;
		
		public Node (int newValue)
		{
			this.value = newValue;
		}
		public int getValue()
		{
			return this.value;
		}
		public Node getLeft()
		{
			return this.left;
		}
		public Node getRight()
		{
			return this.right;
		}
		public void setLeft(Node newLeftNode)
		{
			this.left = newLeftNode;
		}
		public void setRight(Node newRightNode)
		{
			this.right = newRightNode;
		}
	}
  
  public static void main (String[] args)
	{
		int x = 24;
		char y = 0b00000110;
		int z = (int) (1 << x);
		System.out.println(Integer.toBinaryString(z));
		
		TreePrinter t = new TreePrinter();
		HashMap<Integer,ArrayList<Integer>> result = t.getResult();
		
		for ( ArrayList<Integer> a : result.values())
		{
			for ( Integer i : a )
			{
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
}

