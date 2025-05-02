package graphs;
import traversals.DijkstraStrategy;
import util.Weight;
import util.Edge;
import util.EdgePath;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WeightedDirectedGraph<V> extends DirectedGraph<V> {
    private final Map<V, Map<V, Weight>> weights = new HashMap<>();

    public void addEdge (Edge<V> edge) {
        addEdge(edge.from(), edge.to(), edge.weight());
    }

    public void addEdge(V from, V to, int weight) {
        addEdge(from, to, Weight.of(weight));
    }

    public void addEdge(V from, V to, Weight weight) {
        super.addEdge(from, to);
        weights.computeIfAbsent(from, k -> new HashMap<>()).put(to, weight);
    }

    public Map<V, Weight> getWeightedNeighbors(V v) {
        return weights.getOrDefault(v, new HashMap<>());
    }

    /**
     * TODO
     * The method first calculates the new weight by subtracting the given amount
     * from the current weight. If the new weight is non-positive (i.e., zero or negative),
     * it removes the edge from the graph. Otherwise, it updates the weight of the edge.
     */
    public void subtractEdgeWeight(Edge<V> edge, Weight amount) {
        throw new Error("TODO: Not implemented yet");
    }

    /**
     * TODO
     * The method first checks if the edge exists in the graph. If it does not, then
     * it adds the edge with the given weight. If it does exist, it retrieves the current
     * weight of the edge and updates it by adding the given amount to it.
     */
    public void addEdgeWeight(Edge<V> edge, Weight amount) {
        throw new Error("TODO: Not implemented yet");
    }

    /**
     * TODO
     * The method returns a new directed graph with the same vertices and edges. It is
     * important to note that the new graph should not share any references with the original graph.
     * This means that if you modify the new graph, it should not affect the original graph and
     * vice versa.
     */
    public WeightedDirectedGraph<V> copy() {
        throw new Error("TODO: Not implemented yet");
    }

    public Optional<EdgePath<V>> shortestWeightedPath(V start, V goal) {
        DijkstraStrategy<V> strategy = new DijkstraStrategy<>(this, goal);
        traverse(start, strategy);
        return strategy.getPath();
    }
}