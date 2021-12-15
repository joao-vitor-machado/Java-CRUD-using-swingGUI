package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {

    public static final String dataBaseURL = "jdbc:sqlite:clinicaVeterinaria2.db";
    private static Connection connection;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Conectando a aplicação ao SQLite
    public static Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(dataBaseURL);
                if (connection != null){
                    DatabaseMetaData metaData = connection.getMetaData();
                }
            }catch (SQLException e){
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return connection;
    }

    // Criando o método que irá retornar as linhas com os resultados das queries
    protected ResultSet getResultSet(String query){

        Statement statement;
        ResultSet resultSet = null;

        try{
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }

        return resultSet;
    }

    // Método generalizado para execução de updates dos dados no banco
    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    // Função que irá devolver o último ID da tabela
    protected int lastID (String tableName, String primaryKey){

        Statement statement;
        int lastId = -1;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if(resultSet.next()){
                lastId = resultSet.getInt("id");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return lastId;
    }

    public static void terminar(){
        try{
            DAO.getConnection().close();
        }catch (SQLException e){
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Criando as tabelas no SQLite
    protected final boolean createTable(){
        try{
            PreparedStatement statement;
            // Tabela clientes
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente(\n" +
                    "id INTEGER PRIMARY KEY,  \n" +
                    "nome VARCHAR, \n" +
                    "endereco VARCHAR, \n" +
                    "telefone VARCHAR, \n" +
                    "cep VARCHAR, \n" +
                    "cpf VARCHAR, \n" +
                    "email VARCHAR)\n");
            executeUpdate(statement);
            // Tabela especies
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie(\n" +
                    "id INTEGER PRIMARY KEY, \n" +
                    "nome VARCHAR \n" +
                    ")");
            executeUpdate(statement);
            // Tabela animal
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal(\n" +
                    "id INTEGER PRIMARY KEY, \n" +
                    "nome VARCHAR, \n" +
                    "anoNasc INTEGER, \n" +
                    "sexo BIT, \n" +
                    "id_especie INTEGER, \n" +
                    "id_cliente INTEGER, \n" +
                    "FOREIGN KEY (id_especie) REFERENCES especie(id), \n" +
                    "FOREIGN KEY (id_cliente) REFERENCES cliente(id) \n" +
                    ")");
            executeUpdate(statement);
            // Tabela veterinario
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS veterinario(\n" +
                    "id INTEGER PRIMARY KEY,  \n" +
                    "nome VARCHAR, \n" +
                    "endereco VARCHAR, \n" +
                    "telefone VARCHAR, \n" +
                    "cep VARCHAR, \n" +
                    "cpf VARCHAR, \n" +
                    "email VARCHAR)");
            executeUpdate(statement);
            // Tabela tratamento
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tratamento( \n" +
                    "id INTEGER PRIMARY KEY, \n" +
                    "nome VARCHAR, \n" +
                    "dataInicio TEXT, \n" +
                    "dataFim TEXT, \n" +
                    "id_animal INTEGER, \n" +
                    "acabou BIT, \n" +
                    "FOREIGN KEY (id_animal) REFERENCES animal" +
                    ")");
            executeUpdate(statement);
            // Tabela consulta
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n" +
                    "id INTEGER PRIMARY KEY, \n" +
                    "data TEXT, \n" +
                    "comentarios VARCHAR, \n" +
                    "hora INTEGER, \n" +
                    "minutos INTEGER, \n" +
                    "id_tratamento INTEGER, \n" +
                    "id_veterinario INTEGER, \n" +
                    "id_animal INTEGER, \n" +
                    "acabou BIT, \n" +
                    "FOREIGN KEY (id_tratamento) REFERENCES tratamento, \n" +
                    "FOREIGN KEY (id_veterinario) REFERENCES veterinario, \n" +
                    "FOREIGN KEY (id_animal) REFERENCES animal \n" +
                    ")");
            executeUpdate(statement);
            // Tabela exame
            statement = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame( \n" +
                    "id INTEGER PRIMARY KEY, \n" +
                    "descricao VARCHAR, \n" +
                    "id_consulta INTEGER, \n" +
                    "FOREIGN KEY (id_consulta) REFERENCES consulta" +
                    ")");
            executeUpdate(statement);
            // Criando uma primeira espécie
            statement = DAO.getConnection().prepareStatement("INSERT OR IGNORE INTO especie (id, nome) VALUES (1, 'Cachorro')");
            executeUpdate(statement);
            return true;
        }catch (SQLException e){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
}
