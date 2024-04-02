package galeria.modelo.usuarios;

public class Cajero extends Usuarios{
	
	public static final String CAJERO = "Cajero";
	
	public Cajero(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
	}
	
	@Override
	public String getTipoUsuario() {
		return CAJERO;
	}
	
}
