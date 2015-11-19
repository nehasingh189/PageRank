package pagerank_algorithm;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;

public class Perplexity {

	double[] per_arr=new double[999];
	int c=0;
	
	/**
	 * 
	 * @param graph
	 * @param n
	 * @return true if change of perplexity is less than 1 for last 4 iterations
	 */
    public boolean isPerplexity(HashMap<String, Page> graph, int n){
    	Page[] pageArray = new Page[n];
    	graph.values().toArray(pageArray);
    	boolean isPer = false;
    	
    	per_arr[c++] = findPerplexity(pageArray); //store perplexity in the array
    	
    	if(c>3){
    		isPer=true;
    		for(int i =c-1; i>c-4; i-- ){
    			if(Math.abs((per_arr[i]-per_arr[i-1])) >1 ){
    			isPer = false;
    			}
    		}
    	}
    	
    	return isPer;
    	
    }
    		
    /**
     * @param pageArray
     * @return The perplexity for the pageArray, where 
     * where perplexity is simply 2 raised to the (Shannon) 
     * entropy of the PageRank distribution
     */
    double findPerplexity(Page[] pageArray){
    	
    	double perpexlity=0;
    	double temp=0;
    	
    	// sum of [p(x) * log2 p(x)] where x is a page
    	for(int i=0; i<pageArray.length ; i++){
    		temp+= (pageArray[i].rank*log2(pageArray[i].rank));
    	}
    	
    	perpexlity=Math.pow(2, -1*temp);
    	
    	System.out.println("perplexity is "+perpexlity);
		
    	return perpexlity;
    	
    }
	
    
    /**
     * 
     * @param a
     * @param b
     * @return log value of of a to the base b
     */
	public static double logb( double a, double b )
	{
	return Math.log(a) / Math.log(b);
	}

	/**
	 * 
	 * @param a
	 * @return log value of 'a' to base 2
	 */
	public static double log2( double a )
	{
	return logb(a,2);
	}

	
}
