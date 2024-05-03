package galeria.modelo.consola;

import java.util.List;
import java.util.Scanner;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.usuarios.RegistroInicio;
import galeria.modelo.usuarios.Usuarios;

public class ConsolaAdministrador {
	
	public static void principal(Galeria galeria) {
		
		RegistroInicio sesion = galeria.getRegistro();
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("Bienvenido al menú del administrador de la galeria:");
            System.out.println("1. Aprobar nuevos usuarios");
            System.out.println("2. Aprobar nuevas piezas");
            System.out.println("3. Aprobar ofertas subasta");
            System.out.println("4. Aprobar ofertas venta");
            System.out.println("5. Consultar historial de comprador");
            System.out.println("6. Verificar solicitudes inversores");
            System.out.println("7. Devolver piezas en consignación");
            System.out.println("8. Consultar historial de piezas");
            System.out.println("9. Consultar historial de un artista");
            System.out.println("10. Volver al inicio");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	System.out.println("\n");
                	System.out.println("Estos son los usuarios en espera de aprobación: ");
                	System.out.println("\n");
                	
                	boolean ciclo = true;
                	while(ciclo) {
                		
                		List<Usuarios> listaSolicitud = sesion.getSolicitud();
                		System.out.printf("%-10s %-10s %-10s %-10s%n", "Número", "Nombre", "Rol", "Usuario");
                		int contador = 0;
                	
                		for(Usuarios usuario: listaSolicitud) {
                		contador++;
                		System.out.printf("%-10d %-10s %-10s %-10s%n", contador, usuario.getNombre(), usuario.getRol(), usuario.getUsuario());
                		}
                		System.out.println("\n");
                		
                		System.out.print("Ingresa la posicion del usuario: ");
                    	int posicion = scanner2.nextInt();
                    	
                    	System.out.print("¿Desea aprobar la solicitud? (true/false): ");
                    	boolean aprobar = scanner2.nextBoolean();
                    	
                    	galeria.verficarNuevosUsuarios((posicion-1), aprobar);
                    	
                    	System.out.print("¿Deseas seguir verificando? (true/false): ");
                    	ciclo = scanner2.nextBoolean();
                    	System.out.println("\n");
                	}
                	
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                	salir = true;
                	break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
	}
}
