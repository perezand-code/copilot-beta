package heap;

public abstract class HeapNode<E extends Comparable<E>> implements Comparable<HeapNode<E>> {
    protected E value;
    protected MinHeap<E> heap;
    protected int index;

    public HeapNode(E value) {
        this.value = value;
    }

    void setHeap(MinHeap<E> heap) {
        this.heap = heap;
    }

    void setValue(E value) {
        this.value = value;
    }

    void setIndex(int index) {
        this.index = index;
    }

    public E getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public int compareTo(HeapNode<E> other) {
        return value.compareTo(other.value);
    }
}
