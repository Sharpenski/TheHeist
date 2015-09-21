package Exercises;

public class G4G2 {

	public G4G2() {
		
	}
	
	public static void main(String[] args) {
		Node leaf1 = new Node(1);
		Node leaf2 = new Node(2);
		Node leaf3 = new Node(3);
		
		Node tree1 = new Node(10, leaf1, new Node(9, leaf2, leaf3));
		Node tree2 = new Node(11, leaf1, new Node(9, leaf2, leaf3));
		
		System.out.println("Tree 1:\n");
		Node.printTree(tree1, "");
		System.out.println("\nTree 2:\n");
		Node.printTree(tree2, "");
		System.out.println();
		
		tree1.buildArray(tree1);
		System.out.println(tree1.set);
		
		Node.intersect(tree1, tree2);
	}

}
