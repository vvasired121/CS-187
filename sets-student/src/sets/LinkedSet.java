package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

/********************************************************
 * NOTE: Before you start, check the Set interface in
 * Set.java for detailed description of each method.
 *******************************************************/

/********************************************************
 * NOTE: for this project you must use linked lists
 * implemented by yourself. You are NOT ALLOWED to use
 * Java arrays of any type, or any Collection-based class 
 * such as ArrayList, Vector etc. You will receive a 0
 * if you use any of them.
 *******************************************************/ 

/********************************************************
 * NOTE: you are allowed to add new methods if necessary,
 * but do NOT add new files (as they will be ignored).
 *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
	  int num = 0;
	    for(E item: this) {
	      num++;
	    }
	    return num;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
	  if (head == null) {
			return true;
		} else {
			return false;
		}
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
	  for(E item: this) {
	      if(item.equals(o)) return true;
	    }
	    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
	  boolean AllItems = true;
	    for(E item: this) {
	      if(!that.contains(item)) {
	        AllItems = false;
	      }
	    }
	    return AllItems;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
	  return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
	  if(this.contains(e)) {
			return this;
		}
		else {
			LinkedNode<E> x = new LinkedNode<E>(e, head);
			head = x;
			Set<E> a = new LinkedSet<E>(x);
			return a;
		}
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
	  Iterator<E> i = that.iterator();
		 while(i.hasNext()) {
		    	adjoin(i.next());
		    }
	    return this;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	  LinkedNode<E> prev = null;
	    for(E item: this) {
	      if(that.contains(item)) {
	        LinkedNode<E> temp;
	        if(prev == null) { 
	          temp = new LinkedNode<E>(item, null);
	        }else {
	          temp = new LinkedNode<E>(item, prev);
	        }
	        prev = temp;
	      }
	    }
	    return new LinkedSet<E>(prev);
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
	  LinkedNode<E> prev = null;
	    for(E item: this) {
	      if(!that.contains(item)) {
	        LinkedNode<E> temp;
	        if(prev == null) { 
	          temp = new LinkedNode<E>(item, null);
	        }else {
	          temp = new LinkedNode<E>(item, prev);
	        }
	        prev = temp;
	      }
	    }
	    return new LinkedSet<E>(prev);
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
	  Set val = null;
	Iterator<E> Thing = this.iterator();
		while (Thing.hasNext()) {
			E info = (E) Thing.next();
			if (info.equals(e) == false) {
				if (val == null) {
					val = new LinkedSet<E>(info);
				}else{
					val = val.adjoin(info);
				}
			}
		}
		
		return val;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
