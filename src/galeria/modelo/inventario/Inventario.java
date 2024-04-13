package galeria.modelo.inventario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.usuarios.Operador;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class Inventario {
	
	private List<Piezas> piezasTotales;
	private List<Piezas> piezasDeposito;
	private List<Piezas> piezasDevueltas;
	private List<Piezas> piezasVigentes;
	private List<Piezas> piezasExhibir;
	private List<Piezas> piezasSubasta;
	private List<Piezas> piezasVenta;
	private List<Piezas> piezasVendidas;
	private List<Piezas> piezasBloqueadas;
	
	private Operador operador;
	
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
	
	public void añadirPiezas(Piezas piezas){
		
		piezasTotales.add(piezas);
	}
	
	public void añadirPiezasVigente(Piezas piezas){
		
		piezasVigentes.add(piezas);
	}
	
	public void añadirPiezasDeposito(Piezas piezas){
		
		piezasDeposito.add(piezas);
	}
	
	public void añadirPiezasExhibir(Piezas piezas){
		
		piezasExhibir.add(piezas);
	}
	
	public void añadirPiezasSubasta(Piezas piezas){
		
		piezasSubasta.add(piezas);
	}
	
	public void añadirPiezasVenta(Piezas piezas){
		
		piezasVenta.add(piezas);
	}
	
	public void añadirPiezasVendidas(Piezas piezas){
		
		piezasVendidas.add(piezas);
	}
	
	public void añadirPiezasDevueltas(Piezas piezas){
		
		piezasDevueltas.add(piezas);
		piezasDeposito.remove(piezas);
	}
	
	public void añadirPiezasBloqueadas(Piezas piezas){
		
		piezasBloqueadas.add(piezas);
		piezasVenta.remove(piezas);
	}
	
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
	
	public void modificarEstado(String estado, Piezas pieza) {
	
		pieza.asignarEstado(estado);
	}
	
	public List<Piezas> getTotale(){
		return piezasTotales;
	}
	
	public List<Piezas> getDeposito(){
		return piezasDeposito;
	}
	
	public List<Piezas> getDevueltas(){
		return piezasDevueltas;
	}
	
	public List<Piezas> getVigentes(){
		return piezasVigentes;
	}
	
	public List<Piezas> getExhibir(){
		return piezasExhibir;
	}
	
	public List<Piezas> getSubastar(){
		return piezasSubasta;
	}
	
	public List<Piezas> getVenta(){
		return piezasVenta;
	}
	
	public List<Piezas> getVendidas(){
		return piezasVendidas;
	}
	
	public List<Piezas> getBloqueadas(){
		return piezasBloqueadas;
	}
	
	public void agregarPiezaSubasta() {
		
		Iterator<Piezas> iterador = piezasSubasta.iterator();
    	while(iterador.hasNext()) {
    		Piezas pieza = iterador.next();
    		operador.agregarPiezas(pieza);
    	}
	}
}
