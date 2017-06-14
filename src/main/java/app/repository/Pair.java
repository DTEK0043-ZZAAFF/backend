package app.repository;


import java.util.LinkedList;
import java.util.List;

public class Pair<T, S> {
    private T left;
    private S right;

    public Pair(T left, S right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public S getRight() {
        return right;
    }

    public void setRight(S right) {
        this.right = right;
    }

    public static<L,R> Pair<List<L>, List<R>> unzip(List<Pair<L, R>> listOfPairs) {
        List<L> left = new LinkedList<>();
        List<R> right = new LinkedList<>();

        for(Pair<L,R> pair: listOfPairs) {
            left.add(pair.getLeft());
            right.add(pair.getRight());
        }
        return new Pair<>(left, right);
    }
}
