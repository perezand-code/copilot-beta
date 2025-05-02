package traversals;
import graphs.WeightedBiDirectedGraph;
import heap.MinHeap;
import heap.BinaryMinHeap;
import heap.HeapNode;
import util.Weight;
import util.Edge;
import util.Progress;
import java.util.*;

public class MSTStrategy<V> implements TraversalStrategy<V> {
    private final WeightedBiDirectedGraph<V> graph;
    private final MinHeap<Weight> heap = new BinaryMinHeap<>();
    private final Map<V, HeapNode<Weight>> nodeMap = new HashMap<>();
    private final Map<HeapNode<Weight>, V> reverseNodeMap = new HashMap<>();
    private final Map<V, Edge<V>> parentMap = new HashMap<>();
    private final Set<V> visited = new HashSet<>();

    public MSTStrategy(WeightedBiDirectedGraph<V> graph) {
        this.graph = graph;
    }

    public void start(V start) {
        for (V v : graph.getVertices()) {
            Weight dist = v.equals(start) ? Weight.ZERO : Weight.infinity();
            HeapNode<Weight> node = new BinaryMinHeap.Heap2Node<>(dist);
            heap.insert(node);
            nodeMap.put(v, node);
            reverseNodeMap.put(node, v);
        }
    }

    public boolean hasNext() {
        return heap.getSize() > 0;
    }

    public V next() {
        try {
            HeapNode<Weight> minNode = heap.removeMin();
            return reverseNodeMap.get(minNode);
        }
        catch (MinHeap.EmptyHeapExc _ ) {
            throw new Error("next() should not be called when hasNext() is false");
        }
    }

    public Progress visit(V current, Collection<V> neighbors) {
        if (visited.add(current)) {
            for (V neighbor : neighbors) {
                if (visited.contains(neighbor)) continue;
                Weight newCost = graph.getWeightedNeighbors(current).get(neighbor);
                HeapNode<Weight> neighborNode = nodeMap.get(neighbor);
                if (newCost.compareTo(neighborNode.getValue()) < 0) {
                    heap.reduceValue(neighborNode, newCost);
                    parentMap.put(neighbor, new Edge<>(current, neighbor, newCost));
                }
            }
        }
        return Progress.CONTINUE;
    }

    public Collection<Edge<V>> getMSTEdges() {
        return parentMap.values();
    }
}
