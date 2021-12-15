package Model;

public class Animal {
    private final int id;
    private String nome;
    private int anoNasc;
    private boolean sexo; // 0 -> macho / 1 -> Fêmea
    private int idEspecie;
    private int idCliente;


    public Animal(int id, String nome, int anoNasc, boolean sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoNasc=" + anoNasc +
                ", sexo=" + sexo +
                ", idEspecie=" + idEspecie +
                ", idCliente=" + idCliente +
                '}';
    }
}
