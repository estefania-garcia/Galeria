package galeriacasasubasta;

import items.Button;
import items.Notification;
import items.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import galeria.modelo.compras.CentroOfertas;
import galeria.modelo.compras.Ofertas;
import galeria.modelo.controlador.Galeria;
import galeria.modelo.usuarios.ProcesoCompra;
import net.miginfocom.swing.MigLayout;

public class OperadorCajero extends javax.swing.JFrame {

    private JFrame frame = (JFrame) this;
    
    public OperadorCajero(String tipo, String usu, LoginMain principal, Galeria galeria) {
        
        initComponents();
        ProcesoCompra cajero = galeria.getCajero();
        CentroOfertas centroOfertas = galeria.getCentroOfertas();
        itemsMenu(tipo, cajero, centroOfertas);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        
        //Panel principal
        paneles.setOpaque(false);
        //Panel Menu Botones
        items.setOpaque(false);
        //Texto Header
        rol.setText(tipo);
        usuario.setText(usu);
        nombre.setVisible(true);
        inicioCrear.setVisible(false);
        tablas.setVisible(false);
        blogo.setVisible(true);
        bienvenida.setVisible(true);
        bienvenida.setText("<html>Bienvenido, " + tipo + " a nuestra" + " Galería y Casa de Subastas<br></html>");
    }
    
