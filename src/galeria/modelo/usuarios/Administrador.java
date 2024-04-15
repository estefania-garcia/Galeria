package galeria.modelo.usuarios;


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
