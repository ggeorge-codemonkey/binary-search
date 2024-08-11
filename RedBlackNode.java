// Class representing a node in the Red-Black Tree
class RedBlackNode {
    int data;
    RedBlackNode left, right, parent;
    boolean color; // true for red, false for black

    public RedBlackNode(int data) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.color = true; // New nodes are red by default
    }
}