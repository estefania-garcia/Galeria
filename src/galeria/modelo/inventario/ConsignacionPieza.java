package galeria.modelo.inventario;

import galeria.modelo.usuarios.Usuarios;

public class ConsignacionPieza {
	
	private int tiempo;
	private Piezas pieza;
	private Usuarios usuario;
	
	public ConsignacionPieza(int tiempo, Piezas pieza, Usuarios usuario) {
		
		this.tiempo = tiempo;
		this.pieza = pieza;
		this.usuario = usuario;
	}
	
	public int getTiempo() {
		
		return tiempo;
	}
	
	public Piezas getPieza() {
		
		return pieza;
	}
	
	public Usuarios getPropietario() {
		
		return usuario;
	}
	
	public void modificarTiempo(int tiempo) {
		
		this.tiempo = tiempo;
	}
}
