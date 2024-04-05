package galeria.modelo.compras;

public class GaleriaOferta {
	
	private int montoCliente;
	private int montoMinimo;
	
	public GaleriaOferta(int monto, int montoMinimo) {
		
		this.montoCliente = monto;
		this.montoMinimo = montoMinimo;
	}
	
	public GaleriaOferta(int monto) {
		
		this.montoCliente = monto;
	}
	
	public GaleriaOferta() {}
	
	public int getMontoCliente() {
		return montoCliente;
	}
	
	public int getMontoMinimo() {
		return montoMinimo;
	}
}