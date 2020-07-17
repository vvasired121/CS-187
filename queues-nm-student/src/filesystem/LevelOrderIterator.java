package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {

  /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
	
	Queue<File> a = new Queue<File>();
	Queue<File> last = new Queue<File>();
	
	int index, count;
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    // TODO 1  if(rootNode exists() throw FileNotFound Exeception 
	  if (!rootNode.exists()) 
			throw new FileNotFoundException("NotExist");
		a.enqueue(rootNode);
		while(!a.isEmpty()){
			if (a.peek().isDirectory()){
				File[] stuff = a.peek().listFiles();
				Arrays.sort(stuff);
				for (File f: stuff)
					a.enqueue(f);
			}
			last.enqueue(a.dequeue());
			count++;
		} 
  }

  @Override
  public boolean hasNext() {
    // TODO 2 return if q is empty 
	  return !last.isEmpty();
  }

  @Override
  public File next() throws NoSuchElementException {
    // TODO 3 if q is empty throw NoSuchElementException - File n q.dequeue() if(!n is 
	  if (last.isEmpty()) throw new NoSuchElementException("Iteration failed");
      return last.dequeue();
  }

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }

}
