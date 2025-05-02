package traversals;
import util.Progress;
import java.util.*;

public class GoalDirectedBFS<V> implements TraversalStrategy<V> {
    private final V start, goal;
    private final Queue<V> toVisit = new LinkedList<>();
    private final Set<V> visited = new HashSet<>();
    private final Map<V, V> parent = new HashMap<>();
    private List<V> path = List.of();

    public GoalDirectedBFS(V start, V goal) {
        this.start   = start;
        this.goal    = goal;
    }

    public void start(V s) {
        toVisit.add(s);
    }

    public boolean hasNext() {
        return !toVisit.isEmpty();
    }

    public V next() { return toVisit.poll(); }

    public List<V> getPath() {
        return path;
    }

    public Progress visit(V current, Collection<V> neighbors) {
        if (current.equals(goal)) {
            this.path = reconstructVertexPath();
            return Progress.STOP;
        }
        else if (visited.add(current)) {
            for (V neighbor : neighbors) {
                if (!visited.contains(neighbor) && !parent.containsKey(neighbor)) {
                    toVisit.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }
        return Progress.CONTINUE;
    }

    public List<V> reconstructVertexPath() {
        LinkedList<V> path = new LinkedList<>();
        for (V at = goal; at != null; at = parent.get(at)) {
            path.addFirst(at);
            if (at.equals(start)) break;
        }
        return path;
    }

}
