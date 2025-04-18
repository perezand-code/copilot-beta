import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * DirectedGraph represents a directed graph using an adjacency list. We have one
 * generic traversal strategy interface and several implementations for DFS, BFS,
 * random walks, and cycle detection.
 */
public class DirectedGraph<V> {
    private final @NotNull Map<V, Set<V>> adjacencyList;

    /**
     * Constructor initializes an empty graph.
     */
    public DirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph. If the vertex already exists, it does nothing.
     */
    public void addVertex(@NotNull V v) {
        adjacencyList.putIfAbsent(v, new HashSet<>());
    }

    /**
     * Adds a directed edge from 'from' to 'to'. If either vertex does not exist, it adds them.
     */
    public void addEdge(@NotNull V from, @NotNull V to) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(to);
    }

    /**
     * Gets the neighbors of a vertex.
     */
    public @NotNull Set<V> getNeighbors(@NotNull V v) {
        return adjacencyList.getOrDefault(v, Set.of());
    }

    /**
     * Picks a random neighbor from the collection of neighbors. If the collection is empty,
     * it returns an empty Optional.
     */
    public @NotNull Optional<V> getRandomNeighbor(@NotNull Collection<V> neighbors) {
        List<V> list = new ArrayList<>(neighbors);
        Collections.shuffle(list);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.getFirst());
    }

    /**
     * Traverses the graph starting from 'start' using the provided strategy.
     * Each strategy must implement the TraversalStrategy interface which provides
     * a method start to initialize the traversal, hasNext to check if there are
     * more nodes to visit, next to get the next node, and visit to process the
     * current node and its neighbors and decide whether to continue.
     */
    public void traverse(@NotNull V start, @NotNull TraversalStrategy<V> strategy) {
        strategy.start(start);
        while (strategy.hasNext()) {
            V current = strategy.next();
            if (strategy.visit(current, getNeighbors(current)).stop()) {
                break; // allow early termination
            }
        }
    }

    // -------------------------------------------------------------------------------
    // Entry points for various common traversals
    // -------------------------------------------------------------------------------

    /**
     * Depth-First Search (DFS) traversal starting from 'start'.
     */
    public @NotNull List<V> dfs(@NotNull V start) {
        DFSStrategy<V> strategy = new DFSStrategy<>();
        traverse(start, strategy);
        return strategy.getResult();
    }

    /**
     * Breadth-First Search (BFS) traversal starting from 'start'.
     */
    public @NotNull List<V> bfs(@NotNull V start) {
        BFSStrategy<V> strategy = new BFSStrategy<>();
        traverse(start, strategy);
        return strategy.getResult();
    }

    /**
     * Random walk traversal starting from 'start' with a maximum number of steps.
     */
    public @NotNull List<V> randomWalk(@NotNull V start, int maxSteps) {
        RandomStrategy<V> strategy = new RandomStrategy<>(maxSteps, this);
        traverse(start, strategy);
        return strategy.getResult();
    }

    /**
     * Performs multiple random walks from 'start' and returns a frequency map of visited nodes.
     */
    public @NotNull Map<V, Integer> randomWalkWithFrequency(@NotNull V start, int numWalks, int maxSteps) {
        Map<V, Integer> frequency = new HashMap<>();
        for (int i = 0; i < numWalks; i++) {
            RandomStrategy<V> strategy = new RandomStrategy<>(maxSteps, this);
            traverse(start, strategy);
            for (V v : strategy.getResult()) {
                frequency.put(v, frequency.getOrDefault(v, 0) + 1);
            }
        }
        return frequency;
    }

    /**
     * Finds a path from 'start' to 'goal' using a goal-directed BFS strategy.
     */
    public @NotNull List<V> findPath(@NotNull V start, @NotNull V goal) {
        GoalDirectedBFS<V> strategy = new GoalDirectedBFS<>(start, goal);
        traverse(start, strategy);
        return strategy.getPath();
    }

    /**
     * Checks if there is a cycle in the graph starting from 'start'.
     */
    public List<V> findCycle(@NotNull V start) {
        CycleDetectStrategy<V> strategy = new CycleDetectStrategy<>();
        traverse(start, strategy);
        return strategy.getCycle();
    }
}