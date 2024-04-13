package galeria.modelo.inventario;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase modela las piezas que van a ser parte del inventario de la galería
 * */
public abstract class Piezas {
	
	/**
	 * Atributo que relacion el titulo de la pieza
	 * */
	private String titulo;
	
	/**
	 * Atributo que relacion el año de creación de la pieza
	 * */
	private String año;
	
	/**
	 * Atributo que relacion el lugar de creación de la pieza
	 * */
	private String lugar;
	
	/**
	 * Atributo que relacion los autrores de la pieza
	 * */
	private String autores;
	
	/**
	 * Atributo que indica si la pieza fue dada en consignación o no
	 * */
	private boolean deposito;
	
	/**
	 * Atributo que indica la información relaciónada con el propósito de la pieza (exhibir, vender o subastar)
	 * */
	private GaleriaOferta galeriaOferta;
	
	/**
	 * Atributo que relacion el propietario de la pieza
	 * */
	private Usuarios propietario;
	
	/**
	 * Atributo que relacion el estado de la pieza (bloqueda, vendida o devuleta)
	 * */
	private String estado;
	
	/**
	 * Atributo que relacion el número de días que fue dado en consignación de la pieza. Si esta es propiedad de la galería, este atributo será 0
	 * */
	private int dias;
	
	/**
	 * Atributo que indica si la pieza fue admitida o no
	 * */
	private boolean creacion;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param titulo
	 * @param año
	 * @param autores
	 * @param deposito
	 * @param galeriaOferta
	 * @param propietario
	 * */
	public Piezas(String titulo, String año, String autores, boolean deposito, GaleriaOferta galeriaOferta, Usuarios propietario) {
		
		this.titulo = titulo;
		this.año = año;
		this.autores = autores;
		this.deposito = deposito;
		this.galeriaOferta = galeriaOferta;
		this.propietario = propietario;
	}
	
	/**
	 * Método abstracto que indica el tipo de pieza
	 * */
	public abstract String getTipo();
	
	/**
	 * Método getter que indica los autores
	 * @return autores
	 * */
	public String getAutores() {
		return autores;	
	}
	
	/**
	 * Método getter que indica el propietario
	 * @return propietario
	 * */
	public Usuarios getPropietario() {
		return propietario;
	}
	
	/**
	 * Método getter que indica si es deposito o no
	 * @return deposito
	 * */
	public boolean getDeposito() {
		return deposito;
	}
	
	/**
	 * Método getter que indica el titulo
	 * @return titulo
	 * */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Método getter que indica el año de creación
	 * @return año
	 * */
	public String getAño() {
		return año;
	}
	
	/**
	 * Método getter que indica el tipo de oferta según su proposito
	 * @return galeriaOferta
	 * */
	public GaleriaOferta getGaleriaOferta() {
		return galeriaOferta;
	}
	
	/**
	 * Método le asigna el lugar a la pieza, esta puede estar en la bodega o exhibida
	 * @param ubicacion
	 * @return lugar
	 * */
	public String asignarLugar( String ubicacion) {
		this.lugar = ubicacion;
		return lugar;
	}
	
	/**
	 * Método getter que indica el lugar de la pieza
	 * @return lugar
	 * */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Método que le asigna un estado a la pieza
	 * @param estados
	 * @return estado
	 * */
	public String asignarEstado(String estados) {
		this.estado = estados;
		return estado;
	}
	
	/**
	 * Método getter que indica el estado de la pieza
	 * @return estado
	 * */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Método asigna el número de días que la pieza fue dada en prestamo. Si es propiedad de la galería, su valor será siempre 0
	 * @param dias
	 * @return dias
	 * */
	public int actualizarDiasPrestamos(int dias) {
		this.dias = dias;
		return this.dias;
	}
	
	/**
	 * Método getter que indica los dias de prestamo
	 * @return dias
	 * */
	public int getDiasPrestamo() {
		return dias;
	}
	
	/**
	 * Método que indica si la pieza fue aprobada por el administrador
	 * @param crear
	 * @return creacion
	 * */
	public boolean actualizarVerficacionPieza(boolean crear) {
		this.creacion = crear;
		return this.creacion;
	}
	
	/**
	 * Método getter que indica la aprobación de la pieza
	 * @return creacion
	 * */
	public boolean getCreacion() {
		return creacion;
	}
}
