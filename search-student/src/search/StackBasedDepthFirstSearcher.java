package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
	  Stack<T> s = new Stack<T>();
	  List<T> l = new ArrayList<T>();
	  List<T> pre = new ArrayList<T>();
		
		
		T pres = searchProblem.getInitialState();
		T x = null;
		
		s.push(pres);
		while(!s.isEmpty()) {
			T state = s.pop();
			
			if(searchProblem.isGoal(state)) { 
				x  = state;
				break;
			}
			
			visited.add(state);
			
			for(T after : searchProblem.getSuccessors(state)) {
				if(!visited.contains(after)) {
					visited.add(after);
					s.push(after);
					pre.add(state);
					l.add(after);
				}
			}
		}
		List<T> a = new ArrayList<T>();
		a.add(x);
		while(!x.equals(pres)) {
			a.add(pre.get(l.indexOf(x)));
			x = pre.get(l.indexOf(x));
		}
		Collections.reverse(a);
      return a;
  }
}
