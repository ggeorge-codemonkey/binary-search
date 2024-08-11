

// Class representing the Binary Search Tree
class BinarySearchTree {
    Node root;

    // Constructor for BST
    BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node with given data
    void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive method to insert a new node in the BST
    Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        // Return the (unchanged) node pointer
        return root;
    }

    // Method to perform inorder traversal
    void inorder() {
        inorderRec(root);
    }

    // Recursive method to perform inorder traversal
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Main method
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserting nodes into the BST
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Performing inorder traversal
        System.out.println("Inorder traversal of the binary search tree:");
        bst.inorder();
    }
}
