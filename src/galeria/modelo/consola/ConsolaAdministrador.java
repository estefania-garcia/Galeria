package galeria.modelo.consola;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
import galeria.modelo.usuarios.RegistroInicio;
import galeria.modelo.usuarios.Usuarios;

public class ConsolaAdministrador {
	
	public static void principal(Galeria galeria, ConsolaInicio consola) {
		
		RegistroInicio sesion = galeria.getRegistro();
		Inventario inventario = galeria.getInventario();
		OperacionSubasta subasta = galeria.getInventario().getClaseSubasta();
		ProcesoCompra cajero = galeria.getCajero();
		
		consola.persistenciaCargarInventario();
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        int opcion2;

        while (!salir) {
            System.out.println("Bienvenido al menú del administrador de la galeria:");
            System.out.println("1. Aprobar nuevos usuarios");
            System.out.println("2. Aprobar nuevas piezas");
            System.out.println("3. Consultar proceso subastas");
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
                    	
                    	Usuarios usu = sesion.getSolicitud().get(posicion-1);
                    	if(aprobar == true) {
                    		sesion.agregarNuevosAprobados(usu);
                    		if(usu.getRol().equals("Inversor")) {
                    			System.out.print("Ingresa el monto maximo de compras para este usuario: ");
                            	double monto = scanner2.nextDouble();
                            	sesion.crearHistoriales(usu, monto);
                    		}
                    	}
                    	consola.persistenciaSalvar();
                    	
                    	System.out.print("¿Deseas seguir verificando? (true/false): ");
                    	ciclo = scanner2.nextBoolean();
                    	System.out.println("\n");
                	}
                	
                    break;
                case 2:
                	System.out.println("\n");
                	System.out.println("Estos son las piezas en espera de aprobación: ");
                	System.out.println("\n");
                	
                	boolean bucle = true;
                	while(bucle) {
                		
                		List<ConsignacionPieza> listaSolicitudPiezas = inventario.getPiezasSolicitud();
 
                		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n", "Número", "Titulo", "Dueño", "Proposito", "Consignación", "Vigencia");
                    	int contar = 0;
                		for(ConsignacionPieza pieza: listaSolicitudPiezas) {
                       		contar++;
                        	System.out.printf("%-10d %-10s %-10s %-10s %-10s %-10s%n", contar, pieza.getPieza().getTitulo(), pieza.getPieza().getPropietario().getUsuario(), pieza.getPieza().getProposito(), pieza.getPieza().getDeposito(), pieza.getPieza().getVigencia());
                        }
                		System.out.println("\n");
                		System.out.print("El número de piezas es: ");
                		System.out.print(listaSolicitudPiezas.size());
                		System.out.println("\n");
                   		
                		System.out.print("Ingresa la posicion de la pieza: ");
                       	int pos = scanner2.nextInt();
                       	
                      	System.out.print("¿Desea aprobar la solicitud? (true/false): ");
                       	boolean aprobar = scanner2.nextBoolean();
                      	
                       	if(aprobar == true) {
                       		Piezas arte = listaSolicitudPiezas.get(pos-1).getPieza();
                       		arte.asignarVigenica(true);
                           	arte.asignarAprobada(aprobar);
                           	consola.persistenciaSalvarInventario();
                       	}
                       	
                       	System.out.print("¿Deseas seguir verificando? (true/false): ");
                       	bucle = scanner2.nextBoolean();
                       	System.out.println("\n");
                	}
                    break;
                case 3:
                	System.out.println("1. Inicializar subastas");
                	System.out.println("2. Finalizar Subasta");
                    System.out.println("3. Aprobar ofertas subasta");
                    opcion2 = scanner3.nextInt();
                    System.out.println("\n");
                    switch (opcion2) {
                    	case 1:
                    		System.out.println("Acaba de iniciar exitosamente todas las subastas en espera de las piezas que aprobó");
                    		inventario.agregarPiezaSubasta();
                    		break;
                    	case 2:
                    		boolean loop = true;
                        	while(loop) {
                        		
                        		List<Piezas> piezasSubasta = inventario.getSubastar();
                        		System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Número", "Titulo", "Dueño", "Proposito", "Consignación");
                        		int contar = 0;
                        	
                        		for(Piezas pieza: piezasSubasta) {
                        		contar++;
                        		System.out.printf("%-10d %-10s %-10s %-10s %-10s%n", contar, pieza.getTitulo(), pieza.getPropietario().getUsuario(), pieza.getProposito(), pieza.getDeposito());
                        		}
                        		System.out.println("\n");
                        		
                        		System.out.print("Ingresa la posicion de la pieza: ");
                            	int pos = scanner2.nextInt();
                            	
                            	Piezas arte = piezasSubasta.get(pos-1);
                            	subasta.finalizarSubasta(arte);
                            	arte.asignarVigenica(false);
                            	consola.persistenciaSalvarInventario();
                            	System.out.print("¿Deseas seguir verificando? (true/false): ");
                            	loop = scanner2.nextBoolean();
                            	System.out.println("\n");
                        	}
                    		break;
                    	case 3:
                    		boolean lop = true;
                        	while(lop) {
                        		
                        		System.out.println("Todas las ofertas a continuación pasaron los filtros de precio. Son válidas para el sistema");
                        		List<Ofertas> ofertasFinalesSubasta = galeria.getOfertasFinalesSubasta();
                        		System.out.printf("%-10s %-10s %-10s %-10s%n", "Número", "Ofertador", "Pieza", "Monto");
                        		int contar = 0;
                        	
                        		for(Ofertas oferta: ofertasFinalesSubasta) {
                        		contar++;
                        		System.out.printf("%-10d %-10s %-10d %-10s%n", contar, oferta.getComprador().getUsuario(), oferta.getPiezas().getTitulo(), oferta.getMonto());
                        		}
                        		System.out.println("\n");
                        		
                        		System.out.print("Ingresa la posicion de la oferta: ");
                            	int pos = scanner2.nextInt();
                            	
                            	System.out.print("¿Deseas desea aprobarla o rechazarla? (true/false): ");
                            	boolean aprobacion = scanner2.nextBoolean();
                            	Ofertas ofert = ofertasFinalesSubasta.get(pos-1);
                            	
                            	if(aprobacion == true) {
                                	cajero.agregarOfertas(ofert);
                            	}else {
                            		galeria.rechazarOfertasFinalesSubasta(ofert);
                            	}
                            	//PONER PERSISTENCIA
                            	System.out.print("¿Deseas seguir verificando? (true/false): ");
                            	lop = scanner2.nextBoolean();
                            	System.out.println("\n");
                        	}
                    		break;
                    }
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
