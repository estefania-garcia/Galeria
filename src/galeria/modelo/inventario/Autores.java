package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para cear autores y referenciar las obras que ha hecho
 * */
public class Autores {
	
	/**
	 * Atributo que indica el nombre del autor
	 * */
	private String nombre;
	
	/**
	 * Lista de las piezas que ha creado
	 * */
	private List<Piezas> listaPiezas;
	
	/**
	 * Constructor que recibe el nombre e incializa la lista de las piezas
	 * */
	public Autores(String nombre) {
		
		this.nombre = nombre;
		listaPiezas = new ArrayList<Piezas>();
	}
	
	/**
	 * Método para añadir nuevas piezas
	 * */
	public void agregarPiezas(Piezas pieza) {
		
		listaPiezas.add(pieza);
	}
	
	/**
	 * Método getter de la lista de piezas
	 * */
	public List<Piezas> getListaPiezas(){
		
		return listaPiezas;
	}
	
	/**
	 * Método getter del nombre del autor
	 * */
	public String getNombre() {
		
		return nombre;
	}
}
