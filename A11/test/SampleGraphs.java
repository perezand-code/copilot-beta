public class SampleGraphs {
    /*
     *
     *    A -> B
     *    |    |
     *    v    v
     *    C -> D
     *
     */
    public static DirectedGraph<String> g1() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "D");
        return g;
    }

    /*
     *
     *    A -> B -> D
     *    |  / ^   /
     *    | /  |  /
     *    vv   v v
     *    C    E
     *
     */
    public static DirectedGraph<String> g2() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "B");
        g.addEdge("A","C");
        g.addEdge("B","C");
        g.addEdge("B","D");
        g.addEdge("B","E");
        g.addEdge("D","E");
        g.addEdge("E","B");
        return g;
    }

    /*
     *
     *                 A
     *              /  |  \
     *             /   |    \
     *           A2    A7    A8
     *          /  \          |  \
     *         /    \         |    \
     *        A3      A6      A9    A12
     *         |  \            | \
     *         |    \          |   \
     *        A4     A5       A10   A11
     *
     */
    public static DirectedGraph<String> g3() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "A2");
        g.addEdge("A", "A7");
        g.addEdge("A", "A8");
        g.addEdge("A2", "A3");
        g.addEdge("A2", "A6");
        g.addEdge("A3", "A4");
        g.addEdge("A3", "A5");
        g.addEdge("A8", "A9");
        g.addEdge("A8", "A12");
        g.addEdge("A9", "A10");
        g.addEdge("A9", "A11");
        return g;
    }

    /*
     *
     *                  E
     *                  | \
     *                  |   \
     *                  |     \
     *                  |       v
     *         G -------+-----> I
     *         ^ ^      |       |
     *         |  \     |       v
     *         |   -----+------ H
     *         |        |      ^  \
     *         |        |     /     \
     *         |        |    /        \
     *         |        |   /           \
     *         |        v /              v
     *         B -----> A --> C --> D --> F -----> J
     *                        ^                    |
     *                        |                    |
     *                        +--------------------+
     *
     */
    public static DirectedGraph<String> g4() {
        DirectedGraph<String> g = new DirectedGraph<>();
        g.addEdge("A", "C");
        g.addEdge("A", "H");
        g.addEdge("B", "A");
        g.addEdge("B", "G");
        g.addEdge("C", "D");
        g.addEdge("D", "F");
        g.addEdge("E", "A");
        g.addEdge("E", "I");
        g.addEdge("F", "J");
        g.addEdge("G", "I");
        g.addEdge("H", "F");
        g.addEdge("H", "G");
        g.addEdge("I", "H");
        g.addEdge("J", "C");
        return g;
    }

}

