package models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by nazareno on 18/11/14.
 */
@Entity
public class Tarefa {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToOne
    private Disciplina disciplina;

    public Tarefa(String nome, Disciplina disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public Tarefa() {
    }

    public String getNome() {
        return nome;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}
