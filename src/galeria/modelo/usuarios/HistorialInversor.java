package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteDigital;
import galeria.modelo.inventario.ArteTridimensional;
import galeria.modelo.inventario.ArteVisual;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.CrearPiezas;
import galeria.modelo.inventario.Piezas;

public class HistorialInversor implements CrearPiezas{
	
	/**
	 * Lista de las piezas que son propiedad del usuario
	 * */
	private List<Piezas> pizasPropias;
	
	/**
	 * Lista de las piezas que instancia el usuario para ser aprobadas
	 * */
	private List<Piezas> solicitudPiezas;
	
	/**
	 * Atributo que indica el monto maximo a gastar
	 * */
	private double montoMaximo;
	
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
		pizasPropias = new LinkedList<Piezas>();
		solicitudPiezas = new LinkedList<Piezas>();
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
	
	/**
	 * Crea la oferta para la pieza nueva
	 * @param montoMaximo
	 * @param proposito
	 * @param monto
	 * @return GaleriOferta
	 * */
	public GaleriaOferta crearOfertaGaleria(int monto, int montoMinimo, String proposito) {

		GaleriaOferta oferta = new GaleriaOferta(monto, montoMinimo);
		return oferta;
	}
	
	/**
	 * Crea una pieza de tipo digitla, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteDigital
	 * */
	public ArteDigital CrearPiezaDigital(String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, String tipoArte, String tipoArchivo) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		Piezas pieza = new ArteDigital(titulo, proposito, lugar_creacion, año, deposito, oferta, inversor, autores, tipoArte, tipoArchivo);
		galeria.agregarPiezaSolicitud(pieza);
		solicitudPiezas.add(pieza);
		return (ArteDigital) pieza;
	}
	
	/**
	 * Crea una pieza de tipo tridimensional, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteTridimensional
	 * */
	public ArteTridimensional CrearPiezaTridimensional(String titulo,  int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores,double alto, String tecnica, double ancho, double profundidad, double peso, boolean electricidad, String material) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		Piezas pieza = new ArteTridimensional(titulo, proposito, lugar_creacion, deposito, oferta, inversor, año, autores, alto, tecnica, ancho, profundidad, peso, electricidad, material);
		galeria.agregarPiezaSolicitud(pieza);
		solicitudPiezas.add(pieza);
		return (ArteTridimensional) pieza;
	}
	
	/**
	 * Crea una pieza de tipo visual, la envia a solicitud y la agrega a una lista de solicitudes
	 * @param sus parametros son todos los requeridos para la creacion de la pieza
	 * @return ArteVisual
	 * */
	public ArteVisual CrearPiezaPintura(String titulo,  int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, double ancho, double largo , String material, String tecnica, String tipoArte, String tipoArchivo ) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		Piezas pieza = new ArteDigital(titulo, proposito, lugar_creacion, año, deposito, oferta, inversor, autores, tipoArte, tipoArchivo);
		galeria.agregarPiezaSolicitud(pieza);
		solicitudPiezas.add(pieza);
		return (ArteVisual) pieza;
	}
	
	public ConsignacionPieza CrearPiezaEnConsignacion(int tiempo, Piezas pieza) {
		
		ConsignacionPieza consignacion = new ConsignacionPieza(tiempo, pieza, this.inversor);
		return consignacion;
	}
	
	/**
	 * Manda una solicitud de cambio monto
	 * */
	public void crearSolicitudMonto() {
		galeria.agregarSolicitudMonto(inversor);
	}
}
