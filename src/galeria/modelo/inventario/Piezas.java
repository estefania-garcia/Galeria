package galeria.modelo.inventario;

import java.util.Date;

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
	
	private boolean vigencia = false;
	
	private boolean aprobacion = false;
	
	private boolean vendida = false;

	
	private Date fecha_vendida;
	/**
	 * Atributo que relacion el lugar de creación de la pieza
	 * */
	private String año_lugar_creacion;
	
	/**
	 * Atributo que relacion el lugar en el que se encuentra la pieza dentro de la galería
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
	private String estado = "Nueva";
	
	/**
	 * Atributo que indica el proposito de la pieza, si es para vender, exhibir o subastar
	 * */
	private String proposito;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param titulo
	 * @param año
	 * @param autores
	 * @param deposito
	 * @param galeriaOferta
	 * @param propietario
	 * @param proposito
	 * */
	public Piezas(String titulo, String proposito, String año_lugar_creacion, String autores, boolean deposito, GaleriaOferta galeriaOferta, Usuarios propietario) {
		
		this.titulo = titulo;
		this.autores = autores;
		this.deposito = deposito;
		this.galeriaOferta = galeriaOferta;
		this.propietario = propietario;
		this.año_lugar_creacion = año_lugar_creacion;
		this.proposito = proposito;
	}
	
	public void asignarVigenica(boolean vigente) {
		
		this.vigencia = vigente;
	}
	
	public boolean getVigencia() {
		
		return vigencia;
	}
	
	public void asignarVenta(boolean venta) {
		
		this.vendida = venta;
	}
	
	public boolean getVenta() {
		
		return vendida;
	}
	
	public void asignarAprobada(boolean aprobacion) {
		
		this.aprobacion = aprobacion;
	}
	
	public boolean getAprobada() {
		
		return aprobacion;
	}
	
	public void modificarPropietario(Usuarios nuevo) {
		
		this.propietario = nuevo;
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
	 * Método getter que indica el proposito
	 * @return autores
	 * */
	public String getProposito() {
		return proposito;	
	}
	
	/**
	 * Método getter que indica el lugar de creacion
	 * @return autores
	 * */
	public String getLugarCreacion() {
		return año_lugar_creacion;	
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
	 * Método getter que indica el tipo de oferta según su proposito
	 * @return galeriaOferta
	 * */
	public GaleriaOferta getGaleriaOferta() {
		return galeriaOferta;
	}
	
	public Date getFechaVendida() {
		
		return fecha_vendida;
	}
	
	public void asignarFechaVendida(Date fecha) {
		
		this.fecha_vendida = fecha;
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
}
