package Aula7;

public abstract class Forma {
    private String cor;
    
    public Forma(String cor){
        this.cor = cor;
    }

    public abstract double area();

    public abstract double perimetro();


    public String getCor(){
        return this.cor;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    @Override
    public String toString(){
        return "Forma [cor="+cor+ " ] ";
    }


}
