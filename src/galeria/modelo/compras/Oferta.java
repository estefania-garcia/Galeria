package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.*;

public class Oferta {

	private Piezas pieza;
	private Usuarios comprador;
	private int monto;
	private Inversores comprador_monto;
	
	public Oferta(Piezas pieza, Usuarios comprador, int monto) {
		
		this.pieza = pieza;
		this.comprador = comprador;
		this.monto = monto;
	}
	
	public Piezas getPiezas() {
		return pieza;
	}
	 public Usuarios getComprador() {
		 return comprador;
	 }
	 
	 public int getMonto() {
		 return monto;
	 }
	 
	 public boolean verificarMonto() {
		 
		 if(comprador_monto.getTopeMonto() < monto) {
			 return false;
		 }else {
			 return true;
		 }
	 }
}
