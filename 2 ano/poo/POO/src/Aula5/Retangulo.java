package Aula5;

public class Retangulo {
    private double compr, larg;


    public Retangulo(int compr, int larg){
        assert compr > 0 && larg > 0: "Invalid data, couldn't create new rectangle.";
        this.compr = compr;
        this.larg = larg;
    }
    public double getComprimento(){
        return compr;
    }
    
    public double getLargura(){
        return larg;
    }

    public void setComprimento(double comprimento) {
        if (comprimento > 0) compr = comprimento;
    }

    public void setAltura(double largura) {
        if (largura > 0) larg = largura;
    }

    public static String toString(Retangulo x){
        return "Retangulo com "+x.compr+"de comprimento e "+x.larg+"de largura";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Retangulo other = (Retangulo) obj;
        if (Double.doubleToLongBits(larg) != Double.doubleToLongBits(other.larg))
            return false;
        if (Double.doubleToLongBits(compr) != Double.doubleToLongBits(other.compr))
            return false;
        return true;
    }

    public static Retangulo set(int compr, int larg){
        return new Retangulo(compr, larg);
    }

    public static double area(Retangulo x){
        return (x.compr*x.larg);
    }

    public static double perimetro(Retangulo x){
        return (2*x.compr+ 2*x.larg);
    }

}
