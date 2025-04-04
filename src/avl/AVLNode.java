package avl;

import trees.EmptyTreeExc;
import trees.TreePrinter;
import org.jetbrains.annotations.NotNull;

public record AVLNode<E extends Comparable<E>>
        (@NotNull E value,
         @NotNull AVLTree<E> left,
         @NotNull AVLTree<E> right,
         int height)
        implements AVLTree<E> {

    // Constructors -------------------------------------------------------------------------

    public AVLNode(@NotNull E value) {
        this(value, new EmptyAVL<>(), new EmptyAVL<>());
    }

    public AVLNode(@NotNull E value, @NotNull AVLTree<E> left, @NotNull AVLTree<E> right) {
        this(value, left, right, 1 + Math.max(left.height(), right.height()));
    }

    // Simple one-liner methods ------------------------------------------------------------

    public boolean isEmpty() { return false; }
    public int height() {
        return height;
    }
    public int balanceFactor() {
        return left.height() - right.height();
    }
    public boolean isWellFormed() {
        return left.isWellFormed() && right.isWellFormed() && Math.abs(balanceFactor()) <= 1;
    }

    // Search methods -----------------------------------------------------------------------

    public boolean contains(@NotNull E searchValue) {
        return switch (searchValue.compareTo(value)) {
            case  0 -> true;
            case -1 -> left.contains(searchValue);
            case  1 -> right.contains(searchValue);
            default -> throw new RuntimeException("Comparison failed");
        };
    }

    public @NotNull E findMin() {
        try { return left.findMin(); }
        catch (EmptyTreeExc e) { return value; }
    }

    // Insertion and deletion methods ------------------------------------------------------

    public @NotNull AVLTree<E> insert(@NotNull E newValue) {
        return switch (newValue.compareTo(value)) {
            case  0 -> this;
            case -1 -> balance(value, left.insert(newValue), right);
            case  1 -> balance(value, left, right.insert(newValue));
            default -> throw new RuntimeException("Comparison failed");
        };
    }

    public @NotNull AVLTree<E> remove(@NotNull E removeValue) throws EmptyTreeExc {
        return switch (removeValue.compareTo(value)) {
            case  0 -> mergeSubtrees(left,right);
            case -1 -> balance(value, left.remove(removeValue), right);
            case  1 -> balance(value, left, right.remove(removeValue));
            default -> throw new RuntimeException("Comparison failed");
        };
    }

    public static <E extends Comparable<E>> @NotNull AVLTree<E> mergeSubtrees(
            @NotNull AVLTree<E> left,
            @NotNull AVLTree<E> right) {

        try { return balance(right.findMin(), left, right.removeMin()); }
        catch (EmptyTreeExc e) { return left; }
    }

    public @NotNull AVLTree<E> removeMin() {
        try { return balance(value, left.removeMin(), right); }
        catch (EmptyTreeExc e) { return right; }
    }

    // Rotations ----------------------------------------------------------------------------

    public static <E extends Comparable<E>> AVLTree<E> balance
            (@NotNull E value,
             @NotNull AVLTree<E> left,
             @NotNull AVLTree<E> right) {
        return new AVLNode<>(value, left, right).rotate();
    }

    public @NotNull AVLTree<E> rotate() {
        return switch (balanceFactor()) {
            case  2 -> left.balanceFactor() >= 0 ? rotateRight() : rotateLeftRight();
            case -2 -> right.balanceFactor() <= 0 ? rotateLeft() : rotateRightLeft();
            default -> this;
        };
    }

    public @NotNull AVLTree<E> rotateRight() {
        if (left instanceof AVLNode<E>(var lv, var ll, var lr, int _))
            return new AVLNode<>(lv, ll, new AVLNode<>(value, lr, right));
        throw new RuntimeException("Right rotation failed");
    }

    public @NotNull AVLTree<E> rotateLeft() {
        if (right instanceof AVLNode<E>(var rv, var rl, var rr, int _))
            return new AVLNode<>(rv, new AVLNode<>(value, left, rl), rr);
        throw new RuntimeException("Left rotation failed");
    }

    public AVLTree<E> rotateLeftRight() {
        if (left instanceof AVLNode<E> leftN)
            return new AVLNode<>(value, leftN.rotateLeft(), right).rotateRight();
        throw new RuntimeException("Left-Right rotation failed");
    }

    public AVLTree<E> rotateRightLeft() {
        if (right instanceof AVLNode<E> rightN)
            return new AVLNode<>(value, left, rightN.rotateRight()).rotateLeft();
        throw new RuntimeException("Right-Left rotation failed");
    }

    // TreePrinter.PrintableNode interface methods ------------------------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }
    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }
    public String getText() {
        return value.toString();
    }
}
