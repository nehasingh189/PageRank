/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank_algorithm;

import java.util.ArrayList;
import java.util.Iterator;


public class Page {
    
    public double rank;
    public ArrayList<String> vertices;
    public String name;
    public int outlink;
    public double temprank;
    public int inlink;
    
   

	Page()
    {
        vertices = new ArrayList<String>();
    }
    
    public boolean isSink()
    {
        if(outlink == 0)
        {
            return true;
        }
        return false;
    }
    
    public void printPageRank()
    {
        System.out.println(name+" = "+rank);
    }
    
    public void printOutlink()
    {
        System.out.println(name+" = "+outlink);
    }
    
    public void printVertices()
    {
        System.out.print(name+ " --> ");
        
        Iterator ver = vertices.iterator();
        
        while(ver.hasNext())
        {
            System.out.print(ver.next() + " - ");
        }
        
        System.out.println("");
    }
    
    public String write_file()
    {
        String ans = "";
         ans = name+","+rank;
         return ans;
    }
    
    public String write_inlink(){
    	String link_count = "";
    	link_count = name+","+inlink;
    	return link_count;
    			
    }
    
}
