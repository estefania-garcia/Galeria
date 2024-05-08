package galeria.modelo.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import galeria.modelo.compras.GaleriaOferta;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.ArteDigital;
import galeria.modelo.inventario.ArteTridimensional;
import galeria.modelo.inventario.ArteVisual;
import galeria.modelo.inventario.ConsignacionPieza;
import galeria.modelo.usuarios.HistorialInversor;
import galeria.modelo.usuarios.Usuarios;

public class PersistenciaInventario {
	
	public void cargarTodasPiezas(Galeria galeria) throws IOException {
		
		String rutaArchivo = "./Datos/Inventario.json";
		String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
		JSONObject raiz = new JSONObject(jsonCompleto);
		
		cargarPiezasAprobadas(galeria, raiz.getJSONArray("Piezas"));
	}
	
	public void salvarTodasPiezas(Galeria galeria) throws FileNotFoundException {
		
		String rutaArchivo = "./Datos/Inventario.json";
		JSONObject jobject = new JSONObject();
		
		salvarPiezasAprobadas(galeria, jobject);
		
		PrintWriter pw = new PrintWriter( rutaArchivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	public void cargarPiezasAprobadas(Galeria galeria, JSONArray jPiezas) {
		
		int numPiezas = jPiezas.length();
		for(int i = 0; i < numPiezas; i++) {
			JSONObject pieza = jPiezas.getJSONObject(i);
			
			String tipo = pieza.getString("tipo");
			String titulo = pieza.getString("titulo");
			String proposito = pieza.getString("proposito");
			String fechaVenta = pieza.getString("fechaVenta");
			String creacion = pieza.getString("creacion");
			boolean deposito = pieza.getBoolean("deposito");
			boolean vigencia = pieza.getBoolean("vigencia");
			boolean venta = pieza.getBoolean("venta");
			boolean aprobacion = pieza.getBoolean("aprobacion");
			double monto = pieza.getDouble("monto");
			double montoMin = pieza.getDouble("montoMin");
			int tiempo = pieza.getInt("tiempo");
			String propietario = pieza.getString("propietario");
			String autores = pieza.getString("autores");
			String estado = pieza.getString("estado");
			
			Usuarios inver = null;
			List<Usuarios> lista = galeria.getUsuarios();
			for(Usuarios usu : lista) {
				if(usu.getUsuario().equals(propietario) && usu.getRol().equals("Inversor")) {
					inver = usu;
				}
			}
			
			if(tipo.equals("Arte Visual")) {
				String anchoXlargo = pieza.getString("anchoXlargo");
				String tecnica = pieza.getString("tecnica");
				GaleriaOferta ofert = new GaleriaOferta(monto, montoMin);
				ArteVisual arte = new ArteVisual(titulo, proposito, creacion, deposito, ofert, inver, autores, anchoXlargo, tecnica);
				arte.asignarVigenica(vigencia);
				arte.asignarVenta(venta);
				arte.asignarFechaVendida(fechaVenta);
				arte.asignarEstado(estado);
				arte.asignarAprobada(aprobacion);
				ConsignacionPieza consignacion = new ConsignacionPieza(tiempo, arte, inver);
				galeria.getInventario().añadirPiezasSolicitud(consignacion);
				
				HistorialInversor historial = null;
				if(arte.getAprobada() == true) {
					for(HistorialInversor histo : galeria.getListaHistorial()) {
						if(inver.getUsuario().equals(histo.getInversor().getUsuario())) {
							historial = histo;
						}
					}
					historial.agregarPiezasPropias(arte);
				}
			}
			else if(tipo.equals("Arte Tridimensional")){
				String dimensiones = pieza.getString("dimensiones");
				String tecnica = pieza.getString("tecnica");
				double peso = pieza.getDouble("peso");
				boolean electricidad = pieza.getBoolean("electricidad");
				GaleriaOferta ofert = new GaleriaOferta(monto, montoMin);
				ArteTridimensional arte = new ArteTridimensional(titulo, proposito, creacion, deposito, ofert, inver, autores, dimensiones, tecnica, peso, electricidad);
				arte.asignarVigenica(vigencia);
				arte.asignarVenta(venta);
				arte.asignarFechaVendida(fechaVenta);
				arte.asignarEstado(estado);
				arte.asignarAprobada(aprobacion);
				ConsignacionPieza consignacion = new ConsignacionPieza(tiempo, arte, inver);
				galeria.getInventario().añadirPiezasSolicitud(consignacion);
				
				HistorialInversor historial = null;
				if(arte.getAprobada() == true) {
					for(HistorialInversor histo : galeria.getListaHistorial()) {
						if(inver.getUsuario().equals(histo.getInversor().getUsuario())) {
							historial = histo;
						}
					}
					historial.agregarPiezasPropias(arte);
				}
			}
			else if(tipo.equals("Arte Digital")) {
				String tipoArte = pieza.getString("tipoArte");
				String tipoArchivo = pieza.getString("tipoArchivo");
				GaleriaOferta ofert = new GaleriaOferta(monto, montoMin);
				ArteDigital arte = new ArteDigital(titulo, proposito, creacion, deposito, ofert, inver, autores, tipoArte, tipoArchivo);
				arte.asignarVigenica(vigencia);
				arte.asignarVenta(venta);
				arte.asignarFechaVendida(fechaVenta);
				arte.asignarEstado(estado);
				arte.asignarAprobada(aprobacion);
				ConsignacionPieza consignacion = new ConsignacionPieza(tiempo, arte, inver);
				galeria.getInventario().añadirPiezasSolicitud(consignacion);
				
				HistorialInversor historial = null;
				if(arte.getAprobada() == true) {
					for(HistorialInversor histo : galeria.getListaHistorial()) {
						if(inver.getUsuario().equals(histo.getInversor().getUsuario())) {
							historial = histo;
						}
					}
					historial.agregarPiezasPropias(arte);
				}
			}
		}
	}
	
	public void salvarPiezasAprobadas(Galeria galeria, JSONObject jobject) {
		
		JSONArray jInventario = new JSONArray();
		for(ConsignacionPieza piezas : galeria.getPiezasSolicitud()) {
			
			JSONObject jPiezas = new JSONObject();
			
			jPiezas.put("tipo", piezas.getPieza().getTipo());
			jPiezas.put("titulo", piezas.getPieza().getTitulo());
			jPiezas.put("proposito", piezas.getPieza().getProposito());
			jPiezas.put("creacion", piezas.getPieza().getLugarCreacion());
			jPiezas.put("deposito", piezas.getPieza().getDeposito());
			jPiezas.put("monto", piezas.getPieza().getGaleriaOferta().getMontoCliente());
			jPiezas.put("montoMin", piezas.getPieza().getGaleriaOferta().getMontoMinimo());
			jPiezas.put("tiempo", piezas.getTiempo());
			jPiezas.put("propietario", piezas.getPropietario().getUsuario());
			jPiezas.put("autores", piezas.getPieza().getAutores());
			jPiezas.put("vigencia", piezas.getPieza().getVigencia());
			
			jPiezas.put("venta", piezas.getPieza().getVenta());
			jPiezas.put("fechaVenta", piezas.getPieza().getFechaVendida());
			jPiezas.put("estado", piezas.getPieza().getEstado());
			jPiezas.put("aprobacion", piezas.getPieza().getAprobada());
			
			if(piezas.getPieza().getTipo().equals("Arte Visual")) {
				ArteVisual piz = (ArteVisual) piezas.getPieza();
				jPiezas.put("anchoXlargo", piz.getAncho());
				jPiezas.put("tecnica", piz.getTecnica());
			}
			else if(piezas.getPieza().getTipo().equals("Arte Tridimensional")) {
				ArteTridimensional piz = (ArteTridimensional) piezas.getPieza();
				jPiezas.put("dimensiones", piz.getDimensiones());
				jPiezas.put("tecnica", piz.getTectica());
				jPiezas.put("peso", piz.getPeso());
				jPiezas.put("electricidad", piz.getElectricidad());
			}
			else if(piezas.getPieza().getTipo().equals("Arte Digital")) {
				ArteDigital piz = (ArteDigital) piezas.getPieza();
				jPiezas.put("tipoArte", piz.getTipoArte());
				jPiezas.put("tipoArchivo", piz.getTipoArchivo());
			}
			jInventario.put(jPiezas);
		}
		jobject.put("Piezas", jInventario);
	}
}
