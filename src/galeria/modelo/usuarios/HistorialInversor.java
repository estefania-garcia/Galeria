package galeria.modelo.usuarios;


import java.util.ArrayList;
import java.util.List;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.OfertaVenta;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteDigital;
import galeria.modelo.inventario.ArteTridimensional;
import galeria.modelo.inventario.ArteVisual;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Piezas;

public class HistorialInversor{
	
	/**
	 * Lista de las piezas que son propiedad del usuario
	 * */
	private List<Piezas> pizasPropias;
	
	/**
	 * Lista de las piezas compradas
	 * */
	private List<Piezas> piezasCompradas;
	
	/**
	 * Atributo que indica el monto maximo a gastar
	 * */
	private int montoMaximo;
	
	/**
	 * Atributo del valor de las piezas compradas
	 * */
	private int valorColeccion;
	
	/**
	 * Atributo que nos da acceso a galeria
	 * */
	private Galeria galeria;
	
	/**
	 * Atributo que relaciona al usuario
	 * */
	private Usuarios inversor;
	
	/**
	 * Constructor que relaciona propiedades de la super clase y inicializa la listas
	 * */
	public HistorialInversor(int monto, Usuarios inversor) {
		
		this.montoMaximo = monto;
		this.inversor = inversor;
		pizasPropias = new ArrayList<Piezas>();
		piezasCompradas = new ArrayList<Piezas>();
	}
	
	/**
	 * Método para referencia a la clase galeria sin crear una adicional
	 * */
	public void asignarGaleria(Galeria galeria) {
		
		this.galeria = galeria;
	}
	
	/**
	 * Método getter del valor de la coleccion
	 * */
	public int getValorColeccion() {
		
		return valorColeccion;
	}
	
	/**
	 * Método que aumenta el vlaor de la coleccion
	 * */
	public int asignarValorColeccion(int compra) {
		
		valorColeccion = valorColeccion + compra;
		return valorColeccion;
	}
	
	/**
	 * Método que dofica el monto maximo
	 * */
	public void modificarMontoMaximo(int montoMaximo) {
        
		this.montoMaximo = montoMaximo;
	}
	
	/**
	 * Método getter del monto maximo
	 * */
	public int getMontoMaximo() {
		
		return montoMaximo;
	}
	
	/**
	 * Método getter de lista piezas
	 * */
	public List<Piezas> getPiezas(){
		
		return pizasPropias;
	}
	
	/**
	 * Método getter de las peizas compradas
	 * */
	public List<Piezas> getPiezasCompradas(){
		
		return piezasCompradas;
	}
	
	/**
	 * Agregar las piezas propias
	 * */
	public void agregarPiezasPropias(Piezas piezas){
		
		pizasPropias.add(piezas);
	}
	
	/**
	 * Método para agregar peizas compradas
	 * */
	public void agregarPiezasCompradas(Piezas piezas){
		
		piezasCompradas.add(piezas);
	}
	
	/**
	 * Método getter del usuario dueño
	 * */
	public Usuarios getInversor() {
		
		return inversor;
	}
	
	/**
	 * Método para crear una oferta de subasta
	 * */
	public OfertaSubasta crearOfertaSubasta(Piezas pieza, HistorialInversor comprador, int monto) {
		
		OfertaSubasta ofertaNueva = new OfertaSubasta(pieza, comprador, monto);
        return ofertaNueva;
	}
	
	/**
	 * Método para crear un oferta de venta
	 * */
	public OfertaVenta crearOfertaVenta(Piezas pieza, HistorialInversor comprador, int monto) {
		
		OfertaVenta ofertaNueva = new OfertaVenta(pieza, comprador, monto);
        return ofertaNueva;
	}
	
	/**
	 * Crea la oferta para la pieza nueva
	 * */
	public GaleriaOferta crearOfertaGaleria(int monto, int montoMinimo) {

		GaleriaOferta oferta = new GaleriaOferta(monto, montoMinimo);
		return oferta;
	}
	
	/**
	 * Crea una pieza de tipo digitla, la envia a solicitud y la agrega a una lista de solicitudes
	 * */
	public ConsignacionPieza CrearPiezaDigital(int dias, String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String tipoArte, String tipoArchivo) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo);
		Piezas pieza = new ArteDigital(titulo, proposito, lugar_creacion, deposito, oferta, inversor, autores, tipoArte, tipoArchivo);
		ConsignacionPieza consignacion = new ConsignacionPieza(dias, pieza, inversor);
		galeria.getInventario().añadirPiezasSolicitud(consignacion);
		//solicitudPiezas.add(pieza);
		return consignacion;
	}
	
	/**
	 * Crea una pieza de tipo tridimensional, la envia a solicitud y la agrega a una lista de solicitudes
	 * */
	public ConsignacionPieza CrearPiezaTridimensional(int dias, String titulo,  int monto, int montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String dimensiones, String tecnica, int peso, boolean electricidad) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo);
		Piezas pieza = new ArteTridimensional(titulo, proposito, lugar_creacion, deposito, oferta, inversor, autores, dimensiones, tecnica, peso, electricidad);
		ConsignacionPieza consignacion = new ConsignacionPieza(dias, pieza, inversor);
		galeria.getInventario().añadirPiezasSolicitud(consignacion);
		//solicitudPiezas.add(pieza);
		return consignacion;
	}
	
	/**
	 * Crea una pieza de tipo visual, la envia a solicitud y la agrega a una lista de solicitudes
	 * */
	public ConsignacionPieza CrearPiezaPintura(int dias, String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String anchoxlargo,String tecnica) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo);
		Piezas pieza = new ArteVisual(titulo, proposito, lugar_creacion, deposito, oferta, inversor, autores, anchoxlargo, tecnica);
		ConsignacionPieza consignacion = new ConsignacionPieza(dias, pieza, inversor);
		galeria.getInventario().añadirPiezasSolicitud(consignacion);
		//solicitudPiezas.add(pieza);
		return consignacion;
	}
	
	/**
	 * Manda una solicitud de cambio monto
	 * */
	public void crearSolicitudMonto() {
		
		galeria.agregarSolicitudMonto(this);
	}
}
