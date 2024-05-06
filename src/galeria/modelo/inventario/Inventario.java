package galeria.modelo.inventario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.usuarios.OperacionSubasta;

/**
 * Esta clase almacena todas las piezas de la galería y las distribuye en diferentes listas para un manejo más ordenado
 * */

public class Inventario {
	
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
	public Inventario() {
		
		this.operador = new OperacionSubasta();
		piezasTotales = new LinkedList<ConsignacionPieza>();
		piezasDeposito = new LinkedList<ConsignacionPieza>();
		piezasVigentes = new LinkedList<Piezas>();
		piezasDevueltas = new LinkedList<ConsignacionPieza>();
		piezasExhibir = new LinkedList<Piezas>();
		piezasSubasta = new LinkedList<Piezas>();
		piezasVenta = new LinkedList<Piezas>();
		piezasVendidas = new LinkedList<Piezas>();
		piezasSolicitud = new ArrayList<ConsignacionPieza>();
		piezasBloqueadas = new LinkedList<Piezas>();
	}
	
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
	
	public void añadirPiezasSolicitud(ConsignacionPieza piezas){
		
		if(!piezasTotales.contains(piezas)) {
			piezasTotales.add(piezas);
			if(piezas.getPieza().getAprobada() == true) {
				piezasSolicitud.remove(piezas);
				añadirPiezasDeposito(piezas);
			}else {
				piezasSolicitud.add(piezas);
			}
		}
	}
	
	/**
	 * Método que agrega una pieza a las piezas en deposito
	 * @param piezas
	 * */
	public void añadirPiezasDeposito(ConsignacionPieza piezas){

		if(piezas.getPieza().getVigencia() == true) {
			piezasDeposito.add(piezas);
			añadirPiezasTodas(piezas.getPieza());
		}else {
			piezasDeposito.remove(piezas);
			piezasDevueltas.add(piezas);
			añadirPiezasTodas(piezas.getPieza());
		}
	}
	
	public List<ConsignacionPieza> getPiezasSolicitud(){
		
		return piezasSolicitud;
	}
	
	/**
	 * Método que modifica el estado de una pieza
	 * @param piezas
	 * @param estado
	 * */
	public void modificarEstado(String estado, Piezas pieza) {
	
		pieza.asignarEstado(estado);
	}
	
	/**
	 * Método getter de la lista de piezas totales
	 * @return piezasTotale
	 * */
	public List<ConsignacionPieza> getTotales(){
		return piezasTotales;
	}
	
	/**
	 * Método getter de la lista de piezas en deposito
	 * @return piezasDeposito
	 * */
	public List<ConsignacionPieza> getDeposito(){
		return piezasDeposito;
	}
	
	/**
	 * Método getter de la lista de piezas devueltas
	 * @return piezasDevueltas
	 * */
	public List<ConsignacionPieza> getDevueltas(){
		return piezasDevueltas;
	}
	
	/**
	 * Método getter de la lista de piezas vigentes
	 * @return piezasVigentes
	 * */
	public List<Piezas> getVigentes(){
		return piezasVigentes;
	}
	
	/**
	 * Método getter de la lista de piezas en exhibición 
	 * @return piezasExhibir
	 * */
	public List<Piezas> getExhibir(){
		return piezasExhibir;
	}
	
	/**
	 * Método getter de la lista de piezas para subastar
	 * @return piezasSubasta
	 * */
	public List<Piezas> getSubastar(){
		return piezasSubasta;
	}
	
	/**
	 * Método getter de la lista de piezas en venta
	 * @return piezasVenta
	 * */
	public List<Piezas> getVenta(){
		return piezasVenta;
	}
	
	/**
	 * Método getter de la lista de piezas vendidas
	 * @return piezasVendidas
	 * */
	public List<Piezas> getVendidas(){
		return piezasVendidas;
	}
	
	/**
	 * Método getter de la lista de piezas bloqueadas
	 * @return piezasBloqueadas
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
	
	public OperacionSubasta getClaseSubasta() {
		
		return operador;
	}
}
