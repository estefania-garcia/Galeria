package galeriacasasubasta;

import java.awt.Color;
import java.util.Random;

import galeria.modelo.controlador.Galeria;

public class GraficoVentas extends javax.swing.JFrame {

    public GraficoVentas(LoginMain inicio, Galeria galeria) {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 255, 255));
        
        Random random = new Random();
        for(String llave : galeria.getInventario().getFechasMapa().keySet()) {
        	int valor = galeria.getInventario().getFechasMapa().get(llave);
        	grafico1.addItem(new ModeloGrafico(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)), llave, valor));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modeloGrafico1 = new galeriacasasubasta.ModeloGrafico();
        grafico1 = new galeriacasasubasta.Grafico();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        grafico1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grafico1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grafico1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private galeriacasasubasta.Grafico grafico1;
    private galeriacasasubasta.ModeloGrafico modeloGrafico1;
    // End of variables declaration//GEN-END:variables
}
