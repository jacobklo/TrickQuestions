package hk.jlo.Libs;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
/*
 * This is a nullable array
 * Version 1.0
 * Author : Jacob Lo
 */
public class NullableArray<T> implements Collection<T>{

	private int Array_Length = 30;
	private int mI_CurrentSize = 0;
	private Object[] mC_Arr;
	private Class<T> mT_Type;
	
	// Generic class cannot do type checking because of Type erasure
	// Hack : Has to store the class type to perform something like instanceof
	public NullableArray(Class<T> type)
	{
		mT_Type = type;
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

	@SuppressWarnings("unchecked")
	public int At(Object arg0)
	{
		if (arg0 == null)	return -1;
		// Hack : cannot use instanceof here
		if (!mT_Type.isAssignableFrom(arg0.getClass()))	return -1;
		for ( int i = 0 ; i < mC_Arr.length ; i++)
		{
			T tmp = (T) mC_Arr[i];
			if ( tmp != null && tmp.equals(arg0))
				return i;
		}
		return -1;
	}
	public boolean checkIfResize(){
		if (size() + 1 < mC_Arr.length)		return false;
		return resize();
	}
	public boolean resize(){
		return resize(mC_Arr.length * 2);
	}
	
	public boolean resize( int newArrayLength)
	{
		if (newArrayLength < 0 || newArrayLength <= Array_Length) throwArrayIndexOutOfBoundException();
		Object[] newArray = new Object[newArrayLength];
		for (int i = 0 ; i < mC_Arr.length ; i++)
			newArray[i] = mC_Arr[i];
		mC_Arr = newArray;
		return true;
	}
	
	private void throwArrayIndexOutOfBoundException()
	{
		throw new ArrayIndexOutOfBoundsException();
	}
	
	///---COLECTION---///
	public boolean add(int index, T arg0) {
		if (index < 0)	throwArrayIndexOutOfBoundException();
		if ( arg0 == null)	return false;
		checkIfResize();
		if (index >= mC_Arr.length ) throwArrayIndexOutOfBoundException();
		mI_CurrentSize++;
		mC_Arr[index] = arg0;
		return true;
	}
	
	@Override
	public boolean add(T arg0)
	{
		return add(getNextEmpty(), arg0);
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		if ( arg0 == null)	return false;
		for (T a : arg0)
			if (!add(a))
				return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index)
	{
		if (index < 0 || index >= mC_Arr.length)	throwArrayIndexOutOfBoundException();
		return (T) mC_Arr[index];
	}

	@Override
	public void clear() {
		mC_Arr = new Object[Array_Length];
		mI_CurrentSize = 0;
	}
	
	@Override
	public boolean contains(Object arg0) 
	{
		return At(arg0) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		if ( arg0 == null)	return false;
		for (Object o : arg0)
			if(!contains(o))
				return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o)
	{
		if (o == null)	return false;
		if (!this.getClass().equals(o.getClass()))	return false;
		NullableArray<T> casto = (NullableArray<T>) o;
		if (this.Array_Length != casto.Array_Length ||
			this.mI_CurrentSize != casto.mI_CurrentSize)	return false;
		for (int i = 0 ; i < mC_Arr.length ; i++)
		{
			if (!(mC_Arr[i] == null && casto.mC_Arr[i] == null))	return false;
			if (!(mC_Arr[i] != null && mC_Arr[i].equals(casto.mC_Arr[i])))	return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * Size() : Number of non-Null elements
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return mI_CurrentSize;
	}
	
	public boolean remove(int index)
	{
		if (index >= mC_Arr.length || index < 0 || mI_CurrentSize <= 0)	return false;
		if (mC_Arr[index] == null)	return false;
		mC_Arr[index] = null;
		mI_CurrentSize--;
		return true;
	}
	
	@Override
	public boolean remove(Object o)
	{
		int where = At(o);
		if (where == -1)	return false;
		return remove(where);
	}
	
	@Override
	public boolean removeAll(Collection<?> arg0) {
		if ( arg0 == null)	return false;
		for (Object a : arg0)
			if (!remove(a))
				return false;
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> arg0) {
		if (arg0 == null)	return false;
		for (T a : this)
			if (a != null && !arg0.contains(a))
				if ( remove(a))
					return false;
		return true;
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[size()]);
	}

	/*
	 * This toArray() will remove all null value in the middle of this NullableArray!!
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) {
		T[] newArray;
		if (arg0.length >= size())
			newArray = arg0;
		else
			newArray = (T[]) Array.newInstance(arg0.getClass().getComponentType(), size());
		Iterator<T> it = (Iterator<T>) iterator();
		int i = 0;
		while (it.hasNext())
		{
			newArray[i] = (T) it.next();
			i++;
		}
		return newArray;
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
			NullableArray.this.remove(cursor);
		}
	}
}
