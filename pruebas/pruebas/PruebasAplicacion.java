package pruebas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.OperacionSubasta;
import galeria.modelo.usuarios.ProcesoCompra;
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
	
	/**
	 * Se válida que se agregue correctamente la oferta a las listas.
	 * Se verifica que los diferentes metodos esten haciendo una correcta verificación de los montos establecidos para la pieza.
	 * */
	@Test
	public void testCrearOferta() {
		
		Usuarios nuevoUsu = new Usuarios("Lauras", "BTfr2005", "Inversor", "Laura");
		HistorialInversor nuevo = new HistorialInversor(200000, nuevoUsu);
		Galeria galeria = new Galeria();
		nuevo.asignarGaleria(galeria);
		Inventario inventario = galeria.getInventario();
		ConsignacionPieza pieza = nuevo.CrearPiezaDigital(10, "raya", 200000, 2000000, "Subastar", "2020-London", true, nuevoUsu, "MiltonMiller", "Musica", "mp3");
		
		OperacionSubasta operador = new OperacionSubasta(galeria);
		CentroOfertas COfertas = new CentroOfertas(operador, galeria, inventario);
		
		OfertaSubasta ofertas = new OfertaSubasta(pieza.getPieza(), nuevo, 100000);
		COfertas.agregarOfertas(ofertas);
		int tamaño = COfertas.getTotalOfertas().size();
		int TSubastas = COfertas.getOfertasSubasta().size();
		Assertions.assertFalse(ofertas.validarPrecio());
		Assertions.assertFalse(ofertas.venderPieza());
		Assertions.assertEquals(1, tamaño);
		Assertions.assertEquals(1, TSubastas);
	}
	
	/**
	 * Verifica que las piezas de subasta y sus repestivas ofertas se esten agregando correctamente en el mapa correspondiente
	 * */
	@Test
	public void testCrearSubasta() {
		
		Usuarios nuevoUsu = new Usuarios("Lauras", "BTfr2005", "Inversor", "Laura");
		HistorialInversor nuevo = new HistorialInversor(200000, nuevoUsu);
		Galeria galeria = new Galeria();
		nuevo.asignarGaleria(galeria);
		Inventario inventario = galeria.getInventario();
		ConsignacionPieza pieza = nuevo.CrearPiezaDigital(10, "raya", 200000, 2000000, "Subastar", "2020-London", true, nuevoUsu, "MiltonMiller", "Musica", "mp3");
		
		OperacionSubasta operador = new OperacionSubasta(galeria);
		operador.agregarPiezasSubasta(pieza.getPieza());
		int tamaño = operador.getMapa().keySet().size();
		Assertions.assertEquals(1, tamaño);
		
		OfertaSubasta ofertas = new OfertaSubasta(pieza.getPieza(), nuevo, 210000);
		operador.agregarOfertas(ofertas);
		int TOfertas = operador.getMapa().get(pieza.getPieza()).size();
		Assertions.assertEquals(1, TOfertas);
	}
	
	/**
	 * Verifica la correcta compra de la pieza y el cambio de dueño
	 * */
	@Test
	public void testComprarPieza() {
		
		Usuarios nuevoUsu = new Usuarios("Lauras", "BTfr2005", "Inversor", "Laura");
		Usuarios nuevoUsu2 = new Usuarios("Mireya", "BTfr2006", "Inversor", "Mime");
		HistorialInversor nuevo = new HistorialInversor(200000, nuevoUsu);
		HistorialInversor nuevo2 = new HistorialInversor(200000, nuevoUsu2);
		
		Galeria galeria = new Galeria();
		nuevo.asignarGaleria(galeria);
		Inventario inventario = galeria.getInventario();
		ConsignacionPieza pieza = nuevo.CrearPiezaDigital(10, "raya", 200000, 2000000, "Subastar", "2020-London", true, nuevoUsu, "MiltonMiller", "Musica", "mp3");
		ProcesoCompra cajero = new ProcesoCompra(inventario);
		OfertaSubasta ofertas = new OfertaSubasta(pieza.getPieza(), nuevo2, 210000);
		
		cajero.agregarOfertas(ofertas);
		int tamaño = cajero.getlistaOfertas().size();
		Assertions.assertEquals(1, tamaño);
		Assertions.assertEquals(nuevoUsu2, cajero.getlistaOfertas().get(0).getPiezas().getPropietario());
	}
}
