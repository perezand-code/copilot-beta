package heap;
import util.Tree2Printer;
import java.util.*;

public class BinaryMinHeap<E extends Comparable<E>> extends MinHeap<E> {

    public BinaryMinHeap() {}

    public BinaryMinHeap(List<HeapNode<E>> elements) {
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

    public HeapNode<E> getParent(HeapNode<E> elem) throws NoParentE {
        int index = elem.getIndex();
        if (index == 0) throw new NoParentE();
        return nodes.get((index - 1) / 2);
    }

    public List<HeapNode<E>> getChildren(HeapNode<E> elem) throws NoChildE {
        int index = elem.getIndex();
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        List<HeapNode<E>> children = new ArrayList<>();
        if (leftChildIndex < size) children.add(nodes.get(leftChildIndex));
        if (rightChildIndex < size) children.add(nodes.get(rightChildIndex));
        if (children.isEmpty()) throw new NoChildE();
        return children;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryMinHeap<?> that)) return false;
        return size == that.size && nodes.equals(that.nodes);
    }

    public int hashCode() {
        return Objects.hash(nodes, size);
    }

    public static class Heap2Node<E extends Comparable<E>>
            extends HeapNode<E>
            implements Tree2Printer.PrintableNode {

        public Heap2Node(E value) {
            super(value);
        }

        public Tree2Printer.PrintableNode getLeft() {
            try {
                List<HeapNode<E>> children = heap.getChildren(this);
                return (Tree2Printer.PrintableNode) children.getFirst();
            } catch (NoChildE e) {
                return null;
            }
        }

        public Tree2Printer.PrintableNode getRight() {
            try {
                List<HeapNode<E>> children = heap.getChildren(this);
                if (children.size() > 1) return (Tree2Printer.PrintableNode) children.get(1);
                else return null;
            } catch (NoChildE e) {
                return null;
            }
        }

        public String getText() {
            return value.toString();
        }
    }

}