package com.library.datastructures.tree;

import com.library.datastructures.core.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {

    private TreeNode<E> root;

    public BinarySearchTree(E element) {
        this.root = new TreeNode(element);
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void insert(E element) {
        insert(this.root, element);
    }

    /**
     * Insert a node to the binary search tree
     */
    public void insert(TreeNode<E> currentNode, E element) {

        if (currentNode == null || element == null) { return; }

        // currentNode.element > element.element
        if (currentNode.element.compareTo(element) > 0) {
            if (currentNode.left == null) {
                currentNode.left = new TreeNode(element);
                System.out.println("Added node " + element + " to left of " + currentNode.element);
            } else {
                insert(currentNode.left, element);
            }

            // currentNode.element < element.element
        } else if (currentNode.element.compareTo(element) < 0) {
            if (currentNode.right == null) {
                currentNode.right = new TreeNode(element);
                System.out.println("Added node " + element + " to right of " + currentNode.element);
            } else {
                insert(currentNode.right, element);
            }
        }
    }

    public void delete(E element) {
        delete(this.root, element);
    }

    /**
     * Delete a node from the binary search tree
     */
    public TreeNode<E> delete(TreeNode<E> root, E element) {
        if (root == null) {
            throw new RuntimeException("Tree is null !");
        }

        TreeNode<E> temp = null;

        // root.element > element
        if(root.element.compareTo(element) > 0) {
            root.left = delete(root.left, element);
        }
        // root.element < element
        else if(root.element.compareTo(element) < 0) {
            root.right = delete(root.right, element);
        }
        else {
            if(root.left != null && root.right != null) {
                // Replace with the largest element in the left subtree
                temp = findMaximum(root.left);
                root.element = temp.element;
                root.left = delete(root.left, root.element);
            }
            else {
                temp = root;
                if(root.left == null) {
                    root = root.right;
                }
                else if(root.right == null) {
                    root = root.left;
                }

                // free temp node
                temp = null;
            }
        }
        return root;
    }

    private TreeNode<E> findMaximum(TreeNode<E> root) {
        if(root == null) {
            return null;
        }

        while(root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Pre-Order Recursive Traversal
     */
    public static <E> void preOrderRecursive(TreeNode<E> root) {
        if (root != null) {
            System.out.print("  " + root.element);
            preOrderRecursive(root.left);
            preOrderRecursive(root.right);
        }
    }

    /**
     * In-Order Recursive Traversal
     */
    public static <E> void inOrderRecursive(TreeNode<E> root) {
        if (root != null) {
            inOrderRecursive(root.left);
            System.out.print("  " + root.element);
            inOrderRecursive(root.right);
        }
    }

    /**
     * Post-Order Recursive Traversal
     */
    public static <E> void postOrderRecursive(TreeNode<E> root) {
        if (root != null) {
            postOrderRecursive(root.left);
            postOrderRecursive(root.right);
            System.out.print("  " + root.element);
        }
    }

    public void preOrderNonRecursive() {

        TreeNode<E> currentNode = this.root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();

        while (true) {
            while (currentNode != null) {
                System.out.print("  " + currentNode.element);
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            currentNode = stack.pop();
            currentNode = currentNode.right;
        }

        stack.clear();
    }

    public void inOrderNonRecursive() {

        TreeNode<E> currentNode = this.root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();

        while (true) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            currentNode = stack.pop();
            System.out.print("  " + currentNode.element);
            currentNode = currentNode.right;
        }

        stack.clear();
    }

    public void postOrderNonRecursive() {

        TreeNode<E> currentNode = this.root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();

        while (true) {

            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                if (stack.isEmpty()) {
                    System.out.println("Stack is Empty !");
                    stack.clear();
                    return;
                } else {
                    if (stack.peek().right == null) {
                        currentNode = stack.pop();
                        System.out.print("  " + currentNode.element);

                        if (currentNode == stack.peek().right) {
                            System.out.print("  " + stack.peek().element);
                            stack.pop();
                        }
                    }
                }

                if (!stack.isEmpty()) {
                    currentNode = stack.peek().right;
                } else {
                    currentNode = null;
                }
            }
        }
    }

    public void postOrderNonRecursiveNew() {

        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        TreeNode<E> currentNode = this.root;

        do {
            while (currentNode != null) {
                if (currentNode.right != null) {
                    stack.push(currentNode.right);
                }
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();

            if (currentNode.right != null && !stack.isEmpty() && stack.peek() == currentNode.right) {
                stack.pop();
                stack.push(currentNode);
                currentNode = currentNode.right;
            } else {
                System.out.print("  " + currentNode.element);
                currentNode = null;
            }
        } while (!stack.isEmpty());
    }

    public void levelOrder() {

        TreeNode temp = null;
        LinkedList<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();

        if (root == null) {
            return;
        }

        queue.addLast(root);

        while (!queue.isEmpty()) {
            temp = queue.removeFirst();
            // process the current node
            System.out.print("  " + temp.element);
            if (temp.left != null) {
                queue.addLast(temp.left);
            }

            if (temp.right != null) {
                queue.addLast(temp.right);
            }
        }

        queue.clear();
    }

    public TreeNode<E> binarySearchRecursive(E element) {
        return binarySearchRecursive(this.root, element);
    }

    public TreeNode<E> binarySearchRecursive(TreeNode<E> root, E element) {

        if (root == null || element == null) {
            return null;
        }

        // root.element > n.element
        if (root.element.compareTo(element) > 0) {
            return binarySearchRecursive(root.left, element);
        // root.element < n.element
        } else if (root.element.compareTo(element) < 0) {
            return binarySearchRecursive(root.right, element);
        }

        return root;
    }

    public TreeNode<E> binarySearchNonRecursive(E element) {

        TreeNode<E> currentNode = this.root;
        if (currentNode == null) {
            return null;
        }

        while (currentNode != null) {
            // currentNode.element == element
            if (currentNode.element.compareTo(element) == 0) {
                return currentNode;
            // currentNode.element < element
            } else if (currentNode.element.compareTo(element) < 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        BinarySearchTree bts = new BinarySearchTree(10);
        bts.insert(20);
        bts.insert(5);
        bts.insert(4);
        bts.insert(5);
        bts.insert(15);
        bts.insert(25);
        bts.insert(6);

        System.out.println("\n Pre-Order Recursive: ");
        preOrderRecursive(bts.getRoot());
        System.out.println("\n In-Order Recursive: ");
        inOrderRecursive(bts.getRoot());
        System.out.println("\n Post-Order Recursive: ");
        postOrderRecursive(bts.getRoot());

        System.out.println("\n Pre-Order NonRecursive: ");
        bts.preOrderNonRecursive();
        System.out.println("\n In-Order NonRecursive: ");
        bts.inOrderNonRecursive();
        System.out.println("\n Post-Order NonRecursive: ");
        bts.postOrderNonRecursiveNew();
        System.out.println("\n Level-Order NonRecursive: ");
        bts.levelOrder();

        System.out.println("\n");
        System.out.println("\n Binary Search Recursive: "    + bts.binarySearchRecursive(10));
        System.out.println("\n Binary Search NonRecursive: " + bts.binarySearchNonRecursive(15));

        System.out.println("\n Delete Element from Binary Search Tree: 4");
        bts.delete(4);

        System.out.println("\n Pre-Order Recursive: ");
        preOrderRecursive(bts.getRoot());
    }
}
