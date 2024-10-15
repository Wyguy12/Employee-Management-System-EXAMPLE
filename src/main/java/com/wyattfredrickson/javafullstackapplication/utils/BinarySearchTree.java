package com.wyattfredrickson.javafullstackapplication.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * The BinarySearchTree class represents a binary search tree data structure for helping manage employees data efficiently
 * @param <K> The key type
 * @param <V> The value type
 */ 
public class BinarySearchTree<K extends Comparable<K>, V> {

    /**
     * The TreeNode class represents a node in the binary search tree
     */
    private class TreeNode<K, V> {
        K key;  // The key associated with the value
        V value; // The value associated with the key
        TreeNode<K, V> left; // The left child of the node
        TreeNode<K, V> right; // The right child of the node

        /**
         * Constructor for the TreeNode class
         * @param key The key
         * @param value The value
         */
        public TreeNode(K key, V value) {
            this.key = key; // Set the key to the key
            this.value = value;  // Set the value to the value
        }
    }

    /**
     * Begin searching at the root of the binary search tree 
     */
    private TreeNode<K, V> root; // The root of the binary search tree

    /**
     * Method to insert a key-value pair into the binary search tree
     * @param key The key
     * @param value The value
     */
    public void insert(K key, V value) {
        root = insert(root, key, value); // Insert the key-value pair into the binary search tree "root"
    }

    /**
     * Method to insert a key-value pair into the binary search tree
     * @param node The root of the binary search tree
     * @param key The key to insert
     * @param value The value to insert
     * @return The root of the binary search tree
     */
    private TreeNode<K, V> insert(TreeNode<K, V> node, K key, V value) {
        if (node == null) {
            return new TreeNode<>(key, value);
        }
        int compare = key.compareTo(node.key); // Compare the key to the node's key
        if (compare < 0) { // If the key is less than the node's key, insert it into the left subtree
            node.left = insert(node.left, key, value); // Insert the key into the left subtree
        } else if (compare > 0) { // If the key is greater than the node's key, insert it into the right subtree
            node.right = insert(node.right, key, value); // Insert the key into the right subtree
        } else {
            node.value = value; // Else update the value if the key already exists
        }
        return node; // Return the node

    }

    /**
     * Method for searching for a key in the binary search tree
     * @param key The key to search for
     * @return The node associated with the key
     */
    public V search(K key) {
        TreeNode<K, V> node = search(root, key); // Search for the key in the binary search tree
        return node == null ? null : node.value; // Return the value if the node is not null
    }

    /**
     * Method for searching for a key in the binary search tree
     * @param node The root of the binary search tree
     * @param key The key to search for
     * @return The node with the key
     */
    private TreeNode<K, V> search(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key); // Compare the key to the node's key
        if (compare < 0) { // If the key is less than the node's key, search the left subtree
            return search(node.left, key); // Search the left subtree
        } else if (compare > 0) { // If the key is greater than the node's key, search the right subtree
            return search(node.right, key); // Search the right subtree
        } else {
            return node; // Return the node if the key is found
        }
    }

    /**
     * Method to get the keys in the binary search tree
     * @param key The key to get
     */
    public void delete(K key) {
        root = delete(root, key); // Delete the key from the binary search tree
    }

    /**
     * Method to delete a key from the binary search tree
     * @param node The root of the binary search tree
     * @param key The key to delete
     * @return The root of the binary search tree
     */
    private TreeNode<K, V> delete(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key); // Compare the key to the node's key
        if (compare < 0) { // If the key is less than the node's key, delete the key from the left subtree
            node.left = delete(node.left, key); // Delete the key from the left subtree
        } else if (compare > 0) { // If the key is greater than the node's key, delete the key from the right subtree
            node.right = delete(node.right, key); // Delete the key from the right subtree
        } else {
            if (node.right == null) { // If the right child is null, return the left child
                return node.left;
            }
            TreeNode<K, V> temporaryNode = node; // Temporary node to store the node
            node = findMin(temporaryNode.right); // Find the minimum node in the right subtree
            node.right = deleteMin(temporaryNode.right); // Delete the minimum node from the right subtree
            node.left = temporaryNode.left;  // Set the left child to the temporary node's left child
        }
        return node; // Return the node
    }

    /**
     * Method to delete the minimum node from the binary search tree
     * @param node The root of the binary search tree
     * @return The root of the binary search tree
     */
    private TreeNode<K, V> findMin(TreeNode<K, V> node) {
        while (node.left != null) { // While the left child is not null
            node = node.left; // Assign the left child to the node
        }
        return node; // Return the node
    }

    /**
     * Method to delete the minimum node from the binary search tree
     * @param node The root of the binary search tree
     * @return The root of the binary search tree
     */
    private TreeNode<K, V> deleteMin(TreeNode<K, V> node) {
        if (node.left == null) { // If the left child is null
            return node.right; // Return the right child
        }
        node.left = deleteMin(node.left); // Delete the minimum node from the left subtree
        return node; // Return the node
    }

    /**
     * Method to get the keys in the binary search tree
     * @return The keys in the binary search tree
     */
    public List<V> inorder() {
        List<V> list = new ArrayList<>(); // Create a list to store the values
        inorder(root, list); // root, list is passed to the inorder method
        return list; // Return the list
    }

    /**
     * Method to get the keys in the binary search tree
     * @param node The root of the binary search tree
     * @param list The list to store the values
     */
    private void inorder(TreeNode<K, V> node, List<V> list) {
        if (node != null) { // If the node is not null
            inorder(node.left, list); // node.left, list is passed to the inorder method
            list.add(node.value); // Add the node value to the list
            inorder(node.right, list); // node.right, list is passed to the inorder method
        }
    }

}