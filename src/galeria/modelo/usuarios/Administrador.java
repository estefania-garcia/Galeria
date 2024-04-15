package galeria.modelo.usuarios;


public class Administrador extends Usuarios{
	
	/**
	 * Atributo que representa el tipo de usuario de la clase
	 * */
	private static final String ADMINISTRADOR = "Administrador";
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	public Administrador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
	}
	
	/**
	 * Método getter que indica el tipo de Usuario
	 * @return ADMINISTRADOR
	 * */
	@Override
	public String getTipoUsuario() {
		return ADMINISTRADOR;
	}
}