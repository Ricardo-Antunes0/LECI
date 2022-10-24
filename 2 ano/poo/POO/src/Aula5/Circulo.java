package Aula5;
public class Circulo {
    private double raio;

    public Circulo(double raio){
        if(raio > 0) this.raio = raio;
    }

    public double getRaio(){
        return raio;
    }

    public void set(double raio){
        if(raio > 0) this.raio = raio;
    }

    public static String toString(Circulo x){
        return "Circulo com raio "+x.raio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circulo other = (Circulo) obj;
        if (Double.doubleToLongBits(raio) != Double.doubleToLongBits(other.raio))
            return false;
        return true;
    }

    public static double area(Circulo x){
        return (3.14* Math.pow(x.raio,2));
    }

    public static double perimetro(Circulo x){
        return (2*3.14*x.raio);
    }

}