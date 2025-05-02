package traversals;
import util.Progress;
import java.util.Collection;

public interface TraversalStrategy<V> {
    void start(V start);
    boolean hasNext();
    V next();
    Progress visit(V current, Collection<V> neighbors);
}
