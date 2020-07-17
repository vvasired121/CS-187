package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
	  if(solution != null){
			return solution;
		}
	  List<T> way = new ArrayList<T>();
	  T begin = searchProblem.getInitialState();
		Queue<T> q = new LinkedList<T>();
		
		visited.add(begin);
		way.add(begin);
		q.add(begin);
		while(!q.isEmpty()){
			T x = q.remove();
			if(searchProblem.isGoal(x)){
				visited.add(x);
				way.add(x);
				while(!x.equals(searchProblem.getInitialState())){
					T predecessor = way.get(visited.indexOf(x));
					way.add(predecessor);
					x = predecessor;
				}
				break;
			}
			for(T next: searchProblem.getSuccessors(x)){
				if (!visited.contains(next)){
					visited.add(next);
					q.add(next);
					way.add(next);
					way.set(visited.indexOf(next), x);

				}
			}
		}
		int size = way.size();
		for (int i= size-1; i>0; i--) {
			T a = way.get(i);
			T b = way.get(i-1);
			if (!searchProblem.getSuccessors(b).contains(a)) {
				way.remove(b);
			}
		}
		return way;
  }
}
