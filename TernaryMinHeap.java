package heap;
import util.Tree3Printer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TernaryMinHeap<E extends Comparable<E>> extends MinHeap<E> {

    public TernaryMinHeap() {}

    public TernaryMinHeap(List<HeapNode<E>> elements) {
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

    public HeapNode<E> getParent(HeapNode<E> node) throws NoParentE {
        int index = node.getIndex();
        if (index == 0) throw new NoParentE();
        return nodes.get((index - 1) / 3);
    }

    public List<HeapNode<E>> getChildren(HeapNode<E> node) throws NoChildE {
        int index = node.getIndex();
        int index1 = index * 3 + 1;
        int index2 = index * 3 + 2;
        int index3 = index * 3 + 3;
        List<HeapNode<E>> children = new ArrayList<>();
        if (index1 < size) children.add(nodes.get(index1));
        if (index2 < size) children.add(nodes.get(index2));
        if (index3 < size) children.add(nodes.get(index3));
        if (children.isEmpty()) throw new NoChildE();
        return children;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TernaryMinHeap<?> that)) return false;
        return size == that.size && nodes.equals(that.nodes);
    }

    public int hashCode() {
        return Objects.hash(nodes, size);
    }

    public static class Heap3Node<E extends Comparable<E>>
            extends HeapNode<E>
            implements Tree3Printer.PrintableNode {

        public Heap3Node(E value) {
            super(value);
        }

        public Tree3Printer.PrintableNode getLeft() {
            try {
                List<HeapNode<E>> children = heap.getChildren(this);
                return (Tree3Printer.PrintableNode) children.getFirst();
            } catch (NoChildE e) {
                return null;
            }
        }

        public Tree3Printer.PrintableNode getMiddle() {
            try {
                List<HeapNode<E>> heapChildren = heap.getChildren(this);
                if (heapChildren.size() > 1) return (Tree3Printer.PrintableNode) heapChildren.get(1);
                else return null;
            } catch (NoChildE e) {
                return null;
            }
        }

        public Tree3Printer.PrintableNode getRight() {
            try {
                List<HeapNode<E>> heapChildren = heap.getChildren(this);
                if (heapChildren.size() > 2) return (Tree3Printer.PrintableNode) heapChildren.get(2);
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
