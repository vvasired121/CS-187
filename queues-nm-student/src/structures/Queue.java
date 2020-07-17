package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
  int size = 0;
  Node<T> head, tail;
  class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> head;
    public Node<T> tail;
    
    
    public Node(T data) { 
      this.data = data;
    }
    
    public Node(T data, Node<T> next) {
      this.data = data; 
      this.next = next;
    }
  }




  public Queue() {
     head = null;
     tail = null;
     
    // TODO 1
  }

  public Queue(Queue<T> other) {
    // TODO 2   

     
	  Node a = other.head;
      while(a != null) {
          enqueue((T) a.data);
          a = a.next;
          
      }
	  
	  
  }

  @Override
  public boolean isEmpty() {
    // TODO 3
	  if (size == 0) {
			return true;
		}
          return false;
  }

  @Override
  public int size() {
    // TODO 4
    return size;
  }

  @Override
  public void enqueue(T element) {
    // TODO 5 insert on tail deque on head 
	  Node n = new Node(element);
      if(isEmpty()) {
          head = tail = n;
      } else {
          tail.next = n;
          tail = n;
      }
      size++;
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    // TODO 6 T temp = head.data;  head = head.next;  size--;
	  if(isEmpty()) {
          throw new NoSuchElementException("Empty");
      }
      T temp = head.data;
      head = head.next;
      size--;
      return temp;
	  
	 
	  
    
  }

  @Override
  public T peek() throws NoSuchElementException {
    // TODO 7 if isEmpty throw exception else return head.data
	  if(isEmpty()) {
          throw new NoSuchElementException("Empty");
      }
	  return head.data;
  }


  @Override
  public UnboundedQueueInterface<T> reversed() {
    // TODO 8  make a new queue: reverseQ   
	  //void reverseHelper(revQ. head) return revQ reverseHelper(Queue, node) if(node==null){ return  reverseHelper(Queue,node link)
	  //Queue enque(node.data)
	  Queue<T> revq = new Queue(this);
		
		if (revq.isEmpty()) {
			return revq;
		}
		
		T info = revq.peek();
		revq.dequeue();
		revq = (Queue<T>) revq.reversed();
		revq.enqueue(info);
		
		return this;
  }
}


