package sort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
 * Credit : Craking the code interview 
 * 11.2 Write a method to sort an array of strings so that all the anagrams are next to
	each other.
 */
public class SortAnagrams {
	public ArrayList<String> sortAnagrams(ArrayList<String> input){
		input.sort(new SortAnagramsComparator());
		return input;
	}
	
	public class SortAnagramsComparator implements Comparator<String>{

		@Override
		public int compare(String arg0, String arg1) {
			if (arg0 == null)	return (arg1 == null ? 0 : Integer.MIN_VALUE);
			if (arg1 == null)	return Integer.MAX_VALUE;
			if (arg0.length() != arg1.length())	return arg0.length() - arg1.length();
			char[] a0 = arg0.toCharArray();
			char[] a1 = arg1.toCharArray();
			Arrays.sort(a0);
			Arrays.sort(a1);
			for ( int i = 0 ; i < a0.length ; i++){
				if (a0[i] != a1[i])	
					return a0[i] - a1[i];
			}
			return 0;
		}
		
	}
}
