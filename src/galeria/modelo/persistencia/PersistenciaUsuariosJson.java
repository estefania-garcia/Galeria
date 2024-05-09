package galeria.modelo.persistencia;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import galeria.modelo.controlador.Galeria;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.Usuarios;

//Esta calse guardara los datos de inicio de sesion de los usurios y tambien debera guardar los que son las clases de operacionSubasta, procesoCompra y los historiales de los inversores
public class PersistenciaUsuariosJson {
	
	public void cargarTodosUsuarios(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Usuarios.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject( jsonCompleto );
        
        cargarUsuarios(galeria, raiz.getJSONArray("Usuarios"));
        cargarRegistros(galeria, raiz.getJSONArray("Registros"));
        cargarHistoriales(galeria, raiz.getJSONArray("HistorialesInversor"));
        cargarSolicitudMontos(galeria, raiz.getJSONArray("SolicitudesMonto"));
	}
	
	public void salvarTdosoUsuarios(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Usuarios.json";
		JSONObject jobject = new JSONObject();
		
		salvarUsuarios(galeria, jobject);
		salvarRegistros(galeria, jobject);
		salvarHistoriales(galeria, jobject);
		salvarSolicitudMontos(galeria, jobject);
		
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
			galeria.getRegistro().agregarNuevosAprobados(nuevoUsuario);
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
			galeria.getRegistro().crearNuevoUsuario(nuevoUsuario.getRol(), nuevoUsuario.getNombre(), nuevoUsuario.getContrasena(), nuevoUsuario.getUsuario());
			
			galeria.getRegistro().rechazarSolicitud();
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
	
	//Persistencia historial usuarios
	private void cargarHistoriales(Galeria galeria, JSONArray jHistoriales) {
		
		int numHistoriales = jHistoriales.length();
		for(int i = 0; i < numHistoriales; i++) {
			JSONObject historial = jHistoriales.getJSONObject(i);
			
			int monto = historial.getInt("monto");
			String inversor = historial.getString("inversor");
			List<Usuarios> lista = galeria.getUsuarios();
			Usuarios inver = null;
			for(Usuarios usu : lista) {
				if(usu.getUsuario().equals(inversor) && usu.getRol().equals("Inversor")) {
					inver = usu;
				}
			}
			HistorialInversor nuevoHistorial = new HistorialInversor(monto, inver);
			nuevoHistorial.modificarMontoMaximo(monto);
			galeria.getRegistro().crearHistoriales(nuevoHistorial.getInversor(), nuevoHistorial.getMontoMaximo());
		}
	}
		
	//Salva los usuarios sin aprobacion
	private void salvarHistoriales(Galeria galeria, JSONObject jobject) {
			
		JSONArray jHistoriales = new JSONArray();
		for(HistorialInversor historial : galeria.getListaHistorial()) {
				
			JSONObject jHistorial = new JSONObject();
			jHistorial.put("monto", historial.getMontoMaximo());
			jHistorial.put("inversor", historial.getInversor().getUsuario());
			
			jHistoriales.put(jHistorial);
		}
		jobject.put( "HistorialesInversor", jHistoriales );
	}
	
	private void cargarSolicitudMontos(Galeria galeria, JSONArray jSolicitudes) {
		
		int numHSolicitudes = jSolicitudes.length();
		for(int i = 0; i < numHSolicitudes; i++) {
			JSONObject solicitud = jSolicitudes.getJSONObject(i);
			
			String usuario = solicitud.getString("usuario");
			List<HistorialInversor> lista = galeria.getListaHistorial();
			HistorialInversor inver = null;
			for(HistorialInversor usu : lista) {
				if(usu.getInversor().getUsuario().equals(usuario)) {
					inver = usu;
				}
			}
			galeria.agregarSolicitudMonto(inver);
		}
	}

	private void salvarSolicitudMontos(Galeria galeria, JSONObject jobject) {
			
		JSONArray jSolicitudes = new JSONArray();
		for(HistorialInversor historial : galeria.getSolicitudMonto()) {
				
			JSONObject jSolicitud = new JSONObject();
			jSolicitud.put("usuario", historial.getInversor().getUsuario());
			jSolicitud.put("aprobado", historial.getInversor().getUsuario());
			
			jSolicitudes.put(jSolicitud);
		}
		jobject.put( "SolicitudesMonto", jSolicitudes );
	}
}