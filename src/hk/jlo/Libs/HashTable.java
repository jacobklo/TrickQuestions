package hk.jlo.Libs;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.AbstractSet;

/*
 * NOT FINISH
 * This is a HashTable, simplify version of java.util.HashMap
 * Version 0.1
 * Author : Jacob Lo
 */
public class HashTable<K,V> implements Map<K,V>{
	private NullableArray<Object> mC_Table;
	private int mI_NumOfItems;
	
	public HashTable(){
		clear();
	}
	
	///---HELPER---///
	private class HashEntry<K,V> implements Map.Entry<K, V>
	{
		private K key;
		private V value;
		
		public HashEntry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey(){
			return key;
		}

		@Override
		public V getValue(){
			return value;
		}

		@Override
		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}
	}
	
	private class HashIterator implements Iterator<Map.Entry<K, V>>{
		private int cursor;
		
		public HashIterator(){
			cursor = 0;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public java.util.Map.Entry<K, V> next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private int hashIndex(Object key){
		return key.hashCode() % mC_Table.getCapacity();
	}
	/*
	 * Use CommandPattern because transversal logic should only coded ONCE!
	 */
	private abstract class CommandPattern
	{
		public int result = -2;
		public CommandPattern(Object key){
			transversal(key);
		}
		public void transversal(Object key){
			if (key == null)	return;
			int hash = hashIndex(key);
			for ( int i = hash ; i < mC_Table.getCapacity() ; i++){
				result = execute(i);
				if (result > -1)	return;
			}
			// circle back
			for (int i = 0 ; i < hash ; i++){
				result = execute(i);
				if (result > -1)	return;
			}
		}
		public abstract int execute( int index);
	}
	
	private int indexOf(Object key){
		CommandPattern command = new CommandPattern(key){
			@Override
			public int execute(int index) {
				if (mC_Table.get(index) != null){
					Map.Entry<K,V> me = (java.util.Map.Entry<K, V>) mC_Table.get(index);
					if (me.getKey().equals(key))
						return index;
				}
				return -1;
			}
		};
		return command.result;
	}
	private int indexOfValue(Object value){
		if (value == null)	return -1;
		for ( int i = 0 ; i < mC_Table.getCapacity() ; i++){
			if (mC_Table.get(i) != null){
				Map.Entry<K, V> me = (java.util.Map.Entry<K, V>) mC_Table.get(i);
				if (me.getValue().equals(value))
					return i;
			}
		}
		return -1;
	}
	
	///---Collection---///
	@Override
	public void clear() {
		mC_Table = new NullableArray<Object>(Object.class);
		mI_NumOfItems = 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return indexOf(key) >= 0;
	}

	@Override
	public boolean containsValue(Object value) {
		return indexOfValue(value) >= 0;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO 
		return null;
	}
	
	private class EntrySet extends AbstractSet<Map.Entry<K,V>>
	{

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	@Override
	public V get(Object key) {
		int hash = indexOf(key);
		if (hash < 0)	return null;
		Map.Entry<K, V> me = (Map.Entry<K, V>) mC_Table.get(indexOf(key));
		return me.getValue();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Set<K> keySet() {
		// TODO 
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (mC_Table.size()+2 == mC_Table.getCapacity())	throw new ArrayIndexOutOfBoundsException();
		CommandPattern command = new CommandPattern(key){
			@Override
			public int execute(int index) {
				if (mC_Table.get(index) != null){
					Map.Entry<K, V> me = (java.util.Map.Entry<K, V>) mC_Table.get(index);
					if (key.equals(me.getKey()))
						return index;
					else
						return -1;
				}
				return index;
			}
		};
		Map.Entry<K, V> me = (java.util.Map.Entry<K, V>) mC_Table.get(command.result);
		if (me != null){
			V old = me.getValue();
			me.setValue(value);
			return old;
		}
		else{
			me = new HashEntry<K,V>(key,value);
			mC_Table.add(command.result, me);
			mI_NumOfItems++;
			return null;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Iterator<? extends Map.Entry<? extends K, ? extends V>> it = m.entrySet().iterator() ; it .hasNext() ; ){
			Map.Entry< ? extends K, ? extends V> me = it.next();
			put(me.getKey(), me.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		int hash = indexOf(key);
		if (hash < 0)	return null;
		Map.Entry<K, V> me = (java.util.Map.Entry<K, V>) mC_Table.get(hash);
		V old = me.getValue();
		mC_Table.remove(hash);
		mI_NumOfItems--;
		return old;
	}

	@Override
	public int size() {
		return mI_NumOfItems;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
