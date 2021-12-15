package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO(){
        getConnection();
        createTable();
    }

    //Singleton
    public static ConsultaDAO getInstance(){
        return instance==null?new ConsultaDAO():instance;
    }

    //CRUD
    public Consulta create(int id, Calendar date, String comentarios, int hora, int minutos, boolean acabou, int idTratamento, int idVeterinario, int idAnimal){
        try {
            PreparedStatement statement;
            statement = DAO.getConnection().prepareStatement(
                    "INSERT INTO consulta (data, comentarios, hora, minutos, id_tratamento, id_veterinario, id_animal, acabou)" +
                            "VALUES (?,?,?,?,?,?,?,?)"
            );
            statement.setString(1, dateFormat.format(date.getTime()));
            statement.setString(2, comentarios);
            statement.setInt(3, hora);
            statement.setInt(4, minutos);
            statement.setInt(5, idTratamento);
            statement.setInt(6, idVeterinario);
            statement.setInt(7, idAnimal);
            statement.setBoolean(8, acabou);
            executeUpdate(statement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return retrieveByLastId();
    }

    public Consulta buildObject(ResultSet resultSet){
        Consulta consulta = null;
        try {
            Calendar data = Calendar.getInstance();
            data.setTime(dateFormat.parse(resultSet.getString("data")));
            consulta = new Consulta(
                    resultSet.getInt("id"),
                    data,
                    resultSet.getString("comentarios"),
                    resultSet.getInt("hora"),
                    resultSet.getInt("minutos"),
                    resultSet.getBoolean("acabou"),
                    resultSet.getInt("id_tratamento"),
                    resultSet.getInt("id_veterinario"),
                    resultSet.getInt("id_animal")
            );
        } catch (ParseException|SQLException e){
            System.err.println(e.getMessage());
        }
        return consulta;
    }

    public List retrieve(String query){
        List<Consulta> consultas = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        try {
            while (resultSet.next()){
                consultas.add(buildObject(resultSet));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return consultas;
    }

    public Consulta retrieveById(int id){
        List<Consulta> consultas = new ArrayList<>();
        consultas = retrieve("SELECT * FROM consulta WHERE id=" + id);
        return consultas.isEmpty()?null:consultas.get(0);
    }

    public Consulta retrieveByLastId(){
        return retrieveById(lastID("consulta", "id"));
    }

    public List retrieveAll(){
        return retrieve("SELECT * FROM consulta");
    }

    public List retrieveByIdTratamento(int id){
        List<Consulta> consultas = new ArrayList<>();
        consultas = retrieve("SELECT * FROM consulta WHERE id_tratamento=" + id);
        return consultas;
    }

    public List retrieveByIdAnimal(int id){
        List<Consulta> consultas = new ArrayList<>();
        consultas = retrieve("SELECT * FROM consulta WHERE id=" + id);
        return consultas;
    }

    //Update
    public void updateConsulta(Consulta consulta){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, comentarios=?, hora=?, minutos=?, id_tratamento=?, id_veterinario=?, id_animal=?, acabou=?");
            preparedStatement.setString(1, dateFormat.format(consulta.getDate().getTime()));
            preparedStatement.setString(2, consulta.getComentarios());
            preparedStatement.setInt(3, consulta.getHora());
            preparedStatement.setInt(4, consulta.getMinutos());
            preparedStatement.setInt(5, consulta.getIdTratamento());
            preparedStatement.setInt(6, consulta.getIdVeterinario());
            preparedStatement.setInt(7, consulta.getIdAnimal());
            executeUpdate(preparedStatement);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    //Delete
    public void deleteConsulta(Consulta consulta){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DAO.getConnection().prepareStatement(
                    "DELETE FROM consulta WHERE id=" + consulta.getId()
            );
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
