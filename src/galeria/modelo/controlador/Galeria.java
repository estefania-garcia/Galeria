package galeria.modelo.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.Autores;
import galeria.modelo.inventario.CentroAutores;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.persistencia.CentralPersistencia;
import galeria.modelo.persistencia.PersistenciaAutores;
import galeria.modelo.persistencia.PersistenciaInventario;
import galeria.modelo.persistencia.PersistenciaUsuariosJson;
import galeria.modelo.persistencia.PersistenciaVentas;
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
	
	private ProcesoCompra cajero;
	
	private OperacionSubasta operador;
	
	private CentroAutores autores;
	
	private List<HistorialInversor> listaSolicitudMonto;
	
	/**
	 * Constructor que inicializa la lista de usuarios registrados
	 * */
	public Galeria(){
		
		this.operador = new OperacionSubasta();
		this.inventario = new Inventario (operador);
		this.registro = new RegistroInicio();
		this.cajero = new ProcesoCompra();
		this.centroOfertas = new CentroOfertas();
		this.listaSolicitudMonto = new ArrayList<>();
		this.autores = new CentroAutores();
	}
	
	public RegistroInicio getRegistro() {
		
		return registro;
	}
	
	public CentroAutores getCentroAutores() {
		
		return autores;
	}

	public OperacionSubasta getClaseSubasta() {
		
		return operador;
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
	
	public void cargarVentas() throws IOException {
		
		PersistenciaVentas cargador = CentralPersistencia.getPersistenciaOfertas();
		cargador.cargarTodo(this);
	}
	
	public void salvarVentas() throws IOException {
		
		PersistenciaVentas cargador = CentralPersistencia.getPersistenciaOfertas();
		cargador.salvarTodo(this);
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
	
	public void cargarAutores() throws IOException {
		
		PersistenciaAutores cargador = CentralPersistencia.getPersistenciaAutores();
		cargador.cargarTodo(this);
	}
	
	public void salvarAutores() throws IOException {
		
		PersistenciaAutores cargador = CentralPersistencia.getPersistenciaAutores();
		cargador.salvarTodo(this);
	}
	
	public ProcesoCompra getCajero() {
		
		return cajero;
	}
	
	public List<Ofertas> getOfertasSubasta(){
		
		return operador.getofertaFinalesSubasta();
	}
	
	public List<Ofertas> getOfertasVentas(){
		
		return centroOfertas.getOfertaVenta();
	}
	
	public void recharOfertasSubasta(Ofertas oferta) {
		
		operador.recharOfertaFinal(oferta);
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
	
	public void agregarSolicitudMonto(HistorialInversor solicitud) {
		
		listaSolicitudMonto.add(solicitud);
	}
	
	public void eliminarSolicitudMonto(HistorialInversor solicitud) {
		
		listaSolicitudMonto.remove(solicitud);
	}
	
	public List<HistorialInversor> getSolicitudMonto() {
		
		return listaSolicitudMonto;
	}
	
	public List<ConsignacionPieza> getDeposito(){
		
		return inventario.getDeposito();
	}
	
	public List<Piezas> getPiezasVigentes(){
		
		return inventario.getVigentes();
	}
	
	public List<Autores> getListaAutores(){
		
		return autores.getListaAutores();
	}
}
