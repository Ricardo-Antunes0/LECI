package Aula7;

public class Triangulo extends Forma{
    private double l1, l2, l3;

    public Triangulo(String cor,double l1, double l2, double l3){
        super(cor);
        if (l1 + l2 > l3 && l1 + l3 > l2 && l2 + l3 > l1 && l1 > 0 && l2 > 0 && l3 > 0) {
            this.l1 = l1;
            this.l2 = l2;
            this.l3 = l3; 
        }else{
            this.setCor(null);
        }
    }
   
    public double area() {
        double s = (l1 + l2 + l3) / 2;
        return Math.sqrt(s * (s - l1) * (s - l2) * (s - l3));
    }

    public double perimetro() {
        return l1+l2+l3;
    }
    
    public String toString(Triangulo x){
        return super.toString() + "Triangulo com "+x.l1+" lado 1, "+x.l2+" lado 2 e "+x.l3+ " lado 3";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triangulo other = (Triangulo) obj;
        if (Double.doubleToLongBits(l1) != Double.doubleToLongBits(other.l1))
            return false;
        if (Double.doubleToLongBits(l2) != Double.doubleToLongBits(other.l2))
            return false;
        if (Double.doubleToLongBits(l3) != Double.doubleToLongBits(other.l3))
            return false;
        if (getCor() == null) {
            if (other.getCor() != null)
                return false;
        } else if (!getCor().equals(other.getCor()))
            return false;
        return true;
    }

    public double getl1(){
        return l1;
    }
    public double getl2(){
        return l2;
    }
    public double getl3(){
        return l3;
    }
    public void setLado1(double lado1) {
        if (lado1 + l2 > l3 && lado1 + l3 > l2 && l2 + l3 > lado1 && lado1 > 0) l1 = lado1;
    }

    public void setLado2(double lado2) {
        if (l1 + lado2 > l3 && l1 + l3 > lado2 && lado2 + l3 > l1 && lado2 > 0) l2 = lado2;
    }

    public void setLado3(double lado3) {
        if (l1 + l2 > lado3 && l1 + lado3 > l2 && l2 + lado3 > l1 && l3 > 0) l3 = lado3;
    }
    
    public static double area(Triangulo x){
        double p = (x.l1+x.l2+x.l3)/2;
        return Math.sqrt(p*(p-x.l1)*(p-x.l2)*(p-x.l3));
    }

    public static double perimetro(Triangulo x){
        return (x.l1+x.l2+x.l3);
    }
}
