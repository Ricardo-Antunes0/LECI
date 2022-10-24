package Aula7;
public class Circulo extends Forma {
    private double raio;

    public Circulo(String cor, double raio){
        super(cor);
        if(raio > 0) this.raio = raio;
        else{
            setCor(null);
        }
    }

    public double area(){
        return (3.14* Math.pow(raio,2));
    }

    public double perimetro(){
        return (2*3.14*raio);
    }


    public String toString(Circulo x){
        return super.toString() + "Circulo com raio "+x.raio;
    }

    public double getRaio(){
        return raio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(raio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
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
        if (getCor() == null) {
            if (other.getCor() != null)
                return false;
        } else if (!getCor().equals(other.getCor()))
            return false;
        return true;
    }

    public void set(double raio){
        if(raio > 0) this.raio = raio;
    }
}