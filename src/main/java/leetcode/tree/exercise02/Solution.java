package leetcode.tree.exercise02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem
 */

class Node {
    int key;
    Node parent;
    Node leftChild;
    Node rightChild;
    int level;
}

class SearchTree {

    Node root;

    void insert(int key) {
        if (root == null) {
            root = new Node();
            root.key = key;
            return;
        }

        Node current = root;
        while (current != null) {
            if (key < current.key) {
                if (current.leftChild == null) {
                    Node leftChild = new Node();
                    leftChild.key = key;
                    leftChild.parent = current;
                    current.leftChild = leftChild;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    Node rightChild = new Node();
                    rightChild.key = key;
                    rightChild.parent = current;
                    current.rightChild = rightChild;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    /**
     * Needs to subtract 1, cause the exercise counts root as level 0
     */
    int heightRecursive(Node root) {

        if (root == null) {
            return 0;
        }

        int leftTHeight = heightRecursive(root.leftChild);
        int rightTHeight = heightRecursive(root.rightChild);
        return Integer.max(leftTHeight, rightTHeight) + 1;
    }

    int height(Node root) {
        if (root == null) {
            return -1;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.leftChild != null) {
                    queue.add(current.leftChild);
                }
                if (current.rightChild != null) {
                    queue.add(current.rightChild);
                }
            }
            height++;

        }
        return height - 1;
    }
}


public class Solution {

    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//
//        int n =  sc.nextInt();
//        SearchTree tree = new SearchTree();
//        for (int i = 0; i < n; i++) {
//            int key = sc.nextInt();
//            tree.insert(key);
//        }

        SearchTree tree = new SearchTree();
        tree.insert(3);
        tree.insert(1);
        tree.insert(7);
        tree.insert(5);
        tree.insert(4);
        //   3
        //1      7
        //     5
        //   4

        System.out.println(tree.height(tree.root));

        //sc.close();
    }
}
