package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO(){
        getConnection();
        createTable();
    }

    public static TratamentoDAO getInstance(){
        return instance==null?new TratamentoDAO():instance;
    }

    //CRUD
    public Tratamento create(int id, String nome, Calendar dataInicio, Calendar dataFim, int idAnimal){
        try{
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement(
                    "INSERT INTO tratamento (nome, dataInicio, dataFim, id_animal, acabou) VALUES (?,?,?,?,?)"
            );
            statement.setString(1, nome);
            statement.setString(2, dateFormat.format(dataInicio.getTime()));
            statement.setString(3, dateFormat.format(dataFim.getTime()));
            statement.setInt(4, idAnimal);
            statement.setBoolean(5, false);
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println("Erro em create: " + e.getMessage());
        }
        return retrieveLast();
    }

    public Tratamento buildObject(ResultSet resultSet){
        Tratamento tratamento = null;
        try {
            Calendar dataInicio = Calendar.getInstance();
            dataInicio.setTime(dateFormat.parse(resultSet.getString("dataInicio")));
            Calendar dataFim = Calendar.getInstance();
            dataFim.setTime(dateFormat.parse(resultSet.getString("dataFim")));
            tratamento = new Tratamento(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    dataInicio,
                    dataFim,
                    resultSet.getInt("id_animal"),
                    resultSet.getBoolean("acabou")
            );

        }catch (ParseException|SQLException e){
            System.err.println("Erro em buildObject: " + e.getMessage());
        }
        return tratamento;
    }

    public List retriever(String query){

        List<Tratamento> tratamentos = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        try {
            while (resultSet.next()){
                tratamentos.add(buildObject(resultSet));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return tratamentos;
    }

    public Tratamento retrieveById(int id){
        List<Tratamento> tratamentos = new ArrayList<>();
        tratamentos = retriever("SELECT * FROM tratamento WHERE id=" + id);
        return tratamentos.isEmpty()?null:tratamentos.get(0);
    }
    
    public List retrieveByIdAnimal(int idAnimal){
        List<Tratamento> tratamentos = new ArrayList<>();
        tratamentos = retriever("SELECT * FROM tratamento WHERE id_animal=" + idAnimal);
        return tratamentos;
    }

    public List retrieveAll(){
        return retriever("SELECT * FROM tratamento");
    }

    public Tratamento retrieveLast(){
        return retrieveById(lastID("tratamento", "id"));
    }

    public List retrieveBySimilarName(String name){
        return retriever("SELECT * FROM tratamento WHERE nome LIKE '%" + name + "%'");
    }
    
    public List retrieveByNotConcluido(){
        return retriever("SELECT * FROM tratamento WHERE acabou=0");
    }
    public List retrieveByDate(String data){
        return retriever("SELECT * FROM tratamento WHERE dataInicio='" + data + "'");
    }

    //Update
    public void updateTratamento(Tratamento tratamento){
        try {
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("UPDATE tratamento SET nome=?, dataInicio=?, dataFim=?, id_animal=?, acabou=? WHERE id=" + tratamento.getId());
            statement.setString(1, tratamento.getNome());
            statement.setString(2, dateFormat.format(tratamento.getDataInicio().getTime()));
            statement.setString(3, dateFormat.format(tratamento.getDataFim().getTime()));
            statement.setInt(4, tratamento.getIdAnimal());
            statement.setBoolean(5, tratamento.isAcabou());
            executeUpdate(statement);
        } catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }

    //Delete
    public void deleteTratamento(Tratamento tratamento){
        try {
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id=" + tratamento.getId());
            executeUpdate(statement);
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
    }
}
