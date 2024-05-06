package galeria.modelo.inventario;

import org.json.JSONObject;

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
	private String anchoxlargo;

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
	public ArteVisual(String titulo, String proposito, String lugar_creacion, boolean deposito, GaleriaOferta galeriaOferta, Usuarios propietario, String autores, String anchoxlargo, String tecnica) {
		super(titulo, proposito, lugar_creacion, autores, deposito, galeriaOferta, propietario);
		this.anchoxlargo = anchoxlargo;
		this.tecnica = tecnica;
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
	public String getAncho() {
		return anchoxlargo;
	}
	
	/**
	 * Método getter que indica la técnica de la pieza
	 * @return tecnica
	 * */
	public String getTecnica() {
		return tecnica;
	}
}