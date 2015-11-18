import java.util.*;

public class IntervalBSTIterator<K extends Interval> implements Iterator<K> {

    private Stack<IntervalBSTnode<K>> stack; //for keeping track of nodes

    public IntervalBSTIterator(IntervalBSTnode<K> root) {
        stack = new Stack<IntervalBSTnode<K>>();
        //in order traversal and push into the stack
        init(root);
    }
    public boolean hasNext() {
        return stack.size() > 0;
    }

    public K next() {
        if(stack.size() <= 0){
            throw new EmptyStackException();
        }
        else return stack.pop().getKey();
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
    private void init(IntervalBSTnode<K> root){
        if(root == null) return;
        init(root.getLeft());
        stack.push(root);
        init(root.getRight());
    }
}