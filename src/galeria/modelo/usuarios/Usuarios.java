package galeria.modelo.usuarios;

public abstract class Usuarios {
	
	private String usuario;
	private String contrasena;
	private String rol;
	private String nombre;
	private boolean aprobacion;
	
	public Usuarios(String usuario, String contrasena, String rol, String nombre) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
		this.nombre = nombre;
		this.aprobacion = false;
	}

	
	public abstract String getTipoUsuario();
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public String getRol() {
		return rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean darAprobacion(boolean approved) {
	    if (usuario != null && nombre != null && contrasena != null && rol != null) {
	        this.aprobacion = true;
	    }
	    return aprobacion;
	}

}
