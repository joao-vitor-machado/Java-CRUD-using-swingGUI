/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Animal;
import Model.AnimalDAO;
import Model.EspecieDAO;
import java.util.List;

/**
 *
 * @author jvmuc
 */
public class AnimalTableModel extends GenericTableModel {

    public AnimalTableModel(List listaDados) {
        super(new String[] {"Nome", "AnoNasc", "Sexo", "Especie"}, listaDados);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("column out of limits");
        }
    }
    
    @Override 
    public Object getValueAt(int rowIndex, int columnIndex){
        Animal animal = (Animal)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return animal.getNome();
            case 1:
                return animal.getAnoNasc();
            case 2:
                return animal.isSexo() == true?"Macho":"Fêmea";
            case 3: 
                return EspecieDAO.getInstance().retrieveById(animal.getIdEspecie()).getNome();
            default:
                throw new IndexOutOfBoundsException("Column Index out of range");
        }
    }
    
    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex){
        Animal animal = (Animal)listaDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                animal.setNome((String)valor);
                break;
            case 1:
                animal.setAnoNasc((Integer)valor);
                break;
            case 2:
                animal.setSexo((Boolean)valor);
                break;
            case 3:
                animal.setIdEspecie((Integer)valor);
                break;
            default:
                throw new IndexOutOfBoundsException("Column index out range");
        }
        
        AnimalDAO.getInstance().updateAnimal(animal);
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return true;
    }
    
}
