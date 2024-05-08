package galeria.modelo.usuarios;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.OfertaVenta;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteDigital;
import galeria.modelo.inventario.ArteTridimensional;
import galeria.modelo.inventario.ArteVisual;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.CrearPiezas;
import galeria.modelo.inventario.Piezas;

public class HistorialInversor{
	
	/**
	 * Lista de las piezas que son propiedad del usuario
	 * */
	private List<Piezas> pizasPropias;
	
	private List<Piezas> piezasCompradas;
	
	/**
	 * Atributo que indica el monto maximo a gastar
	 * */
	private double montoMaximo;
	
	private double valorColeccion;
	
	/**
	 * Atributo que nos da acceso a galeria
	 * */
	private Galeria galeria;
	
	private Usuarios inversor;
	
	/**
	 * Constructor que relaciona propiedades de la super clase y inicializa la listas
	 * */
	public HistorialInversor(double monto, Usuarios inversor) {
		
		this.montoMaximo = monto;
		this.inversor = inversor;
		pizasPropias = new ArrayList<Piezas>();
		piezasCompradas = new ArrayList<Piezas>();
	}
	
	public void asignarGaleria(Galeria galeria) {
		
		this.galeria = galeria;
	}
	
	public double getValorColeccion() {
		
		return valorColeccion;
	}
	
	/**
	 * Método que dofica el monto maximo
	 * @param montoMaximo
	 * */
	public void modificarMontoMaximo(double montoMaximo) {
        
		this.montoMaximo = montoMaximo;
	}
	
	/**
	 * Método getter del monto maximo
	 * @return montoMaximo
	 * */
	public double getMontoMaximo() {
		
		return montoMaximo;
	}
	
	/**
	 * Método getter de lista piezas
	 * @return piezasPropias
	 * */
	public List<Piezas> getPiezas(){
		
		return pizasPropias;
	}
	
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
	 * Método getter del usuario dueño
	 * */
	public Usuarios getInversor() {
		
		return inversor;
	}
	
	public OfertaSubasta crearOfertaSubasta(Piezas pieza, HistorialInversor comprador, double monto) {
		
		OfertaSubasta ofertaNueva = new OfertaSubasta(pieza, comprador, monto);
        return ofertaNueva;
	}
	
	public OfertaVenta crearOfertaVenta(Piezas pieza, HistorialInversor comprador, double monto) {
		
		OfertaVenta ofertaNueva = new OfertaVenta(pieza, comprador, monto);
        return ofertaNueva;
	}
	
	/**
	 * Crea la oferta para la pieza nueva
	 * @param montoMaximo
	 * @param proposito
	 * @param monto
	 * @return GaleriOferta
	 * */
	public GaleriaOferta crearOfertaGaleria(double monto, double montoMinimo, String proposito) {

		GaleriaOferta oferta = new GaleriaOferta(monto, montoMinimo);
		return oferta;
	}
	
	/**
	 * Crea una pieza de tipo digitla, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteDigital
	 * */
	public ConsignacionPieza CrearPiezaDigital(int dias, String titulo, double monto, double montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String tipoArte, String tipoArchivo) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		Piezas pieza = new ArteDigital(titulo, proposito, lugar_creacion, deposito, oferta, inversor, autores, tipoArte, tipoArchivo);
		ConsignacionPieza consignacion = new ConsignacionPieza(dias, pieza, inversor);
		galeria.getInventario().añadirPiezasSolicitud(consignacion);
		//solicitudPiezas.add(pieza);
		return consignacion;
	}
	
	/**
	 * Crea una pieza de tipo tridimensional, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteTridimensional
	 * */
	public ConsignacionPieza CrearPiezaTridimensional(int dias, String titulo,  double monto, double montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String dimensiones, String tecnica, double peso, boolean electricidad) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		Piezas pieza = new ArteTridimensional(titulo, proposito, lugar_creacion, deposito, oferta, inversor, autores, dimensiones, tecnica, peso, electricidad);
		ConsignacionPieza consignacion = new ConsignacionPieza(dias, pieza, inversor);
		galeria.getInventario().añadirPiezasSolicitud(consignacion);
		//solicitudPiezas.add(pieza);
		return consignacion;
	}
	
	/**
	 * Crea una pieza de tipo visual, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteVisual
	 * */
	public ConsignacionPieza CrearPiezaPintura(int dias, String titulo, double monto, double montoMinimo, String proposito, String lugar_creacion, boolean deposito, Usuarios propietario, String autores, String anchoxlargo,String tecnica) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
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
