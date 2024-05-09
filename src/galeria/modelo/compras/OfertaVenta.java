package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase se usa para representar las ofertas que puede hacer un comprador por una pieza en venta
 * Aqui se implementan metodos que permiten verificar si se cumplen las condicones basicas de la oferta
 * */

public class OfertaVenta extends Ofertas{

	/**
	 * Es un atributo identificador para la clase, de este modo podemos diferenciar entre los dos tipos de oferta 
	 */
	public static final String OFERTAVENTA = "Oferta Venta";
	
	/**
	 * El constructor de la clase solo toma los atributos especificados por la super clase
	 * */
	public OfertaVenta(Piezas pieza, HistorialInversor comprador, int monto) {
		super(pieza, comprador, monto);
	}
	
	/**
	 * Verifica que el monto que el comprador pretende pagar por la pieza sea igual a precio fijo asginado a la pieza
	 * */
	
	@Override
	public boolean validarPrecio() {
		if(super.getPiezas().getGaleriaOferta().getMontoCliente() == super.getMonto()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Indica el tipo de oferta que representa la clase
	 * */
	
	@Override
	public String tipoOferta() {
		return OFERTAVENTA;
	}
}
