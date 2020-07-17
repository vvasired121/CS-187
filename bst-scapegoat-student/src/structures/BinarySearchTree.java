package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  public int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO
	  BSTNode<T> n = root;
		while(n != null){
			if(n.getData().compareTo(t) > 0)
				n = n.getLeft();
			else if(n.getData().compareTo(t) < 0)
				n = n.getRight();
			else
				return true;
		}
		return false;
  }

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean end = contains(t);
    if (end) {
      root = removeFromSubtree(root, t);
    }
    return end;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO
	  if (t == null) {
			throw new NullPointerException();
		}
		if(isEmpty()){
			return null;
		}
		BSTNode<T> node = root;
		while(node != null){
			if(node.getData().compareTo(t) > 0)
				node = node.getLeft();
			else if(node.getData().compareTo(t) < 0)
				node = node.getRight();
			else
				return node.getData();
		}
		return null;
  }


  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> up) {
    if (node == null) {
      return up;
    }
    int out = up.getData().compareTo(node.getData());
    if (out <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), up));
    } else {
      node.setRight(addToSubtree(node.getRight(), up));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO
	  if(isEmpty())
			return null;
		BSTNode<T> nn = root;
		while(nn.getLeft()!=null){
			nn = nn.getLeft();
		}
		return nn.getData();
  }


  @Override
  public T getMaximum() {
    // TODO
	  if (root == null) {
	      return null;
	    }
	    BSTNode<T> curr = root;
	    while (curr.getRight() != null) {
	      curr = curr.getRight();
	    }
	    return curr.getData();
  }


  @Override
  public int height() {
    // TODO
	  if(isEmpty()){
			return -1;
		}
		return getHeight(root);
  }
  public int getHeight(BSTNode<T> curr) {
	    int left = 0;
	    int right = 0;

	    if (curr.getLeft() != null) {
	      left = 1 + getHeight(curr.getLeft());
	    }
	    if (curr.getRight() != null) {
	      right = 1 + getHeight(curr.getRight());
	    }

	    return Math.max(left, right);
	  }

  public Iterator<T> preorderIterator() {
    // TODO
	  Queue<T> q = new LinkedList<T>();
		preOrderTraverse(q, root);
		return q.iterator();
  }
  private void preOrderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preOrderTraverse(queue, node.getLeft());
			preOrderTraverse(queue, node.getRight());
		}
	}

  /**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> q = new LinkedList<T>();
    inorderTraverse(q, root);
    return q.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO
	  Queue<T> l = new LinkedList<T>();
	    postorderTraverse(l, root);
	    return l.iterator();
  }
  private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
	    if (node != null) {
	      inorderTraverse(queue, node.getLeft());
	      inorderTraverse(queue, node.getRight());
	      queue.add(node.getData());

	    }
	  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO
	  return equalsHelp(root, other.getRoot());
  }
  private boolean equalsHelp(BSTNode<T> a, BSTNode<T> b) {
		if (a == null && b == null) return true;
		else if (a == null || b == null) return false;
		else {
			if (!a.getData().equals(b.getData())) return false;
			return equalsHelp(a.getLeft(), b.getLeft()) && equalsHelp(a.getRight(), b.getRight());
		}
	}

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO
	  if(other==null){
			throw new NullPointerException("other is null");
		}
		if(this.size()!=other.size()){
			return false;
		}
		
		Iterator<T> x=this.inorderIterator();
		Iterator<T> y=other.inorderIterator();
		T[] arr1 = (T[]) new Comparable[this.size()];
		for(int i=0;i<this.size();i++){
			arr1[i]=x.next();
		}
		
		T[] arr2 = (T[]) new Comparable[this.size()];
		for(int i=0;i<this.size();i++){
			arr2[i]=y.next();
		}   
		
		boolean h = true;
		for(int i=0;i<this.size();i++){
			if(arr1[i]!=arr2[i]){
				h=false;
			}
		}
		return true;
  }

  @Override
  public boolean isBalanced() {
    // TODO
	  double x = Math.pow(2, height());
	  double y = Math.pow(2, height() + 1);
	    return (x <= size() && size() < y);
  }

  @Override
  @SuppressWarnings("unchecked")

  public void balance() {
    // TODO
	  Queue<T> q = new LinkedList<T>();
		T[] arr = (T[])new Comparable[size()];
		inorderTraverse(q, root);
		for(int x = 0; x < size(); x++){
			arr[x] = q.remove();
		}
		root = sortedArray2BST(0, size()-1, arr);
  }
  public BSTNode<T> sortedArray2BST(int lower, int upper, T[] array) {
		if (lower > upper)
		return null;
		int mid = (lower + upper) / 2;
		BSTNode<T> node = new BSTNode<T>(array[mid], null, null);
		node.setLeft (sortedArray2BST(lower, mid - 1, array));
		node.setRight(sortedArray2BST(mid + 1, upper, array));
		return node;
	}


  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> "
            + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> "
            + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }

  /**
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}