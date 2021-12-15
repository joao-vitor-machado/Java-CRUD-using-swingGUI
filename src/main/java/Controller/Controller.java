/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Animal;
import Model.AnimalDAO;
import View.GenericTableModel;
import javax.swing.JTable;
import Model.Cliente;
import Model.ClienteDAO;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.Especie;
import Model.EspecieDAO;
import Model.Exame;
import Model.ExameDAO;
import Model.Tratamento;
import Model.TratamentoDAO;
import Model.Veterinario;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jvmuc
 */
public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Animal animalSelecionado = null;
    private static Especie especieAnimalSelecionado = null;
    private static Veterinario veterinarioSelecionado = null;
    private static Tratamento tratamentoSelecionado = null;
    private static Consulta consultaSelecionada = null;
    
    private static JLabel clienteSelecionadoLabel = null;
    private static JLabel animalSelecionadoLabel = null;
    private static JLabel especieAnimalSelecionadoLabel = null;
    
    public static void setTextFields(JLabel cliente, JLabel animal, JLabel especie){
        clienteSelecionadoLabel = cliente;
        animalSelecionadoLabel = animal;
        especieAnimalSelecionadoLabel = especie;
    }
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }
    
    public static Animal getAnimalSelecionado(){
        return animalSelecionado;
    }
    
    public static Tratamento getTratamentoSelecionado(){
        return tratamentoSelecionado;
    }
    
    public static Consulta getConsultaSelecionada(){
        return consultaSelecionada;
    }
    
    public static Veterinario getVeterinarioSelecionado(){
        return veterinarioSelecionado;
    }
    
    public static void setSelected(Object selected){
        if(selected instanceof Cliente){
            clienteSelecionado = (Cliente)selected;
            clienteSelecionadoLabel.setText(clienteSelecionado.getNome());
            animalSelecionadoLabel.setText("");
            especieAnimalSelecionadoLabel.setText("");
            animalSelecionado = null;
            especieAnimalSelecionado = null;
            
        }else if(selected instanceof Animal){
            animalSelecionado = (Animal)selected;
            animalSelecionadoLabel.setText(animalSelecionado.getNome());
            especieAnimalSelecionado = EspecieDAO.getInstance().retrieveById(animalSelecionado.getIdEspecie());
            especieAnimalSelecionadoLabel.setText(especieAnimalSelecionado.getNome());

        }else if(selected instanceof Veterinario){
            veterinarioSelecionado = (Veterinario)selected;
        }else if(selected instanceof Tratamento){
            tratamentoSelecionado = (Tratamento)selected;
        }else if(selected instanceof Consulta){
            consultaSelecionada = (Consulta)selected;
        }

    }
    
    public static void apagarAnimal(Animal animal){
        
        List<Tratamento> tratamentos = TratamentoDAO.getInstance().retrieveByIdAnimal(animal.getId());
        List<Consulta> consultas = ConsultaDAO.getInstance().retrieveByIdAnimal(animal.getId());
        List<Exame> exames;
        
        for(Consulta consulta : consultas){
            exames = ExameDAO.getInstance().retrieveByIdConsulta(consulta.getId());
            for(Exame exame : exames){
                ExameDAO.getInstance().delete(exame);
            }
            ConsultaDAO.getInstance().deleteConsulta(consulta);
        }
        
        for(Tratamento tratamento : tratamentos){
            TratamentoDAO.getInstance().deleteTratamento(tratamento);
        }
        
        AnimalDAO.getInstance().deleteAnimal(animal);
    }
    
    public static void apagaCliente(Cliente cliente){
        List<Animal> animais = AnimalDAO.getInstance().retrieveByIdCliente(cliente.getId());
        for(Animal animal : animais){
            Controller.apagarAnimal(animal);
        }
        
        ClienteDAO.getInstance().delete(cliente);
    }
    
}
