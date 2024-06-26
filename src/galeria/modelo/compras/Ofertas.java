package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase crea las ofertas que un comprador puede realizar por alguna pieza
 * Para ello relaciona la pieza, el comprador y el monto
 * 
 * En esta clase se verifica que el monto ofrecido por el comprador cumpla con los requrimientos propios de cada clase, basandose en la información de Piezas
 * */

public abstract class Ofertas {
	
	/**
	 * Atributo que relaciona una pieza con la oferta
	 * */
	private Piezas pieza;
	
	/**
	 * Atributo que relaciona un monto con la oferta
	 * */
	private int monto;
	/**
	 * Atributo que referencia al ofertador
	 * */
	private HistorialInversor comprador;
	
	/**
	 * Inicializa los atributos según los parámetros indicados
	 * */
	public Ofertas(Piezas pieza, HistorialInversor comprador, int monto) {
		
		this.pieza = pieza;
		this.comprador = comprador;
		this.monto = monto;
	}
	
	/**
	 * Método getter del atributo pieza
	 * @return pieza
	 * */
	public Piezas getPiezas() {
		return pieza;
	}
	
	/**
	 * Método getter del atributo comprador
	 * @return comprador
	 * */
	public HistorialInversor getComprador() {
		 return comprador;
	}
	
	/**
	 * Método getter del atributo monto
	 * @return monto
	 * */
	public int getMonto() {
		 return monto;
	}
	 
	/**
	 * Método abstracto para validar el precio fijo o inicial, según corresponda
	 * @retrun boolean
	 * */
	public abstract boolean validarPrecio();
	
	/**
	 * Método abstracto para obtener el tipo de oferta
	 * */
	public abstract String tipoOferta();
	 
	/**
	 * Método para verificar que la oferta que esta haciendo el comprador no supere el monto maximo de compra asignado por el administrador
	 * */
	public boolean verificarMonto() {
		 
		 if(comprador.getMontoMaximo() < monto) {
			 return false;
		 }else {
			 return true;
		 }
	 }
}
