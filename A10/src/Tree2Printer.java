import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree printer:
 * from <a href="https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram">...</a>
 *
 * @author MightyPork
 */
public class Tree2Printer
{
    public interface PrintableNode {
        PrintableNode getLeft();
        PrintableNode getRight();
        String getText();
    }

    public static void print(PrintableNode root) {
        List<List<String>> lines = new ArrayList<>();
        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                }
                else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();
                    next.add(n.getLeft());
                    next.add(n.getRight());
                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;
            lines.add(line);

            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.getLast().size() * (widest + 4);

        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        }
                        else if (line.get(j) != null) c = '└';
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null)
                        for (int k = 0; k < perpiece - 1; k++) System.out.print(" ");
                    else {
                        for (int k = 0; k < hpw; k++) System.out.print(j % 2 == 0 ? " " : "─");
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) System.out.print(j % 2 == 0 ? "─" : " ");
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (String f : line) {
                if (f == null) f = "";
                float gap = perpiece / 2f - f.length() / 2f;
                int gap1 = (int) Math.ceil(gap);
                int gap2 = (int) Math.floor(gap);
                for (int k = 0; k < gap1; k++) System.out.print(" ");
                System.out.print(f);
                for (int k = 0; k < gap2; k++) System.out.print(" ");
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}
