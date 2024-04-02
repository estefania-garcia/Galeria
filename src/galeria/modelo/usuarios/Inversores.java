package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;
import galeria.modelo.inventario.Piezas;

public class Inversores extends Usuarios{

	public static final String INVERSORES = "Inversores";
	private static int topeMonto;
	private List<Piezas> piezasCompradas;
	private List<Piezas> pizasPropias;
	
	public Inversores(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		piezasCompradas = new LinkedList<Piezas>();
		pizasPropias = new LinkedList<Piezas>();
	}
	
	@Override
	public String getTipoUsuario() {
		return INVERSORES;
	}
	
	public List<Piezas> getPiezasCompradas(){
		return piezasCompradas;
	}
	
	public List<Piezas> getPiezasPropias(){
		return pizasPropias;
	}
	
	public void agregarPiezasCompradas(Piezas piezas){
		piezasCompradas.add(piezas);
	}
	
	public void agregarPiezasPropias(Piezas piezas){
		pizasPropias.add(piezas);
	}
	
	public int getTopeMonto() {
		return topeMonto;
	}
	
	public int asignarValorMaximo(int valor) {
		topeMonto = valor;
		return topeMonto;
	}
}
