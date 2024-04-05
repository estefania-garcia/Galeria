package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.inventario.Piezas;

public class Comprador extends Usuarios{

	public static final String COMPRADOR = "Comprador";
	private static int topeMonto;
	private List<Piezas> piezasCompradas;
	
	public Comprador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		piezasCompradas = new LinkedList<Piezas>();
	}
	
	@Override
	public String getTipoUsuario() {
		return COMPRADOR;
	}
	
	public List<Piezas> getPiezasCompradas(){
		return piezasCompradas;
	}
	
	public void agregarPiezasCompradas(Piezas piezas){
		piezasCompradas.add(piezas);
	}
	
	public int getTopeMonto() {
		return topeMonto;
	}
	
	public int asignarValorMaximo(int valor) {
		topeMonto = valor;
		return topeMonto;
	}
}
