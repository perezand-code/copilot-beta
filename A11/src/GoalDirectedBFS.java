import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * GoalDirectedBFS finds a path from start to goal using BFS. The idea
 * is to use a regular BFS strategy but stop when we reach the goal. In addition,
 * in order to reconstruct the path, we need to keep track of the parent of each node.
 * The parent of a node is the node from which we reached it. This way, when we
 * reach the goal, we can reconstruct the path by following the parent pointers.
 */
public class GoalDirectedBFS<V> implements TraversalStrategy<V> {
    private final @NotNull Queue<V>  toVisit;
    private final @NotNull Set<V>    visited;
    private final @NotNull Map<V, V> parent;
    private final @NotNull V         start,  goal;
    private @NotNull List<V>         path;

    public GoalDirectedBFS(@NotNull V start, @NotNull V goal) {
        this.toVisit = new LinkedList<>();
        this.visited = new HashSet<>();
        this.parent  = new HashMap<>();
        this.start   = start;
        this.goal    = goal;
        this.path    = List.of();
    }

    public void start(@NotNull V s) {
        throw new Error("Not implemented");
    }

    public boolean hasNext() {
        throw new Error("Not implemented");
    }

    public @NotNull V next() {
        throw new Error("Not implemented");
    }

    public @NotNull List<V> getPath() {
        throw new Error("Not implemented");
    }

    /**
     * Visits the current node and its neighbors. If the current node has already been
     * visited, it continues to the next node. Otherwise, if the current node is the goal,
     * it reconstructs the path and stops the traversal. If the current node is not the goal,
     * we add its neighbors to the queue but only if they have not been visited, and
     * they are not already in the parent map. This way, we avoid adding the same node
     * multiple times to the queue, and more importantly, we ensure that the parent
     * map only contains the first time we reach a node.
     */
    public @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors) {
        throw new Error("Not implemented");
    }

    private void reconstructPath(@NotNull V current) {
        LinkedList<V> p = new LinkedList<>();
        while (!current.equals(start)) {
            p.addFirst(current);
            current = parent.get(current);
        }
        p.addFirst(start);
        this.path = p;
    }
}