    private void itemsMenu(String tipo, ProcesoCompra cajero, CentroOfertas centroOfertas){
        
        if(tipo.equals("Cajero")){
            
            items.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]push"));
            Button cmd2 = new Button();
            cmd2.setBackground(new Color(72,125,171));
            cmd2.setForeground(new Color(250, 250, 250));
            cmd2.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd2.setText("Lista Cobranza");
            items.add(cmd2, "w 202, h 40");
            
            cmd2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    listaCobranza(cajero);
                }
            });
        }
        else if(tipo.equals("Operador")){
            
            items.setLayout(new MigLayout("wrap", "push[center]push", "push[]10[]10[]10[]10[]10[]10[]push"));
            Button cmd1 = new Button();
            cmd1.setBackground(new Color(72,125,171));
            cmd1.setForeground(new Color(250, 250, 250));
            cmd1.setFont(new Font("SansSerif", Font.BOLD, 15));
            cmd1.setText("Agregar Ofertas");
            items.add(cmd1, "w 202, h 40");
            
            cmd1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    agregarOfertas(centroOfertas);
                }
            });
        }
    }
    
    private void incializar(){
        
        blogo.setVisible(false);
        bienvenida.setVisible(false);
        tablas.setVisible(false);
        inicioCrear.setVisible(false);
        inicioCrear1.setVisible(false);
        scrollTabla.setVisible(false);
        scrollTabla.setVerticalScrollBar(new ScrollBar());
        scrollTabla.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrollTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
    
    private void listaCobranza(ProcesoCompra cajero){
        
        incializar();
        inicioCrear1.setVisible(true);
        tablas.setVisible(true);
        opcion2.setText("Estas son las compras aprobadas");
        descripcion.setText("Todas las ofertas disponibles");
        scrollTabla.setVisible(true);
        //Tabla Clumnas
        tabla.removeRowAll();
        tabla.removeColumnAll();
        tabla.addColumn("Pieza");
        tabla.addColumn("Propietario");
        tabla.addColumn("Fecha Compra");
        tabla.addColumn("Monto");
        tabla.addColumn("Índice Aprobación");
        List<Ofertas> ofertas = cajero.getlistaOfertas();
        for(Ofertas consulta : ofertas){
            tabla.addRow(new Object []{consulta.getPiezas().getTitulo(), consulta.getComprador().getInversor().getUsuario(), consulta.getPiezas().getFechaVendida(), consulta.getMonto()});
        }
    }
    
    private void agregarOfertas(CentroOfertas centroOfertas){
        
        incializar();
        inicioCrear.setVisible(true);
        opcion1.setText("Agregar ofertas verificadas por el sistema");
        botonAccion1.setText("Agregar");
        botonAccion1.setIcon(new ImageIcon(getClass().getResource("/icon/aprobar1.png")));
        botonAccion1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	centroOfertas.agregarOfertasSubasta();
                Notification alertUsu = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Se ha ejecutado la acción");
                alertUsu.showNotification();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneles = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        items = new javax.swing.JPanel();
        logoMenu1 = new galeriacasasubasta.LogoMenu();
        header = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        rol = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        banner = new javax.swing.JPanel();
        blogo = new javax.swing.JLabel();
        bienvenida = new javax.swing.JLabel();
        tablas = new javax.swing.JPanel();
        inicioCrear1 = new javax.swing.JPanel();
        opcion2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new galeriacasasubasta.Tabla();
        inicioCrear = new javax.swing.JPanel();
        opcion1 = new javax.swing.JLabel();
        botonAccion1 = new items.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        paneles.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(53, 92, 125));
        menu.setPreferredSize(new java.awt.Dimension(237, 628));

        javax.swing.GroupLayout itemsLayout = new javax.swing.GroupLayout(items);
        items.setLayout(itemsLayout);
        itemsLayout.setHorizontalGroup(
            itemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        itemsLayout.setVerticalGroup(
            itemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(items, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        header.setBackground(new java.awt.Color(122, 40, 124));

        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/profile.png"))); // NOI18N

        rol.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        rol.setForeground(new java.awt.Color(255, 255, 255));
        rol.setText("Message");

        usuario.setForeground(new java.awt.Color(220, 220, 220));
        usuario.setText("Message Text");

        nombre.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setText("Galeria y Casa de Subastas");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rol, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usuario, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(img)
                .addGap(19, 19, 19))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuario))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(img))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(nombre)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        banner.setBackground(new java.awt.Color(255, 255, 255));

        blogo.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        blogo.setForeground(new java.awt.Color(122, 40, 124));
        blogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bienvenido.png"))); // NOI18N

        bienvenida.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        bienvenida.setForeground(new java.awt.Color(122, 40, 124));
        bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bienvenida.setText("Bienvenido\n");

        tablas.setBackground(new java.awt.Color(255, 255, 255));

        inicioCrear1.setBackground(new java.awt.Color(248, 247, 247));
        inicioCrear1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        inicioCrear1.setForeground(new java.awt.Color(246, 239, 239));

        opcion2.setBackground(new java.awt.Color(9, 13, 118));
        opcion2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        opcion2.setForeground(new java.awt.Color(9, 13, 118));
        opcion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opcion2.setText("Titulo\n");

        descripcion.setBackground(new java.awt.Color(83, 103, 169));
        descripcion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        descripcion.setForeground(new java.awt.Color(83, 103, 169));
        descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descripcion.setText("Descripcion");

        javax.swing.GroupLayout inicioCrear1Layout = new javax.swing.GroupLayout(inicioCrear1);
        inicioCrear1.setLayout(inicioCrear1Layout);
        inicioCrear1Layout.setHorizontalGroup(
            inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrear1Layout.createSequentialGroup()
                .addGroup(inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inicioCrear1Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inicioCrear1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(opcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        inicioCrear1Layout.setVerticalGroup(
            inicioCrear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrear1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(opcion2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descripcion)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        scrollTabla.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTabla.setViewportView(tabla);

        javax.swing.GroupLayout tablasLayout = new javax.swing.GroupLayout(tablas);
        tablas.setLayout(tablasLayout);
        tablasLayout.setHorizontalGroup(
            tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(inicioCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        tablasLayout.setVerticalGroup(
            tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(inicioCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(495, Short.MAX_VALUE)))
            .addGroup(tablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablasLayout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );

        inicioCrear.setBackground(new java.awt.Color(238, 238, 238));
        inicioCrear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        inicioCrear.setForeground(new java.awt.Color(246, 239, 239));

        opcion1.setBackground(new java.awt.Color(9, 13, 118));
        opcion1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        opcion1.setForeground(new java.awt.Color(9, 13, 118));
        opcion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opcion1.setText("Titulo\n");

        botonAccion1.setText("Eliminar");
        botonAccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAccion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioCrearLayout = new javax.swing.GroupLayout(inicioCrear);
        inicioCrear.setLayout(inicioCrearLayout);
        inicioCrearLayout.setHorizontalGroup(
            inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrearLayout.createSequentialGroup()
                .addGroup(inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inicioCrearLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(botonAccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inicioCrearLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        inicioCrearLayout.setVerticalGroup(
            inicioCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioCrearLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(botonAccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bannerLayout = new javax.swing.GroupLayout(banner);
        banner.setLayout(bannerLayout);
        bannerLayout.setHorizontalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(inicioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerLayout.createSequentialGroup()
                    .addContainerGap(179, Short.MAX_VALUE)
                    .addComponent(blogo, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        bannerLayout.setVerticalGroup(
            bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(inicioCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(blogo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
            .addGroup(bannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bannerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelesLayout = new javax.swing.GroupLayout(paneles);
        paneles.setLayout(panelesLayout);
        panelesLayout.setHorizontalGroup(
            panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelesLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelesLayout.setVerticalGroup(
            panelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelesLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAccion1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banner;
    private javax.swing.JLabel bienvenida;
    private javax.swing.JLabel blogo;
    private items.Button botonAccion1;
    private javax.swing.JLabel descripcion;
    private javax.swing.JPanel header;
    private javax.swing.JLabel img;
    private javax.swing.JPanel inicioCrear;
    private javax.swing.JPanel inicioCrear1;
    private javax.swing.JPanel items;
    private galeriacasasubasta.LogoMenu logoMenu1;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel opcion1;
    private javax.swing.JLabel opcion2;
    private javax.swing.JPanel paneles;
    private javax.swing.JLabel rol;
    private javax.swing.JScrollPane scrollTabla;
    private galeriacasasubasta.Tabla tabla;
    private javax.swing.JPanel tablas;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
