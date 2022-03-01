import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Amit Levy 313268773, Maya Shternlicht 308341411
 * 
 */
public class Solver {
	
	int maxDegOut = 0;
	HashMap<Integer,Integer> degpath = new HashMap<Integer,Integer>();
	HashMap<Integer,List<Integer>> degIn = new HashMap<Integer,List<Integer>>();
	Graph graph = new Graph();
	
    public Solver() {
    }
    
    public int findOPT(int vertices, List<VerticesPair> edges) { // O(|V|+|E|)
    	HashMap<Integer,List<Integer>> neighbors = graph.getGraph();   // O(1) 	
    	this.graph.buildGraph(vertices, edges);	// O(|V|+|E|)
    	onRoots(vertices,this.degIn,edges); // O(|V|+|E|)
    	onDegPath(vertices,this.degpath,neighbors,this.degIn); // O(|V|)
    	getMaxDegOut(vertices, neighbors); // O(|V|+|E|)
        return this.maxDegOut; // O(1)
    }
    
    
    public List<Integer> getSol() { // O(|V|+|E|)
    	List<Integer> opos_path = new ArrayList<Integer>();	// O(1)
    	List<Integer> path = new ArrayList<Integer>();	// O(1)
    	HashMap<Integer,List<Integer>> neighbors = graph.getGraph();	// O(1)
    	int leftover = this.maxDegOut;	// O(1)
    	int nextVertex;	// O(1)
    	int vertices = neighbors.size(); // O(1)
    	int curr_deg;	// O(1)
    	
    	for(int i = vertices; i > 0; i--) {  // Runs for |V| iterations, checking through every vertex in the graph . O(|V|)
    		if(neighbors.get(i).size() > 0) {	// O(1)
    			for(int j = 0; j < this.degIn.get(i).size(); j++) { // Runs for |E| iterations,
    																// because every vertex has a certain amount of edges which sums up to |E| in total. O(|E|)
    				nextVertex = this.degIn.get(i).get(j);	// O(1)
    				curr_deg = this.degpath.get(i);	// O(1)
    				if(curr_deg - this.degpath.get(nextVertex) == neighbors.get(i).size()) {	// O(1)
    					opos_path.add(i);	// O(1)
    					leftover -= neighbors.get(i).size();	// O(1)
    					if(this.degIn.get(nextVertex).size()==0) {	// O(1)
    						opos_path.add(nextVertex);	// O(1)
    						leftover -= neighbors.get(nextVertex).size();	// O(1)
    						if(leftover == 0) {	// O(1)
    					    	for(int k = opos_path.size()-1; k>=0; k--) { // Runs for |V| iterations, creating the correct order to return it in a new list . O(|V|)
    					    		// This itteration happens only once during the funtion's call, since once leftover = 0, we will return the correct order of the path
    					    		// and end the function. Therefore, this itteration can't be called more then once, because then the function would return more 
    					    		// then 2 paths at the same call, which is impossible.
    					    		path.add(opos_path.get(k));	// O(1)
    					    	}
    					    	return path;	// O(1)
    						}
    						else {
    					    	opos_path = new ArrayList<Integer>();	// O(1)
    							leftover = this.maxDegOut;	// O(1)
    						}
    					}
    				}
    			}
    		}
    	}
        return path;		// O(1)
    }
    
    
    public void onDegPath(int vertices, HashMap<Integer,Integer> map,HashMap<Integer, List<Integer>> neighbors,HashMap<Integer,List<Integer>> degIn) { // runs in O(|V|)
    	for(int i = 1; i<= vertices; i++) { // Runs for |V| iterations, mapping the degIn of every vertex in the graph. O(|V|)
    		if(degIn.get(i).size() == 0) {	// O(1)
    			map.put(i, neighbors.get(i).size());	// O(1)
    		}
    		else {
    			map.put(i, 0); 	// O(1)   			
    		}
    	}
    }
        
    public void onRoots(int vertices, HashMap<Integer,List<Integer>> degIn, List<VerticesPair> edges) { // runs in O(|V|+|E|)
    	for(int i = 1; i<= vertices; i++) { // Runs for |V| iterations, creates a new ArrayList for each vertex. O(|V|)
    		degIn.put(i, new ArrayList<Integer>());
    	}
    	for(VerticesPair edge : edges) {// Runs for |E| iterations, mapping the opposite direction for every edge in the edges. O(|E|)
    		degIn.get(edge.GetSecond()).add(edge.GetFirst());
    	}
    }
    
	private void getMaxDegOut(int vertices, HashMap<Integer, List<Integer>> neighbors) { // runs in O(|V|+|E|)
		int currentDegOut;	// O(1)
		int currentVertex;	// O(1)
		for(int i = 1; i<= vertices; i++) {	// Runs for |V| iterations, O(|V|)
    		for(int j = 0; j < neighbors.get(i).size(); j++) {	// Runs for |E| iterations, 
																// because every vertex has a certain amount of edges which sums up to |E| in total. O(|E|)
    			currentVertex = neighbors.get(i).get(j);	// O(1)
    			currentDegOut = neighbors.get(currentVertex).size() + this.degpath.get(i);	// O(1)
    			if(this.degpath.get(currentVertex) < currentDegOut) { // O(1)
    				this.degpath.put(currentVertex, currentDegOut);	// O(1)
    			}
    			if(currentDegOut > this.maxDegOut) { // O(1)
    				this.maxDegOut = currentDegOut;	// O(1)
    			}	
    		}
    	}
	}
}