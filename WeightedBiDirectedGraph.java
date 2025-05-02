package graphs;
import traversals.MSTStrategy;
import util.Weight;
import util.Edge;
import java.util.Collection;

public class WeightedBiDirectedGraph<V> extends WeightedDirectedGraph<V> {

    public void addEdge(V from, V to, Weight weight) {
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
    }

    public Collection<Edge<V>> minimumSpanningTree(V start) {
        MSTStrategy<V> strategy = new MSTStrategy<>(this);
        traverse(start, strategy);
        return strategy.getMSTEdges();
    }

}
