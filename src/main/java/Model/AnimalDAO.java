package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends DAO {
    private static AnimalDAO instance;

    private AnimalDAO(){
        getConnection();
        createTable();
    }

    // Criando meu singleton
    public static AnimalDAO getInstance(){
        return (instance == null)?instance = new AnimalDAO():instance;
    }

    //CRUD
    public Animal create(String nome, int anoNasc, boolean sexo, int idEspecie, Cliente cliente){
    try{
        PreparedStatement statement;
        statement = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, anoNasc, sexo, id_especie, id_cliente) VALUES (?,?,?,?,?)");
        statement.setString(1, nome);
        statement.setInt(2, anoNasc);
        statement.setBoolean(3, sexo);
        statement.setInt(4, idEspecie);
        statement.setInt(5, cliente.getId());
        executeUpdate(statement);
    }catch (SQLException e){
        System.err.println(e.getMessage());
    }
    return this.retrieveById(lastID("animal", "id"));
    }

    // Método que irá montar meu animal a partir do result set
    public Animal buildObject(ResultSet resultSet){
        Animal animal = null;
        try{
            animal = new Animal(resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getInt("anoNasc"),
                    resultSet.getBoolean("sexo"),
                    resultSet.getInt("id_especie"),
                    resultSet.getInt("id_cliente"));
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return animal;
    }

    // retriever genérico
    public List retrieve (String query){
        List<Animal> animais = new ArrayList<Animal>();
        ResultSet resultSet = getResultSet(query);

        try{
            while (resultSet.next()){
                animais.add(buildObject(resultSet));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return animais;
    }

    // retriever por ID
    public Animal retrieveById(int id){
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id =" + id);
        return (animais.isEmpty()?null:animais.get(0));
    }

    // retriever de todos
    public List retrieveAll (){
        return this.retrieve("SELECT * FROM animal");
    }

    // retriever do último animal registrado
    public Animal retrieveLast(){
        return this.retrieveById(lastID("animal", "id"));
    }

    // retriever para nome similar ao enviado
    public List retrieveSimilar(String nome){
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }

    // retriever por id do cliente
    public List retrieveByIdCliente(int idCliente){
        List<Animal> animais = new ArrayList<>();
        animais = this.retrieve("SELECT * FROM animal WHERE id_cliente = " + idCliente);
        return (animais.isEmpty()?new ArrayList():animais);
    }

    // Update
    public void updateAnimal(Animal animal){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, sexo=?, id_especie=?, id_cliente=? WHERE id=" + animal.getId());
            statement.setString(1, animal.getNome());
            statement.setInt(2, animal.getAnoNasc());
            statement.setBoolean(3, animal.isSexo());
            statement.setInt(4, animal.getIdEspecie());
            statement.setInt(5, animal.getIdCliente());
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    // Delete
    public void deleteAnimal(Animal animal){
        PreparedStatement statement;
        try{
            statement = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            statement.setInt(1, animal.getId());
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
