package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.Comprador;
import galeria.modelo.usuarios.Usuarios;

public abstract class Ofertas {
	
	private Piezas pieza;
	private Usuarios comprador;
	private int monto;
	private Comprador comprador_monto;
	
	public Ofertas(Piezas pieza, Usuarios comprador, int monto) {
		
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
	 
	 public abstract boolean validarPrecio();
	 
	 public boolean verificarMonto() {
		 
		 if(comprador_monto.getTopeMonto() < monto) {
			 return false;
		 }else {
			 return true;
		 }
	 }
}
