package galeria.modelo.compras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.OperacionSubasta;
/**
 * Esta clase recolecta todas las ofertas hechas por los usuarios y las organiza según su tipo
 * Sirve como base para que las otras partes del sistema puedan tener un acceso ordenado a estas ofertas
 * */

public class CentroOfertas {
	
	/**
	 * Atributo que nos da acceso a la clase operador
	 * */
	private OperacionSubasta operador;
	
	/**
	 * Atributo que nos da acceso a la clase inventario
	 * */
	private Inventario inventario;
	
	/**
	 * Lista que recibe objetos de tipo Ofertas y que guarda todas las ofertas de tipo subasta
	 * */
	private List<Ofertas> ofertasSubasta;
	
	private List<Ofertas> totalOfertas;
	
	/**
	 * Lista que recibe objetos de tipo Ofertas y que guarda todas las ofertas de tipo venta
	 * */
	private List<Ofertas> ofertasVenta;
	
	private Galeria galeria;
	
	/**
	 * Inicializa la lista de ofertas subastas
	 * */
	public CentroOfertas(OperacionSubasta operador, Galeria galeria, Inventario inventario) {
		
		this.operador = operador;
		ofertasSubasta = new ArrayList<Ofertas>();
		totalOfertas = new ArrayList<Ofertas>();
		ofertasVenta = new ArrayList<Ofertas>();
		this.galeria = galeria;
		this.inventario = inventario;
	}
	
	/**
	 * Método que discriman las ofertas que recibe según su tipo
	 * Las de tipo subasta las guarda en la lista al igual que las de tipo venta. Para las ultimas se verifica que no este bloqueda.
	 * @return
	 * */
	public void agregarOfertas(Ofertas oferta) {
		
		boolean aprobar = true;
		for(Ofertas off : totalOfertas) {
			if(off.getComprador().getInversor().getUsuario().equals(oferta.getComprador().getInversor().getUsuario()) && off.getMonto() == oferta.getMonto() && off.getPiezas().getTitulo().equals(oferta.getPiezas().getTitulo())) {
				aprobar = false;
			}
		}
		if(aprobar == true) {
			totalOfertas.add(oferta);
			if(oferta.tipoOferta().equals("Oferta Subasta")){
				ofertasSubasta.add(oferta);
				agregarOfertasSubasta();
			}
			else if(oferta.tipoOferta().equals("Oferta Venta") && oferta.getPiezas().getEstado() != "Bloqueada"){
				Piezas pieza = oferta.getPiezas();
				inventario.modificarEstado("Bloqueada", pieza);
				ofertasVenta.add(oferta);
				galeria.getOfertasFinales(oferta);
			}
		}
	}
	
	public void recharOfertaVenta(Ofertas oferta) {
		
		inventario.modificarEstado("Disponible", oferta.getPiezas());
		ofertasVenta.remove(oferta);
	}
	
	/**
	 * Método getter de la lista subasta
	 * @return ofertaSubasta
	 * */
	public List<Ofertas> getOfertasSubasta(){
		
		return ofertasSubasta;
	}
	
	public List<Ofertas> getTotalOfertas(){
		return totalOfertas;
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
