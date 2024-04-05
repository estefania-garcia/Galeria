package galeria.modelo.inventario;

import java.util.LinkedList;
import java.util.List;

public class Inventario {
	
	private List<Piezas> piezasTotales;
	private List<Piezas> piezasSubastaT;
	private List<Piezas> piezasVentaT;
	private List<Piezas> piezasDevueltas;
	private List<Piezas> piezasVigentes;
	private List<Piezas> piezasExhibir;
	private List<Piezas> piezasExhibirT;
	private List<Piezas> piezasSubasta;
	private List<Piezas> piezasVenta;
	
	public Inventario() {
		
		piezasTotales = new LinkedList<Piezas>();
		piezasSubastaT = new LinkedList<Piezas>();
		piezasVentaT = new LinkedList<Piezas>();
		piezasDevueltas = new LinkedList<Piezas>();
		piezasVigentes = new LinkedList<Piezas>();
		piezasExhibir = new LinkedList<Piezas>();
		piezasSubasta = new LinkedList<Piezas>();
		piezasVenta = new LinkedList<Piezas>();
		piezasExhibirT = new LinkedList<Piezas>();
	}
	
	public List<Piezas> añadirPiezas(Piezas piezas){
		
		piezasTotales.add(piezas);
		return piezasTotales;
	}
	
	public void agregarPiezas(Piezas piezas) {
		if(piezas.getDeposito().getProposito().equals("Subastar")) {
			if(piezas.getDeposito().getTiempo() != 0) {
				piezasSubastaT.add(piezas);
			}else {
				piezasSubasta.add(piezas);
			}
		}
		if(piezas.getDeposito().getProposito().equals("Vender")) {
			if(piezas.getDeposito().getTiempo() != 0) {
				piezasVentaT.add(piezas);
			}else {
				piezasVenta.add(piezas);
			}
		}
		if(piezas.getDeposito().getProposito().equals("Exhibir")) {
			if(piezas.getDeposito().getTiempo() != 0) {
				piezasExhibirT.add(piezas);
			}else {
				piezasExhibir.add(piezas);
			}
		}
		
	}
	
	public void devolverPiezas(Piezas piezas) {
		
		piezasDevueltas.add(piezas);
	}
	
	public void agregarPiezasAprobadas(Piezas piezas) {
		
		piezasVigentes.add(piezas);
	}
}
