package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.Inventario;

public class Cajero extends Usuarios{
	
	/**
	 * Atributo que representa el tipo de usuario de la clase
	 * */
	public static final String CAJERO = "Cajero";
	
	/**
	 * Lista de las ofertas que se efectuan como venta de la galeria
	 * */
	public List<Ofertas> lista_ofertas;
	
	/**
	 * Atributo que nos da acceso a la clase Inventario
	 * */
	private Inventario inventario;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	public Cajero(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		lista_ofertas = new LinkedList<>();
	}
	
	/**
	 * Método getter que indica el tipo de Usuario
	 * @return CAJERO
	 * */
	@Override
	public String getTipoUsuario() {
		return CAJERO;
	}
	
	/**
	 * Método que agrega la oferta a la lista y modifica el estado de la pieza, la agrega en piezas vendidas del inventario y elimina la pieza de las disponibles
	 * @param ofertas
	 * */
	public void agregarOfertas(Ofertas ofertas) {
		lista_ofertas.add(ofertas);
		inventario.modificarEstado("Vendida", ofertas.getPiezas());
		inventario.añadirPiezasVendidas(ofertas.getPiezas());
		inventario.eliminarPiezas(ofertas.getPiezas(), ofertas.getPiezas().getProposito(), ofertas.getPiezas().getDeposito());
	}
}
