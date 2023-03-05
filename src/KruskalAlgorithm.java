import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {
    
    static class Subset {  // The Subset class stores the name of a node's parent and how many nodes it contains.
        int nodeName;
        int numofNodes;
    }

    public int detect(Subset subsets[], int i) {  //This function is used to detect the parent of the node in the subsets array.
        if (subsets[i].nodeName != i)  {
            subsets[i].nodeName = detect(subsets, subsets[i].nodeName);
        }

        return subsets[i].nodeName;
    }

    public void merge(Subset subsets[], int node1, int node2) { // This function checks if two nodes are in the same parent,
    	                                                        //and if they are not in the same parent, it merges the parents of the nodes.
        int firstRoot = detect(subsets, node1);
        int secondRoot = detect(subsets, node2);

        if (subsets[firstRoot].numofNodes < subsets[secondRoot].numofNodes)
            subsets[firstRoot].nodeName = secondRoot;
        
        
        else if (subsets[firstRoot].numofNodes > subsets[secondRoot].numofNodes)
            subsets[secondRoot].nodeName = firstRoot;


        else {
            subsets[secondRoot].nodeName = firstRoot;
            subsets[firstRoot].numofNodes++;
        }
    }

 // The  function to construct Minimum Spanning Tree using Kruskal's algorithm
    public List<EdgeClass> minSpanningTree(List<EdgeClass> edges, int numofVertices) {
        Collections.sort(edges);  // Sort all the edges in non-decreasing order of their weight


        Subset subsets[] = new Subset[numofVertices];    // Array for creating subsets
        for (int i = 0; i < numofVertices; ++i) {
            subsets[i] = new Subset();
            subsets[i].nodeName = i;
            subsets[i].numofNodes = 0;
        }

        List<EdgeClass> minSpanningTree = new ArrayList<>();
        int a = 0; // An index variable, used for result[]
        int b = 0; // An index variable, used for sorted edges

        // Number of edges to be taken is equal to number of vertices - 1
        while (a < numofVertices - 1) {

            EdgeClass otherEdge = edges.get(b++);

            int x = detect(subsets, otherEdge.getFromNode());
            int y = detect(subsets, otherEdge.getToNode());

            // If this edge does not cause a cycle ,include it in result and increase the index of result for next edge
            if (x != y) {
                minSpanningTree.add(otherEdge);
                a++;
                merge(subsets, x, y);
            }
        }
        
        return minSpanningTree;
    }
    
}