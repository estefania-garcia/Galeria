package galeria.modelo.inventario;

import galeria.modelo.usuarios.Usuarios;

/**
 * Clase que relaciona las piezas dadas en consignación
 * */
public class ConsignacionPieza {
	
	/**
	 * Atributo que indica el tiempo de prestamo
	 * */
	private int tiempo;
	
	/**
	 * Atributo que relaciona la pieza en especifico que se dio en prestamos
	 * */
	private Piezas pieza;
	
	/**
	 * Atributo que relacion el usuario propietario de esa pieza
	 * */
	private Usuarios usuario;
	
	/**
	 * Constructor que recibe el tiempo de pretamos, la pieza en prestamo y el usuario dueño
	 * */
	public ConsignacionPieza(int tiempo, Piezas pieza, Usuarios usuario) {
		
		this.tiempo = tiempo;
		this.pieza = pieza;
		this.usuario = usuario;
	}
	
	/**
	 * Método getter del tiempo de prestamo
	 * */
	public int getTiempo() {
		
		return tiempo;
	}
	
	/**
	 * Método getter de la pieza
	 * */
	public Piezas getPieza() {
		
		return pieza;
	}
	
	/**
	 * Método getter del usuario propietario
	 * */
	public Usuarios getPropietario() {
		
		return usuario;
	}
	
	/**
	 * Método que modifica el tiempo de prestamo. Este se debe ejecutar manualmente
	 * */
	public void modificarTiempo(int tiempo) {
		
		this.tiempo = tiempo;
	}
}
