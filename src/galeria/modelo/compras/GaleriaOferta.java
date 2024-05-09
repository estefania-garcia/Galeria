package galeria.modelo.compras;

/**
 * Esta clase es la base para agregarle a la pieza sus caracteristicas según su proposito, por ello contempla los tres casos de uso de una pieza: la subasta, la venta y la exhibición
 * Es un contenedor de información basica que se debe ingresar al crear la pieza para poder realizar a cabo otras acciones dentro del sistema a partir de esta información
 * */
public class GaleriaOferta {
	
	/**
	 * Atributo que referencia al monto fijo, en el caso de la venta o al monto inicial en caso de la subasta
	 * En caso de la exhibición este queda en 0
	 * */
	private int montoCliente;
	
	/**
	 * M Atributo que referencia al monto minimo de compra en el caso de la subasta
	 * En caso de la exhibición y venta este queda en 0
	 * */
	private int montoMinimo;
	
	/**
	 * Constructor para las ofertas de tipo subasta. Relaciona el monto inicial y el monto minimo
	 * */
	public GaleriaOferta(int monto, int montoMinimo) {
		
		this.montoCliente = monto;
		this.montoMinimo = montoMinimo;
	}
	
	/**
	 * Método getter del atributo montoCliente
	 * */
	public int getMontoCliente() {
		return montoCliente;
	}
	
	/**
	 * Método getter del atributo montoMinimo
	 * */
	public int getMontoMinimo() {
		return montoMinimo;
	}
}