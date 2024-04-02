package galeria.modelo.inventario;

import galeria.modelo.compras.OfertaOpcion;

public class ArteVisual extends Piezas{
	
	private static final String ARTEVISUAL = "Arte Visual";
	
	private double ancho;
	private double largo;
	private String tipoArte;
	private String material;
	private String tecnica;
	
	public ArteVisual(String titulo, Deposito deposito, OfertaOpcion ofertaOpcion, String propietario, String año, String lugar, String autores, double ancho, double largo, String tipoArte, String material, String tecnica) {
		super(titulo, año, lugar, autores, deposito, ofertaOpcion, propietario);
		this.ancho = ancho;
		this.largo = largo;
		this.material = material;
		this.tecnica = tecnica;
		this.tipoArte = tipoArte;
	}
	
	@Override
	public String getTipo() {
		return ARTEVISUAL;
	}
	
	public double getAncho() {
		return ancho;
	}
	
	public double getLargo() {
		return largo;
	}
	
	public String getTipoArte() {
		return tipoArte;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public String getTecnica() {
		return tecnica;
	}
}
