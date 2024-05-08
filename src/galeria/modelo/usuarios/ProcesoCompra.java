package galeria.modelo.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.Inventario;

public class ProcesoCompra {
	
	/**
	 * Lista de las ofertas que se efectuan como venta de la galeria
	 * */
	public List<Ofertas> lista_ofertas;
	
	/**
	 * Atributo que nos da acceso a la clase Inventario
	 * */
	private Inventario inventario;
	
	/**
	 * Constructor que inicializa los atributos de la clase
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	public ProcesoCompra() {
		
		lista_ofertas = new ArrayList<>();
	}
	
	/**
	 * Método que agrega la oferta a la lista y modifica el estado de la pieza, la agrega en piezas vendidas del inventario y elimina la pieza de las disponibles
	 * @param ofertas
	 * */
	public void agregarOfertas(Ofertas ofertas) {
		
		lista_ofertas.add(ofertas);
		inventario.modificarEstado("Vendida", ofertas.getPiezas());
		ofertas.getPiezas().asignarVenta(true);
		ofertas.getPiezas().modificarPropietario(ofertas.getComprador().getInversor());
	}
	
	public List<Ofertas> getlistaOfertas(){
		
		return lista_ofertas;
	}
}
