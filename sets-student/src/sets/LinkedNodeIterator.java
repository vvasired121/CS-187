package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
  // TODO (1) define data variables
	LinkedNode<E> head;
	LinkedNode<E> currNode = head;
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
    // TODO (2) choose appropriate parameters and do the initialization
	  this.head = head;
	    currNode = this.head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	  return (currNode != null);
  }

  @Override
  public E next() {
    // TODO (4)
	  if(currNode == null) {
	      throw new NoSuchElementException();
	    }
	    E data = currNode.getData();
	    if(hasNext())
	      currNode = currNode.getNext();
	    return data;
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
