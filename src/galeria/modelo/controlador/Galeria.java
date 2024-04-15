package galeria.modelo.controlador;

<<<<<<< HEAD
public class Galeria {
	
	
	
=======
/**
 * Esta clase recolecta todas las ofertas hechas por los usuarios y las organiza según su tipo
 * Sirve como base para que las otras partes del sistema puedan tener un acceso ordenado a estas ofertas
 * */

public class Galeria {
	
	/**
	 * Lista que guarda todos los usuarios registrados
	 * */
	private List<Usuarios> usuariosRegistrados;
	
	/**
	 * Constructor que inicializa la lista de usuarios registrados
	 * */
	public Galeria ()
	{
		usuariosRegistrados = new LinkedList<Usuarios>();
	}
	
	/**
	 * Método para registrar un usuario, dependiendo del rol
	 * @param usuario
	 * @param contraseña
	 * @param rol
	 * @param nombre
	 * */
	public void registrarUsuario(String usuario, String contrasena, String rol, String nombre){
		
		Usuarios nuevoUsuario = null;
        if ("Administrador".equalsIgnoreCase(rol))
        {
            nuevoUsuario = new Administrador(usuario, contrasena, rol, nombre);
        } 
        else if ("Inversor".equalsIgnoreCase(rol)) 
        {
            nuevoUsuario = new Inversor(usuario, contrasena, rol, nombre);
        } 
        else if ("Cajero".equalsIgnoreCase(rol)) 
        {
            nuevoUsuario = new Cajero(usuario, contrasena, rol, nombre);
        } 
        else if ("Operador".equalsIgnoreCase(rol)) 
        {
            nuevoUsuario = new Operador(usuario, contrasena, rol, nombre);
        } 

        // Agregar el nuevo usuario a la lista de usuarios registrados
        usuariosRegistrados.add(nuevoUsuario);
	}
	
	/**
	 * Método para asignarle el monto máximo de compra a un inversor. Se busca su login en los usuarios registrados y le asigna monto
	 * @param login
	 * @param contraseña
	 * */
	public void asignarMonto (String login, double montoMax)
	{
		 for (Usuarios usuarioBusqueda : usuariosRegistrados )
		 {
			 if (usuarioBusqueda.getUsuario() == login)
			 {
				 usuarioBusqueda.ModificarMontoMaximo(montoMax)
			 }
		 }
	}
	
	public void aumentarMonto (String login)
	{
		
	}
	
	public void verificarAumentoMonto (double monto, String login)
	{
		
	}
	
	public void agregarPieza (Piezas pieza)
	{
		
	}
	
	public void verificarPieza (Piezas pieza)
	{
		
	}
	
	public void cambiarUbicacionPieza (Pieza pieza)
	{
		
	}
	
	public void devolverPieza (Pieza pieza)
	{
		
	}
	
	public void veracidadOferta (Ofertas oferta, Usuario comprador)
	{
		
	}
	
	public void confirmarVenta (Piezas pieza, Inversor inversor)
	{
		
	}
	
	public void cambiarEstadoPieza (Piezas pieza)
	{
		
	}
	
	public void validarOfertaSubastaFinal (boolean aprobar)
	{
		
	}
	
	public void validarOfertaVentaFinal (boolean aprobar)
	{
		
	}
	
	public void actualizarDias (int dias)
	{
		
	}
>>>>>>> branch 'master' of https://github.com/DPOO-Grupo-8-S6/GALERIA_PROYECTO_UNO.git
}

