package Model;

public class Exame {
    private final int id;
    private String descricao;
    private final int idConsulta;

    public Exame(int id, String descricao, int idConsulta) {
        this.id = id;
        this.descricao = descricao;
        this.idConsulta = idConsulta;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    @Override
    public String toString() {
        return "Exame{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", idConsulta=" + idConsulta +
                '}';
    }
}
