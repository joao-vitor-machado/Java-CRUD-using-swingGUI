/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Consulta;
import Model.ConsultaDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jvmuc
 */
public class ConsultasTableModel extends GenericTableModel{
    public ConsultasTableModel(List listaDados){
        super(new String[] {"Dia", "Hora", "Minutos"} ,listaDados);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0:
                return Calendar.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("index out of range");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Consulta consulta = (Consulta)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return this.printDate(consulta.getDate());
            case 1:
                return consulta.getHora();
            case 2:
                return consulta.getMinutos();
            default:
                throw new IndexOutOfBoundsException("index out of range");
        }
    }
    
    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex){
        Consulta consulta = (Consulta)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                consulta.setDate((Calendar)valor);
                break;
            case 1:
                consulta.setHora((Integer)valor);
                break;
            case 2:
                consulta.setMinutos((Integer)valor);
                break;
            default:
                throw new IndexOutOfBoundsException("index out of range");
        }
        
        ConsultaDAO.getInstance().updateConsulta(consulta);
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
}
