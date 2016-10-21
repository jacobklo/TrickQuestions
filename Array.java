package hk.jlo.Libs;
import java.util.Iterator;

public class Array<T> implements Iterable<T>{

	private int Array_Length = 128;
	private int mI_CurrentSize = 0;
	private Object[] mC_Arr;
	

	public Array(int size)
	{
		size = Array_Length;
		clear();
	}
	
	public Array()
	{
		clear();
	}
	///---Helper---///
	private int getNextEmpty()
	{
		for ( int i = 0 ; i < mC_Arr.length ; i++)
			if (mC_Arr[i] == null)
				return i;
		return -1;
	}
	
	
	///---COLECTION---///
	public boolean add(int index, T arg0) {
		if (index >= mC_Arr.length || index < 0)	return false;
		mI_CurrentSize++;
		mC_Arr[index] = arg0;
		// TODO resize
		return true;
	}
	
	public boolean add(T arg0)
	{
		return add(getNextEmpty(), arg0);
	}

	public void clear() {
		mC_Arr = new Object[Array_Length];
		mI_CurrentSize = 0;
	}

	@SuppressWarnings("unchecked")
	public boolean contains(T arg0) {
		for ( int i = 0 ; i < mC_Arr.length ; i++)
		{
			T tmp = (T) mC_Arr[i];
			return tmp.equals(arg0);
		}
		return false;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	
	public int size() {
		return mI_CurrentSize;
	}
	
	private boolean remove(int index)
	{
		if (index >= mC_Arr.length || index < 0 || mI_CurrentSize <= 0)	return false;
		if (mC_Arr[index] == null)	return false;
		mC_Arr[index] = null;
		mI_CurrentSize--;
		return true;
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return new ArrayIterator();
	}

	/*
	 * Loop from 0 to end, even in the middle is null value
	 */
	public class ArrayIterator implements Iterator<T>
	{
		private int cursor;
		
		public ArrayIterator()
		{
			cursor = 0;
		}
		
		@Override
		public boolean hasNext() {
			return ((cursor + 1) < mC_Arr.length);
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext())	return null;
			T t = (T)mC_Arr[cursor];
			cursor++;
			return t;
		}
		
		@Override
		public void remove()
		{
			Array.this.remove(cursor);
		}
	}
}
