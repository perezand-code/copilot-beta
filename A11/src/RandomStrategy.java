import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * RandomStrategy simulates a random walk through the graph. This is a
 * relatively simple strategy that randomly selects a neighbor of the current
 * node to visit next. It continues until there are no more nodes to visit
 * or until we reach a maximum number of steps.
 */
public class RandomStrategy<V> implements TraversalStrategy<V> {
    private final @NotNull DirectedGraph<V> graph;
    private final @NotNull Stack<V>         toVisit;
    private final @NotNull List<V>          result;
    private final int                       maxSteps;
    private int                             steps;

    public RandomStrategy(int maxSteps, @NotNull DirectedGraph<V> graph) {
        this.graph    = graph;
        this.toVisit  = new Stack<>();
        this.result   = new ArrayList<>();
        this.maxSteps = maxSteps;
        this.steps    = 0;
    }

    public void start(@NotNull V start) { toVisit.push(start); }
    public boolean hasNext()            { return !toVisit.isEmpty() && steps < maxSteps; }
    public @NotNull V next()            { return toVisit.pop(); }
    public @NotNull List<V> getResult() { return result; }

    /**
     * Adds the current node to the result list and randomly selects a neighbor
     * to visit next.
     */
    public @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors) {
        steps++;
        result.add(current);
        graph.getRandomNeighbor(neighbors).ifPresent(toVisit::push);
        return Progress.CONTINUE;
    }
}
