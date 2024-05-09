package pruebas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.RegistroInicio;
import galeria.modelo.usuarios.Usuarios;


public class PruebasAplicacion {

	/**
	 * Se comprueba que se puedan crear los usuarios y que no se permita la creación de usuarios con un rol no permitido
	 * */
	@Test
	public void testCrearUsuarios() {
		RegistroInicio nuevo = new RegistroInicio();
		boolean confirma = nuevo.crearNuevoUsuario("Jugador", "Laura", "BTfr2005", "Isabel");
		Assertions.assertFalse(confirma);
	}
	/**
	 * Comprueba que la pieza se cree y se agregue a las listas correctas según los datos ingresados. 
	 * Esto indica que esta recorriendo los metodos correctamente.
	 * */
	@Test
	public void testCrearPieza() {
		Usuarios nuevoUsu = new Usuarios("Lauras", "BTfr2005", "Inversor", "Laura");
		HistorialInversor nuevo = new HistorialInversor(200000, nuevoUsu);
		Galeria galeria = new Galeria();
		nuevo.asignarGaleria(galeria);
		Inventario inventario = galeria.getInventario();
		ConsignacionPieza pieza = nuevo.CrearPiezaDigital(10, "raya", 200000, 2000000, "Subastar", "2020-London", true, nuevoUsu, "MiltonMiller", "Musica", "mp3");
		
		int tamaño = inventario.getPiezasSolicitud().size();
		
		pieza.getPieza().asignarVigenica(true);
		pieza.getPieza().asignarAprobada(true);
		inventario.añadirPiezasDeposito(pieza);
		int tamañoSubasta = inventario.getSubastar().size();
		int tamañoDeposito = inventario.getDeposito().size();
		Assertions.assertEquals(1, tamaño);
		Assertions.assertEquals(1, tamañoSubasta);
		Assertions.assertEquals(1, tamañoDeposito);
	}
	
	@Test
	public void testCrearOferta() {
		
		
	}
}
