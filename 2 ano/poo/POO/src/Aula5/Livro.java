package Aula5;

public class Livro {
    private int id;
    private String titulo, tipoEmprestimo;
    private static int IDS = 100;
    private boolean disponivel;
    

    public Livro(String titulo){
        id = IDS++;
        this.titulo = titulo;
        this.tipoEmprestimo = "NORMAL";
        this.disponivel = true;
    }

    public Livro(String titulo, String tipoEmprestimo){
        this.titulo = titulo;
        this.tipoEmprestimo = tipoEmprestimo;
        id = IDS++;
        this.disponivel = false;
    }

    @Override
    public String toString() {
        return String.format("Livro %d; %s; %s", id, titulo, tipoEmprestimo);
    }


    public void setTipoEmprestimo(String emprestimo){
        tipoEmprestimo = emprestimo;
    }

    public int getId(){
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getTipoEmprestimo(){
        return tipoEmprestimo;
    }

    public boolean disponivel(){
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }



}
