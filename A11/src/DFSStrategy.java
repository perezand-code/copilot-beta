import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Depth-First Search (DFS) strategy. Uses a stack to explore nodes. The
 * method 'start' initializes the traversal with a starting node. The
 * method 'hasNext' checks if there are more nodes to visit. The
 * method 'next' retrieves the next node to visit. The method 'getResult'
 * returns the list of visited nodes. The method 'visit' processes the current
 * node and its neighbors as follows. If the current node has already been
 * visited, it continues to the next node. Otherwise, it adds the current node
 * to the result list and adds its neighbors to the stack. And then indicates
 * the traversal should continue.
 */
public class DFSStrategy<V> implements TraversalStrategy<V> {
    private final @NotNull Stack<V> toVisit;
    private final @NotNull Set<V>   visited;
    private final @NotNull List<V>  result;

    public DFSStrategy() {
        this.toVisit = new Stack<>();
        this.visited = new HashSet<>();
        this.result  = new ArrayList<>();
    }

    public void start(@NotNull V start) {
        toVisit.push(start);
    }
    public boolean hasNext()            {
        return !toVisit.isEmpty();
    }
    public @NotNull V next() {
        return toVisit.pop();
    }
    public @NotNull List<V> getResult() {
        return result;
    }

    public @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors) {
        if (!visited.add(current)) {
            return Progress.CONTINUE;
        }
        else {
            result.add(current);
            toVisit.addAll(neighbors);
            return Progress.CONTINUE;
        }
    }
}
