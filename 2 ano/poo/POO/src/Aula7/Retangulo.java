package Aula7;

public class Retangulo extends Forma {
    private double compr, larg;


    public Retangulo(String cor, int compr, int larg){
        super(cor);
        assert compr > 0 && larg > 0: "Invalid data, couldn't create new rectangle."; //this.setCor(null);
        this.compr = compr;
        this.larg = larg;

    }

    public double area(){
        return compr*larg;
    }

    public double perimetro(){
        return compr+compr+larg+larg;
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

    public String toString(Retangulo x){
        return super.toString() + "Retangulo com "+x.compr+"de comprimento e "+x.larg+"de largura";
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
        if (getCor() == null) {
            if (other.getCor() != null)
                return false;
        } else if (!getCor().equals(other.getCor()))
            return false;
        return true;
    }
}
