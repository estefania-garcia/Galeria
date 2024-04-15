package galeria.modelo.inventario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.usuarios.Operador;

/**
 * Esta clase almacena todas las piezas de la galería y las distribuye en diferentes listas para un manejo más ordenado
 * */

public class Inventario {
	
	/**
	 * Listas de las piezas totales
	 * */
	private List<Piezas> piezasTotales;
	
	/**
	 * Listas de las piezas dadas en deposito
	 * */
	private List<Piezas> piezasDeposito;
	
	/**
	 * Listas de las piezas devueltas
	 * */
	private List<Piezas> piezasDevueltas;
	
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
	private Operador operador;
	
	/**
	 * Constructor que inicializa las listas con las piezas
	 * */
	public Inventario() {
		
		piezasTotales = new LinkedList<Piezas>();
		piezasDeposito = new LinkedList<Piezas>();
		piezasDevueltas = new LinkedList<Piezas>();
		piezasVigentes = new LinkedList<Piezas>();
		piezasExhibir = new LinkedList<Piezas>();
		piezasSubasta = new LinkedList<Piezas>();
		piezasVenta = new LinkedList<Piezas>();
		piezasVendidas = new LinkedList<Piezas>();
		piezasBloqueadas = new LinkedList<Piezas>();
	}
	
	/**
	 * Método que agrega una pieza a las piezas totales
	 * @param piezas
	 * */
	public void añadirPiezas(Piezas piezas){
		
		piezasTotales.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas vigentes
	 * @param piezas
	 * */
	public void añadirPiezasVigente(Piezas piezas){
		
		piezasVigentes.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas en deposito
	 * @param piezas
	 * */
	public void añadirPiezasDeposito(Piezas piezas){
		
		piezasDeposito.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas para exhibición
	 * @param piezas
	 * */
	public void añadirPiezasExhibir(Piezas piezas){
		
		piezasExhibir.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas para subasta
	 * @param piezas
	 * */
	public void añadirPiezasSubasta(Piezas piezas){
		
		piezasSubasta.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas para venta
	 * @param piezas
	 * */
	public void añadirPiezasVenta(Piezas piezas){
		
		piezasVenta.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas vendidas
	 * @param piezas
	 * */
	public void añadirPiezasVendidas(Piezas piezas){
		
		piezasVendidas.add(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas devueltas y las elimina de las piezas en deposito
	 * @param piezas
	 * */
	public void añadirPiezasDevueltas(Piezas piezas){
		
		piezasDevueltas.add(piezas);
		piezasDeposito.remove(piezas);
	}
	
	/**
	 * Método que agrega una pieza a las piezas bloqueadas y las remueve de las piezas en venta
	 * @param piezas
	 * */
	public void añadirPiezasBloqueadas(Piezas piezas){
		
		piezasBloqueadas.add(piezas);
		piezasVenta.remove(piezas);
	}
	
	/**
	 * Método que permite eliminar las piezas que ya no hace parte del catalogo según el indicador que nos lleva a las lista donde estaba guardada y consignación que indica si esta tambien estaba en la lista deposito
	 * @param piezas
	 * @param indicador
	 * @param consignación
	 * */
	public void eliminarPiezas(Piezas piezas, String indicador, boolean consignacion) {
		
		piezasVigentes.remove(piezas);
		
		if(consignacion == true) {
			piezasDeposito.remove(piezas);
		}
		
		if (indicador.equals("Venta")) {
			piezasVenta.remove(piezas);
		}else if(indicador.equals("Subasta")) {
			piezasSubasta.remove(piezas);
		}else if(indicador.equals("Exhibir")) {
			piezasExhibir.remove(piezas);
		}else if(indicador.equals("Bloqueada")) {
			piezasBloqueadas.remove(piezas);
		}
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
	public List<Piezas> getTotale(){
		return piezasTotales;
	}
	
	/**
	 * Método getter de la lista de piezas en deposito
	 * @return piezasDeposito
	 * */
	public List<Piezas> getDeposito(){
		return piezasDeposito;
	}
	
	/**
	 * Método getter de la lista de piezas devueltas
	 * @return piezasDevueltas
	 * */
	public List<Piezas> getDevueltas(){
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
}
