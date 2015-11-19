/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank_algorithm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PageRank_Algorithm {
	Perplexity calculatePerplexity = new Perplexity();
    double sinkPR;
    int N;
    double d = 0.85f;
    int k=0; 
    
    public void findPageRank(HashMap<String, Page> graph, String[] node) 
    		throws FileNotFoundException, UnsupportedEncodingException {

        N = node.length;
        sinkPR=0; //sinkPR = 0
        int c=0;
        PrintWriter writer = new PrintWriter("Page_Rank_Iteration"+(++k)+".csv", "UTF-8");

        /*Calculate SinkPR value*/
        for (int i = 0; i < node.length; i++) {
            if (graph.get(node[i]).isSink()) {
                c++;
                sinkPR+=graph.get(node[i]).rank;  //SinkPR += PR(p)
            }
        }
        System.out.println("Number of Sink Node is "+c);
       
        /*the 'for' loop below will caucluat the page rank for each page in the Hashset */           
        for (int i = 0; i < node.length; i++) {
       	double newrank = (1 - d) / N;   // newPR(p) = (1 - d)/N
        newrank += d * (sinkPR / N);    //newPR(p) += d*sinkPR/N
        Iterator<String> ver = graph.get(node[i]).vertices.iterator();
               while (ver.hasNext()) {
	                String v = ver.next();	              
	                newrank += d * (graph.get(v).rank / (double)graph.get(v).outlink);   //newPR(p) += d*PR(q)/L(q)
	            }
            graph.get(node[i]).temprank = newrank;
        }

        System.out.println("PageRank on "+ (k) +" Iteration");
        
        /*Write down the PageRank in the csv file*/
        for (int i = 0; i < node.length; i++) {
            graph.get(node[i]).rank = graph.get(node[i]).temprank;
          //  graph.get(node[i]).printPageRank();
            writer.println(graph.get(node[i]).write_file());
        }
        writer.close();
        
       /* The below if block should be used when we want to run the iteration irrespective of convergence
        currently the program is written to stop when convergence is reached, that is, when the perplexity value 
        has a difference of less than 1 in last 4 iterations.
        */

        /* if (it > 1) { // if u pass 100, keep decrementing till u reach 0
            findPageRank(graph, node, --it);
        }*/

       /*the program runs till perplexity is not reached. */
      if (!calculatePerplexity.isPerplexity(graph, N)) {
            findPageRank(graph, node);
        }

    }
    
}
