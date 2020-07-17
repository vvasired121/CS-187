package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
  public void add(T t) {
    // TODO
	  upperBound++;
		super.add(t);
		if(Math.pow(3/2, getHeight(getRoot())) > upperBound){
			
		}
  }

  @Override
  public boolean remove(T element) {
    // TODO
	  if(element==null){
			throw new NullPointerException("element is null");
		}
		if(contains(element)){
			root = removeFromSubtree(root, element);
			if(size()*2<upperBound){
			this.balance();
			upperBound= size();
			}
			return true; 
			}
				return false;
	
  }
}
