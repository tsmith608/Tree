public class Tree<K, V> implements BinarySearchTreeI {

    // Node class representing a tree node with key-value pair
    private class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left, right;

        // Constructor to initialize the node with key and value
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    // Root of the binary search tree
    private Node<K, V> root;

    // Constructor initializes the tree with no nodes
    Tree() {
        root = null;
    }

    // Adds a node with a key-value pair to the tree recursively
    private Node<K, V> add(Node<K, V> curr, K key, V value) {
        if (curr == null) // If current node is null, create a new node
            return new Node<K, V>(key, value);

        // Compare the key to determine whether to go left or right in the tree
        if (((Comparable) key).compareTo(curr.key) < 0)
            curr.left = add(curr.left, key, value); // Go left if key is smaller
        else if (((Comparable) key).compareTo(curr.key) > 0)
            curr.right = add(curr.right, key, value); // Go right if key is larger

        return curr;
    }

    // Searches for a key in the tree and returns the node if found
    private Node<K, V> SearchTree(Node<K, V> curr, K key) {
        if (curr == null) {
            return null;
        }
        if (((Comparable) key).compareTo(curr.key) == 0) {
            return curr; // Key found
        }
        if (((Comparable) key).compareTo(curr.key) < 0) {
            return SearchTree(curr.left, key); // Search left
        }
        return SearchTree(curr.right, key); // Search right
    }

    @Override
    // Checks if the tree contains a key
    public boolean contains(Object key) {
        Node<K, V> tmp = SearchTree(root, (K) key);
        return tmp != null; // Return true if node is found
    }

    @Override
    // Retrieves the value associated with a given key
    public V get(Object key) {
        Node<K, V> tmp = SearchTree(root, (K) key);
        if (tmp == null)
            return null; // Return null if key is not found
        return tmp.value;
    }

    // Prints the keys of the tree in inorder
    public void printInorder() {
        inOrderTraversal(root);
        System.out.println("\n");
    }

    // Helper method for inorder traversal of the tree
    private void inOrderTraversal(Node<K, V> curr) {
        if (curr != null) {
            inOrderTraversal(curr.left); // Traverse left subtree
            System.out.print(curr.key + " "); // Visit current node
            inOrderTraversal(curr.right); // Traverse right subtree
        }
    }

    // Finds the node with the minimum key in the tree
    public Node<K, V> minimumKey(Node<K, V> curr) {
        while (curr.left != null) // Move left to find the minimum
            curr = curr.left;
        return curr;
    }

    // Returns the minimum key in the tree
    public K getMin() {
        return minimumKey(root).key;
    }

    // Finds the node with the maximum key in the tree
    public Node<K, V> maximumKey(Node<K, V> curr) {
        while (curr.right != null) // Move right to find the maximum
            curr = curr.right;
        return curr;
    }

    @Override
    // Adds a new key-value pair to the tree
    public void add(Object key, Object obj) {
        root = add(root, (K) key, (V) obj);
    }

    // Deletes a node with a given key from the tree
    public void delete(Node<K, V> root, K key) {
        Node<K, V> parent = null;
        Node<K, V> curr = root;

        while (curr != null && ((Comparable) key).compareTo(curr.key) != 0) {
            parent = curr;
            // Move left or right based on comparison
            if ((curr != null && ((Comparable) key).compareTo(curr.key) < 0)) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (curr == null) // Key not found
            return;

        // Case 1: Node has no children (leaf node)
        if (curr.left == null && curr.right == null) {
            if (curr == root) {
                root = null; // If it's the root, make root null
            } else {
                if (parent.left == curr) {
                    parent.left = null; // Unlink from parent
                } else {
                    parent.right = null; // Unlink from parent
                }
            }
        }
        // Case 2: Node has two children
        else if (curr.left != null && curr.right != null){
            Node<K,V> successor = minimumKey(curr.right); // Find inorder successor
            K successorKey = successor.key;
            V successorValue = successor.value;

            delete(root, successorKey); // Delete the successor node

            curr.key = successorKey; // Replace current node's key and value with successor's
            curr.value = successorValue;
        }
        // Case 3: Node has one child
        else {
            Node<K,V> child = (curr.left == null) ? curr.right : curr.left;
            if(curr == root){
                root = child; // Replace root with child
            }else {
                if (curr == parent.left){
                    parent.left = child; // Unlink current node from parent and link child
                }else{
                    parent.right = child; // Unlink current node from parent and link child
                }
            }
        }
    }

    @Override
    // Removes a node with the specified key from the tree
    public void remove (Object key){
        delete(root, (K) key);
    }
}
