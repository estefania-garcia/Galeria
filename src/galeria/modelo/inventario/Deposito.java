package galeria.modelo.inventario;

import galeria.modelo.usuarios.Usuarios;

public class Deposito {
	
	private int tiempoDias;
	private Usuarios propietario;
	private String propietarioGaleria;
	private String proposito;
	
	public Deposito(int tiempoDias, Usuarios propietario, String proposito) {
		
		this.propietario = propietario;
		this.tiempoDias = tiempoDias;
		this.proposito = proposito;
	}
	
	public Deposito(String propietarioGaleria, String proposito) {
		
		this.propietarioGaleria = propietarioGaleria;
		this.proposito = proposito;
	}
	
	public int getTiempo() {
		return tiempoDias;
	}
	
	public String getGaleria() {
		return propietarioGaleria;
	}
	
	public Usuarios getPropietario() {
		return propietario;
	}
	
	public String getProposito() {
		return proposito;
	}
}
