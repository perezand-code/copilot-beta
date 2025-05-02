package traversals;
import graphs.WeightedDirectedGraph;
import util.Weight;
import util.Edge;
import util.EdgePath;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class finds the maximum flow in a directed graph as follows. First a copy
 * of the graph is made and will be used as a 'residual' graph. Then we have a recursive
 * method 'runMaxFlow' that performs the following steps:
 * <ul>
 *     <li>Find the shortest path from the source to the destination in the residual graph.</li>
 *     <li>If no path is found, return the current maximum flow.</li>
 *     <li>Find the minimum weight of the edges in the path; this is the bottleneck. It is
 *     added to the current maximum flow</li>
 *     <li>Modify the residual graph by subtracting the bottleneck from the edges in the path
 *     (removing the edges if the weight becomes zero) and adding the bottleneck to the reverse edges.</li>
 *     <li>Repeat the process until no more paths can be found.</li>
 * </ul>
 */
public class MaximumFlow<V> {
    private final WeightedDirectedGraph<V> residual;
    private final V source;
    private final V destination;
    private int maxFlow;

    public MaximumFlow(WeightedDirectedGraph<V> graph, V source, V destination) {
        this.residual = graph.copy();
        this.source = source;
        this.destination = destination;
    }

    /**
     * TODO
     */
    public int runMaxFlow() {
        while (true) {
            // Step 1: Find the shortest path from source to destination in the residual graph
            Optional<EdgePath<V>> optionalPath = residual.shortestWeightedPath(source, destination);

            if (optionalPath.isEmpty()) {
                // If no path is found, return the current maximum flow
                return maxFlow;
            }

            EdgePath<V> path = optionalPath.get();

            // Step 2: Find the minimum weight (bottleneck) of the edges in the path
            int bottleneck = path.getEdges().stream()
                    .mapToInt(edge -> residual.getWeight(edge).orElse(Weight.ZERO).asInt())
                    .min()
                    .orElse(0);

            // Add the bottleneck to the current maximum flow
            maxFlow += bottleneck;

            // Step 3: Modify the residual graph
            for (Edge<V> edge : path.getEdges()) {
                // Subtract the bottleneck from the forward edge
                residual.subtractEdgeWeight(edge, Weight.of(bottleneck));

                // Add the bottleneck to the reverse edge
                Edge<V> reverseEdge = edge.reverse();
                residual.addEdgeWeight(reverseEdge, Weight.of(bottleneck));
            }
        }
    }
}