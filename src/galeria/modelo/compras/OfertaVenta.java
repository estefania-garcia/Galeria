package galeria.modelo.compras;

import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.Usuarios;

public class OfertaVenta extends Ofertas{

	public OfertaVenta(Piezas pieza, Usuarios comprador, int monto) {
		super(pieza, comprador, monto);
	}
	
	@Override
	public boolean validarPrecio() {
		if(super.getPiezas().getGaleriaOferta().getMontoCliente() == super.getMonto()) {
			return true;
		}else {
			return false;
		}
	}
}
