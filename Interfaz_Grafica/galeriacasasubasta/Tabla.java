package galeriacasasubasta;

import items.LabelTabla;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JTable{
    
    public Tabla(){
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                LabelTabla titulo = new LabelTabla(value + "");
                titulo.setHorizontalAlignment(JLabel.CENTER);
                return titulo;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder); 
                if (selected) {
                    com.setForeground(new Color(15, 89, 140));
                } else {
                    com.setForeground(new Color(102, 102, 102));
                }
                return com;
            }
        });
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    
    public void removeRow(int row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.removeRow(row);
        
    }
    
    public void addColumn(String nombre) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addColumn(nombre);
    }
    
    public void removeColumnAll() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setColumnCount(0);
    }
    
    public void removeRowAll() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0);
    }
}
