
public class BinaryTree {
	private Node root;
	
	public BinaryTree(Generador gen, V casilla) {
		this.root = new Node(gen, casilla);
	}
	
	public Node getRoot() {
		return this.root;
	}
	
}
