
package pagerank_algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PageRank_Driver {

   
    public static void main(String[] args) {
 
    	Results rs = new Results();
    	PageRank_Algorithm pa = new PageRank_Algorithm();
        HashMap<String, Page> graph = new HashMap<String, Page>(); 
        BufferedReader br;
        FileReader fr;

        try {

            fr = new FileReader("wt2g_inlinks.txt");
            br = new BufferedReader(fr);
            String line;
            int c = 0;
            
	        while ((line = br.readLine()) != null) {
	        String[] result = line.split(" ");
	        Page p = new Page();
	        p.name = result[0]; // the first string in line is name of the page
	        for (int i = 1; i < result.length; i++) {
	        
	        // add list of pages that have inlinks to current page in vertices ArrayList.
		    p.vertices.add(result[i]);  
		    
		    //store total number of count of inlink in the variable inlink, this is just length of vertices Arraylist.
		    p.inlink=result.length; 
		    }
	        
	        // put page name and vertices in the HashMap
	        graph.put(result[0], p); 
	        c++;
	        }
	        br.close();
	           
            System.out.println("Number of Nodes " + c);
            String[] keys = new String[c];
            graph.keySet().toArray(keys); // store page names in Keys array

	        for (int i = 0; i < c; i++) {
		        Iterator<String> ver = graph.get(keys[i]).vertices.iterator(); 
		        //get an iterator that iterates over in-links pages
		        // and increment outlink count for those pages that point to current page.
		        while (ver.hasNext()) {
		        String node = ver.next();
		        graph.get(node).outlink++; 
	         }
	        graph.get(keys[i]).rank = (float) 1 / c; //initial pageRank = 1/No of pages 
	       }

            pa.findPageRank(graph, keys); 
            rs.requiredOutput(graph);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PageRank_Driver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PageRank_Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 }
