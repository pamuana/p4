
class IntervalBSTnode<K extends Interval> {
    //nodes left child
    private IntervalBSTnode<K> left;
    //nodes right child
    private IntervalBSTnode right;
    //key for the node
    private K key;
    //max end in this part of the tree
    private long maxEnd;



    public IntervalBSTnode(K keyValue) {
        this.key = keyValue;
        this.left = null;
        this.right = null;
    }

    public IntervalBSTnode(K keyValue, IntervalBSTnode<K> leftChild, IntervalBSTnode<K> rightChild, long maxEnd) {
        if(keyValue == null){
            throw new IllegalArgumentException();
        }
        this.key = keyValue;
        if(key.compareTo(leftChild.getKey()) == -1 || key.compareTo(rightChild.getKey()) == 1 || key.equals(leftChild.getKey()) || key.equals(rightChild.getKey())){
            throw new IllegalArgumentException();
        }

        this.left = leftChild;
        this.right = rightChild;
        this.maxEnd = maxEnd;
    }

    public K getKey() {
        return key;
    }

    public IntervalBSTnode<K> getLeft() {
        return left;
    }

    public IntervalBSTnode<K> getRight() {
        return right;
    }

    public long getMaxEnd(){
        return maxEnd;
    }

    public void setKey(K newK) {
        if(newK == null){
            throw new IllegalArgumentException();
        }
        key = newK;
    }

    public void setLeft(IntervalBSTnode<K> newL) {
        left = newL;
    }

    public void setRight(IntervalBSTnode<K> newR) {
        right = newR;
    }

    public void setMaxEnd(long newEnd) {
        maxEnd = newEnd;
    }

    public long getStart(){
        return key.getStart();
    }

    public long getEnd(){
        return key.getEnd();
    }

    public K getData(){
        return key;
    }

}