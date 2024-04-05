package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.Ofertas;

public class Administrador{
	
	/*CONSIDERAR LA ELIMINACIÓN DE ESTE ATRIBUTO PUES YA NO HACE PARTE DE LA CLASE ABSTRACTA*/
	private static final String ADMINISTRADOR = "Administrador";
	
	private String usuario;
	private String contrasena;
	private String nombre;
	private Comprador comprador;
	
	private List<Usuarios> nuevosUsuarios = new ArrayList<Usuarios>();
	private List<Usuarios> usuariosConfirmados = new ArrayList<Usuarios>();
	private List<Ofertas> piezasConOferta = new LinkedList<Ofertas>();
	
	public Administrador(String usuario, String contrasena, String nombre) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
	}
	
	public String getTipoUsuario() {
		return ADMINISTRADOR;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Usuarios> nuevosUsuarios(Usuarios usuarios){
		
		nuevosUsuarios.add(usuarios);
		return nuevosUsuarios;
	}
	
	public List<Usuarios> listaUsuarios(Usuarios usuarios, int posUsuario, int monto) {
		
		Usuarios usuario = nuevosUsuarios.get(posUsuario);
		if(usuario.getRol().equals("Inversor")) {
			comprador.asignarValorMaximo(monto);
		}
		usuariosConfirmados.add(usuario);
		nuevosUsuarios.remove(posUsuario);
		return usuariosConfirmados;
	}
	
	public List<Ofertas> agregarOferta(Ofertas ofertas){
		
		piezasConOferta.add(ofertas);
		return piezasConOferta;
	}
}
