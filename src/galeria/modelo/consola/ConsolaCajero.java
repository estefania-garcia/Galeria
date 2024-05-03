package galeria.modelo.consola;

import java.util.Scanner;

public class ConsolaCajero {
	
	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("Bienvenido al menú del operador de la galeria:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. ");
            System.out.println("8. ");
            System.out.println("9. ");
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
