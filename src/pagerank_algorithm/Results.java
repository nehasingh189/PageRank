package pagerank_algorithm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Results {
	int no_outlinks;
	int no_inlinks;
	ArrayList<Integer> in_link_count = new ArrayList<>();
		public void requiredOutput(HashMap<String, Page> graph) throws FileNotFoundException, UnsupportedEncodingException {
			
			//get an iterator on each entry of the graph
			Iterator it =graph.entrySet().iterator();
			//
			PrintWriter writer = new PrintWriter("Page_Inlink_count.csv", "UTF-8");
			PrintWriter writer_for_diff_pr = new PrintWriter("Page_Rank_difference.csv", "UTF-8");
			int PageCount = graph.size();
			// intitial pageRank is 1/N  where N is number of page rank
			double initial_pageRank =  (float) 1 / PageCount;
			System.out.println("initial PageRank value is "+ initial_pageRank);
			//for each entry in the HashSet
			for(String page  : graph.keySet()){
				Page pg= graph.get(page);
				
				//if the page isSink i.e. no outlinks then increment the counter for no_outlinks
				if(pg.isSink()){
					no_outlinks++;	
				}
				
				//if the page has no inlinks then increment the counter for no_inlinks
				if((pg.vertices).size() == 0){
				no_inlinks++;
				}
			   				
				writer.println(pg.write_inlink());
				
				//if pagRank of page is not equal to the intial PageRank, list these pages in a file
				if(pg.rank < initial_pageRank){
					writer_for_diff_pr.println(pg.write_file());
				}
				
			}
			
			System.out.println("Files with no outlinks are: " + no_outlinks);
			System.out.println("Files with no inlinks are: "+ no_inlinks);
			writer.close();
			writer_for_diff_pr.close();

		}
			
}
