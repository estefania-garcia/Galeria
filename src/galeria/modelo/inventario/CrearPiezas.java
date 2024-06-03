package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.usuarios.Usuarios;

public interface CrearPiezas {
	
	public GaleriaOferta crearOfertaGaleria(int monto, int montoMinimo, String proposito);
	
	/**
	 * Crea una pieza de tipo digitla, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteDigital
	 * */
	public ArteDigital CrearPiezaDigital(String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, String tipoArte, String tipoArchivo);
	
	/**
	 * Crea una pieza de tipo tridimensional, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteTridimensional
	 * */
	public ArteTridimensional CrearPiezaTridimensional(String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores,double alto, String tecnica, double ancho, double profundidad, double peso, boolean electricidad, String material);
	
	/**
	 * Crea una pieza de tipo visual, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteVisual
	 * */
	public ArteVisual CrearPiezaPintura(String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, double ancho, double largo , String material, String tecnica, String tipoArte, String tipoArchivo );
}
