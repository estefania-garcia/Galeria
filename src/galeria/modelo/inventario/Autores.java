package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.List;

public class Autores {
	
	private String nombre;
	private List<Piezas> listaPiezas;
	
	public Autores(String nombre) {
		
		this.nombre = nombre;
		listaPiezas = new ArrayList<Piezas>();
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
