package main;
import hk.jlo.Libs.NullableArray;

public class NullableArrayMain {
	public static void main(String[] args)
	{
		NullableArray<Integer> arr = new NullableArray<Integer>(Integer.class);
		for (int i = 0 ; i < 20 ; i=i+2)
		{
			arr.add(i,i);
		}
		
		for (Integer i : arr)
		{
			System.out.println((i != null ? i : "EMPTY"));
		}
	}
}
