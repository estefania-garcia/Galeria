package galeria.modelo.consola;

import java.util.Scanner;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.CentroAutores;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
import galeria.modelo.usuarios.RegistroInicio;

public class ConsolaOperador {
	
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
            System.out.println("1. Agregar Ofertas");
            System.out.println("0. Salir");
            System.out.print("Elije una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	centroOfertas.agregarOfertasSubasta();
                	System.out.println("Se han agregado todas las ofertas válidas");
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
