package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.Ofertas;

public class Administrador extends Usuarios{
	
	/*CONSIDERAR LA ELIMINACIÓN DE ESTE ATRIBUTO PUES YA NO HACE PARTE DE LA CLASE ABSTRACTA*/
	private static final String ADMINISTRADOR = "Administrador";
	
	public Administrador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
	}
	
	@Override
	public String getTipoUsuario() {
		return ADMINISTRADOR;
	}
}
