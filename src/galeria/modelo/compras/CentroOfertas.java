package galeria.modelo.compras;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.Operador;

/**
 * Esta clase recolecta todas las ofertas hechas por los usuarios y las organiza según su tipo
 * Sirve como base para que las otras partes del sistema puedan tener un acceso ordenado a estas ofertas
 * */

public class CentroOfertas {
	
	/**
	 * Atributo que nos da acceso a la clase operador
	 * */
	private Operador operador;
	
	/**
	 * Atributo que nos da acceso a la clase inventario
	 * */
	private Inventario inventario;
	
	/**
	 * Lista que recibe objetos de tipo Ofertas y que guarda todas las ofertas de tipo subasta
	 * */
	private List<Ofertas> ofertasSubasta;
	
	/**
	 * Lista que recibe objetos de tipo Ofertas y que guarda todas las ofertas de tipo venta
	 * */
	private List<Ofertas> ofertasVenta;
	
	/**
	 * Inicializa la lista de ofertas subastas
	 * */
	public CentroOfertas() {
		ofertasSubasta = new LinkedList<Ofertas>();
		ofertasVenta = new LinkedList<Ofertas>();
	}
	
	/**
	 * Método que discriman las ofertas que recibe según su tipo
	 * Las de tipo subasta las guarda en la lista al igual que las de tipo venta. Para las ultimas se verifica que no este bloqueda.
	 * @return
	 * */
	public void agregarOfertas(Ofertas oferta) {
		if(oferta.tipoOferta().equals("Oferta Subasta")){
			ofertasSubasta.add(oferta);
		}else if(oferta.getPiezas().getEstado() != "Bloqueada"){
			Piezas pieza = oferta.getPiezas();
			inventario.modificarEstado("Bloqueada", pieza);
			inventario.añadirPiezasBloqueadas(pieza);
			ofertasVenta.add(oferta);
		}
	}
	
	/**
	 * Método getter de la lista subasta
	 * @return ofertaSubasta
	 * */
	public List<Ofertas> getOfertasSubasta(){
		return ofertasSubasta;
	}
	
	/**
	 * Método getter de la lista venta
	 * @return ofertaVenta 
	 * */
	public List<Ofertas> getOfertaVenta() {
		return ofertasVenta;
	}
	
	/**
	 * Método que recorre la lista de ofertas subasta y se las pasa a operador por medio del metodo agregarOfertas para que este las registre
	 * @return
	 * */
	public void agregarOfertasSubasta() {
		
		Iterator<Ofertas> iterador = ofertasSubasta.iterator();
    	while(iterador.hasNext()) {
    		Ofertas oferta = iterador.next();
    		if(oferta.validarPrecio() == true && oferta.verificarMonto() == true) {
    			operador.agregarOfertas(oferta);
    		}
    	}
	}
	
}
