package galeria.modelo.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;

public class PersistenciaVentas {
	
	public void cargarTodo(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Ofertas.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject( jsonCompleto );
	}
	
	public void salvarTodo(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Ofertas.json";
		JSONObject jobject = new JSONObject();
		
		PrintWriter pw = new PrintWriter( rutaArchivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	private void cargarOfertasSubasta(Galeria galeria, JSONObject jobject) {
	
		JSONArray jUsuarios = new JSONArray();
		for(Ofertas oferta : galeria.getOfertasSubasta()) {
			
			JSONObject jOferta = oferta
		}
	}
	
	private void salvarOfertasSubasta(Galeria galeria, JSONObject jobject) {
		
	}
}