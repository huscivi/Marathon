import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
    private HashMap<String, Map<String, Integer>> nodes = new HashMap<>();  // I kept nodes in the map and their distance from neighboring nodes and those nodes.

    private class Pair {  // I defined this class to compare nodes in dijkstra.
        int distance;
        String node;

        Pair(int distance, String node) {
            this.distance = distance;
            this.node = node;
        }
    }
    
    
    public void addNode(String name) {  // I added a node and the edges to which that node belongs
        nodes.put(name, new HashMap<>());
    }

    public void addEdge(String fromNode, String toNode, int weight) {  // I created the edge to be bidirectional.
       
        nodes.get(fromNode).put(toNode, weight);  
        nodes.get(toNode).put(fromNode, weight);

    }

    public Map<String, Integer> distanceBetween(String start, String end) {  // I wrote the dijkstra algorithm inside this method
    	// This method returns the shortest distance of the node it takes as the first parameter to other nodes in the map.
    	
        Map<String, Integer> distances = new HashMap<>();  // I created a map that I will return.
        for (String node : nodes.keySet()) {
            distances.put(node, Integer.MAX_VALUE);  // I made the distance of each node equal to infinity as per the algorithm.
        }
        distances.put(start, 0);  // I set the distance from the first node to 0 and started navigating the nodes.
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        heap.offer(new Pair(0, start));
        while (!heap.isEmpty()) {
            Pair pair = heap.poll();
            int distance = pair.distance;
            String current = pair.node;
            if (distance > distances.get(current)) {
                continue;
            }
            for (String neighbor : nodes.get(current).keySet()) {  // This code traverses the neighbors of a node in a graph and updates their distances.
                int newDistance = distance + nodes.get(current).get(neighbor);
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    heap.offer(new Pair(newDistance, neighbor));
                }
            
            }
        }
        return distances;
    }


    
    
}