package Aula5;

import java.util.ArrayList;

public class Utilizador {
    private String nome, curso;
    private int nmec;
    private ArrayList<Integer> livrosRequisitados;

    public Utilizador(String nome, int nmec, String curso){
        this.nome = nome;
        this.curso = curso;
        this.nmec = nmec;
        livrosRequisitados = new ArrayList<Integer>();
    }

    @Override
    public String toString() {
        return String.format("Aluno: %d; %s; %s", nmec, nome, curso);
    }

    public void setnMec(int nmec){
        if(nmec > 0) this.nmec = nmec;
    }

    public String nome(){
        return nome;
    }

    public String curso(){
        return curso;
    }

    public int nmec(){
        return nmec;
    }

    public ArrayList<Integer> getLivrosRequisitados() {
        return livrosRequisitados;
    }

    public void setLivrosRequisitados(ArrayList<Integer> livrosRequesitados) {
        this.livrosRequisitados = livrosRequesitados;
    }

}
