package com.shagunakarsh.practice;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
    public int key;
    public Node left;
    public Node right;

    public Node(int key) {
        this.key = key;
        left=null;
        right=null;
    }
}

public class Tree {

    private Node root;

    public Tree() {
        this.root=null;
    }

    public Tree(int root) {
        this.root = new Node(root);
    }

    public static void main(String []args) {
        PrintStream ps = System.out;
        Tree t = new Tree(20);
        t.root.left=new Node(8);
        t.root.right=new Node(22);
        t.root.left.left=new Node(4);
        t.root.left.right=new Node(12);
        t.root.left.right.left=new Node(10);
        t.root.left.right.right=new Node(14);

        print(t.root);
        Node lca=lca(t, 22, 20);
        ps.print("lca: "+ lca.key);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

    }

    static Node lca(Tree t, int v1, int v2) {
        if(t==null || t.root==null) return null;

        return lca(t.root, v1, v2);
    }

    static Node lca(Node n, int v1, int v2) {
        if(v1<n.key && v2<n.key) {
            //left search
            return lca(n.left, v1, v2);
        } else if(v1>n.key && v2>n.key) {
            //right search
            return lca(n.right, v1, v2);
        } else return n;
    }

    static void print(Node root) {
        PrintStream ps = System.out;

        if(root != null) {
            print(root.left);
            ps.print(root.key);
            ps.print(" ");
            print(root.right);
        }

    }
}
