
public class Zalgorithm {

	public Zalgorithm(){
	}
	
	public Zalgorithm(String a, String b){
		String c = a + '$' + b;
		int z[] = new int[c.length()];
		System.out.println(c);
		
		getZarr(c, z, a.length());
		
	}
	
	// Don't use this one.
	public void zCase2(int positionK, int positionR, int positionL, String s, int[] Z){	// if positionK < positionR
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
	//  Z array for given string str[]
	public void getZarr(String str, int Z[], int patternLength)
	{
	   int n = str.length();
	   int L, R, k;
	
	   // [L,R] make a window which matches with prefix of s
	   L = R = 0;
	   for (int i = 1; i < n; ++i)
	   {
	       // if i>R nothing matches so we will calculate.
	       // Z[i] using naive way.
	       if (i > R)	// Case 1
	       {
	           L = R = i;
	
	           // R-L = 0 in starting, so it will start
	           // checking from 0'th index. For example,
	           // for "ababab" and i = 1, the value of R
	           // remains 0 and Z[i] becomes 0. For string
	           // "aaaaaa" and i = 1, Z[i] and R become 5
	           while (R<n && str.charAt(R-L) == str.charAt(R))
	               R++;
	           Z[i] = R-L;
	           // Exact Match Checking
	           if(Z[i] == patternLength){
	        	   System.out.println("exact match found at:  " + i);
	           }
	       }
	       else	// Case 2
	       {
	           // k = i-L so k corresponds to number which
	           // matches in [L,R] interval.
	           k = i-L;
	
	           // if Z[k] is less than remaining interval
	           // then Z[i] will be equal to Z[k].
	           // For example, str = "ababab", i = 3, R = 5
	           // and L = 2
	           if (Z[k] < R-i+1)	// Case 2A
	                Z[i] = Z[k];
	           // Exact Match Checking
	           if(Z[i] == patternLength){
	        	   System.out.println("exact match found at:  " + i);
	           }
	           // For example str = "aaaaaa" and i = 2, R is 5,
	           // L is 0
	           else	// Case 2B
	           {
	               //  else start from R  and check manually
	               L = i;
	               while (R<n && str.charAt(R-L) == str.charAt(R))
	                   R++;
	               Z[i] = R-L;
	               // Exact Match Checking
		           if(Z[i] == patternLength){
		        	   System.out.println("exact match found at:  " + i);
		           }
	           }
	       }
	   }
	   System.out.println("Reached end of string, terminating.");
	}
}
