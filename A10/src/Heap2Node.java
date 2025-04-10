import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Heap2Node<E extends Comparable<E>>
        extends HeapNode<E>
        implements Tree2Printer.PrintableNode {

    public Heap2Node(@NotNull E value) {
        super(value);
    }

    public Tree2Printer.PrintableNode getLeft() {
        try {
            List<HeapNode<E>> children = heap.getChildren(this);
            return (Tree2Printer.PrintableNode) children.getFirst();
        }
        catch (NoChildE e) {
            return null;
        }
    }

    public Tree2Printer.PrintableNode getRight() {
        try {
            List<HeapNode<E>> children = heap.getChildren(this);
            if (children.size() > 1) return (Tree2Printer.PrintableNode) children.get(1);
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
        if (!(o instanceof Heap2Node<?> that)) return false;
        return value.equals(that.value) && index == that.index;
    }
}
