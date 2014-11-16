package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by nazareno on 14/11/14.
 */
@Entity
public class Disciplina {

    @Id
    private String codigo;

    private String nome;

    public Disciplina(String codigo, String nome) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public Disciplina(){
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }
}
