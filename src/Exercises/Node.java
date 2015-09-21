package Exercises;

import java.util.HashSet;
import java.util.Set;

public class Node {
	
	int root;
	Node left, right;

	public Node(int root) {
		this.root = root;
		left = null;
		right = null;
	}
	
	public Node(int root, Node left, Node right) {
		this.root = root;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * @param bintree
	 * @param indent
	 * Print out the binary tree
	 */
	public static void printTree(Node bintree, String indent) {
		if(bintree != null) {
			System.out.println(indent + bintree.root);
			printTree(bintree.left, indent + "l> ");
			printTree(bintree.right, indent + "r> ");
		}
	}
	
	public Set<Integer> set = new HashSet<Integer>();
	
	public void buildArray(Node bintree) {
		if(bintree != null) {
			set.add(bintree.root);
			buildArray(bintree.left);
			buildArray(bintree.right);
		}
	}
	
	/**
	 * @param bintree1
	 * @param bintree2
	 * Print out a list of common elements within two binary trees
	 */
	public static void intersect(Node bintree1, Node bintree2) {
		bintree1.buildArray(bintree1);
		bintree2.buildArray(bintree2);
		Set<Integer> intersect = new HashSet<Integer>();
		for(int i : bintree2.set) {
			if(bintree1.set.contains(i)) {
				intersect.add(i);
			}
		}
		System.out.println("Intersection: " + intersect);
	}
	
	/**
	 * @param bintree1
	 * @param bintree2
	 * Prints out a list of all elements contained within two binary trees
	 */
	public static void union(Node bintree1, Node bintree2) {
		bintree1.buildArray(bintree1);
		bintree2.buildArray(bintree2);
		Set<Integer> union = new HashSet<Integer>();
		union.addAll(bintree1.set);
		union.addAll(bintree2.set);
		System.out.println("Union: " + union);
	}
	
	public static void difference(Node bintree1, Node bintree2) {
		bintree1.buildArray(bintree1);
		bintree2.buildArray(bintree2);
		Set<Integer> diff = new HashSet<Integer>();
		diff.addAll(bintree1.set);
		for(int i : bintree2.set) {
			if(diff.contains(i)) {
				diff.remove(i);
			} 
		}
		System.out.println("Difference: " + diff);
	}
	
	public static void main(String[] args) {
		Node leaf1 = new Node(1);
		Node leaf2 = new Node(2);
		Node leaf3 = new Node(3);
		
		Node tree1 = new Node(10, new Node(178), new Node(77, leaf2, leaf3));
		Node tree2 = new Node(11, leaf1, new Node(9, leaf2, leaf3));
		
		System.out.println("Tree 1:\n");
		Node.printTree(tree1, "");
		System.out.println("\nTree 2:\n");
		Node.printTree(tree2, "");
		System.out.println();
		
		Node.intersect(tree1, tree2);
		Node.union(tree1, tree2);
		Node.difference(tree1, tree2);
	}
	
}
