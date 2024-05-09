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
	
	/**
	 * Atributo que nos da acceso a la clase centro ofertas
	 * */
	private CentroOfertas centroOfertas;
	
	/**
	 * Atributo que nos da acceso a la clase Inversor
	 * */
	private HistorialInversor inversor_p;

	/**
	 * Atributo que nos da acceso a la clase registro inicio
	 * */
	private RegistroInicio registro;
	
	/**
	 * Atributo que nos da acceso a la clase proceso compra
	 * */
	private ProcesoCompra cajero;
	
	/**
	 * Atributo que nos da acceso a la clase operacion subasta
	 * */
	private OperacionSubasta operador;
	
	/**
	 * Atributo que nos da acceso a la clase centro autores
	 * */
	private CentroAutores autores;
	
	/**
	 * Lista que guarda a los usuarios que hicieron una solicitud para aumentar su monto maximo de compras
	 * */
	private List<HistorialInversor> listaSolicitudMonto;
	
	/**
	 * Lista que guarda todas las ofertas finales de las subastas y las ventas
	 * */
	private List<Ofertas> listaOfertasFinales;
	
	/**
	 * Constructor que inicializa las listas y todas las demas clases del sistema
	 * */
	public Galeria(){
		
		this.operador = new OperacionSubasta(this);
		this.inventario = new Inventario (operador);
		this.registro = new RegistroInicio();
		this.cajero = new ProcesoCompra(inventario);
		this.centroOfertas = new CentroOfertas(operador, this, inventario);
		this.listaSolicitudMonto = new ArrayList<>();
		this.autores = new CentroAutores();
		listaOfertasFinales = new ArrayList<>();
	}
	
	/**
	 * Método getter de la clase registro
	 * */
	public RegistroInicio getRegistro() {
		
		return registro;
	}
	
	/**
	 * Método getter de la clase centro autores
	 * */
	public CentroAutores getCentroAutores() {
		
		return autores;
	}

	/**
	 * Método getter de la clase operacion subasta
	 * */
	public OperacionSubasta getClaseSubasta() {
		
		return operador;
	}
	
	/**
	 * Método getter de la clase inventario
	 * */
	public Inventario getInventario() {
		
		return inventario;
	}
	
	/**
	 * Método getter de la lista con los usuarios aprobados desde la clase registro inicio
	 * */
	public List<Usuarios> getUsuarios(){
		
		return registro.getAprobados();
	}
	
	/**
	 * Método getter de la lista de los usuarios sin aprobar 
	 * */
	public List<Usuarios> getRegistrosUsuarios(){
		
		return registro.getSolicitud();
	}
	
	/**
	 * Método que nos da acceso a la persistencia de los usuarios para cargarlos
	 * */
	public void cargarUsuarios() throws IOException {
		
		PersistenciaUsuariosJson cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.cargarTodosUsuarios(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia de los usuarios para salvarlos
	 * */
	public void salvarUsuarios() throws IOException {
		
		PersistenciaUsuariosJson cargador = CentralPersistencia.getPersistenciaUsuarios();
		cargador.salvarTdosoUsuarios(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia de las ventas para cargar
	 * */
	public void cargarVentas() throws IOException {
		
		PersistenciaVentas cargador = CentralPersistencia.getPersistenciaOfertas();
		cargador.cargarTodo(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia de las ventas para salvar
	 * */
	public void salvarVentas() throws IOException {
		
		PersistenciaVentas cargador = CentralPersistencia.getPersistenciaOfertas();
		cargador.salvarTodo(this);
	}
	
	/**
	 * Método que nos da acceso a la lista del historial de cada inversor
	 * */
	public List<HistorialInversor> getListaHistorial(){
		
		return registro.getListaHistorial();
	}
	
	/**
	 * Método que nos da acceso a la persistencia del inventario para cargar
	 * */
	public void cargarInventario() throws IOException {
		
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.cargarTodasPiezas(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia del inventario para salvar
	 * */
	public void salvarInventario() throws IOException {
		
		PersistenciaInventario cargador = CentralPersistencia.getPersistenciaInventario();
		cargador.salvarTodasPiezas(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia de los autores para cargar
	 * */
	public void cargarAutores() throws IOException {
		
		PersistenciaAutores cargador = CentralPersistencia.getPersistenciaAutores();
		cargador.cargarTodo(this);
	}
	
	/**
	 * Método que nos da acceso a la persistencia de los autores para salvar
	 * */
	public void salvarAutores() throws IOException {
		
		PersistenciaAutores cargador = CentralPersistencia.getPersistenciaAutores();
		cargador.salvarTodo(this);
	}
	
	/**
	 * Método getter de la clase proceso compra
	 * */
	public ProcesoCompra getCajero() {
		
		return cajero;
	}
	
	/**
	 * Método getter de la lista de las ofertas totales
	 * */
	public List<Ofertas> getTotalOfertas(){
		
		return centroOfertas.getTotalOfertas();
	}
	
	/**
	 * Método getter de la lista de las ofertas de venta
	 * */
	public List<Ofertas> getOfertasVentas(){
		
		return centroOfertas.getOfertaVenta();
	}
	
	/**
	 * Método para rechazar una oferta no aprobada
	 * */
	public void recharOfertasSubasta(Ofertas oferta) {
		
		operador.recharOfertaFinal(oferta);
	}
	
	/**
	 * Método getter de la clase centro ofertas
	 * */
	public CentroOfertas getCentroOfertas() {
		
		return centroOfertas;
	}
	
	/**
	 * Método getter del mapa de las subastas
	 * */
	public Map<Piezas, List<Ofertas>> getMapaSubastas(){
		
		return operador.getMapa();
	}
	
	/**
	 * Método getter de la lista de las piezas en solicitud
	 * */
	public List<ConsignacionPieza> getPiezasSolicitud(){
		
		return inventario.getPiezasSolicitud();
	}
	
	/**
	 * Método para agregar las solicitudes de aumento de monto de compra
	 * */
	public void agregarSolicitudMonto(HistorialInversor solicitud) {
		
		listaSolicitudMonto.add(solicitud);
	}
	
	/**
	 * Método para eliminar las solicitudes hechas
	 * */
	public void eliminarSolicitudMonto(HistorialInversor solicitud) {
		
		listaSolicitudMonto.remove(solicitud);
	}
	
	/**
	 * Método getter de la lista de los historiales de inversor
	 * */
	public List<HistorialInversor> getSolicitudMonto() {
		
		return listaSolicitudMonto;
	}
	
	/**
	 * Método getter de la lista de piezas en consignación
	 * */
	public List<ConsignacionPieza> getDeposito(){
		
		return inventario.getDeposito();
	}
	
	/**
	 * Método getter de la lista de las piezas vigentes
	 * */
	public List<Piezas> getPiezasVigentes(){
		
		return inventario.getVigentes();
	}
	
	/**
	 * Método getter de la lista de los autores
	 * */
	public List<Autores> getListaAutores(){
		
		return autores.getListaAutores();
	}
	
	/**
	 * Método para agregar ofertas a la lista de ofertas finales
	 * */
	public List<Ofertas> getOfertasFinales(Ofertas oferta){
		
		boolean aprobar = true;
		for(Ofertas off : listaOfertasFinales) {
			if(off.getMonto() == oferta.getMonto() && off.getComprador().getInversor().getUsuario().equals(oferta.getComprador().getInversor().getUsuario()) && off.getPiezas().getTitulo().equals(oferta.getPiezas().getTitulo())) {
				aprobar = false;
			}
		}
		if(aprobar == true) {
			listaOfertasFinales.add(oferta);
		}
		return listaOfertasFinales;
	}
	
	/**
	 * Método getter de la lista de las ofertas finales
	 * */
	public List<Ofertas> getListaOfertasFinales(){
		
		return listaOfertasFinales;
	}
}
