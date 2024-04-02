package galeria.modelo.inventario;

import galeria.modelo.compras.OfertaOpcion;

public class ArteTridimensional extends Piezas{

	private static final String ARTETRIDIMENSIONAL = "Arte Tridimensional";
	
	private double alto;
	private double ancho;
	private double profundidad;
	private double peso;
	private boolean electricidad;
	private String material;
	private String tecnica;
	
	public ArteTridimensional(String titulo, Deposito deposito, OfertaOpcion ofertaOpcion, String propietario, String año, String lugar, String autores, double alto, String tecnica, double ancho, double profundidad, double peso, boolean electricidad, String material) {
		super(titulo, año, lugar, autores, deposito, ofertaOpcion, propietario);
		this.ancho = ancho;
		this.alto = alto;
		this.material = material;
		this.profundidad = profundidad;
		this.peso = peso;
		this.electricidad = electricidad;
		this.tecnica = tecnica;
	}
	
	@Override
	public String getTipo() {
		return ARTETRIDIMENSIONAL;
	}
	
	public double getAncho() {
		return ancho;
	}
	
	public double getAlto() {
		return alto;
	}
	
	public boolean getElectricidad() {
		return electricidad;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public double getProdundidad() {
		return profundidad;
	}
	
	public String getTectica() {
		return tecnica;
	}
}
