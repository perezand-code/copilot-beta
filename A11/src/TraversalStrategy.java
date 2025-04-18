import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * TraversalStrategy interface defines the methods required for
 * a traversal strategy. These are start, hasNext, next, and visit.
 */
public interface TraversalStrategy<V> {
    void start(@NotNull V start);
    boolean hasNext();
    @NotNull V next();
    @NotNull Progress visit(@NotNull V current, @NotNull Collection<V> neighbors);
}
