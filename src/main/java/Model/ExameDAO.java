package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO extends DAO {
    private static ExameDAO instance;

    private ExameDAO(){
        getConnection();
        createTable();
    }

    public static ExameDAO getInstance(){
        return instance==null?new ExameDAO():instance;
    }

    public Exame create(String descricao, int id){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAO.getConnection().prepareStatement(
                    "INSERT INTO exame (descricao, id_consulta) VALUES (?,?)"
            );
            preparedStatement.setString(1, descricao);
            preparedStatement.setInt(2, id);
            executeUpdate(preparedStatement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return retrieveByLastId();
    }

    public Exame buildObject(ResultSet resultSet){
        Exame exame = null;
        try {
            exame = new Exame(
                    resultSet.getInt("id"),
                    resultSet.getString("descricao"),
                    resultSet.getInt("id_consulta")
            );
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return exame;
    }

    public List retriever(String query){
        List<Exame> exames = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        try{
            while (resultSet.next()){
                exames.add(buildObject(resultSet));
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return exames;
    }

    public Exame retrieveById(int id){
        List<Exame> exames = new ArrayList<>();
        exames = retriever("SELECT * FROM exame WHERE id=" + id);
        return exames.isEmpty()?null:exames.get(0);
    }

    public List retrieveAll(){
        return retriever("SELECT * FROM exame");
    }

    public Exame retrieveByLastId(){
        return retrieveById(lastID("exame", "id"));
    }

    public List retrieveByIdConsulta(int id){
        List<Exame> exames = new ArrayList<>();
        exames = retriever("SELECT * FROM exame WHERE id_consulta=" + id);
        return exames;
    }

    //Update
    public void updateExame(Exame exame){
        PreparedStatement statement;
        try {
            statement = DAO.getConnection().prepareStatement(
                    "UPDATE exame SET descricao=?, id_consulta=?"
            );
            statement.setString(1, exame.getDescricao());
            statement.setInt(2, exame.getIdConsulta());
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    //Delete
    public void delete(Exame exame){
        PreparedStatement statement;
        try {
            statement = DAO.getConnection().prepareStatement("DELETE FROM exame WHERE id=" + exame.getId());
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
