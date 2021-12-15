package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO extends DAO {
    private static ClienteDAO instance;

    private ClienteDAO(){
        getConnection();
        createTable();
    }

    // Singleton
    public static ClienteDAO getInstance(){
        return (instance == null?(instance = new ClienteDAO()):instance);
    }

    // CRUD
    public Cliente create(String nome, String endereco, String telefone, String cep, String cpf, String email){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("INSERT INTO cliente (nome, endereco, telefone, cep, cpf, email) VALUES (?,?,?,?,?,?)");
            statement.setString(1, nome);
            statement.setString(2, endereco);
            statement.setString(3, telefone);
            statement.setString(4, cep);
            statement.setString(5, cpf);
            statement.setString(6, email);
            executeUpdate(statement);
        }catch(SQLException e){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return this.retrieveById((lastID("cliente", "id")));
    }

    // Método que transforma os dados sql em um objeto do tipo cliente
    public Cliente buildObject(ResultSet resultSet){
        Cliente cliente = null;
        try{
            cliente = new Cliente(resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("telefone"),
                    resultSet.getString("cep"),
                    resultSet.getString("cpf"),
                    resultSet.getString("email"));

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return cliente;
    }

    //Retriever genérico
    public List retrieve(String query){
        List<Cliente> clientes = new ArrayList<Cliente>();
        ResultSet resultSet = getResultSet(query);
        try{
            while (resultSet.next()){
                clientes.add(buildObject(resultSet));
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return clientes;
    }

    // Retriever por id
    public Cliente retrieveById(int id){
        List<Cliente> clientes = this.retrieve("SELECT * FROM cliente WHERE id = " + id);
        return (clientes.isEmpty()?null:clientes.get(0));
    }

    // Retriever de todos
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM cliente");
    }

    // Retriever do último da tabela
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM CLIENTE WHERE id = " + lastID("cliente", "id"));
    }

    // Retriever por nome similar ao enviado
    public List retrieveBySimilarName(String name){
        return retrieve("SELECT * FROM cliente WHERE nome LIKE '%" + name + "%'");
    }

    // Update
    public void updateCliente(Cliente cliente){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement(
                    "UPDATE cliente SET nome=?, endereco=?, telefone=?, cep=?, cpf=?, email=?"
            );
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEndereco());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getCep());
            statement.setString(5, cliente.getCpf());
            statement.setString(6, cliente.getEmail());
            executeUpdate(statement);

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    // Delete
    public void delete(Cliente cliente){
        PreparedStatement statement;
        try{
            statement = DAO.getConnection().prepareStatement("DELETE FROM cliente WHERE id = ?");
            statement.setInt(1, cliente.getId());
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
