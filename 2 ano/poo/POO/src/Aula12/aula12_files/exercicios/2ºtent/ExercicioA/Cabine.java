package ExercicioA;


import java.util.Set;
import java.util.TreeSet;

public abstract class Cabine {
    
    private int numero;
    private int maxCapacity;
    private Set<String> ocupantes = new TreeSet<>();    //E um conjunto porque eu nao posso ter ocupantes repetidos

    public Cabine(int numero, int maxCapacity){
        this.numero = numero;
        this.maxCapacity = maxCapacity;
    }

    public Cabine(int numero, int maxCapacity, String[] x){
        this.numero = numero;
        this.maxCapacity = maxCapacity;
        setOcupante(x);
    }


    public void setOcupante(String[] x){
        if(x.length > maxCapacity){
            throw new  IllegalArgumentException();
        }
        else{
            for(String i : x){
                ocupantes.add(i);
            }
        }
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int num){
        numero = num;
    }

    public int getCapMax(){
        return maxCapacity;
    }

    public void setCapMax(int max){
        maxCapacity = max;
    }

    public Set<String> getOcupantes(){
        return ocupantes;
    }

    public void setOcupantes(Set<String> ocup){
        ocupantes = ocup;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maxCapacity;
        result = prime * result + numero;
        result = prime * result + ((ocupantes == null) ? 0 : ocupantes.hashCode());
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
        Cabine other = (Cabine) obj;
        if (maxCapacity != other.maxCapacity)
            return false;
        if (numero != other.numero)
            return false;
        if (ocupantes == null) {
            if (other.ocupantes != null)
                return false;
        } else if (!ocupantes.equals(other.ocupantes))
            return false;
        return true;
    }

    @Override
    public String toString(){
        String a;
        if(ocupantes.size() == 0){
            a = "Disponivel para venda!";
        }
        else {
            a = ocupantes.toString();
        }
        return "[ Nº "+numero+" (max "+maxCapacity+" pessoas ) : "+a+ " ]";
    }
}
