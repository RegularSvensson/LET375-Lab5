package mobile;

public class Mobile {
	
	private enum MobileType { SIMPLE, COMPOSITE }
	private MobileType type;
	private float weight;                   // Simple
	private float leftLength, rightLength;  // Composite
	private Mobile left, right;
	
	public Mobile( float weight ) {
		type = MobileType.SIMPLE;
		this.weight = weight;
		left = null;
		right = null;
		
	}
	
	public Mobile( Mobile left, float leftLength, Mobile right, float rightLength ) {
		type = MobileType.COMPOSITE;
		this.left = left;
		this.right = right;
	    this.leftLength = leftLength;
	    this.rightLength = rightLength;	
	}
	
	// Return the total mass of the mobile
	public float getWeight() {
		if ( isSimple() )
			return weight;
		else
			return left.getWeight() + right.getWeight();
	}  
	
	// B1
	/**
	 * Returns the height of a mobile.
	 * @return height of mobile
	 */
	public int getHeight() {
	    // check if base case
		if (isSimple())
			return 1;
		// return call to getHeight recursively
		return 1 + Math.max(left.getHeight(), right.getHeight());
	}  
	
	// B2
	/**
	 * Prints the leaves of the mobile.
	 */
	public void flatten()  {
		// check if base case
		if (isSimple())
			System.out.print(weight + " ");
		// else, call flatten recursively
		else {
			left.flatten();
			right.flatten();
		}
		
	}  
	
//	B3
	/**
	 * Prints a structured view of the mobile.
	 */
	public void prettyPrint() {
	      // check if base case
		if (isSimple())
			System.out.print(weight + " ");
		// else, print and call prettyPrint recursively
		else {
			System.out.print("[");
			left.prettyPrint();
			System.out.print("," + leftLength + ", ");
			right.prettyPrint();
			System.out.print(", " + rightLength);
			System.out.print("]");
		}
	}
	
// Determine if the mobile is balanced
	public boolean isBalanced() {
		final double eps = 0.000001;
		return isSimple() ||
		    left.isBalanced() && right.isBalanced() &&
		        Math.abs( leftLength * left.getWeight() -
				rightLength * right.getWeight() ) < eps;
	}   

// B4
	/**
	 * Determines if two mobiles are equal.
	 * @param rhs mobile 
	 * @return true or false
	 */
	public boolean equals(  Mobile rhs ) {
	    // create variable for comparison purposes
	    final double eps = 0.000001;
	    
	    // check if base case
	    if (isSimple())
	    	return (eps > Math.abs(weight - rhs.weight)) && rhs.isSimple();
	    // return calls to equals recursively
	    return (right.equals(rhs.right) && 
	    		left.equals(rhs.left)) && 
	    		(eps > Math.abs(rightLength - rhs.rightLength)) &&
	    		(eps > Math.abs(leftLength - rhs.leftLength));
	    		
	}
	
//	B5
	/**
	 * Returns a clone of this mobile.
	 */
	public Mobile clone() {
         // check if base case
         if (isSimple())
        	 // return a new Mobile with the same weight
        	 return new Mobile(weight);
         // return call to clone recursively
         return new Mobile(left.clone(), leftLength, right.clone(), rightLength);
	}
	
// B3
	/**
	 * Change this mobile to its mirror image.
	 */
	public void mirror() {
         // check if base case
		if (isSimple())
			// do nothing
			return;
		else {
			// mirror submobiles
			left.mirror();
			right.mirror();
			
			// switch right and left
			Mobile mobile = right;
			float length = rightLength;
			right = left;
			rightLength = leftLength;
			left = mobile;
			leftLength = length;
		}
	}
	
	private boolean isSimple() { 
		return type == MobileType.SIMPLE; 
	}
	
	public static void main(String[] args) {
		Mobile  m1 = new Mobile( 1 ),
		        m2 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		        m = new Mobile( m1, 10, m2, 2 );
	
		System.out.println("Total mass: " + m.getWeight() );

		System.out.println("Height:     " + m.getHeight() );
		m.flatten(); System.out.println();
		m.prettyPrint(); System.out.println();
		if ( m.isBalanced() )
			System.out.println("Balanced!");
		else
			System.out.println("Not balanced!");

		Mobile m22 = new Mobile( new Mobile( 2 ), 6,  new Mobile( 3 ), 4 ),
		       m3 = new Mobile( m1, 10, m22, 2 );
		if ( m.equals(m3) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");
		
		Mobile c = m.clone();
		if ( c.equals(m) )
			System.out.println("Equal!");		// They should be!
		else
			System.out.println("Not equal!");

		if ( c == m )
			System.out.println("Identical!");	// They should definitely not!
		else
			System.out.println("Not identical!");
		
		m.mirror();
		m.prettyPrint(); System.out.println();
		m.mirror();
		m.prettyPrint(); System.out.println();

	}
}
