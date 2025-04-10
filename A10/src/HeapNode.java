import org.jetbrains.annotations.NotNull;

public abstract class HeapNode<E extends Comparable<E>> implements Comparable<HeapNode<E>> {
    protected AbstractHeap<E> heap;
    protected @NotNull E value;
    protected int index;

    public HeapNode(@NotNull E value) {
        this.value = value;
    }

    void setHeap(@NotNull AbstractHeap<E> heap) {
        this.heap = heap;
    }

    void setValue(@NotNull E value) {
        this.value = value;
    }

    void setIndex(int index) {
        this.index = index;
    }

    public @NotNull AbstractHeap<E> getHeap() {
        return heap;
    }

    public @NotNull E getValue() {
        return value;
    }

    int getIndex() {
        return index;
    }

    public int compareTo(@NotNull HeapNode<E> other) {
        return value.compareTo(other.value);
    }

    public @NotNull String toString() {
        return String.format("%s@(%d)", value, index);
    }
}
