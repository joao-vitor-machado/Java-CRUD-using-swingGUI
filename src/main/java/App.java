import Model.AnimalDAO;
import Model.ClienteDAO;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.DAO;
import Model.EspecieDAO;
import Model.Exame;
import Model.ExameDAO;
import Model.TratamentoDAO;
import Model.Veterinario;
import Model.VeterinarioDAO;
import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
//        VeterinarioDAO.getInstance().create(new Veterinario(1, "Fabio", "Rua Fagundes", "1199988978", "89758675091", "78678667856", "fabiao@gmail.com"));
//        System.out.print(AnimalDAO.getInstance().retrieveById(1));
//        System.out.print(TratamentoDAO.getInstance().retrieveAll());
//        Consulta consulta = new Consulta(1, Calendar.getInstance(), "uma consulta de rotina", 12, 30, false, 1, 1, 1);
//        
//        ConsultaDAO.getInstance().create(consulta);
//        System.out.print(ConsultaDAO.getInstance().retrieveByIdAnimal(1));
//        System.out.print(ClienteDAO.getInstance().retrieveBySimilarName("Jo"));
//          DAO.getConnection().prepareStatement("DROP ")
        System.out.println(ConsultaDAO.getInstance().retrieveAll());
        
    }

}
