package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VeterinarioDAO extends DAO {
    private static VeterinarioDAO instance;

    private VeterinarioDAO(){
        getConnection();
        createTable();
    }

    public static VeterinarioDAO getInstance(){
        return instance==null?instance = new VeterinarioDAO():instance;
    }

    public Veterinario create(String nome, String endereco, String telefone, String cep, String cpf, String email){
       try {
           PreparedStatement statement;
           statement = DAO.getConnection().prepareStatement("INSERT INTO veterinario (nome, endereco, telefone, cep, cpf, email)" +
                   "VALUES (?, ?, ?, ?, ?, ?)");
           statement.setString(1, nome);
           statement.setString(2, endereco);
           statement.setString(3, telefone);
           statement.setString(4, cep);
           statement.setString(5, cpf);
           statement.setString(6, email);
           executeUpdate(statement);
       }catch (SQLException e){
           Logger.getLogger(e.getMessage());
       }
        return retrieveLast();
    }

    public Veterinario buildObject(ResultSet resultSet){
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario (
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("telefone"),
                    resultSet.getString("cep"),
                    resultSet.getString("cpf"),
                    resultSet.getString("email")
            );
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return veterinario;
    }

    public List retrieve(String query){
        List<Veterinario> veterinarios = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        try {
            while (resultSet.next()){
                veterinarios.add(buildObject(resultSet));
            }
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return veterinarios;
    }

    public Veterinario retrieveById(int id){
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM veterinario WHERE id=" + id);
        return veterinarios.isEmpty()?null:veterinarios.get(0);
    }

    public Veterinario retrieveLast(){
        return retrieveById(lastID("veterinario", "id"));
    }

    public List retrieveAll(){
        return retrieve("SELECT * FROM veterinario");
    }

    public List retrieveBySimilarName(String name){
        return retrieve("SELECT * FROM veterinario WHERE nome LIKE '%" + name + "%'");
    }

    public void updateVeterinario(Veterinario veterinario){
        try {
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("UPDATE cliente SET nome?, endereco=?, telefone=?, cep=?, cpf=?, email=?");
            statement.setString(1, veterinario.getNome());
            statement.setString(2, veterinario.getEndereco());
            statement.setString(3, veterinario.getTelefone());
            statement.setString(4, veterinario.getCep());
            statement.setString(5, veterinario.getCpf());
            statement.setString(6, veterinario.getEmail());
            executeUpdate(statement);
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }

    public void deleteVeterinario(Veterinario veterinario){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("DELETE FROM veterinario WHERE id=" + veterinario.getId());
            executeUpdate(statement);
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }
}
