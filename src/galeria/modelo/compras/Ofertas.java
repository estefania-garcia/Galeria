package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.Inversor;
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
	 * Atributo que relaciona un comprador con la oferta
	 * */
	private Usuarios comprador;
	/**
	 * Atributo que relaciona un monto con la oferta
	 * */
	private int monto;
	/**
	 * Atributo que nos da acceso al monto maximo asignado al comprador
	 * */
	private Inversor comprador_monto;
	
	/**
	 * Inicializa los atributos según los parámetros
	 * @param pieza
	 * @param comprador
	 * @param monto
	 * */
	public Ofertas(Piezas pieza, Usuarios comprador, int monto) {
		
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
	public Usuarios getComprador() {
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
	 * @return String
	 * */
	public abstract String tipoOferta();
	 
	/**
	 * Método para verificar que la oferta que esta haciendo el comprador no supere el monto maximo de compra asignado por el administrador
	 * @return boolean
	 * */
	public boolean verificarMonto() {
		 
		 if(comprador_monto.getMontoMaximo() < monto) {
			 return false;
		 }else {
			 return true;
		 }
	 }
}
