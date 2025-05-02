package heap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Collections.swap;

public abstract class MinHeap<E extends Comparable<E>> {
    protected List<HeapNode<E>> nodes = new ArrayList<>();
    protected int size = 0;

    public abstract HeapNode<E> getParent(HeapNode<E> node) throws NoParentE;

    public abstract List<HeapNode<E>> getChildren(HeapNode<E> node) throws NoChildE;

    public List<HeapNode<E>> getNodes() { return nodes; }

    public int getSize() {
        return size;
    }

    public HeapNode<E> getMinChild(HeapNode<E> elem) throws NoChildE {
        try {
            List<HeapNode<E>> children = getChildren(elem);
            return Collections.min(children);
        }
        catch (NoSuchElementException _) {
            throw new NoChildE();
        }
    }

    public void swapNodes (HeapNode<E> a, HeapNode<E> b) {
        int indexA = a.getIndex();
        int indexB = b.getIndex();
        swap(nodes, indexA, indexB);
        a.setIndex(indexB);
        b.setIndex(indexA);
    }

    public void moveUp(HeapNode<E> elem) {
        try {
            HeapNode<E> parent = getParent(elem);
            if (elem.compareTo(parent) < 0) {
                swapNodes(elem, parent);
                moveUp(elem);
            }
        }
        catch (NoParentE _) {}
    }

    public void reduceValue (HeapNode<E> elem, E newValue) {
        elem.setValue(newValue);
        moveUp(elem);
    }

    public void moveDown(HeapNode<E> elem) {
        try {
            HeapNode<E> minChild = getMinChild(elem);
            if (elem.compareTo(minChild) > 0) {
                swapNodes(elem, minChild);
                moveDown(elem);
            }
        }
        catch (NoChildE _) {}
    }

    public HeapNode<E> getMin() throws EmptyHeapExc {
        if (size == 0) throw new EmptyHeapExc();
        return nodes.getFirst();
    }

    public void insert(HeapNode<E> elem) {
        nodes.add(elem);
        elem.setHeap(this);
        elem.setIndex(size);
        size++;
        moveUp(elem);
    }

    public HeapNode<E> removeMin() throws EmptyHeapExc {
        if (size == 0) throw new EmptyHeapExc();
        else {
            size--;
            if (size == 0) return nodes.removeFirst();
            HeapNode<E> min = nodes.getFirst();
            HeapNode<E> last = nodes.removeLast();
            nodes.set(0, last);
            last.setIndex(0);
            moveDown(last);
            return min;
        }
    }

    public static class NoParentE extends Exception {}
    public static class NoChildE extends Exception {}
    public static class EmptyHeapExc extends Exception {}
}
