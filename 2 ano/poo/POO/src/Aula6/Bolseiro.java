package Aula6;
import Aula5.Date;

public class Bolseiro extends Aluno {
    private int montante;

    public Bolseiro(String nome, int cc, Date dataNasc, int montante){
        super(nome,cc,dataNasc);
        this.montante = montante;
    }

    public Bolseiro(String nome, int cc, Date dataNasc, Date dataInsc, int montante){
        super(nome,cc,dataNasc, dataInsc);
        this.montante = montante;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Montante: %d", montante);
    }

    public int getMontante(){
        return this.montante;
    }

    public void setMontante(int mont){
        montante = mont;
    }


}
