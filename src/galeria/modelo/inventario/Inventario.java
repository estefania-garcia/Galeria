package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import galeria.modelo.usuarios.OperacionSubasta;

/**
 * Esta clase almacena todas las piezas de la galería y las distribuye en diferentes listas para un manejo más ordenado
 * */

public class Inventario {
	
	/**
	 * Lista de piezas en solicitud
	 * */
	private List<ConsignacionPieza> piezasSolicitud;
	
	/**
	 * Listas de las piezas totales
	 * */
	private List<ConsignacionPieza> piezasTotales;
	
	/**
	 * Listas de las piezas dadas en deposito
	 * */
	private List<ConsignacionPieza> piezasDeposito;
	
	/**
	 * Listas de las piezas devueltas
	 * */
	private List<ConsignacionPieza> piezasDevueltas;
	
	/**
	 * Listas de las piezas vigentes
	 * */
	private List<Piezas> piezasVigentes;
	
	/**
	 * Listas de las piezas para exhibición
	 * */
	private List<Piezas> piezasExhibir;
	
	/**
	 * Listas de las piezas para subasta
	 * */
	private List<Piezas> piezasSubasta;
	
	/**
	 * Listas de las piezas para venta
	 * */
	private List<Piezas> piezasVenta;
	
	/**
	 * Listas de las piezas vendidas
	 * */
	private List<Piezas> piezasVendidas;
	
	/**
	 * Listas de las piezas bloqueadas
	 * */
	private List<Piezas> piezasBloqueadas;
	
	/**
	 * Atributo que nos da acceso a los métodos de operador
	 * */
	private OperacionSubasta operador;
	
	/**
	 * Constructor que inicializa las listas con las piezas
	 * */
	public Inventario(OperacionSubasta operador) {
		
		this.operador = operador;
		piezasTotales = new ArrayList<ConsignacionPieza>();
		piezasDeposito = new ArrayList<ConsignacionPieza>();
		piezasVigentes = new ArrayList<Piezas>();
		piezasDevueltas = new ArrayList<ConsignacionPieza>();
		piezasExhibir = new ArrayList<Piezas>();
		piezasSubasta = new ArrayList<Piezas>();
		piezasVenta = new ArrayList<Piezas>();
		piezasVendidas = new ArrayList<Piezas>();
		piezasSolicitud = new ArrayList<ConsignacionPieza>();
		piezasBloqueadas = new ArrayList<Piezas>();
	}
	
	/**
	 * Método en general que agrega y filtra segun sus caracteristicas en las diferentes listas
	 * */
	public void añadirPiezasTodas(Piezas pieza) {

		if(pieza.getVigencia() == true) {
			piezasVigentes.add(pieza);
			if(pieza.getProposito().equals("Exhibir")) {
				piezasExhibir.add(pieza);
			}
			else if(pieza.getProposito().equals("Subastar")) {
				if(pieza.getVenta() == true) {
					piezasVendidas.add(pieza);
					piezasSubasta.remove(pieza);
				}else {
					piezasSubasta.add(pieza);
				}
			}
			else if(pieza.getProposito().equals("Vender")) {
				if(pieza.getEstado().equals("Bloqueada")) {
					piezasBloqueadas.add(pieza);
					piezasVenta.remove(pieza);
				}
				else if(pieza.getVenta() == true) {
					piezasVendidas.add(pieza);
					piezasBloqueadas.remove(pieza);
					piezasVenta.remove(pieza);
				}else {
					piezasBloqueadas.remove(pieza);
					piezasVenta.add(pieza);
				}
			}
		}else {
			piezasVigentes.remove(pieza);
			if(pieza.getProposito().equals("Exhibir")) {
				piezasExhibir.remove(pieza);
			}
			else if(pieza.getProposito().equals("Subastar")) {
				piezasSubasta.remove(pieza);
			}
			else if(pieza.getProposito().equals("Vender")) {
				if(pieza.getEstado().equals("Bloqueada")) {
					piezasBloqueadas.remove(pieza);
					piezasVenta.remove(pieza);
				}else {
					piezasVenta.remove(pieza);
				}
			}
		}
	}
	
	/**
	 * Método que agrega las piezas en solicitud y a su vez llama a otro método para filtrarlas
	 * */
	public void añadirPiezasSolicitud(ConsignacionPieza pieza) {
		
		boolean confirmacion = true;
		for(ConsignacionPieza piezas : piezasSolicitud) {
			if(piezas.getPieza().getTitulo().equals(pieza.getPieza().getTitulo()) && piezas.getPieza().getAutores().equals(pieza.getPieza().getAutores())) {
				confirmacion = false;
				break;
			}
		}
		if(confirmacion == true) {
			piezasSolicitud.add(pieza);
			añadirPiezasDeposito(pieza);
		}
	}
	
	/**
	 * Método que agrega una pieza a las piezas en deposito y las filtra segun sus caracteristicas
	 * */
	public void añadirPiezasDeposito(ConsignacionPieza piezas){

		if(piezas.getPieza().getAprobada() == true) {
			añadirPiezasTodas(piezas.getPieza());
			if(piezas.getPieza().getDeposito() == true && piezas.getPieza().getVigencia() == true) {
				piezasDeposito.add(piezas);
			}
			else if(piezas.getPieza().getDeposito() == true && piezas.getPieza().getVigencia() == false) {
				piezasDevueltas.add(piezas);
				piezasDeposito.remove(piezas);
			}
		}
	}
	
	/**
	 * Método getter de las piezas en solicitud
	 * */
	public List<ConsignacionPieza> getPiezasSolicitud(){
		
		return piezasSolicitud;
	}
	
	/**
	 * Método que modifica el estado de una pieza
	 * */
	public void modificarEstado(String estado, Piezas pieza) {
	
		pieza.asignarEstado(estado);
	}
	
	/**
	 * Método getter de la lista de piezas totales
	 * */
	public List<ConsignacionPieza> getTotales(){
		
		return piezasTotales;
	}
	
	/**
	 * Método getter de la lista de piezas en deposito
	 * */
	public List<ConsignacionPieza> getDeposito(){
		
		return piezasDeposito;
	}
	
	/**
	 * Método getter de la lista de piezas devueltas
	 * */
	public List<ConsignacionPieza> getDevueltas(){
		
		return piezasDevueltas;
	}
	
	/**
	 * Método getter de la lista de piezas vigentes
	 * */
	public List<Piezas> getVigentes(){
		
		return piezasVigentes;
	}
	
	/**
	 * Método getter de la lista de piezas en exhibición 
	 * */
	public List<Piezas> getExhibir(){
		return piezasExhibir;
	}
	
	/**
	 * Método getter de la lista de piezas para subastar
	 * */
	public List<Piezas> getSubastar(){
		
		return piezasSubasta;
	}
	
	/**
	 * Método getter de la lista de piezas en venta
	 * */
	public List<Piezas> getVenta(){
		return piezasVenta;
	}
	
	/**
	 * Método getter de la lista de piezas vendidas
	 * */
	public List<Piezas> getVendidas(){
		return piezasVendidas;
	}
	
	/**
	 * Método getter de la lista de piezas bloqueadas
	 * */
	public List<Piezas> getBloqueadas(){
		return piezasBloqueadas;
	}
	
	/**
	 * Método que le indica a operador cuales son las piezas a subastar y se las pasa para iniciar la puja
	 * */
	public void agregarPiezaSubasta() {
		
		Iterator<Piezas> iterador = piezasSubasta.iterator();
    	while(iterador.hasNext()) {
    		Piezas pieza = iterador.next();
    		operador.agregarPiezasSubasta(pieza);
    	}
	}
}
