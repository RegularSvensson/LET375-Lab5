package linearrecursion;

import java.io.IOException;
/**
 * @author NN & Mona Khoshoi, Elias Svensson
 * @version 2012-05- & 2016-05-04
 */
public class LinearRecursion {
// A.1
	/**
	 * Reads user input of a string of chars
	 * and prints it in reverse.
	 * @throws IOException
	 */
    public static void reverseInput() throws IOException {
        // read input from user and store in char
    	char c = (char) System.in.read();
    	// check if char is not a new line
    	if (c != '\n') {
    		// call reverseIntput method recursively
        	reverseInput();
    		// print char recursively
        	System.out.print(c);
    	}
    }
        
// A.2
    /**
     * Multiplies two integers and
     * returns the answer.
     * @param m integer #1
     * @param n integer #2
     * @return n * m
     */
    public static int multiply(int m,int n) {
        // check if base case 
        if (m == 0) 
        	return 0;
        // check if m > 0
        else if (m > 0) 
        	// return call to multiply recursively
        	return n + multiply(m - 1, n);
        // else, m < 0
        else 
        	// return call to multiply recursively
        	return - n + multiply(m + 1, n);
    } 
    
// A.3
    /**
     * Counts the number of digits of an integer
     * and returns the answer.
     * @param n integer
     * @return number of digits
     */
    public static int countDigits(int n) {
        // check if base case 
        if (n < 10)
        	return 1;
        // return call to countDigits recursively
        return 1 + countDigits(n / 10);
    }
           
    public static ListNode cons( int element, ListNode l ) {
        return new ListNode( element, l );
    }
    
    public static String toString( ListNode l ) {
        return "[" + toStringRec(l) + "]";
    }
    
    public static String toStringRec( ListNode l ) {
        if ( l == null )
            return "";
        else {
            return "" + l.element + 
                ((l.next == null) ? "" : "," + toStringRec(l.next));
        }
    }
    
    public static void print( String prompt, ListNode l ) {
        System.out.println(prompt + ": " + toString(l));
    }

// A.4
/**
 * Returns a copy of a list.
 * @param l list
 * @return copy of l
 */
 public static ListNode copy( ListNode l ) {
     // check if base case
	 if (l == null)
		 return null;
	 // return call to copy recursively
	 return cons(l.element, copy(l.next));
 }
    
// A.5  
/**
 * Returns a list that contains the elements
 * of two other lists.
 * @param l1 list #1
 * @param l2 list #2
 * @return list with elements from l1 and l2
 */
 public static ListNode append( ListNode l1, ListNode l2 ) {
    // check if base case
 	if (l1 == null)
 		return copy(l2);
 	// return call to append recursively
 	return cons(l1.element, append(l1.next, l2));
 }
    
/**********************************************
 * Some test cases.
 * Uncomment as you proceed!
 * ********************************************/
    public static void main(String[] args) throws IOException {
// A.1
     reverseInput();
     System.out.println();
// A.2
      System.out.println(multiply(5,7));
      System.out.println(multiply(-5,7));
      System.out.println(multiply(-5,7));
      System.out.println(multiply(-5,-7));
      System.out.println(multiply(0,7));
      System.out.println(multiply(5,0));
// A.3
      System.out.println(countDigits(0));
      System.out.println(countDigits(5));
      System.out.println(countDigits(123));
                
        // An array of some test lists
        ListNode[] ll = {
            null,
            cons(1,null),
            cons(2,cons(3,null)),
            cons(4,cons(5,cons(6,null)))
        };
// A.4      
         System.out.println("test copy");
         for ( int i = 0; i < ll.length; i++ ) {
             ListNode l = cons(999,copy(ll[i]));
             print("l",l);       // result
             print("l"+i,ll[i]); // original should be untouched
         }
// A.5     
         System.out.println("test append from left"); 
         for ( int i = 0; i < ll.length - 1; i++ ) {
             ListNode l = append(ll[i],ll[ll.length-1]);
             print("l",l);       // result
             print("l"+i,ll[i]); // original should be untouched
             print("l"+(ll.length-1),ll[ll.length-1]); // original should be untouched
         }
        
         System.out.println("test append from right"); 
         for ( int i = 0; i < ll.length - 1; i++ ) {
             ListNode l = append(ll[ll.length-1],ll[i]);
             print("l",l);       // result
             print("l"+(ll.length-1),ll[ll.length-1]); // original should be untouched
             print("l"+i,ll[i]); // original should be untouched
         }
	}
}
