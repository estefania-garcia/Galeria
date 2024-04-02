package galeria.modelo.compras;

public class OfertaOpcion {
	
	private int montoCliente;
	private int montoMinimo;
	
	public OfertaOpcion(int monto, int montoMinimo) {
		
		this.montoCliente = monto;
		this.montoMinimo = montoMinimo;
	}
	
	public OfertaOpcion(int monto) {
		
		this.montoCliente = monto;
	}
	
	public OfertaOpcion() {}
	
	public int getMontoCliente() {
		return montoCliente;
	}
	
	public int getMontoMinimo() {
		return montoMinimo;
	}
}
