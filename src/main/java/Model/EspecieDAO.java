package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EspecieDAO extends DAO {
    private static EspecieDAO instance;

    private EspecieDAO(){
        getConnection();
        createTable();
    }

    //Singleton
    public static EspecieDAO getInstance(){
        return ((instance == null)?instance = new EspecieDAO():instance);
    }

    //CRUD
    public Especie create(String nome){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("INSERT INTO especie (nome) VALUES (?)");
            statement.setString(1, nome);
            executeUpdate(statement);
        }catch(SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return retrieveById(lastID("especie", "id"));

    }

    //Método que irá buildar o que for trazido do DB
    public Especie buildObject(ResultSet resultSet){
        Especie especie = null;
        try{
            especie = new Especie(
                    resultSet.getInt("id"),
            resultSet.getString("nome")
            );
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return especie;
    }

    public List retrieve(String query){
        List<Especie> especies = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);

        try{
            while (resultSet.next()){
                especies.add(buildObject(resultSet));
            }
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return especies;
    }

    public Especie retrieveById(int id){
        List<Especie> especies = this.retrieve("SELECT * FROM especie WHERE id=" + id);
        return (especies.isEmpty()?null:especies.get(0));
    }

    public List retrieveAll(){
        return this.retrieve("SELECT * FROM especie");
    }

    public Especie retrieveLast(){
        List<Especie> especie = retrieve("SELECT * FROM especie WHERE id=" + lastID("especie", "id"));
        return especie.isEmpty()?null:especie.get(0);
    }

    public List<Especie> retrieveBySimilarName(String name){
        List<Especie> especies = retrieve("SELECT * FROM especie WHERE nome LIKE '%" + name + "%'");
        return especies;
    }

    //Update
    public void updateEspecie(Especie especie){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement(
                    "UPDATE especie SET nome=?"
            );
            statement.setString(1,especie.getNome());
            executeUpdate(statement);
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }

    //Delete
    public void deleteEspecie(int id){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("DELETE FROM especie WHERE id=" + id);
            executeUpdate(statement);
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }

}
