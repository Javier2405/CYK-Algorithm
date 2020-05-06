import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CYK {
	
	private Generador[] generadores;
	
	public CYK(String[][] input, String cadena) throws IOException {
		this.generadores = new Generador[input.length+1];
		this.generadores[input.length]=new Generador("0");
		
		//CREAR LISTA DE GENERADORES
		for(int i = 0; i<input.length; i++) {
			this.generadores[i] = new Generador(input[i]);
		}
		
		//REVISAR GENERADORES Y PRODUCCIONES DE CADA UNO
		for(int i = 0; i<generadores.length; i++) {
			//System.out.println("Generador por revisar: " + this.generadores[i].getName());
			for(int j = 0; j<this.generadores[i].getProduccion().length;j++) {
				//System.out.println("genera: " + this.generadores[i].getProduccion()[j]);
			}
		}
		
		//------------------------------------------------------
		//-------------------CYK-------------------------------
		//------------------------------------------------------
				
		//CREAR TABLA PARA CYK VACIA
		V[][] cyk_tabla = new V[cadena.length()][cadena.length()];
		for(int i = 0; i<cyk_tabla.length; i++) {
			for (int j = 0; j<cyk_tabla[i].length; j++) {
				cyk_tabla[i][j] = new V(i+1,j+1);
			}
		}
		
		//ASIGNAR DIAGONAL PRINCIPAL
		for(int a =0; a<cadena.length();a++) {
			for(int i = 0; i<generadores.length; i++) {
				for(int j = 0; j<this.generadores[i].getProduccion().length;j++) {
					if(this.generadores[i].getProduccion()[j].equals(cadena.substring(a,a+1))) {
						cyk_tabla[a][a].setGenerador(this.generadores[i]);
						cyk_tabla[a][a].setTerminal(cadena.substring(a,a+1));
						
					}
				}
			}
		}
		
		//GENERAR EL RESTO DE LA TABLA CYK
		ArrayList<Generador> aux = null;
		ArrayList<Generador> aux2 = null;
		String par = ""; 
		for(int b = 1; b<cadena.length();b++) {
			for(int a = 0;a<cadena.length()-b;a++) {
				for(int i = 0; i<generadores.length; i++) {
					for(int j = 0; j<this.generadores[i].getProduccion().length;j++) {
						for (int x = 0; x<b;x++) {
							aux = cyk_tabla[a][a+x].getGeneradores();
							aux2 = cyk_tabla[a+x+1][a+b].getGeneradores();
							for(int z = 0; z<aux.size();z++) {
								for(int y = 0; y<aux2.size();y++) {
									par = aux.get(z).getName() + aux2.get(y).getName();
									if(par.equals(this.generadores[i].getProduccion()[j])) {
										//System.out.println("par: "+par);
										//System.out.println("a: "+a+" a+b: "+ num);
										//System.out.println(this.generadores[i].getName());
										if(!cyk_tabla[a][a+b].getGeneradores().contains(this.generadores[i])) {
											cyk_tabla[a][a+b].setPar(aux.get(z),aux2.get(y),cyk_tabla[a][a+x],cyk_tabla[a+x+1][a+b]);
											cyk_tabla[a][a+b].setGenerador(this.generadores[i]);
										}
									}
								}
							}
						}
						
					}
				}
				if(cyk_tabla[a][a+b].getGeneradores().size()==0) {
					cyk_tabla[a][a+b].setGenerador(this.generadores[this.generadores.length-1]);
				aux = null;
				aux2 = null;
				par = "";
				}
			}
		}
		
		//IMPRIMIR TABLA CYK
		for(int i = 0; i<cyk_tabla.length;i++) {
			for(int j =0; j<cyk_tabla[i].length; j++) {
				System.out.print(cyk_tabla[i][j].showIt()+ " , ");
			}
			System.out.println();
		}
		
		//------------------------------------------------------
		//-------------------Backtracking-----------------------
		//------------------------------------------------------
		
		System.out.println();
		System.out.println();
		
		//CREAR ESTRUCTURA DE DATOS DE ARBOL
		BinaryTree arbol = null;
		
		//CREAR DOC TXT
		//INICIALIZAMOS EL ARCHIVO TXT
		PrintWriter pw = new PrintWriter(new FileWriter("D:\\5to semestre\\Matematicas computacionales\\Ejemplo.txt"));
		pw.println("digraph {");
		
		//LLAMAR FUNCION BACKTRACKING
		if(!cyk_tabla[0][cadena.length()-1].getGeneradores().contains(this.generadores[0])) {
			System.out.println("La cadena no pertenece a la gramatica.");
		}else {
			for(int i = 0; i<cyk_tabla[0][cadena.length()-1].getGeneradores().size();i++) {
				if(cyk_tabla[0][cadena.length()-1].getGeneradores().get(i).getName() == "S") {
					arbol = new BinaryTree(cyk_tabla[0][cadena.length()-1].getGeneradores().get(i),cyk_tabla[0][cadena.length()-1]);
					//System.out.print(cyk_tabla[0][cadena.length()-1].getGeneradores().get(i).getName());
					backtracking(cyk_tabla[0][cadena.length()-1],cyk_tabla[0][cadena.length()-1].getGeneradores().get(i),arbol.getRoot(),pw);
				}
			}
			pw.println("}");
			pw.close();
		}		
	}
	
	//FUNCION RECURSIVA PARA EXPLORAR EL ARBOL COMPLETO
	public void backtracking(V v1,Generador Gen1, Node root,PrintWriter pw) {
		if(v1.getX() != v1.getY()) {
			for(int i = 0; i<v1.getPar().size();i++) {
				if(v1.getGeneradores().get(i).getName().equals(Gen1.getName())) {
					root.setLeft(new Node(v1.getPar().get(i).getGen1(),v1.getPar().get(i).getV1()));
					root.setRight(new Node(v1.getPar().get(i).getGen2(),v1.getPar().get(i).getV2()));
					pw.println(Gen1.getName()+v1.getX()+v1.getY()+"[label=\""+ Gen1.getName()+"\"]");
					pw.println(Gen1.getName()+v1.getX()+v1.getY()+"->"+root.getLeft().getKey());
					pw.println(Gen1.getName()+v1.getX()+v1.getY()+"->"+root.getRight().getKey());
					
					backtracking(v1.getPar().get(i).getV1(),v1.getPar().get(i).getGen1(),root.getLeft(),pw);
					backtracking(v1.getPar().get(i).getV2(),v1.getPar().get(i).getGen2(),root.getRight(),pw);
				}
			}
		}else {
			pw.println(Gen1.getName()+v1.getX()+v1.getY()+"[label=\""+ Gen1.getName()+"\"]");
			pw.println(v1.getTerminal()+v1.getX()+v1.getY()+"[label=\""+ v1.getTerminal()+"\"]");
			pw.println(Gen1.getName()+v1.getX()+v1.getY()+"->"+v1.getTerminal()+v1.getX()+v1.getY());
		}
	}
	
	public static void main(String[] args) throws IOException {
		//input en FNC
		
		//EJEMPLO TAREA EXTRA
		String cadena = "aabbab";
		String[][] input = {{"S","CB","DA","AD","BC","AB","BA"},{"A","a"},{"B","b"},{"C","AS"},{"D","BS"}};
		
		
		//EJEMPLO TAREA CH
		//String[][] input = {{"S","AB","BA"},{"A","a","AB"},{"B","b","AA"}};
		//String cadena = "baaaaab";
		
		CYK cyk_alg = new CYK(input,cadena);
	}
}
