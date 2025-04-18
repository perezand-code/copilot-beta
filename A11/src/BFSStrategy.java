import org.jetbrains.annotations.NotNull;
import java.util.*;

/**
 * Breadth-First Search (BFS) strategy. Uses a queue to explore nodes.
 * The method 'start' initializes the traversal with a starting node.
 * The method 'hasNext' checks if there are more nodes to visit.
 * The method 'next' retrieves the next node to visit.
 * The method 'getResult' returns the list of visited nodes.
 * The method 'visit' processes the current node and its neighbors as
 * follows. If the current node has already been visited, it continues to the next node.
 * Otherwise, it adds the current node to the result list and adds its neighbors to the queue.
 * And then indicates the traversal should continue.
 */
public class BFSStrategy<V> implements TraversalStrategy<V> {
    private final @NotNull Queue<V> toVisit;
    private final @NotNull Set<V>   visited;
    private final @NotNull List<V>  result;

    public BFSStrategy() {
        this.toVisit = new LinkedList<>();
        this.visited = new HashSet<>();
        this.result  = new ArrayList<>();
    }

    public void start(@NotNull V start)  { throw new Error("Not implemented"); }
    public boolean hasNext()             { throw new Error("Not implemented"); }
    public @NotNull V next()             { throw new Error("Not implemented"); }
    public @NotNull List<V> getResult()  { throw new Error("Not implemented"); }

    public @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors) {
        throw new Error("Not implemented");
    }
}
