package lab4;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Creates a MyBinaryTree object that stores unsorted nodes in a binary tree.
 *
 * @param <E> the data type of the elements in the tree
 * @author Abby Pitcairn
 * @version October 18, 2025
 */
public class MyBinaryTree<E extends Comparable<E>> {

    /**
     * Root Node of the tree
     */
    protected Node<E> root;

    /**
     * Nested class for a Node object
     *
     * @param <E> the data type of the element in the node
     */
    protected static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Recursively searches for the next available insertion spot
     * and inserts a new node with the given value to that space
     * in the tree.
     *
     * @param value - the value to insert into the tree.
     */
    public void insert(E value) {
        root = insertRecursively(root, value);
    }

    /**
     * Recursive helper for insertion.
     *
     * @param current - the current Node being evaluated.
     * @param value   - the value to insert into the tree.
     */
    private Node<E> insertRecursively(Node<E> current, E value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.data) < 0) {
            current.left = insertRecursively(current.left, value);
        } else if (value.compareTo(current.data) > 0) {
            current.right = insertRecursively(current.right, value);
        }
        return current;
    }

    public boolean search(E target) {
        return recursiveSearch(root, target);
    }

    private boolean recursiveSearch(Node<E> current, E target) {
        if (current == null) {
            return false;
        }
        int cmp = target.compareTo(current.data);
        if (cmp < 0) {
            return recursiveSearch(current.left, target);
        } else if (cmp > 0) {
            return recursiveSearch(current.right, target);
        } else {
            return true;
        }
    }

    /**
     * Helper function to quickly build a tree from a List of elements.
     *
     * @param elements - a List of data type E to be added to the tree.
     */
    public void buildTree(List<E> elements) {
        for (E element : elements) {
            insert(element);
        }
    }

    public void breadthFirstPrint() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<E> node = q.remove();
            System.out.printf("%s ", node.data);
            if (node.left != null) {
                q.add(node.left);
            }

            if (node.right != null) {
                q.add(node.right);
            }
        }
        System.out.println();
    }
}
