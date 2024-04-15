package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.inventario.Inventario;

public class Cajero extends Usuarios{
	
	public static final String CAJERO = "Cajero";
	public List<Ofertas> lista_ofertas;
	private Inventario inventario;
	
	public Cajero(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		lista_ofertas = new LinkedList<>();
	}
	
	@Override
	public String getTipoUsuario() {
		return CAJERO;
	}
	
	public void agregarOfertas(Ofertas ofertas) {
		lista_ofertas.add(ofertas);
		inventario.modificarEstado("Vendida", ofertas.getPiezas());
		inventario.añadirPiezasVendidas(ofertas.getPiezas());
		inventario.eliminarPiezas(ofertas.getPiezas(), ofertas.getPiezas().getProposito(), ofertas.getPiezas().getDeposito());
	}
}
