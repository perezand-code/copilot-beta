package traversals;
import util.Progress;
import java.util.*;

public class CycleDetectStrategy<V> implements TraversalStrategy<V> {
    private final Stack<Frame<V>> toVisit = new Stack<>();
    private final Set<V> visited = new HashSet<>();
    private final List<V> inPath = new ArrayList<>();
    private Frame<V> currentFrame = new Frame<>();

    private static class Frame<V> {
        V node;
        boolean isMarker;
        Frame() {}
        Frame(V node) { this.node = node; }
        Frame(V node, boolean m) { this.node = node; isMarker = m; }
        void setMarker() { isMarker = true; }
        boolean isMarker() { return isMarker; }
    }

    public void start(V start) {
        toVisit.push(new Frame<>(start));
    }

    public boolean hasNext() {
        return !toVisit.isEmpty();
    }

    public V next() {
        this.currentFrame = toVisit.pop();
        return this.currentFrame.node;
    }

    public List<V> getCycle() {
        return inPath;
    }

    public Progress visit(V current, Collection<V> neighbors) {
        if (currentFrame.isMarker()) inPath.remove(current);
        else if (inPath.contains(current)) {
                inPath.add(current);
                return Progress.STOP;
        }
        else if (visited.add(current)) {
            inPath.add(current);
            toVisit.push(new Frame<>(current, true));
            for (V neighbor : neighbors) {
                toVisit.push(new Frame<>(neighbor));
            }
        }
        return Progress.CONTINUE;
    }
}
