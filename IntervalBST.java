import java.util.Iterator;



public class IntervalBST<K extends Interval> implements SortedListADT<K> {
    private IntervalBSTnode<K> root;
    private int size;

    public IntervalBST() {
        size = 0;
    }

    public void insert(K key){
        //make sure key is non null or already exists
        if(key == null || lookup(key) != null) throw new IllegalArgumentException();
        //case that tree is empty
        if(root == null){
            IntervalBSTnode<K> newNode = new IntervalBSTnode<K>(key);
            root = newNode;
            size++;
            return;
        }
        insert(key, root);
        size++;
    }

    private void insert(K key, IntervalBSTnode<K> pos){
        if(pos.getKey().compareTo(key) == -1){
            if(pos.getRight() == null){
                pos.setRight(new IntervalBSTnode<K>(key));
                return;
            }
            insert(key, pos.getRight());
            return;
        }
        if(pos.getKey().compareTo(key) == 1){
            if(pos.getLeft() == null){
                pos.setLeft(new IntervalBSTnode<K>(key));
                return;
            }
            insert(key, pos.getLeft());
        }
    }

    public boolean delete(K key) {
        //check if key exists in tree
        if(lookup(key) == null) return false;
        if(root.getKey().equals(key)){
            //case that the root is the node we wish to delete
            //now replace that node with the in-order successor or predescessor

            if(root.getLeft() != null){
                //use in-order predecessor
                IntervalBSTnode<K> curr = root.getLeft();
                IntervalBSTnode<K> parent = root;
                while(curr.getRight() != null){
                    parent = curr;
                    curr = curr.getRight();
                }
                root = curr;
                parent.setRight(null);
            }
            if(root.getLeft() == null && root.getRight() != null){
                //use in-order successor
                IntervalBSTnode<K> curr = root.getRight();
                IntervalBSTnode<K> parent = root;
                while(curr.getRight() != null){
                    parent = curr;
                    curr = curr.getLeft();

                }
                root = curr;
                parent.setLeft(null);
            }
            if(root.getLeft() == null && root.getRight() == null){
                root = null;
            }
            size--;
            return true;
        }
        return delete(key , null, root);
    }

    private boolean delete(K key, IntervalBSTnode<K> parent, IntervalBSTnode<K> pos){
        if(key.equals(pos.getKey())){
            //case, the pos has no children, delink the node
            if(pos.getRight() == null && pos.getLeft() == null){
                if(parent.getRight().equals(pos)){
                    parent.setRight(null);
                }
                else parent.setLeft(null);
            }
            //case , the key has one child, the keynode's parent adopts the keynode's child
            if(pos.getRight() != null && pos.getLeft() == null){
                //pos has a right node
                if(parent.getRight().equals(pos)){
                    parent.setRight(pos.getRight());
                }else parent.setLeft(pos.getRight());
            }
            if(pos.getLeft() != null && pos.getRight() == null){
                //pos has a left node
                if(parent.getRight().equals(pos)){
                    parent.setRight(pos.getLeft());
                }else parent.setLeft(pos.getLeft());
            }
            if(pos.getRight() != null && pos.getLeft() != null){
                //max value we wish to use to replace
                K max = getMax(pos);
                delete(max);
                pos.setKey(max);
            }
            size--;
            return true;
        }

        if(pos.getKey().compareTo(key) == -1){
            return delete(key,pos, pos.getRight());
        }
        else return delete(key,pos , pos.getLeft());
    }

    private K getMax(IntervalBSTnode<K> start){
        IntervalBSTnode<K> curr = start;
        while(curr.getRight() != null){
            curr = curr.getRight();
        }
        return curr.getKey();
    }

    public K lookup(K key) {
        return lookup(key , root);
    }

    /**
     * @param key
     * @param pos
     * @return
     */
    private K lookup(K key, IntervalBSTnode<K> pos){
        //means we've hit the end of the tree, the element does not exist
        if (pos == null) return null;
        //secondary base case is if we've found the element
        if(pos.getKey().equals(key)){
            return pos.getKey();
        }

        if(pos.getKey().compareTo(key) == -1){
            return lookup(key, pos.getRight());
        }
        else return lookup(key, pos.getLeft());
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<K> iterator() {
        return new IntervalBSTIterator<K>(root);
    }

}