package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que reúne a todos los autores que se hayan registrado en el sistema. 
 * Sirve como un contenedor de informacion
 * */
public class CentroAutores {
	
	/**
	 * Lista de todos los autores
	 * */
	private List<Autores> listaAutores;
	
	/**
	 * Constructor que inicializa la lista de los autores
	 * */
	public CentroAutores() {
		
		listaAutores = new ArrayList<Autores>();
	}
	
	/**
	 * Mpetodo que agrega a nuevo autores verificando que no esten repetidos
	 * */
	public void guardarNuevoAutor(Autores autor) {
		
		boolean aprobar = true;
		for(Autores au : listaAutores) {
			if(au.getNombre().equals(autor.getNombre())) {
				aprobar = false;
			}
		}
		if(aprobar == true) {
			listaAutores.add(autor);
		}
	}
	
	/**
	 * Método getter de la lista de los autores
	 * */
	public List<Autores> getListaAutores(){
		
		return listaAutores;
	}
	
	/**
	 * Método que sirve para buscar a un autor por su nombre
	 * */
	public Autores buscarAutor(String nombre) {
		
		Iterator<Autores> iterador = listaAutores.iterator();
		while(iterador.hasNext()) {
			Autores autor = iterador.next();
			if(autor.getNombre().equals(nombre)) {
				return autor;
			}else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Método que permite crear a un autor y llevarlo al método de agregación
	 * */
	public Autores crearAutor(String nombre) {
		
		Autores autor = new Autores(nombre);
		guardarNuevoAutor(autor);
		return autor;
	}
	
	/**
	 * Método que agrega peizas según el autor
	 * */
	public void añadirPiezas(String nombre, Piezas pieza) {
		
		Autores autor = null;
		for(Autores autores : listaAutores) {
			if(autores.getNombre().equals(nombre)) {
				autor = autores;
			}
		}
		autor.agregarPiezas(pieza);
	}
}
