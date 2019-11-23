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
        IntTreeNode root = new IntTreeNode(43);
        IntTree tree = new IntTree(root);

        // Constructing the BST
        tree.add(root, 32);
        tree.add(root, 22);
        tree.add(root, 10);
        tree.add(root, 2);
        tree.add(root, 12);
        tree.add(root, 25);
        tree.add(root, 23);
        tree.add(root, 27);
        tree.add(root, 73);
        tree.add(root, 48);
        tree.add(root, 45);
        tree.add(root, 88);
        tree.add(root, 99);
        tree.add(root, 100);



        //tree.print();
        //tree.printSideways();

        tree.printLevelOrder(root);

        System.out.println("-------------------------------------------------------------");
        System.out.println("                               43");    // 31 spaces
        System.out.println("                   32                      73"); // 11 spaces
        System.out.println("            22                      48            88"); // 6 spaces
        System.out.println("       10        25             45                    99"); // 3 spaces
        System.out.println("      2  12    23  27                                   100"); // 1 space


    }

     // Constructs an empty binary tree
     public IntTree() {
         overallRoot = null;
     }

     // Constructs a binary tree with the given node as its root.
     public IntTree(IntTreeNode overallRoot) {
         this.overallRoot = overallRoot;

     }

    private void printLevelOrder(IntTreeNode root)
    {

        for (int i = 1; i <= 5; i++) {
            if (i == 1) { // if level equals 1
                for (int j = 0; j < 31; j++) {
                    System.out.print(" "); // print this many spaces
                }
            }
            else if (i == 2) {
                for (int j = 0; j < 19; j++) {
                    System.out.print(" ");
                }
            }
            else if (i == 3) {
                for (int j = 0; j < 12; j++) {
                    System.out.print(" ");
                }
            }
            else if (i == 4) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(" ");
                }
            }
            else if (i == 5) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(" ");
                }
            }
            printGivenLevel(root, i);
            System.out.println();
        }
    }
    /* Print nodes at a given level */
    public void printGivenLevel(IntTreeNode root, int level)
    {
        if (root == null)
            return;
        if (level == 1) {

            System.out.print(root.data);
            //System.out.print("");
        }
        else if (level == 2)
        {

            printGivenLevel(root.left, level-1);

            for (int j = 0; j < 12; j++) {
                System.out.print(" ");
            }
            if (root.right == null) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(" ");
                }
            }

            printGivenLevel(root.right, level-1);
        }
        else if (level == 3)
        {

            printGivenLevel(root.left, level-1);


            for (int j = 0; j < 1; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < 11; j++) {
                //System.out.print("\b");
            }

            printGivenLevel(root.right, level-1);
        }
        else if (level == 4)
        {
            printGivenLevel(root.left, level-1);
            for (int j = 0; j < 34; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 6; j++) {
                System.out.print("\b");
            }
            printGivenLevel(root.right, level-1);
        }
        else if (level == 5)
        {
            printGivenLevel(root.left, level-1);
            for (int j = 0; j < 40; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2; j++) {
                System.out.print("\b");
            }
            printGivenLevel(root.right, level-1);
        }
    }


    public void printSideways() {
        printSideways(overallRoot, " ");
    }

    private void printSideways(IntTreeNode root, String indent) {
        if (root != null) {
            printSideways(root.right, indent + "    ");
            System.out.println(indent + root.data);
            printSideways(root.left, indent + "    ");
        }

    }

    // PRINT
    public void print() {
        print(overallRoot);
        System.out.println();
    }

    private void print(IntTreeNode root) {
        if (root != null) {
            print(root.left);
            System.out.println(root.data + " ");
            print(root.right);
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


