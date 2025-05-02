package traversals;
import graphs.WeightedDirectedGraph;
import heap.MinHeap;
import heap.BinaryMinHeap;
import heap.HeapNode;
import util.*;
import java.util.*;

public class DijkstraStrategy<V> implements TraversalStrategy<V> {
    private final WeightedDirectedGraph<V> graph;
    private final V goal;
    private final MinHeap<Weight> heap = new BinaryMinHeap<>();
    private final Map<V, HeapNode<Weight>> nodeMap = new HashMap<>();
    private final Map<HeapNode<Weight>, V> reverseNodeMap = new HashMap<>();
    private final Map<V, Edge<V>> parentMap = new HashMap<>();

    public DijkstraStrategy(WeightedDirectedGraph<V> graph, V goal) {
        this.graph = graph;
        this.goal = goal;
    }

    public void start(V start) {
        for (V v : graph.getVertices()) {
            Weight dist = v.equals(start) ? Weight.ZERO : Weight.infinity();
            BinaryMinHeap.Heap2Node<Weight> node = new BinaryMinHeap.Heap2Node<>(dist);
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
        if (current.equals(goal)) return Progress.STOP;
        Weight currentDist = nodeMap.get(current).getValue();
        for (V neighbor : neighbors) {
            Weight weight = graph.getWeightedNeighbors(current).get(neighbor);
            Weight newDist = currentDist.plus(weight);
            HeapNode<Weight> neighborNode = nodeMap.get(neighbor);
            if (newDist.compareTo(neighborNode.getValue()) < 0) {
                heap.reduceValue(neighborNode, newDist);
                parentMap.put(neighbor, new Edge<>(current, neighbor, weight));
            }
        }
        return Progress.CONTINUE;
    }

    public Optional<EdgePath<V>> getPath () {
        HeapNode<Weight> destNode = nodeMap.get(goal);
        if (destNode.getValue().isInfinite()) return Optional.empty();
        return Optional.of(reconstructEdgePath());
    }

    public EdgePath<V> reconstructEdgePath() {
        LinkedList<Edge<V>> path = new LinkedList<>();
        for (V at = goal; parentMap.containsKey(at); at = parentMap.get(at).from()) {
            path.addFirst(parentMap.get(at));
        }
        return new EdgePath<>(path);
    }
}
