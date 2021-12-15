package Model;

import java.util.Calendar;

public class Consulta {
    private final int id;
    private Calendar date;
    private String comentarios;
    private int hora;
    private int minutos;
    private int idTratamento;
    private int idVeterinario;
    private int idAnimal;
    private boolean acabou; // 0 -> não // 1 -> sim

    public Consulta(int id, Calendar date, String comentarios, int hora, int minutos, boolean acabou, int idTratamento, int idVeterinario, int idAnimal) {
        this.id = id;
        this.date = date;
        this.comentarios = comentarios;
        this.hora = hora;
        this.minutos = minutos;
        this.idTratamento = idTratamento;
        this.idVeterinario = idVeterinario;
        this.idAnimal = idAnimal;
        this.acabou = acabou;
    }

    public boolean isAcabou() {
        return acabou;
    }

    public void setAcabou(boolean acabou) {
        this.acabou = acabou;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String printHoras(){
        return this.getHora() + ":" + this.getMinutos();
    }
    
    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", date=" + date +
                ", comentarios='" + comentarios + '\'' +
                ", hora=" + hora +
                ", minutos=" + minutos +
                ", idTratamento=" + idTratamento +
                ", idVeterinario=" + idVeterinario +
                ", idAnimal=" + idAnimal +
                '}';
    }
}
