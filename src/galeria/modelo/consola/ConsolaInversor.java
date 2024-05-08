package galeria.modelo.consola;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.OfertaVenta;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteVisual;
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

public class ConsolaInversor {
	
	public static void principal(Galeria galeria, ConsolaInicio consola, String usuario) {
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        Scanner scanner4 = new Scanner(System.in);
        
        Console console = System.console();
        
        RegistroInicio sesion = galeria.getRegistro();
		OperacionSubasta subasta = galeria.getClaseSubasta();
		Inventario inventario = galeria.getInventario();
		ProcesoCompra cajero = galeria.getCajero();
		CentroOfertas centroOferta = galeria.getCentroOfertas();
		CentroAutores cenAutores = galeria.getCentroAutores();
		
		consola.persistenciaCargarInventario();
		consola.persistenciaCargarAutores();
        
        HistorialInversor inversor = null;
        for(HistorialInversor usu : galeria.getListaHistorial()) {
        	String usuP = usu.getInversor().getUsuario();
        	if(usuP.equals(usuario)) {
        		inversor = usu;
        		usu.asignarGaleria(galeria);
        	}
        }
        
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("Bienvenido al menú del inversor de la galeria:");
            System.out.println("1. Crear una nueva pieza");
            System.out.println("2. Ofertar por pieza en subasta");
            System.out.println("3. Ofertar por pieza en venta");
            System.out.println("4. Consultar el historial de una pieza");
            System.out.println("5. Consultar historial de un autor");
            System.out.println("6. Solicitar ampliar monto maximo de compras");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();
            
            List<String> posiblesPropositos = new ArrayList<>();
            posiblesPropositos.add("Exhibir");
            posiblesPropositos.add("Subastar");
            posiblesPropositos.add("Vender");
            
            switch (opcion) {
                case 1:
                	System.out.print("Ingrese el tipo de pieza que desea crear (Arte Digital, Arte Tridimensional, Arte Cuadros: ");
                	String tipo = scanner4.nextLine();
                	
                	System.out.print("Ingrese el título: ");
                    String titulo = scanner2.next();
                    
                    System.out.print("Ingrese los autores. Si es más de uno, separelo por comas: ");
                    String autores = scanner2.next();

                    System.out.print("Ingrese el propósito (Exhibir, Subastar, Vender): ");
                    String proposito = scanner2.next();
                    int monto = 0;
                    int montoMin = 0;
                    
                    if(!posiblesPropositos.contains(proposito)) {
                    	System.out.print("Ingrese un valor válido");
                    	break;
                    }
                    else if(proposito.equals("Exhibir")){
                    	monto = 0;
                    	montoMin = 0;
                    }
                    else if(proposito.equals("Subastar")) {
                    	System.out.print("Ingrese el monto inicial: ");
                        monto = scanner2.nextInt();
                        
                        System.out.print("Ingrese el monto minimo de venta: ");
                        montoMin = scanner2.nextInt();
                    }
                    else if(proposito.equals("Vender")) {
                    	System.out.print("Ingrese el precio fijo de venta: ");
                        monto = scanner2.nextInt();
                        montoMin = 0;
                    }

                    System.out.print("Ingrese el lugar de creación y el año, de esta forma (año-lugar): ");
                    String lugar_creacion = scanner2.next();

                    System.out.print("¿Desea darla en modo de consignación? (true/false): ");
                    boolean deposito = scanner2.nextBoolean();
                    int tiempo = 0;
                    if(deposito == true) {
                    	System.out.print("Ingrese el número de dias: ");
                        tiempo = scanner2.nextInt();
                    }else {
                    	tiempo = 0;
                    }
                    
                	if(tipo.equals("Arte Cuadros")) {
                        
                        System.out.print("Ingrese el ancho y largo: ");
                        String anchoxlargo = scanner2.next();

                        System.out.print("Ingrese la técnica de la obra: ");
                        String tecnica = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaPintura(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, anchoxlargo, tecnica);
                        inventario.añadirPiezasSolicitud(pieza);
                        
                    	System.out.println("Registro existoso. A espera de aprobación.");
                    	
                    	String[] nombresCompletos = autores.split(",");
                    	for (String nombreCompleto : nombresCompletos) {
                    		String nombreC = nombreCompleto.replaceAll("(?<=.)([A-Z])", " $1");
                    		Autores auto = cenAutores.crearAutor(nombreC);
                    		auto.agregarPiezas(pieza.getPieza());
                    		consola.persistenciaSalvarAutores();
                        }
                	}
                	else if(tipo.equals("Arte Tridimensional")) {

                		System.out.print("Ingresa el peso: ");
                        int peso = scanner3.nextInt();
                        
                        System.out.print("¿La obra necesita electricidad?: ");
                        boolean electricidad = scanner3.nextBoolean();
                        
                        System.out.print("Ingrese la técnica de la obra: ");
                        String tecnica = scanner2.next();
                        
                        System.out.print("Ingrese las dimensiones de la obra: ");
                        String dimensiones = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaTridimensional(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, dimensiones, tecnica, peso, electricidad);
                        inventario.añadirPiezasSolicitud(pieza);
                    	
                    	System.out.println("Registro existoso. A espera de aprobación.");
                    	String[] nombresCompletos = autores.split(",");
                    	for (String nombreCompleto : nombresCompletos) {
                    		String nombreC = nombreCompleto.replaceAll("(?<=.)([A-Z])", " $1");
                    		Autores auto = cenAutores.crearAutor(nombreC);
                    		auto.agregarPiezas(pieza.getPieza());
                    		consola.persistenciaSalvarAutores();
                        }
                	}
                	else if(tipo.equals("Arte Digital")) {
                		
                        System.out.print("Ingrese el tipo de arte: ");
                        String tipoArte = scanner2.next();
                        
                        System.out.print("Ingrese el tipo de archivo: ");
                        String tipoArchivo = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaDigital(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, tipoArte, tipoArchivo);
                        inventario.añadirPiezasSolicitud(pieza);
                    	
                    	System.out.println("Registro existoso. A espera de aprobación.");
                    	String[] nombresCompletos = autores.split(",");
                    	for (String nombreCompleto : nombresCompletos) {
                    		String nombreC = nombreCompleto.replaceAll("(?<=.)([A-Z])", " $1");
                    		Autores auto = cenAutores.crearAutor(nombreC);
                    		auto.agregarPiezas(pieza.getPieza());
                    		consola.persistenciaSalvarAutores();
                        }
                	}
                	else {
                		System.out.println("Ingrese un valor válido");
                	}
                	
                	consola.persistenciaSalvarInventario();
                    break;
                case 2:
                	inventario.agregarPiezaSubasta();
                	System.out.println("Estas son las piezas disponibles: ");
                	Set<Piezas> piezasSubasta = galeria.getMapaSubastas().keySet();
                	System.out.printf("%-10s %-10s %-10s %-10s", "Número", "Pieza", "Autor/es", "Puja Actual");
                	System.out.print("\n");
                	int contador = 0;
                	for(Piezas pieza : piezasSubasta) {
                		contador++;
                		List<Ofertas> oferta = galeria.getMapaSubastas().get(pieza);
                		if(oferta.size() > 0) {
                			System.out.printf("%-10d %-10s %-10s %-10f%n", contador, pieza.getTitulo(), pieza.getAutores(), oferta.get(oferta.size()-1).getMonto());
                		}
                		else {
                			System.out.printf("%-10d %-10s %-10s %-10f%n", contador, pieza.getTitulo(), pieza.getAutores(), pieza.getGaleriaOferta().getMontoCliente());
                		}
                	}
                	System.out.print("\n");
                	System.out.print("Ingrese el titulo de la pieza: ");
                	String titulo1 = scanner2.next();
                	
                	System.out.print("Ingrese el autor de la pieza: ");
                	String autor = scanner2.next();
                	
                	Piezas seleccionada = null;
                	for(Piezas piezas : piezasSubasta) {
                		if(piezas.getTitulo().equals(titulo1) && piezas.getAutores().equals(autor)) {
                			seleccionada = piezas;
                		}
                	}
                	System.out.println("Ingrese el monto: ");
                	int montoParticipa = scanner2.nextInt();
                	System.out.println("Si ingresa un valor menor su oferta no será tomada en cuenta");
                	OfertaSubasta ofertaNuevas = inversor.crearOfertaSubasta(seleccionada, inversor, montoParticipa);
                    centroOferta.agregarOfertas(ofertaNuevas);
                	break;
                case 3:
                	System.out.println("Estas son las piezas disponibles: ");
                	List<Piezas> piezasVenta = inventario.getVenta();
                	System.out.printf("%-10s %-10s %-10s %-10s", "Número", "Pieza", "Autor/es", "Valor Fijo");
                	int cuenta = 0;
                	for(Piezas pieza : piezasVenta) {
                		cuenta++; 
                		System.out.printf("%-10d %-10s %-10s %-10d%n", cuenta, pieza.getTitulo(), pieza.getAutores(), pieza.getGaleriaOferta().getMontoCliente());
                	}
                	System.out.print("Ingrese el titulo de la pieza: ");
                	String titulo11 = scanner2.next();
                	
                	System.out.print("Ingrese el autor de la pieza: ");
                	String autor1 = scanner2.next();
                	
                	Piezas seleccion = null;
                	for(Piezas piezas : piezasVenta) {
                		if(piezas.getTitulo().equals(titulo11) && piezas.getAutores().equals(autor1)) {
                			seleccion = piezas;
                		}
                	}
                	System.out.println("Si ingresa un valor menor su oferta no será tomada en cuenta");
                	OfertaVenta ofertaNueva = inversor.crearOfertaVenta(seleccion, inversor, seleccion.getGaleriaOferta().getMontoCliente());
                    centroOferta.agregarOfertas(ofertaNueva);
                    break;
                case 4:
                	System.out.print("Ingrese el titulo de la pieza que tuiere consultar: ");
                	String titulo111 = scanner2.next();
                	
                	System.out.print("Ingrese el autor de la pieza que tuiere consultar: ");
                	String autor11 = scanner2.next();
                	
                	List<ConsignacionPieza> piezas = galeria.getPiezasSolicitud();
                	ConsignacionPieza pieza = null;
                	for(ConsignacionPieza arte : piezas) {
                		if(arte.getPieza().getTitulo().equals(titulo111) && arte.getPieza().getAutores().equals(autor11)) {
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
                	
                	//PONER PERSISTENCIA DE PIEZA QUE LE PERTENECEN
                    break;
                case 5:
                	System.out.print("Ingresa el nombre del autor que desea consultar: ");
                	String nombre = scanner2.next();
                	
                	List<Autores> autoresDisponibles = galeria.getCentroAutores().getListaAutores();
                	Autores autorEncontrado = null;
                	for(Autores autor111 : autoresDisponibles) {
                		if(autor111.getNombre().equals(nombre)) {
                			autorEncontrado = autor111;
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
                	//PONER PERSISTENCIA DE PIEZA QUE LE PERTENECEN
                    break;
                case 6:
                	inversor.crearSolicitudMonto();
                	System.out.println("Su solicitud ha sido registrada con exito.");
                	consola.persistenciaSalvar();
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
