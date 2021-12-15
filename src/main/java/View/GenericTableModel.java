/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Point;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jvmuc
 */
public abstract class GenericTableModel extends AbstractTableModel{
    protected ArrayList<Object> listaDados;
    protected String[] columns;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public GenericTableModel(String[] columns, List listaDados){
    
        this.columns = columns;
        this.listaDados = (ArrayList)listaDados;
        
    }
    
    @Override 
    public int getColumnCount(){
        return columns.length;
    }
    
    @Override 
    public int getRowCount(){
        return listaDados.size();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    
    //Métodos auxiliares
    public Object getItem(int linhaIndex){
        if(linhaIndex < 0)
            return null;
        else
            return listaDados.get(linhaIndex);
    }
    
    public void addItem(Object item){
        listaDados.add(item);
        int ultimoIndice = getColumnCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void removeItem(int itemIndex){
        listaDados.remove(itemIndex);
        fireTableRowsDeleted(itemIndex, itemIndex);
    }
    
    public void addListOfItems(List<Object> listaObjetos){
        this.clear();
        try{
            for(Object item : listaObjetos){
            listaDados.add(item);
        }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void clear(){
        listaDados.clear();
        fireTableDataChanged();
    }
    
    public boolean isEmpty(){
        return listaDados.isEmpty();
    }
    
    // Parte da View
    
    public void setColumnWidth(JTable myTable, int[] vWidth){
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int i = 0; i < vWidth.length; i++){
            TableColumn coluna = myTable.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(vWidth[i]);
        }
    }
    
    public void selectAndScroll(JTable myTable, int rowIndex){
        myTable.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
        scrollToVisible(myTable, rowIndex);
    }

    private void scrollToVisible(JTable myTable, int rowIndex) {
        scrollToVisible(myTable, rowIndex, 0); 
    }

    private void scrollToVisible(JTable myTable, int rowIndex, int columnIndex) {
        if(!(myTable.getParent() instanceof JViewport)){
            return;
        }
        this.setViewPortPosition((JViewport) myTable.getParent(), myTable.getCellRect(rowIndex, columnIndex, true));
    }

    private void setViewPortPosition(JViewport viewport, Rectangle position) {
        Point pt = viewport.getViewPosition();
        position.setLocation(position.x - pt.x, position.y - pt.y);
        viewport.scrollRectToVisible(position);
    }
    
    protected String printDate(Calendar data){
        return data == null ? "" : dateFormat.format(data.getTime());
    }
}
