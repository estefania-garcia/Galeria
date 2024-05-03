package galeria.modelo.usuarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Piezas;

public class OperacionSubasta {
	
	/**
	 * Lista de las ofertas de la subasta que se van registrando en el mapa
	 * */
	private List<Ofertas> listaOfertas;
	/**
	 * Mapa que registra las ofertas de la subasta segun la pieza, donde las llaves son la pieza y el valor es la lista de las ofertas asociadas a esta.
	 * */
	private Map<Piezas, List<Ofertas>> mapaSubastas = new HashMap<>();
	/**
	 * Atributo que nos da acceso a la clase OfertaSubasta
	 * */
	private OfertaSubasta oferta_subastas;
	/**
	 * Atributo que nos da acceso a la clase Galeria
	 * */
	private Galeria galeria;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	public OperacionSubasta() {
		
		listaOfertas = new LinkedList<>();
		mapaSubastas = new HashMap<>();
	}
	
	/**
	 * Método que agrega piezas que van a estar disponibles en la subasta
	 * @param piezas
	 * */
	public void agregarPiezasSubasta(Piezas piezas) {
		if(mapaSubastas.get(piezas) == null) {
			mapaSubastas.put(piezas, listaOfertas);
		}
	}
	
	/**
	 * Método que agrega ofertas a las piezas que están disponibles para la subasta
	 * @param ofertas
	 * */
	public void agregarOfertas(Ofertas ofertas){
		Iterator<Map.Entry<Piezas, List<Ofertas>>> iterador = mapaSubastas.entrySet().iterator();
    	while(iterador.hasNext()) {
    		Map.Entry<Piezas, List<Ofertas>> mapa = iterador.next();
    		Piezas clave = mapa.getKey();
    		List<Ofertas> llave = mapa.getValue();
    		if(clave == ofertas.getPiezas()) {
    			int pos = llave.size() - 1;
    			Ofertas ul_oferta = llave.get(pos);
    			if(ul_oferta.getMonto() < ofertas.getMonto()) {
    				llave.add(ofertas);
    			}
    		}
    	}
	}
	
	/**
	 * Método que verifica cual fue la oferta mayor de una pieza en subasta y se la pasa a Galeria.
	 * @param ofertas
	 * */
	public void obtenerMayorOferta(Piezas pieza) {
		List<Ofertas> lista = mapaSubastas.get(pieza);
		Ofertas oferta_final = lista.get(lista.size()-1);
		if(oferta_subastas.venderPieza() == true) {
			galeria.validarOfertaFinal(oferta_final);
		}
	}
}
