package main;
import sort.SortAnagrams;
import java.util.ArrayList;

public class SortAnagramsMain {
	public static void main(String[] args){
		SortAnagrams sa = new SortAnagrams();
		ArrayList<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("sda");
		list.add("bca");
		ArrayList<String> sorted = sa.sortAnagrams(list);
		System.out.println(sorted);
	}
}
