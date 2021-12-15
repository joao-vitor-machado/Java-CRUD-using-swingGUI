/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cliente;
import Model.ClienteDAO;
import java.util.List;

/**
 *
 * @author jvmuc
 */
public class ClienteTableModel extends GenericTableModel{
    
    public ClienteTableModel(List listaDados){
        super(new String[] {"Nome", "Endereço", "Telefone", "CEP", "CPF", "E-mail"}, listaDados);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0: 
                return String.class;
            case 1: 
                return String.class;                
            case 2: 
                return String.class;
            case 3: 
                return String.class;
            case 4: 
                return String.class;
            case 5: 
                return String.class;
            default:
                throw new IndexOutOfBoundsException("Column index out of range");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Cliente cliente = (Cliente) listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return cliente.getNome();
            case 1:
                return cliente.getEndereco();
            case 2:
                return cliente.getTelefone();
            case 3:
                return cliente.getCep();
            case 4:
                return cliente.getCpf();
            case 5:
                return cliente.getEmail();
            default:
                throw new IndexOutOfBoundsException("Column index out of range");
        }
    }
    
    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex){
        Cliente cliente = (Cliente) listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                cliente.setNome((String)valor);
                break;
            case 1:
                cliente.setEndereco((String)valor);
                break;
            case 2:
                cliente.setTelefone((String)valor);
                break;
            case 3:
                cliente.setCep((String)valor);
                break;
            case 4:
                cliente.setCpf((String)valor);
                break;
            case 5:
                cliente.setEmail((String)valor);
                break;
            default:
                throw new IndexOutOfBoundsException("Column Index out of range");
        }
        
        ClienteDAO.getInstance().updateCliente(cliente);
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
    
    
    
    
}
