package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;

public abstract class Piezas {
	
	private String titulo;
	private String año;
	private String lugar;
	private String autores;
	private Deposito deposito;
	private GaleriaOferta galeriaOferta;
	private String propietario;
	private String estado;
	
	public Piezas(String titulo, String año, String autores, String lugar, Deposito deposito, GaleriaOferta galeriaOferta, String propietario) {
		
		this.titulo = titulo;
		this.año = año;
		this.lugar = lugar;
		this.autores = autores;
		this.deposito = deposito;
		this.galeriaOferta = galeriaOferta;
		this.propietario = propietario;
	}
	
	public abstract String getTipo();
	
	public String getAutores() {
		return autores;	
	}
	
	public String getPropietario() {
		return propietario;
	}
	
	
	public Deposito getDeposito() {
		return deposito;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAño() {
		return año;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public String getEstado(String estados) {
		
		this.estado = estados;
		return estado;
	}

	public GaleriaOferta getGaleriaOferta() {
		return galeriaOferta;
	}
}
