package galeria.modelo.consola;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteVisual;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
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
        
        RegistroInicio sesion = galeria.getRegistro();
		Inventario inventario = galeria.getInventario();
		OperacionSubasta subasta = galeria.getInventario().getClaseSubasta();
		ProcesoCompra cajero = galeria.getCajero();
		
		consola.persistenciaCargarInventario();
        
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
            System.out.println("6. Consultar traza de cuenta");
            System.out.println("7. Solicitar ampliar monto maximo de compras");
            System.out.println("10. Volver al inicio");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();
            
            List<String> posiblesPropositos = new LinkedList<>();
            posiblesPropositos.add("Exhibir");
            posiblesPropositos.add("Subastar");
            posiblesPropositos.add("Vender");
            
            switch (opcion) {
                case 1:
                	System.out.print("Ingrese el tipo de pieza que desea crear (Arte Digital, Arte Tridimensional, Arte Cuadros: ");
                	String tipo = scanner2.nextLine();
                	if(tipo.equals("Arte Cuadros")) {

                        System.out.print("Ingrese el título: ");
                        String titulo = scanner2.next();
                        
                        System.out.print("Ingrese los autores. Si es más de uno, separelo por comas: ");
                        String autores = scanner2.next();

                        System.out.print("Ingrese el propósito (Exhibir, Subastar, Vender): ");
                        String proposito = scanner2.next();
                        double monto = 0;
                        double montoMin = 0;
                        
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
                            monto = scanner2.nextDouble();
                            
                            System.out.print("Ingrese el monto minimo de venta: ");
                            montoMin = scanner2.nextDouble();
                        }
                        else if(proposito.equals("Vender")) {
                        	System.out.print("Ingrese el precio fijo de venta: ");
                            monto = scanner2.nextDouble();
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
                        
                        System.out.print("Ingrese el ancho y largo: ");
                        String anchoxlargo = scanner2.next();

                        System.out.print("Ingrese la técnica de la obra: ");
                        String tecnica = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaPintura(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, anchoxlargo, tecnica);
                        inventario.añadirPiezasSolicitud(pieza);
                        
                    	System.out.println("Registro existoso. A espera de aprobación.");
                	}
                	else if(tipo.equals("Arte Tridimensional")) {
                		
                		System.out.print("Ingrese el título: ");
                        String titulo = scanner2.nextLine();

                        System.out.print("Ingrese el propósito (Exhibir, Subastar, Vender): ");
                        String proposito = scanner2.nextLine();
                        double monto = 0;
                        double montoMin = 0;
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
                            monto = scanner2.nextDouble();
                            
                            System.out.print("Ingrese el monto minimo de venta: ");
                            montoMin = scanner2.nextDouble();
                        }
                        else if(proposito.equals("Vender")) {
                        	System.out.print("Ingrese el precio fijo de venta: ");
                            monto = scanner2.nextDouble();
                            montoMin = 0;
                        }

                        System.out.print("Ingrese el lugar de creación y el año, de esta forma (año-lugar): ");
                        String lugar_creacion = scanner2.next();
                        
                        System.out.print("Ingrese el peso: ");
                        double peso = scanner2.nextDouble();
                        
                        System.out.print("Ingrese los autores. Si es más de uno, separelo por comas: ");
                        String autores = scanner3.nextLine();
                        
                        System.out.print("¿La obra necesita electricidad?: ");
                        boolean electricidad = scanner3.nextBoolean();
                        
                        System.out.print("Ingrese la técnica de la obra: ");
                        String tecnica = scanner2.next();
                        
                        System.out.print("¿Desea darla en modo de consignación? (true/false): ");
                        boolean deposito = scanner3.nextBoolean();
                        int tiempo = 0;
                        if(deposito == true) {
                        	System.out.print("Ingrese el número de dias: ");
                            tiempo = scanner2.nextInt();
                        }else {
                        	tiempo = 0;
                        }
                        
                        System.out.print("Ingrese las dimensiones de la obra: ");
                        String dimensiones = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaTridimensional(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, dimensiones, tecnica, peso, electricidad);
                        inventario.añadirPiezasSolicitud(pieza);
                    	
                    	System.out.println("Registro existoso. A espera de aprobación.");
                	}
                	else if(tipo.equals("Arte Digital")) {
                		
                		System.out.print("Ingrese el título: ");
                        String titulo = scanner2.nextLine();

                        System.out.print("Ingrese el propósito (Exhibir, Subastar, Vender): ");
                        String proposito = scanner2.nextLine();
                        double monto = 0;
                        double montoMin = 0;
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
                            monto = scanner2.nextDouble();
                            
                            System.out.print("Ingrese el monto minimo de venta: ");
                            montoMin = scanner2.nextDouble();
                        }
                        else if(proposito.equals("Vender")) {
                        	System.out.print("Ingrese el precio fijo de venta: ");
                            monto = scanner2.nextDouble();
                            montoMin = 0;
                        }

                        System.out.print("Ingrese el lugar de creación y el año, de esta forma (año-lugar): ");
                        String lugar_creacion = scanner2.next();

                        System.out.print("Ingrese los autores. Si es más de uno, separelo por comas: ");
                        String autores = scanner2.next();

                        System.out.print("¿Desea darla en modo de consignación? (true/false): ");
                        boolean deposito = scanner2.nextBoolean();
                        int tiempo = 0;
                        if(deposito == true) {
                        	System.out.print("Ingrese el número de dias: ");
                            tiempo = scanner2.nextInt();
                        }else {
                        	tiempo = 0;
                        }
                        
                        System.out.print("Ingrese el tipo de arte: ");
                        String tipoArte = scanner2.next();
                        
                        System.out.print("Ingrese el tipo de archivo: ");
                        String tipoArchivo = scanner2.next();
                        
                        ConsignacionPieza pieza = inversor.CrearPiezaDigital(tiempo, titulo, monto, montoMin, proposito, lugar_creacion, deposito, inversor.getInversor(), autores, tipoArte, tipoArchivo);
                        inventario.añadirPiezasSolicitud(pieza);
                    	
                    	System.out.println("Registro existoso. A espera de aprobación.");
                	}
                	else {
                		System.out.println("Ingrese un valor válido");
                	}
                	consola.persistenciaSalvarInventario();
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
                case 10:
                	ConsolaInicio.main(null);
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
