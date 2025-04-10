import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BinaryMinHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    public BinaryMinHeap() {
        nodes = new ArrayList<>();
        size = 0;
    }

    public BinaryMinHeap(@NotNull List<HeapNode<E>> elements) {
        nodes = new ArrayList<>(elements);
        size = elements.size();
        for (int i = 0; i < size; i++) {
            HeapNode<E> elem = elements.get(i);
            elem.setHeap(this);
            elem.setIndex(i);
        }
        for (int i = size / 2 - 1; i >= 0; i--) {
            moveDown(nodes.get(i));
        }
    }

    /**
     * getparent Gets the parent of the given element.
     */
    @NotNull HeapNode<E> getParent(@NotNull HeapNode<E> elem) throws NoParentE {
        throw new Error("TODO: Implement getParent");
    }

    /**
     * getChildren Gets the children of the given element in order of left to right.
     */
    @NotNull List<HeapNode<E>> getChildren(@NotNull HeapNode<E> elem) throws NoChildE {
        throw new Error("TODO: Implement getChildren");
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
        if (!(o instanceof BinaryMinHeap<?> that)) return false;
        return size == that.size && nodes.equals(that.nodes);
    }
}
