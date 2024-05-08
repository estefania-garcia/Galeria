package galeria.modelo.consola;

import java.util.List;
import java.util.Scanner;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Autores;
import galeria.modelo.inventario.CentroAutores;
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
		OperacionSubasta subasta = galeria.getClaseSubasta();
		Inventario inventario = galeria.getInventario();
		ProcesoCompra cajero = galeria.getCajero();
		CentroAutores autores = galeria.getCentroAutores();
		
		consola.persistenciaCargarInventario();
		consola.persistenciaCargarVentas();
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        int opcion2;
        int contador;

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
                		System.out.printf("%-10s %-10s %-10s %-15s%n", "Número", "Nombre", "Rol", "Usuario");
                		contador = 0;
                	
                		for(Usuarios usuario: listaSolicitud) {
                		contador++;
                		System.out.printf("%-10d %-10s %-10s %-15s%n", contador, usuario.getNombre(), usuario.getRol(), usuario.getUsuario());
                		}
                		
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
 
                		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-20s%n", "Número", "Titulo", "Dueño", "Proposito", "Consignación", "Vigencia");
                    	contador = 0;
                		for(ConsignacionPieza pieza: listaSolicitudPiezas) {
                       		contador++;
                        	System.out.printf("%-10d %-10s %-10s %-10s %-10s %-20s%n", contador, pieza.getPieza().getTitulo(), pieza.getPieza().getPropietario().getUsuario(), pieza.getPieza().getProposito(), pieza.getPieza().getDeposito(), pieza.getPieza().getVigencia());
                        }
                		System.out.println("\n");
                		System.out.print("El número de piezas es: ");
                		System.out.print(listaSolicitudPiezas.size());
                		System.out.println("\n");
                   		
                		System.out.print("Ingresa la posicion de la pieza: ");
                       	int pos = scanner2.nextInt();
                       	
                       	if(pos == 0) {
                    		bucle = false;
                    		break;
                    	}
                       	
                      	System.out.print("¿Desea aprobar la solicitud? (true/false): ");
                       	boolean aprobar = scanner2.nextBoolean();
                      	
                       	if(aprobar == true) {
                       		Piezas arte = listaSolicitudPiezas.get(pos-1).getPieza();
                       		arte.asignarVigenica(true);
                           	arte.asignarAprobada(true);
                           	consola.persistenciaSalvarInventario();
                       	}
                       	
                       	System.out.print("¿Deseas seguir verificando? (true/false): ");
                       	bucle = scanner2.nextBoolean();
                       	System.out.println("\n");
                	}
                    break;
                case 3:
                	System.out.println("Acaba de iniciar exitosamente todas las subastas en espera de las piezas que aprobó");
            		inventario.agregarPiezaSubasta();
            		
                	System.out.println("1. Finalizar Subasta");
                    System.out.println("3. Aprobar ofertas subasta");
                    System.out.print("Elije una opción: ");
                    opcion2 = scanner3.nextInt();
                    System.out.print("\n");
                    switch (opcion2) {
                    	case 1:
                    		boolean loop = true;
                        	while(loop) {
                        		
                        		List<Piezas> piezasSubasta = inventario.getSubastar();
                        		System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Número", "Titulo", "Dueño", "Proposito", "Consignación");
                        		contador = 0;
                        	
                        		for(Piezas pieza: piezasSubasta) {
                        		contador++;
                        		System.out.printf("%-10d %-10s %-10s %-10s %-10s%n", contador, pieza.getTitulo(), pieza.getPropietario().getUsuario(), pieza.getProposito(), pieza.getDeposito());
                        		}
                        		System.out.println("\n");
                        		System.out.print("El número de los elementos es: ");
                        		System.out.println(piezasSubasta.size());
                        		
                        		System.out.print("Ingresa la posicion de la pieza (Pon 0 para salir): ");
                            	int pos = scanner2.nextInt();
                            	
                            	if(pos == 0) {
                            		loop = false;
                            		break;
                            	}
                            	
                            	Piezas arte = piezasSubasta.get(pos-1);
                            	subasta.finalizarSubasta(arte);
                            	arte.asignarVigenica(false);
                            	consola.persistenciaSalvarInventario();
                            	
                            	System.out.print("¿Deseas seguir verificando? (true/false): ");
                            	loop = scanner2.nextBoolean();
                            	System.out.println("\n");
                        	}
                    		break;
                    	case 2:
                    		boolean lop = true;
                        	while(lop) {
                        		
                        		System.out.println("Todas las ofertas a continuación pasaron los filtros de precio. Son válidas para el sistema");
                        		List<Ofertas> ofertasFinalesSubasta = galeria.getOfertasSubasta();
                        		System.out.printf("%-10s %-10s %-10s %-10s%n", "Número", "Ofertador", "Pieza", "Monto");
                        		contador = 0;
                        	
                        		for(Ofertas oferta: ofertasFinalesSubasta) {
                        		contador++;
                        		System.out.printf("%-10d %-10s %-10d %-10s%n", contador, oferta.getComprador().getInversor().getUsuario(), oferta.getPiezas().getTitulo(), oferta.getMonto());
                        		}
                        		System.out.println("\n");
                        		
                        		System.out.print("Ingresa la posicion de la oferta: ");
                            	int pos = scanner2.nextInt();
                            	
                            	System.out.print("¿Deseas desea aprobarla o rechazarla? (true/false): ");
                            	boolean aprobacion = scanner2.nextBoolean();
                            	Ofertas ofert = ofertasFinalesSubasta.get(pos-1);
                            	
                            	if(aprobacion == true) {
                            		System.out.print("Ingresa la fecha actual sin espacios: ");
                                	String fechaVenta = scanner2.next();
                                	ofert.getPiezas().asignarFechaVendida(fechaVenta);
                                	ofert.getPiezas().asignarVenta(true);
                                	
                                	cajero.agregarOfertas(ofert);
                                	consola.persistenciaSalvarInventario();
                                	consola.persistenciaSalvarVentas();
                            	}else {
                            		galeria.recharOfertasSubasta(ofert);
                            	}
                            	System.out.print("¿Deseas seguir verificando? (true/false): ");
                            	lop = scanner2.nextBoolean();
                            	System.out.println("\n");
                        	}
                    		break;
                    }
                    break;
                case 4:
                	boolean lop = true;
                	while(lop) {
                		
                		System.out.println("Todas las ofertas a continuación pasaron los filtros de precio. Son válidas para el sistema");
                		List<Ofertas> ofertasVenta = galeria.getOfertasVentas();
                		System.out.printf("%-10s %-10s %-10s %-10s%n", "Número", "Ofertador", "Pieza", "Monto");
                		contador = 0;
                	
                		for(Ofertas oferta: ofertasVenta) {
                		contador++;
                		System.out.printf("%-10d %-10s %-10d %-10s%n", contador, oferta.getComprador().getInversor().getUsuario(), oferta.getPiezas().getTitulo(), oferta.getMonto());
                		}
                		System.out.println("\n");
                		
                		System.out.print("Ingresa la posicion de la oferta: ");
                    	int pos = scanner2.nextInt();
                    	
                    	System.out.print("¿Deseas desea aprobarla o rechazarla? (true/false): ");
                    	boolean aprobacion = scanner2.nextBoolean();
                    	Ofertas ofert = ofertasVenta.get(pos-1);
                    	
                    	if(aprobacion == true) {
                    		System.out.print("Ingresa la fecha actual sin espacios: ");
                        	String fechaVenta = scanner2.next();
                        	ofert.getPiezas().asignarFechaVendida(fechaVenta);
                        	ofert.getPiezas().asignarVenta(true);
                        	
                        	cajero.agregarOfertas(ofert);
                        	consola.persistenciaSalvarInventario();
                        	consola.persistenciaSalvarVentas();
                    	}else {
                    		galeria.getCentroOfertas().recharOfertaVenta(ofert);
                    	}
                    	System.out.print("¿Deseas seguir verificando? (true/false): ");
                    	lop = scanner2.nextBoolean();
                    	System.out.println("\n");
                	}
            		break;
                case 5:
                	System.out.print("Ingresa el usuario del inversor que desea consultar: ");
                	String consulta = scanner2.next();
                	
                	List<HistorialInversor> historiales = galeria.getListaHistorial();
                	HistorialInversor encontrado = null;
                	for(HistorialInversor usuario : historiales) {
                		if(usuario.getInversor().getUsuario().equals(consulta)) {
                			encontrado = usuario;
                		}
                	}
                	
                	System.out.print("El valor de la colección es: ");
                	System.out.print(encontrado.getValorColeccion());
                	System.out.print("\n");
                	
                	System.out.printf("%-10s %-10s%n", "Fecha Compra", "Pieza");
                	for(Piezas pieza : encontrado.getPiezasCompradas()) {
                		System.out.printf("%-10d %-10s%n", pieza.getFechaVendida(), pieza.getTitulo());
                	}
                	
                	System.out.printf("%-10s %-10s%n", "Tipo", "Pieza");
                	for(Piezas pieza : encontrado.getPiezasCompradas()) {
                		System.out.printf("%-10s %-10s%n", pieza.getTipo(), pieza.getTitulo());
                	}
                    break;
                case 6:
                	System.out.print("Estos son los compradores que tiene solicitud de aumento de monto maximo de compra: ");
                    
                    List<HistorialInversor> solicitudesMonto = galeria.getSolicitudMonto();
            		System.out.printf("%-10s %-10s %-10s%n", "Número", "Inversor", "Monto Actual");
            		contador = 0;
            	
            		for(HistorialInversor historial: solicitudesMonto) {
            		contador++;
            		System.out.printf("%-10d %-10s %-10d%n", contador, historial.getInversor().getUsuario(), historial.getMontoMaximo());
            		}
            		System.out.println("\n");
            		System.out.print("Ingresa la posicion de la solicitud: ");
                	int pos = scanner2.nextInt();
                	
                	System.out.print("¿Deseas desea aprobarla o rechazarla? (true/false): ");
                	boolean aprobacion = scanner2.nextBoolean();
                	
                	HistorialInversor inverMontoA = solicitudesMonto.get(pos-1);
                	
                	if(aprobacion == true) {
                		System.out.print("Ingresa el nuevo valor: ");
                    	double valor = scanner2.nextDouble();
                		inverMontoA.modificarMontoMaximo(valor);
                		consola.persistenciaSalvar();
                	}
                	System.out.print("¿Deseas seguir verificando? (true/false): ");
                	lop = scanner2.nextBoolean();
                	System.out.println("\n");
                	break;
                case 7:
                	System.out.print("Estas son las piezas en prestamo: ");
                    
                	boolean repetir = true;
                	while(repetir) {
                		List<ConsignacionPieza> listaPiezasDevolver = galeria.getDeposito();
                		System.out.printf("%-10s %-10s %-10s %-10s%n", "Número", "Pieza", "Propietario", "Tipo");
                		contador = 0;
            	
                		for(ConsignacionPieza pieza: listaPiezasDevolver) {
                			contador++;
                			System.out.printf("%-10d %-10s %-10s %-10s%n", contador, pieza.getPieza().getTitulo(), pieza.getPropietario().getUsuario(), pieza.getPieza().getTipo());
                		}
                		System.out.println("\n");
                		System.out.print("Ingresa la posicion de la solicitud: ");
                		int ubicacion = scanner2.nextInt();
                	
                		System.out.print("¿Deseas desea aprobarla o rechazarla? (true/false): ");
                		boolean booleano = scanner2.nextBoolean();
                	
                		ConsignacionPieza consignacion = listaPiezasDevolver.get(ubicacion-1);
                	
                		if(booleano == true) {
                			consignacion.getPieza().asignarVigenica(false);
                			consola.persistenciaSalvarInventario();
                		}
                		System.out.print("¿Deseas seguir verificando? (true/false): ");
                		repetir = scanner2.nextBoolean();
                		System.out.println("\n");
                	}
                	break;
                case 8:
                	System.out.print("Ingrese el titulo de la pieza que quiere consultar: ");
                	String titulo = scanner2.next();
                	
                	System.out.print("Ingrese el autor de la pieza que tuiere consultar: ");
                	String autor = scanner2.next();
                	
                	List<ConsignacionPieza> piezas = galeria.getPiezasSolicitud();
                	ConsignacionPieza pieza = null;
                	for(ConsignacionPieza arte : piezas) {
                		if(arte.getPieza().getTitulo().equals(titulo) && arte.getPieza().getAutores().equals(autor)) {
                			pieza = arte;
                		}
                	}
                	
                	System.out.printf("%-10s %-10s %-10s %-10s %-10d%n", "Titulo", "Tipo", "Dueño", "Fecha Venta", "Precio Venta");
                	if(pieza.getPieza().getProposito().equals("Vender")) {
                		System.out.printf("%-10s %-10s %-10s %-10s %-10d%n", pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(), pieza.getPropietario().getUsuario(), pieza.getPieza().getFechaVendida(), pieza.getPieza().getGaleriaOferta().getMontoCliente());
                	}
                	else if(pieza.getPieza().getProposito().equals("Subastar")) {
                		System.out.printf("%-10s %-10s %-10s %-10s %-10d%n", pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(), pieza.getPropietario().getUsuario(), pieza.getPieza().getFechaVendida(), pieza.getPieza().getGaleriaOferta().getMontoMinimo());
                	}
                	else if(pieza.getPieza().getProposito().equals("Exhibir")) {
                		System.out.print("Esta pieza no se vende");
                		System.out.printf("%-10s %-10s %-10s %-10s %-10d%n", pieza.getPieza().getTitulo(), pieza.getPieza().getTipo(), pieza.getPropietario().getUsuario(), pieza.getPieza().getFechaVendida(), 0);
                	}
                    break;
                case 9:
                	System.out.print("Ingresa el nombre del autor que desea consultar: ");
                	String nombre = scanner2.next();
                	
                	List<Autores> autoresDisponibles = galeria.getCentroAutores().getListaAutores();
                	Autores autorEncontrado = null;
                	for(Autores autor1 : autoresDisponibles) {
                		if(autor1.getNombre().equals(nombre)) {
                			autorEncontrado = autor1;
                		}
                	}
                	
                	System.out.printf("%-10s %-10s %-10s %-10s%n", "Pieza", "Fecha", "Fecha Vendida", "Monto");
                	for(Piezas piezas1 : autorEncontrado.getListaPiezas()) {
                		if(piezas1.getProposito().equals("Subastar")) {
                			System.out.printf("%-10s %-10s %-10s %-10d%n", piezas1.getTitulo(), piezas1.getLugarCreacion(), piezas1.getFechaVendida(), piezas1.getGaleriaOferta().getMontoMinimo());
                		}
                		else if(piezas1.getProposito().equals("Vender")) {
                			System.out.printf("%-10s %-10s %-10s %-10d%n", piezas1.getTitulo(), piezas1.getLugarCreacion(), piezas1.getFechaVendida(), piezas1.getGaleriaOferta().getMontoCliente());
                		}
                		else if(piezas1.getProposito().equals("Exhibir")) {
                			System.out.printf("%-10s %-10s %-10s %-10d%n", piezas1.getTitulo(), piezas1.getLugarCreacion(), piezas1.getFechaVendida(), 0);
                		}
                	}
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
