package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase es una subclase de Piezas que representa piezas como esculturas
 * */
public class ArteTridimensional extends Piezas{

	/**
	 * Constante que representa el tipo de Pieza
	 * */
	private static final String ARTETRIDIMENSIONAL = "Arte Tridimensional";
	
	/**
	 * Atributo que relacion el alto de la pieza
	 * */
	private double alto;
	
	/**
	 * Atributo que relacion el ancho de la pieza
	 * */
	private double ancho;
	
	/**
	 * Atributo que relacion la profundidad de la pieza
	 * */
	private double profundidad;
	
	/**
	 * Atributo que relacion el peso de la pieza
	 * */
	private double peso;
	
	/**
	 * Atributo que indica si la pieza requiere electricidad o no
	 * */
	private boolean electricidad;
	
	/**
	 * Atributo que relacion el material de la pieza
	 * */
	private String material;
	
	/**
	 * Atributo que relacion la técnica de la pieza
	 * */
	private String tecnica;
	
	/**
	 * El constructor junta los atributos del constructor de la super clase y añade sus propieos atributos
	 * @param ancho
	 * @param alto
	 * @param material
	 * @param produndidad
	 * @param peso
	 * @param electricidad
	 * @param tecnica
	 * */
	public ArteTridimensional(String titulo, String lugar_creacion, boolean deposito, GaleriaOferta galeriaOferta, 
			Usuarios propietario, String año, String autores, double alto, String tecnica, double ancho, 
			double profundidad, double peso, boolean electricidad, String material) {
		super(titulo, lugar_creacion, año, autores, deposito, galeriaOferta, propietario);
		this.ancho = ancho;
		this.alto = alto;
		this.material = material;
		this.profundidad = profundidad;
		this.peso = peso;
		this.electricidad = electricidad;
		this.tecnica = tecnica;
	}
	
	/**
	 * Método que indica el tipo de pieza
	 * @return ARTEVISUAL
	 * */
	@Override
	public String getTipo() {
		return ARTETRIDIMENSIONAL;
	}
	
	/**
	 * Método getter que indica el ancho de la pieza
	 * @return ancho
	 * */
	public double getAncho() {
		return ancho;
	}
	
	/**
	 * Método getter que indica el alto de la pieza
	 * @return alto
	 * */
	public double getAlto() {
		return alto;
	}
	
	/**
	 * Método getter que indica si la pieza necesita electricidad
	 * @return electricidad
	 * */
	public boolean getElectricidad() {
		return electricidad;
	}
	
	/**
	 * Método getter que indica el material de la pieza
	 * @return material
	 * */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * Método getter que indica el peso de la pieza
	 * @return peso
	 * */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Método getter que indica la produndidad de la pieza
	 * @return produndidad
	 * */
	public double getProdundidad() {
		return profundidad;
	}
	
	/**
	 * Método getter que indica la técnica de la pieza
	 * @return técnica
	 * */
	public String getTectica() {
		return tecnica;
	}
}
