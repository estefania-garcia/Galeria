package galeria.modelo.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Autores;

public class PersistenciaAutores {

	public void cargarTodo(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Autores.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        cargarAutores(galeria, raiz.getJSONArray("Autores"));
	}
	
	public void salvarTodo(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Autores.json";
		JSONObject jobject = new JSONObject();
		
		salvarAutores(galeria, jobject);
		
		PrintWriter pw = new PrintWriter( rutaArchivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	private void cargarAutores(Galeria galeria, JSONArray jOfertas) {
	
		int numAutores = jOfertas.length();
		for(int i = 0; i < numAutores; i++) {
			JSONObject oferta = jOfertas.getJSONObject(i);
			
			String nombre = oferta.getString("nombre");
			galeria.getCentroAutores().crearAutor(nombre);
		}
	}
	
	private void salvarAutores(Galeria galeria, JSONObject jobject) {
		
		JSONArray jAutores = new JSONArray();
		for(Autores autor : galeria.getListaAutores()) {
			
			JSONObject jAutor = new JSONObject();
			
			jAutor.put("nombre", autor.getNombre());
			
			jAutores.put(jAutor);
		}
		jobject.put("Autores", jAutores);
	}
}
