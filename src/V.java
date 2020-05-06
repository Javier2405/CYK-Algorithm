import java.util.ArrayList;

public class V {
	
	private ArrayList<Generador> generadores;
	private String name;
	private ArrayList<Par> par_prod;
	private int x_cord,
				y_cord;
	private String terminal;
	
	public V(int i, int j){
		this.name = Integer.toString(i) + "," + Integer.toString(j);
		generadores = new ArrayList<Generador>();
		par_prod = new ArrayList<Par>();
		this.x_cord = i;
		this.y_cord = j;
		
	}
	
	public int getX() {
		return this.x_cord;
	}
	
	public int getY() {
		return this.y_cord;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setGenerador(Generador gen) {
		this.generadores.add(gen);
	}
	
	public String showIt() {
		String aux = "";
		for(int i =0; i<generadores.size();i++) {
			aux += generadores.get(i).getName();
		}
		return aux;
	}
	
	public ArrayList<Generador> getGeneradores() {
		return this.generadores;
	}
	
	public void setPar(Generador gen1, Generador gen2, V v1, V v2) {
		Par aux = new Par(gen1,gen2,v1,v2);
		//System.out.println("casilla: "+ this.name + " viene de: " +aux.getGen1().getName() +" y "+ aux.getGen2().getName());
		this.par_prod.add(aux);
	}
	
	public ArrayList<Par> getPar() {
		return this.par_prod;
	}
	
	public String getTerminal() {
		return this.terminal;
	}
	
	public void setTerminal(String ter) {
		this.terminal = ter;
	}
	
}
