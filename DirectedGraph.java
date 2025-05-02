package graphs;
import traversals.*;
import java.util.*;

public class DirectedGraph<V> {
    private final Map<V, Set<V>> adjacencyList = new HashMap<>();

    public void addVertex(V v) {
        adjacencyList.putIfAbsent(v, new HashSet<>());
    }

    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(to);
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    public Set<V> getNeighbors(V v) {
        return adjacencyList.getOrDefault(v, new HashSet<>());
    }

    public Optional<V> getRandomNeighbor(Collection<V> neighbors) {
        List<V> list = new ArrayList<>(neighbors);
        Collections.shuffle(list);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.getFirst());
    }

    public boolean hasEdge(V from, V to) {
        return adjacencyList.containsKey(from) && adjacencyList.get(from).contains(to);
    }

    /**
     * TODO
     * This method should return a new directed graph with the same vertices and edges,
     * but with the direction of the edges reversed.
     */
    public DirectedGraph<V> reverse() {
        DirectedGraph<V> reversed = new DirectedGraph<>();
        for (V from : adjacencyList.keySet()) {
            for (V to : adjacencyList.get(from)) {
                reversed.addEdge(to, from);
            }
        }
        return reversed;
    }
    public void traverse(V start, TraversalStrategy<V> strategy) {
        strategy.start(start);
        while (strategy.hasNext()) {
            V current = strategy.next();
            if (strategy.visit(current, getNeighbors(current)).stop()) break;
        }
    }

    public List<V> dfs(V start) {
        DFSStrategy<V> strategy = new DFSStrategy<>();
        traverse(start, strategy);
        return strategy.getResult();
    }

    public List<V> bfs(V start) {
        BFSStrategy<V> strategy = new BFSStrategy<>();
        traverse(start, strategy);
        return strategy.getResult();
    }

    public List<V> randomWalk(V start, int maxSteps) {
        RandomStrategy<V> strategy = new RandomStrategy<>(this, maxSteps);
        traverse(start, strategy);
        return strategy.getResult();
    }

    public Map<V, Integer> randomWalkWithFrequency(V start, int numWalks, int maxSteps) {
        Map<V, Integer> frequency = new HashMap<>();
        for (int i = 0; i < numWalks; i++) {
            RandomStrategy<V> strategy = new RandomStrategy<>(this, maxSteps);
            traverse(start, strategy);
            for (V v : strategy.getResult()) {
                frequency.put(v, frequency.getOrDefault(v, 0) + 1);
            }
        }
        return frequency;
    }

    public List<V> findBFSPath(V start, V goal) {
        GoalDirectedBFS<V> strategy = new GoalDirectedBFS<>(start, goal);
        traverse(start, strategy);
        return strategy.getPath();
    }

    public List<V> findCycle(V start) {
        CycleDetectStrategy<V> strategy = new CycleDetectStrategy<>();
        traverse(start, strategy);
        return strategy.getCycle();
    }
}