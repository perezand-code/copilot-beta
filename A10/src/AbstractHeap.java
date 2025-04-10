import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.swap;

public abstract class AbstractHeap<E extends Comparable<E>> {
    protected @NotNull List<HeapNode<E>> nodes;
    protected int size;

    protected AbstractHeap() {
        nodes = List.of();
        size = 0;
    }

    abstract @NotNull HeapNode<E> getParent(@NotNull HeapNode<E> node) throws NoParentE;
    abstract @NotNull List<HeapNode<E>> getChildren(@NotNull HeapNode<E> node) throws NoChildE;

    int getSize() {
        return size;
    }

    /**
     *  getminchild Returns the minimum child of the given node or throws NoChildE if the node has
     * no children.
     */
    @NotNull HeapNode<E> getMinChild(@NotNull HeapNode<E> elem) throws NoChildE {
        throw new Error("TODO: Implement getMinChild");
    }

    /**
     * swapNodes Swaps the two nodes in the heap. It is important to note that this method
     * must update the indices of the nodes as well.
     */
    void swapNodes (@NotNull HeapNode<E> a, @NotNull HeapNode<E> b) {
        throw new Error("TODO: Implement swapNodes");
    }

    /**
     * move up Moves the given node up in the heap until it is in the correct position
     * according to the heap property.
     */
    void moveUp(@NotNull HeapNode<E> elem) {
        throw new Error("TODO: Implement moveUp");
    }

    /**
     * reduce value Reduces the value of the given node and moves it up in the heap until it is
     * in the correct position according to the heap property.
     */
    void reduceValue (@NotNull HeapNode<E> elem, @NotNull E newValue) {
        throw new Error("TODO: Implement reduceValue");
    }

    /**
     * movedown Moves the given node down in the heap until it is in the correct position
     * according to the heap property.
     */
    void moveDown(@NotNull HeapNode<E> elem) {
        throw new Error("TODO: Implement moveDown");
    }

    /**
     * getmin() Returns the minimum element in the heap without removing it. Throws
     * EmptyE if the heap is empty.
     */
    @NotNull HeapNode<E> getMin() throws EmptyE {
        throw new Error("TODO: Implement getMin");
    }

    /**
     * insert Inserts the given node into the heap. The node must not already be in the
     * heap so its fields 'index' and 'heap' must be properly initialized at this point.
     * The node is added to the end of the heap and then moved up to its
     * correct position.
     */
    void insert(@NotNull HeapNode<E> elem) {
        throw new Error("TODO: Implement insert");
    }

    /**
     * removemin() Removes the minimum element from the heap and returns it. But first,
     * the element at index 0 in the array cannot be left empty: it must be updated to
     * contain the new minimum. To do that, the last node in the array is moved to the
     * first position, and then we invoke moveDown on it. This will ensure that the
     * heap property is maintained. The method returns the minimum element that was
     * removed.
     */
    @NotNull HeapNode<E> removeMin() throws EmptyE {
        throw new Error("TODO: Implement removeMin");
    }
}
