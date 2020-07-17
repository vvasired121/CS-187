package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
	List<Integer> a = new ArrayList<Integer>();
  public EightPuzzle(List<Integer> firsts) {
	  if (firsts.size() != 9) {
	      throw new IllegalArgumentException();
	    }
	    for (int i = 0; i <= 8; i++) {
	      if (!firsts.contains(i)) {
	        throw new IllegalArgumentException();
	      }
	    }
	    a = firsts;
  }

  @Override
  public List<Integer> getInitialState() {
    // TODO
    return a;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    // TODO
	  List<List<Integer>> s = new ArrayList<List<Integer>>();
		int z = currentState.indexOf(0);
		boolean t = false;
		boolean b = false;
		boolean l = false;
		boolean r = false;
		if(z % 3 == 0){
			l = true;
		}
		if(z % 3 == 2){
			r = true;
		}
		if(z - 3 < 0){
			t = true;
		}
		if(z + 3 > 8){
			b = true;
		}
		if(l){
			List<Integer> lt = new ArrayList<Integer>(currentState);
			Collections.swap(lt, z, z+1);
			s.add(lt);
		}
		if(r){
			List<Integer> rt = new ArrayList<Integer>(currentState);
			Collections.swap(rt, z, z-1);
			s.add(rt);
		}
		if(t){
			List<Integer> tT = new ArrayList<Integer>(currentState);
			Collections.swap(tT, z, z + 3);
			s.add(tT);
		}
		if(b){
			List<Integer> bt = new ArrayList<Integer>(currentState);
			Collections.swap(bt, z, z - 3);
			s.add(bt);
		}
		if(!t && !b){
			List<Integer> up = new ArrayList<Integer>(currentState);
			List<Integer> nb = new ArrayList<Integer>(currentState);
			Collections.swap(up, z, z-3);
			Collections.swap(nb, z, z+3);
			s.add(up);
			s.add(nb);
		}
		if(!l && !r){
			List<Integer> nl = new ArrayList<Integer>(currentState);
			List<Integer> nr = new ArrayList<Integer>(currentState);
			Collections.swap(nl, z, z-1);
			Collections.swap(nr, z, z+1);
			s.add(nl);
			s.add(nr);
		}
		return s;
  }


  @Override
  public boolean isGoal(List<Integer> state) {
    // TODO
	  if(state == null){
			throw new NullPointerException();
		}
		int counter = 1;
		for(int j = 0; j < state.size() - 1; j++){
			if(!state.get(j).equals(counter)){
				return false;
			}
			counter++;
		}
		if(state.get(8) != 0){
			return false;
		}
		return true;
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
