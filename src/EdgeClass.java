public class EdgeClass implements Comparable<EdgeClass> {  // i created this class for edges in kruskal algorithm
        private int fromNode, toNode, nodeWeight;
        
        EdgeClass(int fromNode,int toNode, int edgeWeight) {
        	this.fromNode=fromNode;
        	this.toNode= toNode;
        	this.nodeWeight= edgeWeight;
        }

        public int compareTo(EdgeClass compareEdge) {
            return this.nodeWeight - compareEdge.nodeWeight;
        }

		public int getFromNode() {
			return fromNode;
		}

		public void setFromNode(int FromNode) {
			this.fromNode = toNode;
		}

		public int getToNode() {
			return toNode;
		}

		public void setDestination(int destination) {
			this.toNode = destination;
		}

		public int getNodeWeight() {
			return nodeWeight;
		}

		public void setWeight(int nodeWeight) {
			this.nodeWeight = nodeWeight;
		}
    }