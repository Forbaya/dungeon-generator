package gen;


import utils.ArrayList;

public class Graph {
    private int nodes;
    private ArrayList[] adjListArray;

    public Graph(int nodes) {
        this.nodes = nodes;
        adjListArray = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjListArray[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
    }

    public int getNodes() {
        return nodes;
    }
}
