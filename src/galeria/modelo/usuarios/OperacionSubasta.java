package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	private List<Ofertas> listaOfertasFinales;
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
	 * */
	public OperacionSubasta(Galeria galeria) {
		
		listaOfertas = new ArrayList<Ofertas>();
		listaOfertasFinales = new ArrayList<Ofertas>();
		mapaSubastas = new HashMap<>();
		this.galeria = galeria;
	}
	
	/**
	 * Método que agrega piezas que van a estar disponibles en la subasta
	 * */
	public void agregarPiezasSubasta(Piezas piezas) {
		
		if(mapaSubastas.get(piezas) == null) {
			mapaSubastas.put(piezas, listaOfertas);
		}
	}
	
	/**
	 * Método que agrega ofertas a las piezas que están disponibles para la subasta
	 * */
	public void agregarOfertas(Ofertas ofertas){
		
		Iterator<Map.Entry<Piezas, List<Ofertas>>> iterador = mapaSubastas.entrySet().iterator();
    	while(iterador.hasNext()) {
    		Map.Entry<Piezas, List<Ofertas>> mapa = iterador.next();
    		Piezas clave = mapa.getKey();
    		List<Ofertas> llave = mapa.getValue();
    		if(clave == ofertas.getPiezas()) {
    			if(llave.size() > 0) {
    				int pos = llave.size() - 1;
        			Ofertas ul_oferta = llave.get(pos);
        			if(ul_oferta.getMonto() < ofertas.getMonto()) {
        				llave.add(ofertas);
        			}
    			}else {
    				llave.add(ofertas);
    			}
    		}
    	}
	}
	
	/**
	 * Método que finaliza una subasta y pasa la ultima ofer (la mayor) a la clase galeria
	 * */
	public void finalizarSubasta(Piezas pieza) {
		
		List<Ofertas> lista = mapaSubastas.get(pieza);
		if(lista.size() > 0) {
			Ofertas oferta_final = lista.get(lista.size()-1);
			mapaSubastas.remove(pieza);
			if(oferta_subastas.venderPieza() == true) {
				listaOfertasFinales.add(oferta_final);
				galeria.getOfertasFinales(oferta_final);
			}
		}
	}
	
	/**
	 * Método para rechazar una oferta
	 * */
	public void recharOfertaFinal(Ofertas oferta) {
		
		listaOfertasFinales.remove(oferta);
	}
	
	/**
	 * Método getter del mapa de las subastas
	 * */
	public Map<Piezas, List<Ofertas>> getMapa(){
		
		return mapaSubastas;
	}
	
	/**
	 * Método getter de las ofertas finales de las subastas
	 * */
	public List<Ofertas> getofertaFinalesSubasta(){
		
		return listaOfertasFinales;
	}
}