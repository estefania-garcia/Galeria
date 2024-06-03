package galeriacasasubasta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import galeria.modelo.consola.ConsolaInicio;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.inventario.Inventario;
import galeria.modelo.usuarios.RegistroInicio;
import net.miginfocom.swing.MigLayout;
import items.*;
import javax.swing.JFrame;

public class LoginMain extends javax.swing.JFrame {
    
    private JFrame frame = (JFrame) this;
    private static LoginMain principal;
    
    private static final Galeria galeria = new Galeria();
	
	public void persistenciaCargar() {
		
        try {
			galeria.cargarUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvar() {
		
        try {
			galeria.salvarUsuarios();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarInventario() {
		
        try {
			galeria.cargarInventario();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarInventario() {
		
        try {
			galeria.salvarInventario();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarVentas() {
		
        try {
			galeria.cargarVentas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarAutores() {
		
        try {
			galeria.salvarAutores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaCargarAutores() {
		
        try {
			galeria.cargarAutores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void persistenciaSalvarVentas() {
		
        try {
			galeria.salvarVentas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public LoginMain() {
        
        initComponents();
		RegistroInicio sesion = galeria.getRegistro();
		Inventario inventario = galeria.getInventario();
		persistenciaCargar();
		
        getContentPane().setBackground(new Color(163, 228, 215));
        banner.setOpaque(false);
        initRegister(sesion);
        initInicio(sesion, galeria);
        register.setVisible(false);
        inicio.setVisible(true);
    }
    
    private void initRegister(RegistroInicio sesion) {
        
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]push"));
        JLabel label = new JLabel("Crear cuenta");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(Color.WHITE);
        register.add(label);
        
        MyTextField txtNombre = new MyTextField();
        txtNombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/name.png")));
        txtNombre.setHint("Nombre");
        register.add(txtNombre, "w 60%");
        
        MyTextField txtRol = new MyTextField();
        txtRol.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/rol.png")));
        txtRol.setHint("Rol");
        register.add(txtRol, "w 60%");
        
        MyTextField txtUsuario = new MyTextField();
        txtUsuario.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUsuario.setHint("Usuario");
        register.add(txtUsuario, "w 60%");
        
        MyPasswordField txtContrasena = new MyPasswordField();
        txtContrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtContrasena.setHint("Contraseña");
        register.add(txtContrasena, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(167, 132, 139));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Registrarse");
        register.add(cmd, "w 40%, h 40");
        
        Button cmd2 = new Button();
        cmd2.setBackground(new Color(109, 172, 160));
        cmd2.setForeground(new Color(250, 250, 250));
        cmd2.setFont(new Font("SansSerif", Font.BOLD, 15));
        cmd2.setText("Iniciar Sesión");
        register.add(cmd2, "w 40%, h 40");

        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String rol = txtRol.getText();
                String usu = txtUsuario.getText();
                String name = txtNombre.getText();
                String pass = String.valueOf(txtContrasena.getPassword());
                boolean verifica = true;
                if(usu.equals("")){
                    verifica = false;
                }
                if(name.equals("")){
                    verifica = false;
                }
                if(pass.equals("")){
                    verifica = false;
                }
                if(rol.equals("")){
                    verifica = false;
                }
                if(verifica == false){
                    Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Ingresa todos los datos");
                    panel.showNotification();
                }else{
                	
                    boolean comprobar = sesion.crearNuevoUsuario(rol, name, pass, usu);
                    if(comprobar == true && !rol.equals("Administrador")) {
                    	persistenciaSalvar();
                    	Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "A esperar de aprobación");
                        panel.showNotification();
                    }
                    else if(comprobar == true && rol.equals("Administrador")) {
                    	persistenciaSalvar();
                    	Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "A esperar de aprobación");
                        panel.showNotification();
                    }
                    else {
                    	Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Sus datos no son válidos");
                        panel.showNotification();
                    }
                }
            } 
        });
        cmd2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                register.setVisible(false);
                inicio.setVisible(true);
            }
        });
    }
    
    private void initInicio(RegistroInicio sesion, Galeria galeria) {
        
        inicio.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]push"));
        JLabel label = new JLabel("Iniciar Sesión");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(Color.WHITE);
        inicio.add(label);

        MyTextField txtRol = new MyTextField();
        txtRol.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/rol.png")));
        txtRol.setHint("Rol");
        inicio.add(txtRol, "w 60%");
        
        MyTextField txtUsuario = new MyTextField();
        txtUsuario.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUsuario.setHint("Usuario");
        inicio.add(txtUsuario, "w 60%");
        
        MyPasswordField txtContrasena = new MyPasswordField();
        txtContrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtContrasena.setHint("Contraseña");
        inicio.add(txtContrasena, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(167, 132, 139));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Iniciar Sesión");
        inicio.add(cmd, "w 40%, h 40");
        
        Button cmd2 = new Button();
        cmd2.setBackground(new Color(109, 172, 160));
        cmd2.setForeground(new Color(250, 250, 250));
        cmd2.setFont(new Font("SansSerif", Font.BOLD, 15));
        cmd2.setText("Registrarse");
        inicio.add(cmd2, "w 40%, h 40");
        
        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String rol = txtRol.getText();
                String usu = txtUsuario.getText();
                String pass = String.valueOf(txtContrasena.getPassword());
                boolean verifica = true;
                if(usu.equals("")){
                    verifica = false;
                }
                if(pass.equals("")){
                    verifica = false;
                }
                if(rol.equals("")){
                    verifica = false;
                }
                if(verifica == false){
                    Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Ingresa todos los datos");
                    panel.showNotification();
                }else{
                	boolean comprobacion = sesion.inicioSesion(rol, usu, pass);
                	if(comprobacion == true) {
                		persistenciaSalvar();
                		dispose();
                        if(rol.equals("Administrador")){
                            Principal admin = new Principal(rol, usu, principal, galeria);
                            admin.setVisible(true);
                        }
                        else if(rol.equals("Inversor")){
                            Inversor inver = new Inversor(rol, usu, principal, galeria, usu);
                            inver.setVisible(true);
                        }
                        else if(rol.equals("Cajero") | rol.equals("Operador")){
                        	OperadorCajero oc = new OperadorCajero(rol, usu, principal, galeria);
                            oc.setVisible(true);
                        }
                	}else {
                		Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "No tienes una cuenta aprobada");
                        panel.showNotification();
                	}
                }
            } 
        });
        
        cmd2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                register.setVisible(true);
                inicio.setVisible(false);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        banner = new javax.swing.JLayeredPane();
        register = new javax.swing.JPanel();
        inicio = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        banner.setBackground(new java.awt.Color(255, 255, 255));
        banner.setForeground(new java.awt.Color(255, 255, 255));
        banner.setOpaque(true);

        register.setOpaque(false);

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        inicio.setBackground(new java.awt.Color(255, 102, 102));
        inicio.setOpaque(false);

        javax.swing.GroupLayout inicioLayout = new javax.swing.GroupLayout(inicio);
        inicio.setLayout(inicioLayout);
        inicioLayout.setHorizontalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        inicioLayout.setVerticalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        banner.setLayer(register, javax.swing.JLayeredPane.DEFAULT_LAYER);
        banner.setLayer(inicio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bannerLayout = new javax.swing.GroupLayout(banner);
        banner.setLayout(bannerLayout);
        bannerLayout.setHorizontalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bannerLayout.setVerticalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(banner)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(banner)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	principal = new LoginMain();
            	principal.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane banner;
    private javax.swing.JPanel inicio;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
