// Class representing the Red-Black Tree
class RedBlackTree {
    private RedBlackNode root;
    private final RedBlackNode TNULL; // Represents the NULL nodes (leaves)

    public RedBlackTree() {
        TNULL = new RedBlackNode(0);
        TNULL.color = false; // NULL nodes are black
        root = TNULL;
    }

    // Preorder traversal
    public void preOrderTraversal(RedBlackNode node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Inorder traversal
    public void inOrderTraversal(RedBlackNode node) {
        if (node != TNULL) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    // Postorder traversal
    public void postOrderTraversal(RedBlackNode node) {
        if (node != TNULL) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Search the tree
    public RedBlackNode searchTree(int key) {
        return searchTreeHelper(this.root, key);
    }

    // Helper method for searching the tree
    private RedBlackNode searchTreeHelper(RedBlackNode node, int key) {
        if (node == TNULL || key == node.data) {
            return node;
        }

        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
        }   

    // Balance the tree after deletion of a node
    private void fixDelete(RedBlackNode x) {
        RedBlackNode s;
        while (x != root && x.color == false) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == true) {
                    s.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == false && s.right.color == false) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (s.right.color == false) {
                        s.left.color = false;
                        s.color = true;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.right.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == true) {
                    s.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.left.color == false && s.right.color == false) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (s.left.color == false) {
                        s.right.color = false;
                        s.color = true;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.left.color = false;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }

    private void rbTransplant(RedBlackNode u, RedBlackNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(RedBlackNode node, int key) {
        RedBlackNode z = TNULL;
        RedBlackNode x, y;
        while (node != TNULL) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        boolean yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == false) {
            fixDelete(x);
        }
    }

    // Find the node with the minimum key
    private RedBlackNode minimum(RedBlackNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    // Balance the tree after insertion of a node
    private void fixInsert(RedBlackNode k) {
        RedBlackNode u;
        while (k.parent.color == true) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = false;
    }

    private void printHelper(RedBlackNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    // Insert a new node
    public void insert(int key) {
        RedBlackNode node = new RedBlackNode(key);
        node.parent = null;

        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = true; // new node must be red

        RedBlackNode y = null;
        RedBlackNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    public RedBlackNode getRoot() {
        return this.root;
    }

    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    // Left rotate the subtree rooted at x
private void leftRotate(RedBlackNode x) {
    RedBlackNode y = x.right;
    x.right = y.left;
    if (y.left != TNULL) {
        y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
        this.root = y;
    } else if (x == x.parent.left) {
        x.parent.left = y;
    } else {
        x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
}

// Right rotate the subtree rooted at y
private void rightRotate(RedBlackNode y) {
    RedBlackNode x = y.left;
    y.left = x.right;
    if (x.right != TNULL) {
        x.right.parent = y;
    }
    x.parent = y.parent;
    if (y.parent == null) {
        this.root = x;
    } else if (y == y.parent.right) {
        y.parent.right = x;
    } else {
        y.parent.left = x;
    }
    x.right = y;
    y.parent = x;
}

}


