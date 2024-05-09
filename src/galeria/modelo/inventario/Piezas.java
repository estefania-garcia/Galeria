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
	
	/**
	 * Atributo booleano que indica si esta vigente o no. Se inicializa en false esperando a aprobacion
	 * */
	private boolean vigencia = false;
	
	/**
	 * Atributo booleano que indica si esta fue aprobada o no. Se inicializa en false esperando a aprobacion
	 * */
	private boolean aprobacion = false;
	
	/**
	 * Atributo booleano que indica si fue vendida o no. Se inicializa en false esperando a aprobacion de alguna oferta
	 * */
	private boolean vendida = false;

	/**
	 * Atributo que relacion la fecha de venta. Inicia en vacio
	 * */
	private String fecha_vendida = "";
	
	/**
	 * Atributo que relacion el lugar de creación de la pieza
	 * */
	private String año_lugar_creacion;
	
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
	
	/**
	 * Método para cambiar la vigencia
	 * */
	public void asignarVigenica(boolean vigente) {
		
		this.vigencia = vigente;
	}
	
	/**
	 * Método getter para la vigencia
	 * */
	public boolean getVigencia() {
		
		return vigencia;
	}
	
	/**
	 * Método que cambia el estado de venta
	 * */
	public void asignarVenta(boolean venta) {
		
		this.vendida = venta;
	}
	
	/**
	 * Método getter del booleano de la venta
	 * */
	public boolean getVenta() {
		
		return vendida;
	}
	
	/**
	 * Método que cambia su estado de aprobacion
	 * */
	public void asignarAprobada(boolean aprobacion) {
		
		this.aprobacion = aprobacion;
	}
	
	/**
	 * Método getter de la aprobación
	 * */
	public boolean getAprobada() {
		
		return aprobacion;
	}
	
	/**
	 * Método que permite cambiar el propietario de una pieza si esta fue comprada por otro
	 * */
	public void modificarPropietario(Usuarios nuevo) {
		
		this.propietario = nuevo;
	}
	
	/**
	 * Método abstracto que indica el tipo de pieza
	 * */
	public abstract String getTipo();
	
	/**
	 * Método getter que indica los autores
	 * */
	public String getAutores() {
		return autores;	
	}
	
	/**
	 * Método getter que indica el proposito
	 * */
	public String getProposito() {
		return proposito;	
	}
	
	/**
	 * Método getter que indica el lugar de creacion
	 * */
	public String getLugarCreacion() {
		return año_lugar_creacion;	
	}
	
	/**
	 * Método getter que indica el propietario
	 * */
	public Usuarios getPropietario() {
		return propietario;
	}
	
	/**
	 * Método getter que indica si es deposito o no
	 * */
	public boolean getDeposito() {
		return deposito;
	}
	
	/**
	 * Método getter que indica el titulo
	 * */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Método getter que indica el tipo de oferta según su proposito
	 * */
	public GaleriaOferta getGaleriaOferta() {
		return galeriaOferta;
	}
	
	/**
	 * Método getter de la fecha de venta
	 * */
	public String getFechaVendida() {
		
		return fecha_vendida;
	}
	
	/**
	 * Método que permite cambiar la fecha de venta
	 * */
	public void asignarFechaVendida(String fecha) {
		
		this.fecha_vendida = fecha;
	}
	
	/**
	 * Método que le asigna un estado a la pieza
	 * */
	public String asignarEstado(String estados) {
		this.estado = estados;
		return estado;
	}
	
	/**
	 * Método getter que indica el estado de la pieza
	 * */
	public String getEstado() {
		return estado;
	}
}
