package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.*;

public class Operador extends Usuarios{

	public static final String OPERADOR = "Operador";
	private List<Oferta> ordenSuabasta;
	
	public Operador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		ordenSuabasta = new LinkedList<Oferta>();
	}
	
	@Override
	public String getTipoUsuario() {
		return OPERADOR;
	}
	
	public List<Oferta> agregarOfertas(Oferta ofertas){
		if(ofertas.getMonto() >= ofertas.getPiezas().getGaleriaOferta().getMontoMinimo()) {
			ordenSuabasta.add(ofertas);
		}
		/*Ordenar la lista segun el monto de la oferta*/
		return ordenSuabasta;
	}
	
	public Oferta obtenerMayorOferta() {
		
		Oferta mayor = ordenSuabasta.get(0);
		return mayor;
	}
}
