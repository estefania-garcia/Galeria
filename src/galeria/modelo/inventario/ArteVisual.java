package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase es una subclase de Piezas que representa piezas como pinturas, dibujos, entre otros
 * */

public class ArteVisual extends Piezas{
	
	/**
	 * Constante que representa el tipo de Pieza
	 * */
	private static final String ARTEVISUAL = "Arte Visual";
	
	/**
	 * Atributo que relacion el ancho de la pieza
	 * */
	private double ancho;
	
	/**
	 * Atributo que relacion el largo de la pieza
	 * */
	private double largo;
	
	/**
	 * Atributo que relacion el tipo de arte de la pieza
	 * */
	private String tipoArte;
	
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
	 * @param largo
	 * @param material
	 * @param tecnica
	 * @param tipoArte
	 * */
	public ArteVisual(String titulo, boolean deposito, GaleriaOferta galeriaOferta, Usuarios propietario, String año, String autores, double ancho, double largo, String tipoArte, String material, String tecnica) {
		super(titulo, año, autores, deposito, galeriaOferta, propietario);
		this.ancho = ancho;
		this.largo = largo;
		this.material = material;
		this.tecnica = tecnica;
		this.tipoArte = tipoArte;
	}
	
	/**
	 * Método que indica el tipo de pieza
	 * @return ARTEVISUAL
	 * */
	@Override
	public String getTipo() {
		return ARTEVISUAL;
	}
	
	/**
	 * Método getter que indica el ancho de la pieza
	 * @return ancho
	 * */
	public double getAncho() {
		return ancho;
	}
	
	/**
	 * Método getter que indica el largo de la pieza
	 * @return largo
	 * */
	public double getLargo() {
		return largo;
	}
	
	/**
	 * Método getter que indica el tipo de arte de la pieza
	 * @return tipoArte
	 * */
	public String getTipoArte() {
		return tipoArte;
	}
	
	/**
	 * Método getter que indica el material de la pieza
	 * @return material
	 * */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * Método getter que indica la técnica de la pieza
	 * @return tecnica
	 * */
	public String getTecnica() {
		return tecnica;
	}
}
