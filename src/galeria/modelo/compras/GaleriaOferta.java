package galeria.modelo.compras;

/**
 * Esta clase es la base para agregarle a la pieza sus caracteristicas según su proposito, por ello contempla los tres casos de uso de una pieza: la subasta, la venta y la exhibición
 * Es un contenedor de información basica que se debe ingresar al crear la pieza para poder realizar a cabo otras acciones dentro del sistema a partir de esta información
 * */
public class GaleriaOferta {
	
	/**
	 * Método getter del atributo monto cliente. Para las subasta representa el monto inicial y para las ventas representa el monto fijo
	 * */
	private double montoCliente;
	
	/**
	 * Método getter del atributo monto minimo. Aplica para las ofertas de tipo subasta
	 * */
	private double montoMinimo;
	
	/**
	 * Constructor para las ofertas de tipo subasta. Relaciona el monto inicial y el monto minimo
	 * @param monto
	 * @param montoMinimo
	 * */
	public GaleriaOferta(double monto, double montoMinimo) {
		
		this.montoCliente = monto;
		this.montoMinimo = montoMinimo;
	}
	
	/**
	 * Método getter del atributo montoCliente
	 * */
	public double getMontoCliente() {
		return montoCliente;
	}
	
	/**
	 * Método getter del atributo montoMinimo
	 * */
	public double getMontoMinimo() {
		return montoMinimo;
	}
}