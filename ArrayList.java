package com.haoxuan;
@SuppressWarnings("unchecked")
public class ArrayList<E> {
	
	
	/**
	 * 元素的数量
	 */
	private int size;
	/**
	 * 所有的元素
	 */
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 2;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	public ArrayList(int capaticy) {
		capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
		elements = (E[]) new Object[capaticy];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 清除所有元素
	 */
	public void clear() {
		for(int i =0;i<size;i++) {
			elements[i] = null;
		}
		size = 0;
	}

	/**
	 * 元素的数量
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	/**
	 * 添加元素到尾部
	 * @param element
	 */
	public void add(E element) {
		add(size,element);
	}

	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}

	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	public E set(int index, E element) {
		rangeCheck(index);
		E oldElment = elements[index];
		elements[index] = element;
		return oldElment;
	}

	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacity(size+1);
		for(int i = size -1 ;i >= index; i--) {
			elements[i+1] = elements[i];
		}
		elements[index] = element;
		size++;
	}
	private void ensureCapacity(int capacity) {
		//原来数组的容量
		int oldCapacity = elements.length;
		if(oldCapacity > capacity) {
			return;
		}
		int newCapacity = oldCapacity + (oldCapacity>>1);//增加1.5
		E[] newElements = (E[]) new Object[newCapacity];
		for(int i =0;i<size ;i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity+"扩容为:"+newCapacity);
	}

	/**
	 * 删除index位置的元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		rangeCheck(index);
		E oldElement = elements[index];
		for(int i = index;i<size-1;i++) {
			elements[i] = elements[i+1];
		}
		elements[--size] = null;
		return oldElement;
	}

	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	public int indexOf(E element) {
		if(element == null) {
			for(int i=0;i<size;i++) {
				if(elements[i]==null) {
					return i;
				}
			}
		}else {
			for(int i=0;i<size;i++) {
				if(element.equals(elements[i])) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}
	
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size:").append(size).append("[");
		for(int i =0;i<size ;i++) {
			if(i!=0) {
				sb.append(",");
			}
			sb.append(elements[i]);
		}
		
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("a", 13));
		list.add(0, new Person("b",	2));
		list.add(2, new Person("b",	3));
		//list.remove(1);
	
		System.out.println(list.isEmpty());
		System.out.println(list.indexOf(new Person("a", 2)));
		System.out.println(list);
	}
	
	
}
