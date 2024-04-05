package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.inventario.Piezas;

public class Propietario extends Usuarios{

	public static final String PROPIETARIO = "Propietario";
	private List<Piezas> pizasPropias;
	
	public Propietario(String usuario, String contrasena, String rol, String nombre) {
		super(usuario, contrasena, rol, nombre);
		pizasPropias = new LinkedList<Piezas>();
	}
	
	@Override
	public String getTipoUsuario() {
		return PROPIETARIO;
	}
	
	public List<Piezas> getPiezas(){
		return pizasPropias;
	}
	
	public void agregarPiezasPropias(Piezas piezas){
		pizasPropias.add(piezas);
	}
}
