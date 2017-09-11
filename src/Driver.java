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
	}
	
	public void exactPatternMatch(String t, String p){
		/*
		 * 	Form string s from p$t
		 * 	Run modified Z-algorithm on s
		 * 		this means only store the Z values from 2 to |p|
		 * 		print out only the positions where the Z value = lenght of p.
		 * 
		 */
	}
	
	public void zCase2(int positionK, int positionR, int positionL, String s){	// if positionK < positionR
		int k = positionK,
			r = positionR,
			l = positionL;
		
		/*
		 * subcase A: (where the matches stop before or up to positionR)
		 * let a = positionK + Z[k-l+1] 
		 * if Z[k-l] < positionR - positionK
		 * 	Z[k] = Z[k-l+ 1] is the result
		 * 	if Z[k] == length of p <- almost certain will never be true.
		 * 		print out positionK
		 * 	k++, we don't need a comparision, we already know its value, so move on
		 * 
		 */
		if(Z[k-l] < (r-k) ){	// if we stay to the left of r inside the zBox
			Z[k] = Z[k-l];		// we already know this Z value
			k++;				// check the next k position
		}
		
		/*
		 * subcase B: (where matches exist outside of the zBox, past positionR)
		 * goto positionR, and begin explicitly matching until failure to match.
		 * if Z[k-l+1] >= positionR - positionK
		 * 	pointR' = [(positionR - positionK) + position(K-l+1)] + 1
		 * 	pChar = pointR'
		 * 	tChar = positionR + 1
		 * 	while(pChar == tChar)
		 * 		pChar++ increment both spaces, and compare again
		 * 		tChar++	
		 * 	When pChar != tChar, mismatch has occured, record this spot as the new R
		 * 	positionL = positionK
		 * 	positionR = tChar + 1
		 */
		else if(Z[k-l] >= (r-k)){	// if we go past the known area aka zBox
			Z[k] = Z[k-l];			// get the known Z value for now to add to later.
			int i = k-l;			// i = k` ie. the position in the search string that matches to k
			while(s.charAt(i) == s.charAt(r)){	// while they match
				i++;							// increment and compare again
				r++;							// and push r forward
			}
			l = k;	// now that we've found a mismatch, readjust everything to start again
			Z[k] = i;	// Add however far we traveled to Z[k].
			k++;	
		}
	}
	
}
