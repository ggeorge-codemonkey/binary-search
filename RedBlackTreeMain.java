public class RedBlackTreeMain {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
    
        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);
    
        tree.printTree();
    
        System.out.println("\nAfter deleting an element");
        tree.deleteNode(40);
        tree.printTree();
        }

}
