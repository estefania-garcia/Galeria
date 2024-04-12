package galeria.modelo.inventario;

//HOLAAAAAAAAAAA
import galeria.modelo.compras.GaleriaOferta;

public class ArteDigital extends Piezas{

	private static final String ARTEDIGITAL = "Arte Digital";
	
	private double ancho;
	private double largo;
	private boolean audio;
	private boolean imagen;
	
	public ArteDigital(String titulo, String año, Deposito deposito, GaleriaOferta galeriaOferta, String propietario, String lugar, String autores, double ancho, double largo, boolean audio, boolean imagen) {
		super(titulo, año, lugar, autores, deposito, galeriaOferta, propietario);
		this.ancho = ancho;
		this.largo = largo;
		this.audio = audio;
		this.imagen = imagen;
	}
	
	@Override
	public String getTipo() {
		return ARTEDIGITAL;
	}
	
	public double getAncho() {
		return ancho;
	}
	
	public double getLargo() {
		return largo;
	}
	
	public boolean getImagen() {
		return imagen;
	}
	
	public boolean getAudio() {
		return audio;
	}
}
