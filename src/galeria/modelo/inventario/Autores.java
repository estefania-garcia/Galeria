package galeria.modelo.inventario;

import java.util.LinkedList;
import java.util.List;

public class Autores {
	
	private String nombre;
	private List<Piezas> listaPiezas;
	
	public Autores(String nombre) {
		
		this.nombre = nombre;
		listaPiezas = new LinkedList<Piezas>();
	}
	
	public void agregarPiezas(Piezas pieza) {
		
		listaPiezas.add(pieza);
	}
	
	public List<Piezas> getListaPiezas(){
		
		return listaPiezas;
	}
	
	public String getNombre() {
		
		return nombre;
	}
}
