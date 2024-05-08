package galeria.modelo.consola;

import java.io.IOException;
import java.util.Scanner;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.usuarios.RegistroInicio;

public class ConsolaInicio {
	
	private static final Galeria galeria = new Galeria();
	
	public void persistenciaCargar() {
		
        try {
			galeria.cargarUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvar() {
		
        try {
			galeria.salvarUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarInventario() {
		
        try {
			galeria.cargarInventario();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarInventario() {
		
        try {
			galeria.salvarInventario();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarVentas() {
		
        try {
			galeria.cargarVentas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarAutores() {
		
        try {
			galeria.salvarAutores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarAutores() {
		
        try {
			galeria.cargarAutores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarVentas() {
		
        try {
			galeria.cargarVentas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		ConsolaInicio consola = new ConsolaInicio();
		RegistroInicio sesion = galeria.getRegistro();
		Inventario inventario = galeria.getInventario();
		consola.persistenciaCargar();
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
        	System.out.print("\n");
            System.out.println("Bienvenido a nuestra galería y casa de subastas:");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	System.out.print("\n");
                    System.out.println("Has elegido Iniciar Sesión.");
                    System.out.print("\n");
                    
                    System.out.print("Ingresa tu rol (Administrador, Cajero, Operador, Inversor): ");
                    String rol = scanner2.nextLine();
                    
                    System.out.print("Ingresa tu usuario: ");
                    String usuario = scanner2.nextLine();
                    
                    System.out.print("Ingresa tu contraseña: ");
                    String contraseña = scanner2.nextLine();
                    
                    boolean comprobacion = sesion.inicioSesion(rol, usuario, contraseña); 
                    if(comprobacion == true) {
                    	consola.persistenciaSalvar();
                    	if(rol.equals("Administrador")) {
                    		
                    		System.out.print("\n");
                    		ConsolaAdministrador.principal(galeria, consola);
                    	}
                    	else if(rol.equals("Cajero")) {
                    		
                    		System.out.print("\n");
                    		ConsolaCajero.principal(galeria, consola);
                    	}
                    	else if(rol.equals("Operador")) {
                    		
                    		System.out.print("\n");
                    		ConsolaOperador.principal(galeria, consola);
                    	}
                    	else if(rol.equals("Inversor")) {
                    		
                    		System.out.print("\n");
                    		ConsolaInversor.principal(galeria, consola, usuario);
                    	}
                    }else {
                    	System.out.print("\n");
                    	System.out.println("Verifica que los datos sean correctos o registrate. Si ya te registraste entonces debes esperar la aprobación del administrador");
                    }
                    //salir = true;
                    break;
                case 2:
                	System.out.print("\n");
                    System.out.println("Has elegido Registrarse.");
                    
                    System.out.print("Ingresa tu nombre: ");
                    String nombre = scanner2.nextLine();
                    
                    System.out.print("Ingresa tu rol (Administrador, Cajero, Operador, Inversor): ");
                    String rolR = scanner2.nextLine();
                    
                    System.out.print("Ingresa tu usuario: ");
                    String usuarioR = scanner2.nextLine();
                    
                    System.out.print("Ingresa tu contraseña: ");
                    String contraseñaR = scanner2.nextLine();
                    
                    boolean comprobar = sesion.crearNuevoUsuario(rolR, nombre, contraseñaR, usuarioR);
                    if(comprobar == true && !rolR.equals("Administrador")) {
                    	consola.persistenciaSalvar();
                    	System.out.print("\n");
                    	System.out.println("Registro exitoso. Debe esperar la aprobación del administrador.");
                    }
                    else if(comprobar == true && rolR.equals("Administrador")) {
                    	consola.persistenciaSalvar();
                    	System.out.print("\n");
                    	System.out.println("Registro exitoso. Bienvenido administrador de este chuzo.");
                    }
                    else {
                    	System.out.print("\n");
                    	System.out.println("Registro fallido. Intentalo de nuevo con otros datos.");
                    }
                    break;
                case 0:
                	consola.persistenciaSalvar();
                	salir = true;
                	break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
        System.out.println("¡Hasta luego!");
    }
}
