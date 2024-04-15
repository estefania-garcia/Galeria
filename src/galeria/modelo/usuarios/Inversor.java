package galeria.modelo.usuarios;

import java.util.LinkedList;
import java.util.List;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.inventario.ArteDigital;
import galeria.modelo.inventario.ArteTridimensional;
import galeria.modelo.inventario.Piezas;

public class Inversor extends Usuarios{
	
	public static final String INVERSOR = "Inversor";
	private List<Piezas> pizasPropias;
	private double montoMaximo;
	
	public Inversor(String usuario, String contrasena, String rol, String nombre) {
		super(usuario, contrasena, rol, nombre);
		pizasPropias = new LinkedList<Piezas>();
	}
	public void modificarMontoMaximo(double montoMaximo) {
        this.montoMaximo = montoMaximo;
	}
	
	public double getMontoMaximo() {
		return montoMaximo;
	}
	
	@Override
	public String getTipoUsuario() {
		return INVERSOR;
	}
	
	public List<Piezas> getPiezas(){
		return pizasPropias;
	}
	
	public void agregarPiezasPropias(Piezas piezas){
		pizasPropias.add(piezas);
	}
	
	public GaleriaOferta crearOfertaGaleria(int monto, int montoMinimo, String proposito) {
		
		if(proposito.equals("Subasta")) {
			GaleriaOferta oferta = new GaleriaOferta(monto, montoMinimo);
			return oferta;
		}else if(proposito.equals("Vender")) {
			GaleriaOferta oferta = new GaleriaOferta(monto);
			return oferta;
		}else if(proposito.equals("Exhibir")) {
			GaleriaOferta oferta = new GaleriaOferta();
			return oferta;
		}
		return null;
	}
	
	public ArteDigital CrearPiezaDigital(String titulo, int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, String tipoArte, String tipoArchivo) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		return new ArteDigital(titulo, proposito, lugar_creacion, año, deposito, oferta, this, autores, tipoArte, tipoArchivo);
	}
	
	public ArteTridimensional CrearPiezaTridimensional(String titulo,  int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores,double alto, String tecnica, double ancho, double profundidad, double peso, boolean electricidad, String material) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		return new ArteTridimensional(titulo, proposito, lugar_creacion, deposito, oferta, this, año, autores, alto, tecnica, ancho, profundidad, peso, electricidad, material);
	}
	public ArteDigital CrearPiezaPintura(String titulo,  int monto, int montoMinimo, String proposito, String lugar_creacion, String año, boolean deposito, Usuarios propietario, String autores, double ancho, double largo , String material, String tecnica, String tipoArte, String tipoArchivo ) {
		GaleriaOferta oferta = crearOfertaGaleria(monto, montoMinimo, proposito);
		return new ArteDigital(titulo, proposito, lugar_creacion, año, deposito, oferta, this, autores, tipoArte, tipoArchivo);
	}
}