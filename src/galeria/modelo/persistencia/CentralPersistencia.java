package galeria.modelo.persistencia;

public class CentralPersistencia {

	public static final String JSON = "JSON";
	
	public static PersistenciaUsuariosJson getPersistenciaUsuarios(){
        
		return new PersistenciaUsuariosJson();
    }
}
