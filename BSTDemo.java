public class BSTDemo {
    public static void main(String[] args) {
        // Create a new binary search tree
        Tree<Integer, String> bst = new Tree<>();

        // Add some key-value pairs to the tree
        bst.add(10, "Process 10");
        bst.add(5, "Process 5");
        bst.add(15, "Process 15");

        // Check if a key exists in the tree
        boolean res = bst.contains(10);
        System.out.println("Contains 10? " + res); // Expected: true

        // Print the keys of the tree in inorder
        System.out.print("Inorder traversal: ");
        bst.printInorder(); // Expected: 5 10 15

        // Get and print the minimum key in the tree
        int min = bst.getMin();
        System.out.println("Minimum key: " + min); // Expected: 5

        // Remove a key from the tree
        bst.remove(10);
        System.out.println("After removing key 10:");

        // Print the keys again to see the change
        bst.printInorder(); // Expected: 5 15
    }
}
