package Model;

import java.util.Calendar;

public class Tratamento {
    private final int id;
    private String nome;
    private Calendar dataInicio;
    private Calendar dataFim;
    private int idAnimal;
    private boolean acabou; // 0 -> não / 1 -> sim

    public Tratamento(int id, String nome, Calendar dataInicio, Calendar dataFim, int idAnimal, boolean acabou) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idAnimal = idAnimal;
        this.acabou = acabou;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "Tratamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + TratamentoDAO.getInstance().dateFormat.format(dataInicio.getTime()) +
                ", dataFim=" + TratamentoDAO.getInstance().dateFormat.format(dataFim.getTime()) +
                ", idAnimal=" + idAnimal +
                ", acabou=" + acabou +
                '}';
    }
}
