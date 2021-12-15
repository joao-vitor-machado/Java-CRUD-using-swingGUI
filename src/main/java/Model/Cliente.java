package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private List<Animal> animais;

    public Cliente(int id, String nome, String endereco, String telefone, String cep, String cpf, String email) {
        super(id, nome, endereco, telefone, cep, cpf, email);
        this.animais = new ArrayList<Animal>();
    }

    public List<Animal> getAnimais() {

        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                super.toString() +
                "animais=" + animais +
                '}';
    }
}

