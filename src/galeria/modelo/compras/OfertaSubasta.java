package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase se usa para representar las ofertas que puede hacer un comprador por una pieza en subasta
 * Aqui se implementan metodos que permiten verificar si se cumplen las condicones basicas de la oferta
 * 
 * Por un lado esta el cumplimiento del monto inicial y por el otro lado el cumplimiento del monto minimo
 * El operador hara uso de estos metodos para validar la aprobación de la oferta
 * */
public class OfertaSubasta extends Ofertas{
	
	/**
	 * Atributo que representa el tipo de oferta de la clase
	 * */
	public static final String OFERTASUBASTA = "Oferta Subasta";
	
	/**
	 * El constructor de la clase solo toma los atributos especificados por la super clase
	 * */
	public OfertaSubasta(Piezas pieza, HistorialInversor comprador, int monto) {
		super(pieza, comprador, monto);
	}

	/**
	 * Verifica que el monto que el comprador pretende pagar por la pieza sea mayor o igual al precio inicial que le fue asignado a la pieza
	 * */
	@Override
	public boolean validarPrecio() {
		if(super.getPiezas().getGaleriaOferta().getMontoCliente() <= super.getMonto()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Verifica que el monto que el comprador pretende pagar por la pieza sea mayor o igual al precio minimo asignado a la pieza para poder ser vendida
	 * */
	public boolean venderPieza() {
		if(super.getPiezas().getGaleriaOferta().getMontoMinimo() <= super.getMonto()) {
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
		return OFERTASUBASTA;
	}
}
