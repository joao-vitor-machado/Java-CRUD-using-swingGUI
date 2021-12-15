/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Veterinario;
import Model.VeterinarioDAO;
import java.util.List;

/**
 *
 * @author jvmuc
 */
public class VeterinarioTableModel extends GenericTableModel {

    public VeterinarioTableModel(List listaDados) {
        super(new String[]{"Nome", "Endereço", "Telefone", "CEP", "CPF", "E-mail"}, listaDados);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
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
                throw new IndexOutOfBoundsException("Index out of limits");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) listaDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veterinario.getNome();
            case 1:
                return veterinario.getEndereco();
            case 2:
                return veterinario.getTelefone();
            case 3:
                return veterinario.getCep();
            case 4:
                return veterinario.getCpf();
            case 5:
                return veterinario.getEmail();
            default:
                throw new IndexOutOfBoundsException("Index out of limits");
        }
    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) listaDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                veterinario.setNome((String) valor);
                break;
            case 1:
                veterinario.setEndereco((String) valor);
                break;
            case 2:
                veterinario.setTelefone((String) valor);
                break;
            case 3:
                veterinario.setCep((String) valor);
                break;
            case 4:
                veterinario.setCpf((String) valor);
                break;
            case 5:
                veterinario.setEmail((String) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("Index out of limits");
        }

        VeterinarioDAO.getInstance().updateVeterinario(veterinario);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
