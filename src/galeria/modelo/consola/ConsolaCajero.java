package galeria.modelo.consola;

import java.util.List;
import java.util.Scanner;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.CentroAutores;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
import galeria.modelo.usuarios.RegistroInicio;

public class ConsolaCajero {
	
	public static void principal(Galeria galeria, ConsolaInicio consola) {
		
		RegistroInicio sesion = galeria.getRegistro();
		OperacionSubasta subasta = galeria.getClaseSubasta();
		Inventario inventario = galeria.getInventario();
		ProcesoCompra cajero = galeria.getCajero();
		CentroAutores autores = galeria.getCentroAutores();
		CentroOfertas centroOfertas = galeria.getCentroOfertas();
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("Bienvenido al menú del operador de la galeria:");
            System.out.println("1. Consultar lista de ofertas para cobranza");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	System.out.print("Estas son las ofertas registradas: ");
                	List<Ofertas> ofertas = cajero.getlistaOfertas();
                	
                	System.out.printf("%-10s %-10s %-10s %-10s%n", "Pieza", "Propietario", "Fecha Compra", "Monto");
                	for(Ofertas oferta : ofertas) {
                		System.out.printf("%-10s %-10s %-10s %-10s%n", oferta.getPiezas().getTitulo(), oferta.getComprador().getInversor().getUsuario(), oferta.getPiezas().getFechaVendida(), oferta.getMonto());
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
