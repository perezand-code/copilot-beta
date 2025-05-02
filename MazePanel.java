package util;
import graphs.MazeGraph;
import graphs.WeightedDirectedGraph;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MazePanel extends JPanel {
    private final int dimension;
    private final WeightedDirectedGraph<MazeGraph.Vertex> maze;
    private final List<MazeGraph.Vertex> solutionPath;
    private boolean solutionVisible;

    public MazePanel(
            int dimension,
            WeightedDirectedGraph<MazeGraph.Vertex> maze,
            List<MazeGraph.Vertex> solution) {
        this.dimension = dimension;
        this.maze = maze;
        this.solutionPath = solution;
        new javax.swing.Timer(1000, e -> {
            this.solutionVisible = true;
            repaint();
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int cellWidth = Math.min(panelWidth, panelHeight) / dimension;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, panelWidth, panelHeight);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(0, 0, panelWidth - 1, panelHeight - 1);
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                var v = new MazeGraph.Vertex(x, y);
                int x1 = x * cellWidth;
                int y1 = y * cellWidth;
                int x2 = x1 + cellWidth;
                int y2 = y1 + cellWidth;
                var bottom = new MazeGraph.Vertex(x, y + 1);
                boolean hasBottom = y < dimension - 1 && (maze.hasEdge(v, bottom) || maze.hasEdge(bottom, v));
                if (!hasBottom) g2.drawLine(x1, y2, x2, y2);
                var right = new MazeGraph.Vertex(x + 1, y);
                boolean hasRight = x < dimension - 1 && (maze.hasEdge(v, right) || maze.hasEdge(right, v));
                if (!hasRight) g2.drawLine(x2, y1, x2, y2);
                if (x == 0) g2.drawLine(x1, y1, x1, y2);
                if (y == 0) g2.drawLine(x1, y1, x2, y1);
            }
        }
        if (solutionVisible) {
            if (solutionPath.size() > 1) {
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(
                        6,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                ));
                for (int i = 0; i < solutionPath.size() - 1; i++) {
                    var a = solutionPath.get(i);
                    var b = solutionPath.get(i + 1);
                    int ax = a.x() * cellWidth + cellWidth / 2;
                    int ay = a.y() * cellWidth + cellWidth / 2;
                    int bx = b.x() * cellWidth + cellWidth / 2;
                    int by = b.y() * cellWidth + cellWidth / 2;
                    g2.drawLine(ax, ay, bx, by);
                }
                var start = solutionPath.getFirst();
                var end = solutionPath.getLast();
                int r = cellWidth / 3;
                int offset = r / 2;
                g2.setColor(Color.GREEN);
                g2.fillOval(start.x() * cellWidth + cellWidth / 2 - offset,
                        start.y() * cellWidth + cellWidth / 2 - offset,
                        r, r);
                g2.setColor(Color.BLUE);
                g2.fillOval(end.x() * cellWidth + cellWidth / 2 - offset,
                        end.y() * cellWidth + cellWidth / 2 - offset,
                        r, r);
            }
        }
    }

    public static void showMaze(
            int dimension,
            WeightedDirectedGraph<MazeGraph.Vertex> maze,
            List<MazeGraph.Vertex> solution) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
            frame.getContentPane().add(new MazePanel(dimension, maze, solution));
            frame.setSize(800, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
