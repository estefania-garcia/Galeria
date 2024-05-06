package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RegistroInicio {
	
	/**
	 * Lista que guarda los nuevos usuarios, es decir, aquellos que se han registrado pero no han sido aprobados
	 * */
	private List<Usuarios> listaNuevosUsuarios;
	
	/**
	 * Lista que guarda los usuarios aprobados por el administrador
	 * */
	private List<Usuarios> listaUsuariosRegistrados;
	
	/**
	 * Lista que guarda todos los historiales de todos los usuarios de tipo "Inversor"
	 * */
	private List<HistorialInversor> listaHistorialesInversores;
	
	/**
	 * Atributo que contiene la única instancia de la clase encargada de llevar el trazo de las subastas. Manejado por el "Operador"
	 * */
	private OperacionSubasta subasta;
	
	/**
	 * Atributo que contiene la única instancia de la clase encargada de llevar el trazo de las ofertas finales. Manejado por el "Cajero"
	 * */
	private ProcesoCompra caja;
	
	/**
	 * Método contructor. Inicializa las dos listas de los usuarios y la lista de los historiales de los inversores
	 * */
	public RegistroInicio() {
		
		this.listaNuevosUsuarios = new ArrayList<Usuarios>();
		this.listaUsuariosRegistrados = new ArrayList<Usuarios>();
		this.listaHistorialesInversores = new ArrayList<HistorialInversor>();
	}
	
	/**
	 * Método getter de la lista de los usuarios sin aprobación pero registrados
	 * */
	public List<Usuarios> getSolicitud(){
		
		return listaNuevosUsuarios;
	}
	
	/**
	 * Método getter de la lista de los usuarios aprobados
	 * */
	public List<Usuarios> getAprobados(){
		
		return listaUsuariosRegistrados;
	}
	
	/**
	 * Método que agrega a la lista de usuarios aprobados el usuario entrado por parametro
	 * */
	public void agregarNuevosAprobados(Usuarios usuario) {
		
		boolean aprobar = true;
		int contador = 0;
		for(Usuarios usu : listaUsuariosRegistrados) {
			if(usu.getRol().equals(usuario.getRol()) && !usuario.getRol().equals("Inversor")) {
				contador++;
			}
			if(usu.getRol().equals(usuario.getRol()) && usu.getUsuario().equals(usuario.getUsuario()) && usu.getContrasena().equals(usuario.getContrasena()) && usu.getNombre().equals(usuario.getNombre())) {
				aprobar = false;
			}
		}
		if(aprobar == true) {
			if(contador == 0 && !usuario.getRol().equals("Inversor")) {
				listaUsuariosRegistrados.add(usuario);
			}
			else if(usuario.getRol().equals("Inversor")) {
				listaUsuariosRegistrados.add(usuario);
			}
		}
	}
	
	/**
	 * Método que elimina de la lista de usuarios registrados el usuario entrado por parametro
	 * */		
	public void rechazarSolicitud() {
			
		Iterator<Usuarios> iterador = listaUsuariosRegistrados.iterator();
	    while(iterador.hasNext()) {
	   		Usuarios ingreso = iterador.next();
	   		if(listaNuevosUsuarios.contains(ingreso)) {
	   			listaNuevosUsuarios.remove(ingreso);
	   		}
	    }
	}
	
	/**
	 * Este método verifica que el nuevo usuario registrado sea válido y no este repetido 
	 * Este método registra al primer administrador que entra como usuario aprobado directamente
	 * */
	public boolean crearNuevoUsuario(String rol, String nombre, String contraseña, String usuario) {
		
		Usuarios nuevo = new Usuarios(usuario, contraseña, rol, nombre);
		boolean aprobado = true;
		int contador = 0;
		
		if(!rol.equals("Administrador") && !rol.equals("Operador") && !rol.equals("Inversor") && !rol.equals("Cajero")) {
    		aprobado = false;
    	}
		
		for(Usuarios admin: listaUsuariosRegistrados) {
			
			if(admin.getRol().equals(rol) && admin.getContrasena().equals(contraseña) && admin.getUsuario().equals(usuario)) {
				aprobado = false;
				break;
			}
			else if(admin.getUsuario().equals(usuario)) {
				aprobado = false;
				break;
			}
			else if(rol.equals("Administrador")) {
				if(admin.getRol().equals("Administrador")) {
					contador++;
				}
			}
		}
		
		for(Usuarios ingreso: listaNuevosUsuarios) {
			
			if(ingreso.getRol().equals(rol) && ingreso.getContrasena().equals(contraseña) && ingreso.getUsuario().equals(usuario)) {
				aprobado = false;
				break;
    		}
			else if(ingreso.getUsuario().equals(usuario)) {
				aprobado = false;
				break;
			}
		}
		
		if(contador == 0 && rol.equals("Administrador")) {
			listaUsuariosRegistrados.add(nuevo);
		}
		else if(contador > 0 && rol.equals("Administrador")) {
			aprobado = false;
		}else {
			listaNuevosUsuarios.add(nuevo);
		}
		if(aprobado == false) {
			listaNuevosUsuarios.remove(nuevo);
		}
		Iterator<Usuarios> iterador = listaUsuariosRegistrados.iterator();
	    while(iterador.hasNext()) {
	   		Usuarios ingreso = iterador.next();
	   		if(listaNuevosUsuarios.contains(ingreso)) {
	   			listaNuevosUsuarios.remove(ingreso);
	   		}
	    }
		return aprobado;
	}
	
	/**
	 * Este método verifica que el usuario que intenta iniciar sesión sea válido, es decir, que se encuentre en la lista de los usuarios aprobados
	 * */
	public boolean inicioSesion(String rol, String usuario, String contraseña) {
			
		Iterator<Usuarios> iterador = listaUsuariosRegistrados.iterator();
	    while(iterador.hasNext()) {
	   		Usuarios ingreso = iterador.next();
	   		if(ingreso.getRol().equals(rol) && ingreso.getUsuario().equals(usuario) && ingreso.getContrasena().equals(contraseña) ) {
	   			return true;
	   		}
	    }
		return false;
	}
	
	public void crearHistoriales(Usuarios usuario, double monto) {

		HistorialInversor nombre = new HistorialInversor(monto, usuario);
		listaHistorialesInversores.add(nombre) ;
	}
	
	public List<HistorialInversor> getListaHistorial(){
		
		return listaHistorialesInversores;
	}
}