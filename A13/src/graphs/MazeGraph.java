package graphs;
import util.Weight;
import java.util.List;
import java.util.Random;

public class MazeGraph extends WeightedBiDirectedGraph<MazeGraph.Vertex> {
    private final int dimension;
    private final Random rand;
    private WeightedDirectedGraph<Vertex> maze;
    private List<Vertex> solution;

    public MazeGraph(int dimension) {
        this.dimension = dimension;
        this.rand = new Random();
        generateGrid();
    }

    private void generateGrid() {
        for (var x = 0; x < dimension; x++) {
            for (var y = 0; y < dimension; y++) {
                var v = new Vertex(x, y);
                if (x > 0) {
                    Vertex b = new Vertex(x - 1, y);
                    addEdge(v, b, Weight.of(1 + rand.nextInt(100)));
                }
                if (y > 0) {
                    Vertex b = new Vertex(x, y - 1);
                    addEdge(v, b, Weight.of(1 + rand.nextInt(100)));
                }
            }
        }
    }

    public void generateMaze() {
        var start = new Vertex(0, 0);
        this.maze = new WeightedDirectedGraph<>();
        minimumSpanningTree(start).forEach(maze::addEdge);
    }

    public void solveMaze() {
        var start = new Vertex(0, 0);
        var goal = new Vertex(dimension - 1, dimension - 1);
        this.solution = maze.shortestWeightedPath(start, goal).orElseThrow().nodes();
    }

    public WeightedDirectedGraph<Vertex> getMaze() {
        return maze;
    }

    public List<Vertex> getSolution() {
        return solution;
    }

    public record Vertex(int x, int y) {}
}
