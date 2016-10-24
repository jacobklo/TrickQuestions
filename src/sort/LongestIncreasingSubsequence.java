package sort;

public class LongestIncreasingSubsequence<T extends Number & Comparable<T>> {
	public void longestIncreasingSubsequence(Token[] tokens){
		// TODO hmm this is very hard now i think that. need some time to think for an O(n log n ) solution
	}
	
	public class Token implements Comparable<T>{
		private static final int NUM_OF_SETS = 2;
		private T[] vector;
		
		public Token(T[] vector){
			if (vector == null)		throw new NullPointerException();
			if (vector.length != NUM_OF_SETS)	throw new IllegalArgumentException();
			this.vector = vector;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for ( int i = 0 ; i < vector.length ; i++){
				sb.append(vector[i]);
				if ( i+1 < vector.length)
					sb.append(vector[i]);
			}
			sb.append(")");
			return sb.toString();
		}

		@Override
		public int compareTo(T arg0) {
			int result = 0;
			boolean allEquals = true;
			for ( int i = 0 ; i < vector.length ; i++){
				if (!vector[i].equals(arg0)){
					allEquals = false;
				}
				result += vector[i].compareTo(arg0);
			}
			if (!allEquals && result == 0){
				for (int i = 0 ; i < vector.length ; i++){
					if (vector[i].compareTo(arg0) != 0)
						return vector[i].compareTo(arg0);
				}
			}
			return result;
		}
	}
}
