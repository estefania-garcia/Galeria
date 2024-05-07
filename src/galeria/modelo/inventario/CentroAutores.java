package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CentroAutores {
	
	private List<Autores> listaAutores;
	
	public CentroAutores() {
		
		listaAutores = new ArrayList<Autores>();
	}
	
	public void guardarNuevoAutor(Autores autor) {
		
		listaAutores.add(autor);
	}
	
	public List<Autores> getListaAutores(){
		
		return listaAutores;
	}
	
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
	
	public void crearAutor(String nombre) {
		
		Autores autor = new Autores(nombre);
		guardarNuevoAutor(autor);
	}
	
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
