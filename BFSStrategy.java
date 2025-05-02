package traversals;
import util.Progress;
import java.util.*;

public class BFSStrategy<V> implements TraversalStrategy<V> {
    private final Queue<V> toVisit = new LinkedList<>();
    private final Set<V> visited = new HashSet<>();
    private final List<V> result = new ArrayList<>();

    public void start(V start) { toVisit.add(start); }
    public boolean hasNext() { return !toVisit.isEmpty(); }
    public V next() { return toVisit.poll(); }
    public List<V> getResult() { return result; }

    public Progress visit(V current, Collection<V> neighbors) {
        if (visited.add(current)) {
            result.add(current);
            toVisit.addAll(neighbors);
        }
        return Progress.CONTINUE;
    }
}
