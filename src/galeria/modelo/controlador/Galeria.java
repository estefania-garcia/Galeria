package galeria.modelo.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.persistencia.CentralPersistencia;
import galeria.modelo.persistencia.PersistenciaInventario;
import galeria.modelo.persistencia.PersistenciaUsuariosJson;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
import galeria.modelo.usuarios.RegistroInicio;
import galeria.modelo.usuarios.Usuarios;

/**
 * Esta clase recolecta todas las ofertas hechas por los usuarios y las organiza según su tipo
 * Sirve como base para que las otras partes del sistema puedan tener un acceso ordenado a estas ofertas
 * */

public class Galeria {
	
	/**
	 * Atributo que nos da acceso a la clase Inventario
	 * */
	private Inventario inventario;
	
	private CentroOfertas centroOfertas;
	
	/**
	 * Atributo que nos da acceso a la clase Inversor
	 * */
	private HistorialInversor inversor_p;
	
	private RegistroInicio registro;
	
	/**
	 * Mapa que guarda todos los usuarios registrados, donde la llave es el tipo de usuario y el valor son los usuarios con ese rol.
	 * */
	private Map<String, List<Usuarios>> usuariosRegistrados;
	
	/**
	 * Lista que guarda los inversores con solicitud de aumento de monto.
	 * */
	private List<Usuarios> inversoresEnSolicitud; 
	
	/**
	 * Lista que guarda todas las piezas que deben ser aprobadas.
	 * */
	private List<Piezas> piezasEnSolicitud;
	
	private List<Ofertas> listaOfertasFinalesSubastas;
	
	private ProcesoCompra cajero;
	
	private OperacionSubasta operador;
	
	/**
	 * Constructor que inicializa la lista de usuarios registrados
	 * */
	public Galeria(){
		
		this.listaOfertasFinalesSubastas = new ArrayList<>();
		this.inventario = new Inventario ();
		this.registro = new RegistroInicio();
		this.cajero = new ProcesoCompra();
		this.centroOfertas = new CentroOfertas();
		this.operador = new OperacionSubasta();
	}
	
	public RegistroInicio getRegistro() {
		
		return registro;
	}
	
	public Inventario getInventario() {
		
		return inventario;
	}
	
	public List<Usuarios> getUsuarios(){
		
		return registro.getAprobados();
	}
	
	public List<Usuarios> getRegistrosUsuarios(){
		
		return registro.getSolicitud();
	}
	
	public void cargarUsuarios() throws IOException {
		
		PersistenciaUsuariosJson cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.cargarTodosUsuarios(this);
	}
	
	public void salvarUsuarios() throws IOException {
		
		PersistenciaUsuariosJson cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.salvarTdosoUsuarios(this);
	}
	
	public List<HistorialInversor> getListaHistorial(){
		
		return registro.getListaHistorial();
	}
	
	public void cargarInventario() throws IOException {
		
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.cargarTodasPiezas(this);
	}
	
	public void salvarInventario() throws IOException {
		
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.salvarTodasPiezas(this);
	}
	
	public List<Ofertas> ofertasFinalesSubastas(Ofertas oferta){
		
		listaOfertasFinalesSubastas.add(oferta);
		return listaOfertasFinalesSubastas;
	}
	
	public List<Ofertas> getOfertasFinalesSubasta(){
		
		return listaOfertasFinalesSubastas;
	}
	
	public void rechazarOfertasFinalesSubasta(Ofertas oferta) {
		
		listaOfertasFinalesSubastas.remove(oferta);
	}
	
	public ProcesoCompra getCajero() {
		
		return cajero;
	}
	
	public List<Ofertas> getOfertasSubasta(){
		
		return centroOfertas.getOfertasSubasta();
	}
	
	public List<Ofertas> getOfertasVentas(){
		
		return centroOfertas.getOfertaVenta();
	}
	
	public CentroOfertas getCentroOfertas() {
		
		return centroOfertas;
	}
	
	public Map<Piezas, List<Ofertas>> getMapaSubastas(){
		
		return operador.getMapa();
	}
	
	public List<ConsignacionPieza> getPiezasSolicitud(){
		
		return inventario.getPiezasSolicitud();
	}
}
