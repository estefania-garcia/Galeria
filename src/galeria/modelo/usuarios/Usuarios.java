package galeria.modelo.usuarios;

/**
 * Esta clase modela los usuarios que van a ser parte de la galería y tiene 4 subclases, para cada tipo de usuario.
 * */
public abstract class Usuarios {
	
	/**
	 * Atributo que relaciona el login del usuario
	 * */
	private String usuario;
	/**
	 * Atributo que relaciona la contraseña del usuario
	 * */
	private String contrasena;
	/**
	 * Atributo que relaciona el rol del usuario para instanciar la subclase correcta
	 * */
	private String rol;
	/**
	 * Atributo que relaciona el nombre del usuario
	 * */
	private String nombre;
	/**
	 * Atributo que indica si el usuario fue aprobado o no
	 * */
	private boolean aprobacion;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	
	public Usuarios(String usuario, String contrasena, String rol, String nombre) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
		this.nombre = nombre;
		this.aprobacion = false;
	}

	/**
	 * Método getter que indica el tipo de Usuario
	 * @return tipo de usuario
	 * */
	public abstract String getTipoUsuario();
	
	/**
	 * Método getter que indica el login del Usuario
	 * @return login
	 * */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Método getter que indica la contraseña del Usuario
	 * @return contraseña
	 * */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * Método getter que indica el rol del Usuario
	 * @return rol
	 * */
	public String getRol() {
		return rol;
	}
	
	/**
	 * Método getter que indica el nombre del Usuario
	 * @return nombre
	 * */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Método que aprueba el Usuario para que pueda estar activo en la galeria
	 * @return nombre
	 * */
	public boolean darAprobacion(boolean approved) {
	    if (usuario != null && nombre != null && contrasena != null && rol != null) {
	        this.aprobacion = true;
	    }
	    return aprobacion;
	}

}