package recursive;
import graphs.DirectedGraph;
import java.util.*;

/**
 * This class implements the Kosaraju algorithm for finding strongly connected components.
 * A strongly connected component (SCC) is a maximal subgraph where every vertex is reachable
 * from every other vertex in the subgraph. The algorithm works is two passes:
 * <ul>
 * <li> Perform topological sort on the original graph starting from every vertex.
 * This will give a topological ordering of the vertices.
 * <li> Reverse the graph, clear the visited set, and perform a DFS on the
 * vertices in the order given by the topological sort. Each time a new vertex
 * is visited, a new strongly connected component is started.
 * </ul>
 */
public class SCC<V> extends DFS<V> {
    private final List<Set<V>> components;
    private Set<V> current;

    public SCC(DirectedGraph<V> g) {
        super(g);
        this.components = new ArrayList<>();
        this.current = new HashSet<>();
    }

    /**
     * TODO
     * Computes the strongly connected components of the graph as explained above. The
     * first pass is done by a new instance of TopologicalSort. The second pass is done
     * by this class by overriding some of the abstract methods of the DFS class.
     */
    public List<Set<V>> computeSCCs() {
        DirectedGraph<V> reversed = graph.reverse();
        List<V> order = new TopologicalSort<>(graph).getSorted();
        Set<V> visited = new HashSet<>();
        List<Set<V>> components = new ArrayList<>();

        for (V vertex : order) {
            if (!visited.contains(vertex)) {
                Set<V> component = new HashSet<>();
                dfs(reversed, vertex, visited, component);
                components.add(component);
            }
        }
        return components;
    }
    private void dfs(DirectedGraph<V> graph, V vertex, Set<V> visited, Set<V> component) {
        visited.add(vertex);
        component.add(vertex);
        for (V neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, component);
            }
        }
    }
}
