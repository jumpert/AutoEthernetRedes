/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jumpert.autoethernet;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jpere
 */
public class ColorCelda extends DefaultTableCellRenderer {
    
    @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
            
            JLabel label = new JLabel();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            //setHorizontalAlignment(SwingConstants.CENTER);
            
            table.getColumnModel().getColumn(col).setCellRenderer(this);
            if (model.getValueAt(row, 4).toString().endsWith("Alert")) {
                setBackground(new Color(180,30, 0));
                setForeground(new Color(240,240,240));
            } else {
                Color nuevoColor = new Color(102,255,51);
                setBackground(nuevoColor);
                setForeground(new Color(0,0,101));
            }
            
            
        return this;
        
    }
    
}
