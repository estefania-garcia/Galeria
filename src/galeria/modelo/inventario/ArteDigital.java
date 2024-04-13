package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase es una subclase de Piezas que representa obras como música o presentaciones
 * */
public class ArteDigital extends Piezas{

	/**
	 * Constante que representa el tipo de Pieza
	 * */
	private static final String ARTEDIGITAL = "Arte Digital";
	
	/**
	 * Atributo que relacion el tipo de archivo de la pieza. Es decir, si el mp4, mp3, entre otros
	 * */
	private String tipoArchivo;
	
	/**
	 * Atributo que relacion el tipo de arte de la pieza
	 * */
	private String tipoArte;
	
	/**
	 * El constructor junta los atributos del constructor de la super clase y añade sus propieos atributos
	 * @param tipoArchivo
	 * @param tipoArte
	 * */
	public ArteDigital(String titulo, String año, boolean deposito, GaleriaOferta galeriaOferta, Usuarios propietario, String autores, String tipoArte, String tipoArchivo) {
		super(titulo, año, autores, deposito, galeriaOferta, propietario);
		this.tipoArchivo = tipoArchivo;
		this.tipoArte = tipoArte;
	}
	
	/**
	 * Método que indica el tipo de pieza
	 * @return ARTEVISUAL
	 * */
	@Override
	public String getTipo() {
		return ARTEDIGITAL;
	}
	
	/**
	 * Método getter que indica el tipo de arte de la pieza
	 * @return tipoArte
	 * */
	public String getTipoArte() {
		return tipoArte;
	}
	
	/**
	 * Método getter que indica el tipo de archivo de la pieza
	 * @return tipoArchivo
	 * */
	public String getTipoArchivo() {
		return tipoArchivo;
	}
}
