/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jumpert.autoethernet;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jpere
 */
public class ColorCelda extends JTable {
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        Component componente = super.prepareRenderer(renderer, rowIndex, columnIndex);
        if (getValueAt(rowIndex, columnIndex).getClass().equals(String.class)){
            String compare = this.getValueAt(rowIndex, columnIndex).toString();
            if (compare == "Alert"){
                componente.setBackground(Color.orange);
            }
        } else {
            Color nuevoColor = new Color(204,255,229);
            componente.setBackground(nuevoColor);
        }
        return componente;
    }
    
}
