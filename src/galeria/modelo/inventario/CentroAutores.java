package galeria.modelo.inventario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CentroAutores {
	
	private List<Autores> listaAutores;
	
	public CentroAutores() {
		
		listaAutores = new LinkedList<Autores>();
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
}
