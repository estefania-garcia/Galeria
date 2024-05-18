package component;

import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import galeria.modelo.*;
import galeria.modelo.controlador.Galeria;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

	private Galeria nuevaGaleria;
    public PanelLoginAndRegister(Galeria nuevaGaleria) {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
        this.nuevaGaleria = nuevaGaleria;
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Crear cuenta");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(167, 132, 139));
        register.add(label);
        
        MyTextField txtNombre = new MyTextField();
        txtNombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/name.png")));
        txtNombre.setHint("Nombre");
        register.add(txtNombre, "w 60%");
        
        String nombreTexto = txtNombre.getText();
        
        MyTextField txtRol = new MyTextField();
        txtRol.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/rol.png")));
        txtRol.setHint("Rol");
        register.add(txtRol, "w 60%");
        
        String rolTexto = txtRol.getText();
        
        MyTextField txtUsuario = new MyTextField();
        txtUsuario.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUsuario.setHint("Usuario");
        register.add(txtUsuario, "w 60%");
        
        String usuarioTexto = txtUsuario.getText();
        
        MyPasswordField txtContrasena = new MyPasswordField();
        txtContrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtContrasena.setHint("Contraseña");
        register.add(txtContrasena, "w 60%");
        char[] passChar = txtContrasena.getPassword();
        
        String passTexto = new String(passChar);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(167, 132, 139));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Registrarse");
        register.add(cmd, "w 40%, h 40");
        
        //Espacio para llamar la logica
        
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Iniciar Sesión");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(167, 132, 139));
        login.add(label);
        
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setHint("Usuario");
        login.add(txtUser, "w 60%");
        
        MyTextField txtRol = new MyTextField();
        txtRol.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/rol.png")));
        txtRol.setHint("Rol");
        login.add(txtRol, "w 60%");
        
        MyPasswordField txtContrasena = new MyPasswordField();
        txtContrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtContrasena.setHint("Contraseña");
        login.add(txtContrasena, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(167, 132, 139));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Inciar Sesión");
        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
