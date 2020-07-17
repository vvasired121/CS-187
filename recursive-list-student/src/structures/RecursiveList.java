package structures;

import java.util.Iterator;


public class RecursiveList<T> implements ListInterface<T> {
	private int size;
	 private LLNode<T> head;
	  LLNode<T> tail;
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	public RecursiveList() {
		size = 0;
		head = null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// TODO Auto-generated method stub
		if (elem == null) throw new NullPointerException();
	    size++;
	    if (isEmpty()) {
	      head = new LLNode<T>(null, elem);
	      return this;
	    }
	    LLNode<T> nn = new LLNode<T>(head, elem);
	    head = nn;
	    return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		// TODO Auto-generated method stub
		if (elem==null) throw new NullPointerException();
		else {
			if (head==null) {
				head = new LLNode<T> (head, elem);
				size++;
				return this;
			}
			else {
			LLNode<T> curr = head;
			return insertLastHelper(curr, elem, size);
			}
		}	
	}
	public ListInterface<T> insertLastHelper(LLNode<T> curr, T element, int i){
		if (i == 1) { 
			curr.next = new LLNode<T>(curr, element);
			size++; 
			return this;
		}
		
		else {
			return insertLastHelper(curr.next,element,i-1);
			
		}
		}
	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
	    if (elem == null) throw new NullPointerException("the element you attempted to insert was null");
	    if (index == 0) {
	      insertFirst(elem);
	    }else {
	      LLNode<T> Node = new LLNode<T>(null, elem);
	      insertAtHelper(head, Node, index);
	    }
	    return this;
		
	}
	private void insertAtHelper(LLNode<T> cur, LLNode<T> node, int index){
	    if (index - 1 == 0) {
	      if (index != size) {
	        LLNode<T> prev = cur.next;
	        cur.next = node;
	        node.next = prev;
	      }else { 
	        cur.next = node;
	      }
	      size++;
	    }else {
	      insertAtHelper(cur.next, node, --index);
	    }
	  }

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if (isEmpty()) throw new IllegalStateException();
	    return removeAt(0);
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		if (isEmpty()) throw new IllegalStateException();
	    return removeAt(size - 1);
	}

	@Override
	public T removeAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
	    return removeAtHelper(head, i);
	}
	private T removeAtHelper(LLNode<T> curr, int a) {
	    if (a == 0) {
	      T x = head.data;
	      head = head.next;
	      size--;
	      return x;
	    }
	    if (a == 1) {
	      T info = curr.next.data;
	      curr.next = curr.next.next;
	      size--;
	      return info; 
	    }
	    return removeAtHelper(curr.next, --a);
	  }
	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		if (isEmpty()) throw new IllegalStateException();
	    return head.data;
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		if (head==null || size ==0) throw new IllegalStateException();
		else {
			LLNode<T> cur = head;
			return getLastHelper(cur, size);	
		}
	}
	private T getLastHelper(LLNode<T> curr, int i) { 
		if (i==1) {
			T a = curr.data;
			return a;
		}
		else {
			return getLastHelper(curr.next, i-1); 
			
		}
	}
	

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
	    return getHelper(head, i);
	}
	private T getHelper(LLNode<T> curr, int i){  
	    if (i == 0) {
	      return curr.data;
	    }
	    return getHelper(curr.next, --i);
	  }


	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		if(elem == null)
			throw new NullPointerException();
		if(indexOf(elem) != -1) {
			this.removeAt(indexOf(elem));
			return true;
		}
		return false;
	}
	
	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		if (elem == null) throw new NullPointerException();
		else {
			LLNode<T> current = head;
			return indexOfHelper(elem, 0, current);
		}
	}
	
private int indexOfHelper( T element, int index, LLNode<T> curr){
	if (curr==null) return -1;
	if (curr.data.equals(element)) return index;
	else {
		return indexOfHelper(element, index+1, curr.next);
	
	}
}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)return true;
		return false;
	}
	private class LLNode<T>{
		  
		  T data;
		  LLNode<T> next;

		  public LLNode(LLNode<T> next, T data) {
			  this.data = data;
			  this.next = next;
		    
		  }

		}
	class LinkedNodeIterator<T> implements Iterator<T> {
		   
		  public LLNode<T> iterator;

		  public LinkedNodeIterator(LLNode<T> head) {
		     	  iterator = head;
		  }

		  @Override
		  public boolean hasNext() {


			  return (iterator!=null);
		  }

		  @Override
		  public T next() {
			if (!hasNext())
				return null;
			T x = iterator.data;
			iterator = iterator.next;
			return x;
		  }

		  @Override
		  public void remove() {
		    throw new UnsupportedOperationException();
		  }
		}
	
}
