package wordfrequencycounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import structures.ArrayHashTable;



public class WordFrequencyCounter {
	static ArrayHashTable<String, Integer> freqCounter;

  /**
   * Constructor.
   */
  public WordFrequencyCounter() {
    // TODO
	  freqCounter = new ArrayHashTable<String, Integer>();
  }

  /**
   * Load file and pass each word in the file to the addword method.
   * @param filename : name of file.
   * @throws IOException : IOException.
   */
  public void loadFile(String filename) throws IOException {
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    while ((line = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(line);
      while (st.hasMoreTokens()) {
        String temp = st.nextToken();
        addWord(temp);
      }
    }
    br.close();
  }

  /**
   * Return the number of words in the file.
   * @param word : word.
   * @return : the number of word in the file.
   */
  public int countWord(String word) {
    Integer a = freqCounter.get(word);
    if(a==null)
    	return 0;
    else
    	return a;
  }

  /**
   * Update word frequency table.
   */
  static void addWord(String word) {
    // TODO
	  Integer a = freqCounter.get(word);
	    if(a==null)
	    	freqCounter.put(word,1);
	    else
	    	freqCounter.put(word,a+1);
	    
  }
}
