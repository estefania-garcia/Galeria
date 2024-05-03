package galeria.modelo.usuarios;

import org.json.JSONObject;

/**
 * Esta clase modela los usuarios que van a ser parte de la galería y tiene 4 subclases, para cada tipo de usuario.
 * */
public class Usuarios {
	
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
	}

	/**
	 * Método getter que indica el tipo de Usuario
	 * @return tipo de usuario
	 * */
	public String getTipoUsuario() {
		
		return rol;
	}
	
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
	
	public static Usuarios cargarDesdeJSON(JSONObject usuario) {
		
		String rol = usuario.getString("rol");
		String usuario1 = usuario.getString("usuario");
		String nombre = usuario.getString("nombre");
		String contraseña = usuario.getString("contraseña");
		return new Usuarios(usuario1, contraseña, rol, nombre);
	}
	
	public JSONObject salvarEnJson() {
		
		JSONObject jobject = new JSONObject();
		jobject.put("rol", this.rol);
		jobject.put("nombre", this.nombre);
		jobject.put("usuario", this.usuario);
		jobject.put("contraseña", this.contrasena);
		return jobject;
	}
}