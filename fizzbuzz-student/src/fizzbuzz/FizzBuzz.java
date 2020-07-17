package fizzbuzz;

public class FizzBuzz {
  private final int fizzNumber;
  private final int buzzNumber;

  /**
   * Construct an object that can compute fizzbuzz values for a game of 
   * Fizz and Buzz.
   * 
   * @param fizzNumber an integer between 1 and 9
   * @param buzzNumber an integer between 1 and 9
   */
  public FizzBuzz(int fizzNumber, int buzzNumber){
    this.fizzNumber = fizzNumber;
    this.buzzNumber = buzzNumber;
  }

  /**
   * Returns the fizzbuzz value for n. The rules are:
   * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
   * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
   * - however, if both the above conditions are true, you must return "fizzbuzz"
   * - if none of the above conditions is true, return the number itself.
   *
   * <p>For example, getValue(1) returns "1".
   * 
   * @param n a positive integer
   * @return the fizzbuzz value, as a String
   */
  
  public String getValue(int n){
	  String result = "";
	  if((n % fizzNumber == 0) || (String.valueOf(n).contains("" + fizzNumber))){
    	result = result + "fizz";
	  }
      if(n % buzzNumber == 0 || (String.valueOf(n).contains ("" + buzzNumber))){
    	  result = result + "buzz";
      }
      if(result.equals("")){
    	  return String.valueOf(n);
      }
      return result;  // return the number itself as a String    
  }

  /**
   * Returns an array of the fizzbuzz values from start to end, inclusive.
   * 
   * <p>For example, if the fizz number is 3 and buzz number is 4,
   * getValues(2,6) should return an array of Strings:
   * 
   * <p>{"2", "fizz", "buzz", "5", "fizz"}
   * 
   * @param start
   *            the number to start from; start > 0
   * @param end
   *            the number to end at; end >= start
   * @return the array of fizzbuzz values
   */
  public String[] getValues(int start, int end){
	  String[] arr = new String[end-start +1];
	  int x = start;	  	  
	  for(int j = 0; j < arr.length; j++){ 
		  arr[j] = getValue(x);
		  x++;
	  }
	  return arr; 		     
  }
}
