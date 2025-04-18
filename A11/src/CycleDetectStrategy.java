import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * CycleDetectStrategy detects cycles in the graph.
 * It uses a depth-first search (DFS) approach to traverse the graph that
 * is modified to detect cycles. The main issue is that nodes may be
 * revisited for different reasons, such as being part of a cycle or
 * simply being reachable through different paths. To handle this,
 * the strategy uses a list 'inPath' to keep track of the nodes that we
 * started visiting but have not yet finished visiting.
 */
public class CycleDetectStrategy<V> implements TraversalStrategy<V> {
    private final @NotNull Stack<Frame<V>> toVisit;
    private final @NotNull Set<V>          visited;
    private final @NotNull List<V>         inPath;
    private @NotNull Frame<V>              currentFrame;

    public CycleDetectStrategy() {
        this.toVisit      = new Stack<>();
        this.visited      = new HashSet<>();
        this.inPath       = new ArrayList<>();
        this.currentFrame = new EmptyFrame<>();
    }

    /**
     * Abstract class representing a frame in the stack. The frame
     * can be empty indicating we are done. It can also be a node frame
     * indicating we are visiting a node, or a marker indicating we
     * are done visiting a node.
     */
    private abstract static class Frame<V> {}
    private static class EmptyFrame<V> extends Frame<V> {}
    private static class NodeFrame<V> extends Frame<V> {
        final @NotNull V node;
        NodeFrame(@NotNull V node) { this.node = node; }
    }
    private static class Marker<V> extends Frame<V> {
        final @NotNull V node;
        Marker(@NotNull V node) { this.node = node; }
    }

    public void start(@NotNull V start) {
        throw new Error("Not implemented");
    }

    public boolean hasNext() {
        throw new Error("Not implemented");
    }

    /**
     * Returns the next node to visit. This method should only be called
     * when hasNext() is true. It will pop the current frame from the stack
     * and return the node from either a NodeFrame or Marker.
     */
    public @NotNull V next() {
        throw new Error("Not implemented");
    }

    public List<V> getCycle() {
        throw new Error("Not implemented");
    }

    /**
     * Visits the current node and its neighbors. This method can only be called
     * when hasNext() is true and when the current node is the same node that is
     * in the currentFrame. If the current node is a Marker it means that we are done
     * visiting the node, and we can remove it from the inPath list and continue
     * the traversal. If the current node is a NodeFrame it means that we are
     * visiting a new node. If the node is already in the inPath list, it means
     * that we have found a cycle. In this case, we add the node to the inPath
     * list and return Progress.STOP. If the node is not in the inPath list,
     * we add it to the inPath list and push a Marker for the current node
     * on the stack. Then we push all the neighbors of the current node
     * on the stack as NodeFrames.
     */
    public @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors) {
        throw new Error("Not implemented");
    }
}
