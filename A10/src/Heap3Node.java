import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Heap3Node<E extends Comparable<E>>
        extends HeapNode<E>
        implements Tree3Printer.PrintableNode {

    public Heap3Node(@NotNull E value) {
            super(value);
    }

    public Tree3Printer.PrintableNode getLeft() {
        try {
            List<HeapNode<E>> children = heap.getChildren(this);
            return (Tree3Printer.PrintableNode) children.getFirst();
        }
        catch (NoChildE e) {
            return null;
        }
    }

    public Tree3Printer.PrintableNode getMiddle() {
        try {
            List<HeapNode<E>> heapChildren = heap.getChildren(this);
            if (heapChildren.size() > 1) return (Tree3Printer.PrintableNode) heapChildren.get(1);
            else return null;
        }
        catch (NoChildE e) {
            return null;
        }
    }

    public Tree3Printer.PrintableNode getRight() {
        try {
            List<HeapNode<E>> heapChildren = heap.getChildren(this);
            if (heapChildren.size() > 2) return (Tree3Printer.PrintableNode) heapChildren.get(2);
            else return null;
        }
        catch (NoChildE e) {
            return null;
        }
    }

    public String getText() {
        return value.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Heap3Node<?> that)) return false;
        return value.equals(that.value) && index == that.index;
    }
}
