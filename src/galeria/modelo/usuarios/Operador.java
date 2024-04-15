import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.*;

public class Operador extends Usuarios{

	public static final String OPERADOR = "Operador";
	private List<Ofertas> ordenSuabasta;
	
	public Operador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		ordenSuabasta = new LinkedList<Ofertas>();
	}
	
	@Override
	public String getTipoUsuario() {
		return OPERADOR;
	}
	
	public List<Ofertas> agregarOfertas(Ofertas ofertas){
		if(ofertas.getMonto() >= ofertas.getPiezas().getGaleriaOferta().getMontoMinimo()) {
			ordenSuabasta.add(ofertas);
		}
		return ordenSuabasta;
	}
	//Retorna la Oferta mas grand, de una lista de ofertas que entra por parametro
	public Ofertas obtenerMayorOferta() {
		
		Ofertas mayor = ordenSuabasta.get(0);
		return mayor;
	}
}