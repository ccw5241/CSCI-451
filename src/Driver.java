/*	
 * 	CSCI-451 HW-01 Z-Algorithm
 * 	Norman Tang & Chen Wei Cao
 * 
 */

import java.io.IOException;
import java.util.Scanner;


public class Driver {
	
	public static void main(String[] args) throws IOException
	{
		// Stuff will go into here.
		String a, b;
		a = "aba";
		b = "ababaccccaba";
		// Expected Matches Found At: 4, 5, 13
		
		Zalgorithm DoIt = new Zalgorithm(a, b);
	}
	
}
