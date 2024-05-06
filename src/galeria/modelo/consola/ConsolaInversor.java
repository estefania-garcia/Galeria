package galeria.modelo.consola;

import java.util.Scanner;

public class ConsolaInversor {
	
	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("Bienvenido al menú del operador de la galeria:");
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

            switch (opcion) {
                case 1:
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
