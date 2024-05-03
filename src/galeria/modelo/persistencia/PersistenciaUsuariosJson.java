package galeria.modelo.persistencia;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.usuarios.Usuarios;

//Esta calse guardara los datos de inicio de sesion de los usurios y tambien debera guardar los que son las clases de operacionSubasta, procesoCompra y los historiales de los inversores
public class PersistenciaUsuariosJson {
	
	public void cargarTodosUsuarios(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Usuarios.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        cargarUsuarios(galeria, raiz.getJSONArray("Usuarios"));
        cargarRegistros(galeria, raiz.getJSONArray("Registros"));
	}
	
	public void salvarTdosoUsuarios(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Usuarios.json";
		JSONObject jobject = new JSONObject();
		
		salvarUsuarios(galeria, jobject);
		salvarRegistros(galeria, jobject);
		
		PrintWriter pw = new PrintWriter( rutaArchivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	//Carga los usuarios aprobados
	private void cargarUsuarios(Galeria galeria, JSONArray jUsuarios) {
		
		int numUsuarios = jUsuarios.length();
		for(int i = 0; i < numUsuarios; i++) {
			JSONObject usuario = jUsuarios.getJSONObject(i);
			Usuarios nuevoUsuario = null;
			nuevoUsuario = Usuarios.cargarDesdeJSON(usuario);
		}
	}
	
	//Salva los usuarios aprobados
	private void salvarUsuarios(Galeria galeria, JSONObject jobject) {
		
		JSONArray jUsuarios = new JSONArray();
		for(Usuarios usuario : galeria.getUsuarios()) {
			
			JSONObject jUsuario1 = usuario.salvarEnJson();
			jUsuarios.put(jUsuario1);
		}
		jobject.put( "Usuarios", jUsuarios );
	}
	
	//Carga los usuarios sin aprobacion
		private void cargarRegistros(Galeria galeria, JSONArray jUsuarios) {
			
			int numUsuarios = jUsuarios.length();
			for(int i = 0; i < numUsuarios; i++) {
				JSONObject usuario = jUsuarios.getJSONObject(i);
				Usuarios nuevoUsuario = null;
				nuevoUsuario = Usuarios.cargarDesdeJSON(usuario);
			}
		}
		
		//Salva los usuarios sin aprobacion
		private void salvarRegistros(Galeria galeria, JSONObject jobject) {
			
			JSONArray jUsuarios = new JSONArray();
			for(Usuarios usuario : galeria.getRegistrosUsuarios()) {
				
				JSONObject jUsuario1 = usuario.salvarEnJson();
				jUsuarios.put(jUsuario1);
			}
			jobject.put( "Registros", jUsuarios );
		}
}