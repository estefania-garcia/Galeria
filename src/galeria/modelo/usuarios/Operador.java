package galeria.modelo.usuarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.modelo.compras.*;
import galeria.modelo.inventario.Piezas;

public class Operador extends Usuarios{

	public static final String OPERADOR = "Operador";
	private List<Ofertas> listaOfertas;
	private Map<Piezas, List<Ofertas>> mapaSubastas = new HashMap<>();
	private OfertaSubasta oferta_subastas;
	
	public Operador(String usuario, String contrasena, String rol, String nombre) {
		
		super(usuario, contrasena, rol, nombre);
		listaOfertas = new LinkedList<>();
		mapaSubastas = new HashMap<>();
	}
	
	@Override
	public String getTipoUsuario() {
		return OPERADOR;
	}
	
	public void agregarPiezasSubasta(Piezas piezas) {
		if(mapaSubastas.get(piezas) == null) {
			mapaSubastas.put(piezas, listaOfertas);
		}
	}
	
	public void agregarOfertas(Ofertas ofertas){
		Iterator<Map.Entry<Piezas, List<Ofertas>>> iterador = mapaSubastas.entrySet().iterator();
    	while(iterador.hasNext()) {
    		Map.Entry<Piezas, List<Ofertas>> mapa = iterador.next();
    		Piezas clave = mapa.getKey();
    		List<Ofertas> llave = mapa.getValue();
    		if(clave == ofertas.getPiezas()) {
    			int pos = llave.size() - 1;
    			Ofertas ul_oferta = llave.get(pos);
    			if(ul_oferta.getMonto() < ofertas.getMonto()) {
    				llave.add(ofertas);
    			}
    		}
    	}
	}
	//Retorna la Oferta mas grand, de una lista de ofertas que entra por parametro
	public Ofertas obtenerMayorOferta(Piezas pieza) {
		List<Ofertas> lista = mapaSubastas.get(pieza);
		Ofertas oferta_final = lista.get(lista.size()-1);
		if(oferta_subastas.venderPieza() == true) {
			return oferta_final;
		}
		return null;
	}
}