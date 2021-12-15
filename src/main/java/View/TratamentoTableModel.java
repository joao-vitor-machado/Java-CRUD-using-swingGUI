/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Tratamento;
import Model.TratamentoDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jvmuc
 */
public class TratamentoTableModel extends GenericTableModel{
    
    public TratamentoTableModel(List listaDados){
        super(new String[] {"Nome", "Data de Início", "Dada de Fim", "Conclusão"}, listaDados);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Calendar.class;
            case 2:
                return Calendar.class;
            case 3:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("index out of range");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Tratamento tratamento = (Tratamento)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return tratamento.getNome();
            case 1:
                return this.printDate(tratamento.getDataInicio());
            case 2:
                return this.printDate(tratamento.getDataFim());
            case 3: 
                return tratamento.isAcabou();
            default:
                throw new IndexOutOfBoundsException("index out of range");
        }
    }
    
    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex){
        Tratamento tratamento = (Tratamento)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                tratamento.setNome((String)valor);
                break;
            case 1:
                tratamento.setDataInicio((Calendar)valor);
                break;
            case 2:
                tratamento.setDataFim((Calendar)valor);
                break;
            case 3: 
                tratamento.setAcabou(true);
                break;
            default:
                throw new IndexOutOfBoundsException("index out of range 1");
        }
        
        TratamentoDAO.getInstance().updateTratamento(tratamento);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 3){
            return false;
        }else{
            return true;
        }
    }
    
}
