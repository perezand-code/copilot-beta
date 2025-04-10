import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapTest {

    @Test
    void test1 () throws EmptyE, NoParentE, NoChildE {
        AbstractHeap<Integer> heap = new BinaryMinHeap<>();
        Heap2Node<Integer> node5 = new Heap2Node<>(5);
        Heap2Node<Integer> node3 = new Heap2Node<>(3);
        Heap2Node<Integer> node8 = new Heap2Node<>(8);
        Heap2Node<Integer> node1 = new Heap2Node<>(1);

        heap.insert(node5);
        heap.insert(node3);
        heap.insert(node8);
        heap.insert(node1);

        System.out.println("After inserting 5,3,8,1\n" + heap);
        Tree2Printer.print((Tree2Printer.PrintableNode) heap.getMin());

        Heap2Node<Integer> temp5 = new Heap2Node<>(5);
        Heap2Node<Integer> temp3 = new Heap2Node<>(3);
        Heap2Node<Integer> temp8 = new Heap2Node<>(8);
        Heap2Node<Integer> temp1 = new Heap2Node<>(1);
        assertEquals(heap, new BinaryMinHeap<>(List.of(temp5, temp3, temp8, temp1)));
        assertEquals(node1, heap.getMin());
        assertEquals(node1, heap.getParent(node3));
        assertEquals(node1, heap.getParent(node8));
        assertEquals(node3, heap.getParent(node5));
        List<HeapNode<Integer>> children1 = heap.getChildren(node1);
        assertEquals(2, children1.size());
        assertEquals(node3, children1.getFirst());
        assertEquals(node8, children1.get(1));
        List<HeapNode<Integer>> children3 = heap.getChildren(node3);
        assertEquals(1, children3.size());
        assertEquals(node5, children3.getFirst());
        assertEquals(node3, heap.getMinChild(node1));
        heap.reduceValue(node5, 0);

        System.out.println("After reducing 5 to 0\n" + heap);
        Tree2Printer.print((Tree2Printer.PrintableNode) heap.getMin());

        Heap2Node<Integer> temp0 = new Heap2Node<>(0);
        temp3 = new Heap2Node<>(3);
        temp8 = new Heap2Node<>(8);
        temp1 = new Heap2Node<>(1);
        BinaryMinHeap<Integer> expectedHeap = new BinaryMinHeap<>(List.of(temp0, temp3, temp8, temp1));
        assertEquals(expectedHeap, heap);

        HeapNode<Integer> m;
        m = heap.removeMin();
        assertEquals(new Heap2Node<>(0),m);
        m = heap.removeMin();
        assertEquals(new Heap2Node<>(1),m);
        m = heap.removeMin();
        assertEquals(new Heap2Node<>(3),m);
        m = heap.removeMin();
        assertEquals(new Heap2Node<>(8),m);
        assertEquals(0, heap.getSize());
    }

    @Test
    void test2 () throws EmptyE, NoParentE, NoChildE {
        AbstractHeap<Integer> heap = new TernaryMinHeap<>();
        Heap3Node<Integer> node5 = new Heap3Node<>(5);
        Heap3Node<Integer> node3 = new Heap3Node<>(3);
        Heap3Node<Integer> node8 = new Heap3Node<>(8);
        Heap3Node<Integer> node1 = new Heap3Node<>(1);

        heap.insert(node5);
        heap.insert(node3);
        heap.insert(node8);
        heap.insert(node1);

        System.out.println("After inserting 5,3,8,1\n" + heap);
        Tree3Printer.print((Tree3Printer.PrintableNode) heap.getMin());

        Heap3Node<Integer> temp5 = new Heap3Node<>(5);
        Heap3Node<Integer> temp3 = new Heap3Node<>(3);
        Heap3Node<Integer> temp8 = new Heap3Node<>(8);
        Heap3Node<Integer> temp1 = new Heap3Node<>(1);
        assertEquals(heap, new TernaryMinHeap<>(List.of(temp1, temp5, temp8, temp3)));
        assertEquals(node1, heap.getMin());
        assertEquals(node1, heap.getParent(node5));
        assertEquals(node1, heap.getParent(node8));
        assertEquals(node1, heap.getParent(node3));
        List<HeapNode<Integer>> children1 = heap.getChildren(node1);
        assertEquals(3, children1.size());
        assertEquals(node5, children1.getFirst());
        assertEquals(node8, children1.get(1));
        assertEquals(node3, children1.getLast());
        assertEquals(node3, heap.getMinChild(node1));
        heap.reduceValue(node5, 0);

        System.out.println("After reducing 5 to 0\n" + heap);
        Tree3Printer.print((Tree3Printer.PrintableNode) heap.getMin());

        Heap3Node<Integer> temp0 = new Heap3Node<>(0);
        temp3 = new Heap3Node<>(3);
        temp8 = new Heap3Node<>(8);
        temp1 = new Heap3Node<>(1);
        TernaryMinHeap<Integer> expectedHeap = new TernaryMinHeap<>(List.of(temp0, temp1, temp8, temp3));
        assertEquals(expectedHeap, heap);
        HeapNode<Integer> m;
        m = heap.removeMin();
        assertEquals(new Heap3Node<>(0), m);
        m = heap.removeMin();
        assertEquals(new Heap3Node<>(1), m);
        m = heap.removeMin();
        assertEquals(new Heap3Node<>(3), m);
        m = heap.removeMin();
        assertEquals(new Heap3Node<>(8), m);
        assertEquals(0, heap.getSize());

    }

}
