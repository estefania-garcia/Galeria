package galeria.modelo.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.compras.OfertaSubasta;
import galeria.modelo.compras.OfertaVenta;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.inventario.Piezas;
import galeria.modelo.usuarios.Usuarios;

public class PersistenciaVentas {
	
	public void cargarTodo(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Ofertas.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        cargarOfertas(galeria, raiz.getJSONArray("CentroOfertas"));
	}
	
	public void salvarTodo(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Ofertas.json";
		JSONObject jobject = new JSONObject();
		
		salvarOfertas(galeria, jobject);
		
		PrintWriter pw = new PrintWriter( rutaArchivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	private void cargarOfertas(Galeria galeria, JSONArray jOfertas) {
	
		int numOfertas = jOfertas.length();
		for(int i = 0; i < numOfertas; i++) {
			JSONObject oferta = jOfertas.getJSONObject(i);
			
			String ofertador = oferta.getString("ofertador");
			String titulo = oferta.getString("titulo");
			String autores = oferta.getString("autores");
			double monto = oferta.getDouble("monto");
			String tipo = oferta.getString("tipo");
			
			Usuarios inver = null;
			List<Usuarios> usuarios = galeria.getUsuarios();
			for(Usuarios usu : usuarios) {
				if(usu.getUsuario().equals(ofertador) && usu.getRol().equals("Inversor")) {
					inver = usu;
				}
			}
			
			Piezas pieza = null;
			List<ConsignacionPieza> arte = galeria.getPiezasTotales();
			for(ConsignacionPieza art : arte) {
				if(art.getPieza().getTitulo().equals(titulo) && art.getPieza().getAutores().equals(autores)) {
					pieza = art.getPieza();
				}
			}
			
			if(tipo.equals("Oferta Subasta")) {
				OfertaSubasta nueva = new OfertaSubasta(pieza, inver, monto);
				galeria.getCentroOfertas().agregarOfertas(nueva);
			}else {
				OfertaVenta nueva = new OfertaVenta(pieza, inver, monto);
				galeria.getCentroOfertas().agregarOfertas(nueva);
			}
		}
	}
	
	private void salvarOfertas(Galeria galeria, JSONObject jobject) {
		
		JSONArray jOfertas = new JSONArray();
		for(Ofertas oferta : galeria.getOfertasSubasta()) {
			
			JSONObject jOferta = new JSONObject();
			
			jOferta.put("ofertador", oferta.getComprador().getUsuario());
			jOferta.put("titulo", oferta.getPiezas().getTitulo());
			jOferta.put("autores", oferta.getPiezas().getAutores());
			jOferta.put("monto", oferta.getMonto());
			jOferta.put("tipo", oferta.tipoOferta());
			
			jOfertas.put(jOferta);
		}
		jobject.put("CentroOfertas", jOfertas);
	}
}