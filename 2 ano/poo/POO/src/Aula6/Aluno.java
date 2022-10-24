package Aula6;

import Aula5.Date;

public class Aluno extends Pessoa {
    private int  nmec;
    private Date dataInsc;
    private static int nMec = 100;

    
    public Aluno(String nome, int cc, Date dataNasc) {
        super(nome, cc, dataNasc);
        this.nmec = nMec++;
        this.dataInsc = new Date();
    }

    public Aluno(String nome,int cc, Date dataNasc, Date dataInsc){
        this(nome,cc,dataNasc);
        this.dataInsc = dataInsc;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Nmec: %d; Data de Inscrição: %s", nMec, dataInsc.toString());
    }

    public int getNMec(){
        return nmec;
    }
    
    public void setnMec(int nMec) {
        Aluno.nMec = nMec;
    }

    public Date getDataInsc() {
        return dataInsc;
    }

    public void setDataInsc(Date dataInsc) {
        this.dataInsc = dataInsc;
    }

    public static void setnMecs(int nMecs) {
        Aluno.nMec = nMecs;
    }
}
