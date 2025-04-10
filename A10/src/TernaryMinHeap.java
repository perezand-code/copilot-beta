import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TernaryMinHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    public TernaryMinHeap() {
        nodes = new ArrayList<>();
        size = 0;
    }

    public TernaryMinHeap(@NotNull List<HeapNode<E>> elements) {
        nodes = new ArrayList<>(elements);
        this.size = elements.size();
        for (int i = 0; i < size; i++) {
            HeapNode<E> elem = elements.get(i);
            elem.setHeap(this);
            elem.setIndex(i);
        }
        for (int i = size / 3 - 1; i >= 0; i--) {
            moveDown(nodes.get(i));
        }
    }

    /**
     * getParent Gets the parent of a node.
     */
    @NotNull HeapNode<E> getParent(@NotNull HeapNode<E> node) throws NoParentE {
        throw new Error("TODO: Implement getParent method");
    }

    /**
     * getChildren Gets the children of a node in order of left, middle, right.
     */
    @NotNull List<HeapNode<E>> getChildren(@NotNull HeapNode<E> node) throws NoChildE {
        throw new Error("TODO: Implement getChildren method");
    }

    public @NotNull String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(nodes.get(i)).append(" ");
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TernaryMinHeap<?> that)) return false;
        return size == that.size && nodes.equals(that.nodes);
    }

}
