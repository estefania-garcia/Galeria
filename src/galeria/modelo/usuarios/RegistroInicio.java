package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RegistroInicio {
	
	//Lista de los usuarios que aun esperan aprobacion
	private List<Usuarios> listaNuevosUsuarios;
	
	//Lista de los usuarios que ya fueron aprobados
	private List<Usuarios> listaUsuariosRegistrados;
	
	public RegistroInicio() {
		
		this.listaNuevosUsuarios = new ArrayList<Usuarios>();
		this.listaUsuariosRegistrados = new LinkedList<Usuarios>();
	}
	
	//FUNCIONA
	public List<Usuarios> getSolicitud(){
		
		return listaNuevosUsuarios;
	}
	
	//FUNCIONA (Crea nuevos usarios y los manda a solicitud)
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
		return aprobado;
	}
	
	//FUNCIONA (Verifica el inicio de sesion)
	public boolean inicioSesion(String rol, String usuario, String contraseña) {
			
		Iterator<Usuarios> iterador = listaUsuariosRegistrados.iterator();
	    while(iterador.hasNext()) {
	   		Usuarios ingreso = iterador.next();
	   		if(ingreso.getRol().equals(rol) && ingreso.getUsuario().equals(usuario) && ingreso.getContrasena().equals(contraseña) ) {
	   			return true;
	   		}else {
    			return false;
	    	}
	    }
		return false;
	}
	
	//A ESPERA DE APROBACION COMPLETA
	public boolean agregarNuevosAprobados(Usuarios usuario) {
		
		Iterator<Usuarios> iterador = listaUsuariosRegistrados.iterator();
    	while(iterador.hasNext()) {
    		Usuarios ingreso = iterador.next();
    		if(ingreso.getRol().equals(usuario.getRol()) && ingreso.getUsuario().equals(usuario.getUsuario()) && ingreso.getContrasena().equals(usuario.getContrasena()) ) {
    			return false;
    		}
    		else if(ingreso.getUsuario().equals(usuario.getUsuario())) {
    			return false;
    		}
    		else if(ingreso.getRol().equals("Administrador") && usuario.getRol().equals("Administrador")) {
    			return false;
    		}
    		else if(ingreso.getRol().equals("Cajero") && usuario.getRol().equals("Cajero")) {
    			return false;
    		}
    		else if(ingreso.getRol().equals("Operador") && usuario.getRol().equals("Operador")) {
    			return false;
    		}
    		else {
    			listaUsuariosRegistrados.add(usuario);
    			listaNuevosUsuarios.remove(usuario);
    			return true;
    		}
    	}
    	if(listaUsuariosRegistrados.size() == 0) {
    		
    		listaUsuariosRegistrados.add(usuario);
    		listaNuevosUsuarios.remove(usuario);
			return true;
    	}
		return true;
	}
	
	public void rechazarSoliciutd(Usuarios usuario) {
		
		listaNuevosUsuarios.remove(usuario);
	}

	public void agregarRegistrados(Usuarios usuario) {
		
		listaUsuariosRegistrados.add(usuario);
		listaNuevosUsuarios.remove(usuario);
	}
	
	public List<Usuarios> getAprobados(){
		
		return listaUsuariosRegistrados;
	}
}