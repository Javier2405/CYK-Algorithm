public class Node {
	
	private String key;
	private String toPrint;
	private Node left,right;
	

	public Node(Generador gen, V casilla) {
		this.key = gen.getName() + Integer.toString(casilla.getX()) + Integer.toString(casilla.getY());
		this.left = this.right = null;
		if(casilla.getX() == casilla.getY()) {
			this.toPrint = casilla.getTerminal();
		} else {
			this.toPrint = gen.getName();
		}
	}
	
	public String getKey() {
		return this.key;
	}
	
	public void setRight(Node nodo) {
		this.right = nodo;
	}
	
	public void setLeft(Node nodo) {
		this.left = nodo;
	}
	
	public String getPrint() {
		return this.toPrint;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
}
