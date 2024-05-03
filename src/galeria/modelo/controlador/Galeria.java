package galeria.modelo.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedList;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.persistencia.CentralPersistencia;
import galeria.modelo.persistencia.PersistenciaUsuariosJson;
import galeria.modelo.usuarios.HistorialInversor;
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
	 * Atributo que nos da acceso a la clase Inversor
	 * */
	private HistorialInversor inversor_p;
	
	/**
	 * Atributo que nos da acceso a la clase Cajero
	 * */
	private ProcesoCompra cajero_p;
	
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
	
	/**
	 * Constructor que inicializa la lista de usuarios registrados
	 * */
	public Galeria(){
		
		List<Piezas> piezasEnSolicitud = new LinkedList<Piezas>();
		List<Usuarios> inversoresEnSolicitud = new LinkedList<Usuarios>();
		Inventario inventario = new Inventario ();
		this.registro = new RegistroInicio();
	}
	
	//METODO PARA APROBAR NEUVOS USUARIOS
	public void verficarNuevosUsuarios(int posicion, boolean aprobacion) {
		
		Usuarios usuario = registro.getSolicitud().get(posicion);
		if(aprobacion == true) {
			registro.agregarNuevosAprobados(usuario);
		}else {
			registro.rechazarSoliciutd(usuario);
		}
	}
	
	public RegistroInicio getRegistro() {
		
		return registro;
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
	
	
	/**
	 * Método para asignarle el monto máximo de compra a un inversor. Se busca su login en los usuarios registrados y le asigna monto
	 * @param login
	 * @param contraseña
	 * */
	public void asignarMonto(String login, double montoMax)
	{
		List<Usuarios> inversores = usuariosRegistrados.get("Inversor");
		if (inversores != null)
		{
			Iterator<Usuarios> iterator = inversores.iterator();
			while (iterator.hasNext()) 
			{
				Usuarios inversor = iterator.next();
				if (inversor.getUsuario().equals(login))
				{
					inversor_p.modificarMontoMaximo(montoMax);
				}
			}
		}
	}
	
	/**
	 * Agregar inversor son solicitud de aumento de Monto a la lista de solicitudes.
	 * @param Inversor
	 * */
	public void agregarSolicitudMonto (Usuarios inversor)
	{
		inversoresEnSolicitud.add(inversor);
	}
	
	/**
	 * Método para aumentarle el monto máximo de compra a un inversor. Se busca su login en los inversores con solicitud y le asigna monto
	 * @param monto
	 * @param login
	 * */
	public void verificarAumentoMonto (double monto, String login)
	{
		if (inversoresEnSolicitud != null)
		{
			Iterator<Usuarios> iterator = inversoresEnSolicitud.iterator();
			while (iterator.hasNext()) 
			{
				Usuarios inversor = iterator.next();
				if (inversor.getUsuario().equals(login))
				{
					inversor_p.modificarMontoMaximo(monto);
				}
			}
		}
	}
	/**
	 * Agrega una pieza creada por un Inversor a la lista en solicitud para ser aprobada.
	 * @param pieza
	 * */
	public void agregarPiezaSolicitud (Piezas pieza)
	{
		piezasEnSolicitud.add(pieza);
	}
	
	/**
	 * Verifica que las piezas en solicitud no se encuentren actualmente en inventario, y se agregan a las listas correspondientes de inventario.
	 * Se aprueba el atributo de creación de las piezas.
	 * @param n.a.
	 * */
	public void verificarPieza ()
	{
		Iterator<Piezas> iterator = piezasEnSolicitud.iterator();
		while (iterator.hasNext()) 
		{
			Piezas pieza = iterator.next();
			boolean encontrada = false;
			Iterator<Piezas> inventarioTotal = inventario.getTotale().iterator();
			while (inventarioTotal.hasNext()) 
			{
				Piezas piezaInventario = iterator.next();
				if (pieza.equals(piezaInventario))
				{
					encontrada = true;
					break;
				}	
			}	
			if (encontrada == false)
			{
				inventario.añadirPiezas(pieza);
				if (pieza.getDeposito()== true)
				{
					//inventario.añadirPiezasDeposito(pieza);
					inventario.añadirPiezasVigente(pieza);
				}
				if (pieza.getLugar()=="Exhibición")
				{
					inventario.añadirPiezasExhibir(pieza);
				}
				else if (pieza.getLugar()=="Subasta")
				{
					inventario.añadirPiezasSubasta(pieza);
				}
				else if (pieza.getLugar()=="Venta")
				{
					inventario.añadirPiezasVenta(pieza);
				}
				pieza.actualizarVerficacionPieza(true);
				
			}
		}	
	}
	/**
	 * Cambiar el lugar de la pieza, esta puede estar en la bodega o exhibida
	 * @param ubicacion
	 * @param pieza
	 * */
	public void cambiarUbicacionPieza (Piezas pieza, String ubicacion)
	{
		pieza.asignarLugar(ubicacion);
	}
	/**
	 * Hacer la devolución de una pieza que haya sido por "consignación" y sus días ya sean 0.
	 * @param pieza
	 * */
	public void devolverPieza (Piezas pieza)
	{
		if (pieza.getDeposito()==true)
		{
			if (pieza.getDiasPrestamo()==0)
			{
				//inventario.añadirPiezasDevueltas(pieza);
			}
		}
	}
	
	/**
	 * Pasa la oferta final a cajero para hacer efectiva la venta.
	 * @param oferta
	 * */
	public void validarOfertaFinal (Ofertas oferta)
	{
		cajero_p.agregarOfertas(oferta);
	}
	
	/**
	 * Asignar el número de días que la pieza fue dada en prestamo. Si es propiedad de la galería, su valor será siempre 0
	 * @param dias
	 * */
	public void actualizarDias (Piezas pieza, int dias)
	{
		pieza.actualizarDiasPrestamos(dias);
	}
}
