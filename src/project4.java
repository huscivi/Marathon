import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class project4 {
	 public static void main(String[] args) throws FileNotFoundException, IOException  {
		 	 		 
		String input= args[0];	
		String output = args[1];
			
		Graph dijkstraGraph= new Graph();
	    KruskalAlgorithm kruskalGraph = new KruskalAlgorithm();

		File Obj = new File(input); 
		Scanner Reader = new Scanner(Obj); 
		
	    int numOfNodes = Integer.parseInt(Reader.nextLine());   // I read the first line of the input and created the nodes
	    for(int i=0; i<numOfNodes; i++) {
	    	String s = "s" + Integer.toString(i);
	    	dijkstraGraph.addNode(s);
	    }	    
	    
	    int numOfFlaggedNodes = Integer.parseInt(Reader.nextLine());  // I read the 2nd line of the input and found the number of flags.
	    
	    String data = Reader.nextLine();   // I read the 3rd line and recorded which two nodes we will find the distance between.
        String [] twoNodes = data.split(" ");    

        String data2 = Reader.nextLine();   // 
        String [] flagNodes = data2.split(" ");  

	    
	    while (Reader.hasNextLine()) {  // I read the distance between the nodes in the input and placed it on the graph.
			String row = Reader.nextLine(); 
            String [] rowElements = row.split(" "); 
            
            int numOfEdges= (rowElements.length - 1) / 2 ;

            for(int i=0; i<numOfEdges;i++) {
            	dijkstraGraph.addEdge(rowElements[0] ,rowElements[1+2*i] , Integer.parseInt(rowElements[2+2*i]));
            }          
	    }	    
    
	    int output1 = dijkstraGraph.distanceBetween(twoNodes[0], twoNodes[1]).get(twoNodes[1]);  // I found the shortest path between two nodes using dijkstra
        
	    List<EdgeClass> edges = new LinkedList<>(); // I kept the shortest distances between flagged nodes in edges.
	    
	    for(int i=0; i<numOfFlaggedNodes-1; i++) {   // I calculated the distance of flagged nodes to other nodes using djkstra. 	
	    	Map<String, Integer> value= dijkstraGraph.distanceBetween(flagNodes[i], flagNodes[i]);

	    	
	    	for(int j=i+1;j<numOfFlaggedNodes;j++)  {	   		
	    		EdgeClass edge = new EdgeClass(i, j, value.get(flagNodes[j]));
	    		edges.add(edge); // I created a minimum spanning tree and saved its edges.
	    	}
	    }
	    
	    List<EdgeClass> kruskalEdges= kruskalGraph.minSpanningTree(edges, flagNodes.length); 
	    // I calculated the distance between the flagged nodes using a kruskal with a minimum spanning tree. 
	    
	    int output2=0;
	    for(int i=0;i<kruskalEdges.size();i++)  {  // I got the result by summing the distances between the flagged nodes
	    	output2+=kruskalEdges.get(i).getNodeWeight();
	    }
	        
	    FileWriter myWriter = new FileWriter(output, false);  // I printed the results to the file.
	    myWriter.write(Integer.toString(output1));
	    myWriter.write("\n");
	    myWriter.write(Integer.toString(output2));  	 

		Reader.close();
		myWriter.close();

	    }
}