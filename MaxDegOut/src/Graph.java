import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Amit Levy 313268773, Maya Shternlicht 308341411
 * 
 */
public class Graph {
	
	HashMap<Integer,List<Integer>> graph = new HashMap<Integer,List<Integer>>();
	
	public void buildGraph(int vertices, List<VerticesPair> edges) { // Runs in O(|V|+|E|)
    	for(int i = 1; i<= vertices; i++) { // Runs for |V| itterations, creates an array in the hashmap for every vertex. O(|V|)
    		this.graph.put(i, new ArrayList<Integer>()); // O(1)
    	}
    	for(VerticesPair edge : edges) { // Runs for |E| itterations, for every edge in E, mapping it in the correct order in the graph. O(|E|)
    		this.graph.get(edge.GetFirst()).add(edge.GetSecond()); // O(1)
    	}
	}
	
	public HashMap<Integer,List<Integer>> getGraph(){ // O(1)
		return this.graph; // O(1)
	}
}
