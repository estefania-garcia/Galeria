package galeria.modelo.persistencia;

public class CentralPersistencia {
	
	public static PersistenciaUsuariosJson getPersistenciaUsuarios(){
        
		return new PersistenciaUsuariosJson();
    }
	
	public static PersistenciaInventario getPersistenciaInventario(){
        
		return new PersistenciaInventario();
    }
	
	public static PersistenciaVentas getPersistenciaOfertas() {
		
		return new PersistenciaVentas();
	}
	
	public static PersistenciaAutores getPersistenciaAutores() {
		
		return new PersistenciaAutores();
	}
}
