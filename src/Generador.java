public class Generador {
	
	private String name;
	private String[] producciones; 
	
	public Generador(String[] input){
		this.name = input[0];
		producciones = new String[input.length-1];
		for(int i =1; i<input.length;i++) {
			producciones[i-1]=input[i];
		}
	}
	
	public Generador(String name) {
		this.name = name;
		producciones = new String[0];
	}
	
	public String[] getProduccion() {
		return this.producciones;
	}
	
	public String getName() {
		return this.name;
	}
	
	
}
