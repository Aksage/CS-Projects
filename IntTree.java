// Create a BST with a minimum height of 5.

// User should be able to select the function they wish to perform until they decide to exit the program.
// Methods should all use recursive logic.
// SEARCH for a value
// REMOVE a number
// ADD a number
// PRINT the tree


import java.util.NoSuchElementException;

//an IntTree object represents an entire binary tree of ints.
public class IntTree {


    private IntTreeNode overallRoot;    // null for an empty tree


    public static void main(String[] args) {
        IntTreeNode node = new IntTreeNode(3);
        IntTree tree = new IntTree(node);
        //tree.overallRoot.left = new IntTreeNode(2);

        tree.add(node, 2);
        tree.add(node, 6);
        tree.add(node, 30);
        tree.add(node, 23);

        System.out.println(tree.contains(node, 30));
        tree.remove(30);
        System.out.println(tree.contains(node, 30));

        System.out.println(tree.overallRoot.right);
        //tree.print();

    }

     // Constructs an empty binary tree
     public IntTree() {
         overallRoot = null;
     }

     // Constructs a binary tree with the given node as its root.
     public IntTree(IntTreeNode overallRoot) {
         this.overallRoot = overallRoot;
     }

    // PRINT
    public void print() {
        print(overallRoot);
        System.out.println();
    }

    private void print(IntTreeNode root) {
        if (root != null) {
            print(overallRoot.left);
            System.out.println(overallRoot.data + " ");
            print(overallRoot.right);
        }
    }


     // SEARCH
     public boolean contains(int value) {
         return contains(overallRoot, value);
     }

     private boolean contains(IntTreeNode root, int value) {
         if (root == null) {
             return false;
         }
         else if (root.data == value) {
             return true;
         }
         else if (root.data > value) {
             return contains(root.left, value);
         }
         else {     // root.data < value
             return contains(root.right, value);
         }
     }

    // ADD
    // Adds the given value to this BST in sorted order.
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    private IntTreeNode add(IntTreeNode root, int value) {
         if (root == null) {
             root = new IntTreeNode(value);
         }
         else if (root.data > value) {
             root.left = add(root.left, value);
         }
         else if (root.data < value) {
             root.right = add(root.right, value);
         }

         return root;
    }


    // REMOVES
    // Removes the given value from this BST, if it exists.
    public void remove(int value) {
         overallRoot = remove(overallRoot, value);
    }

    private IntTreeNode remove(IntTreeNode root, int value) {
         if (root == null) {
             return null;
         }
         else if (root.data > value) {
             root.left = remove(root.left, value);
         }
         else if (root.data < value) {
             root.right = remove(root.right, value);
         }
         else {     // root.data == value; remove this node
             if (root.right == null) {
                 return root.left;      // no R child; replace w/ L
             }
             else if (root.left == null) {
                 return root.right;     // no L child; replace w/ R
             }
             else {
                 // both children; replace w/ min from R
                 root.data = getMin(root.right);
                 root.right = remove(root.right, root.data);
             }
         }
         return root;
    }







    public int getMin() {
         if (overallRoot == null) {
             throw new NoSuchElementException();
         }
         return getMin(overallRoot);
    }

    private int getMin(IntTreeNode root) {
         if (root.left == null) {
             return root.data;
         }
         else {
             return getMin(root.left);
         }
    }







}


