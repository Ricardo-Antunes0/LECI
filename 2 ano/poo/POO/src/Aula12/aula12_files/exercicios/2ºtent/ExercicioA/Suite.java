package ExercicioA;

public class Suite extends Cabine{
    private int nQuartos;

    public Suite(int numero, int maxCapacity, int nQuartos){
        super(numero,maxCapacity);
        this.nQuartos = nQuartos;
    }

    public Suite(int numero, int maxCapacity){
        super(numero,maxCapacity);
    }

    public int getNumQuartos(){
        return nQuartos;
    }

    public void setnQuartos(int nQuartos){
        this.nQuartos = nQuartos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + nQuartos;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Suite other = (Suite) obj;
        if (nQuartos != other.nQuartos)
            return false;
        return true;
    }
    

    @Override
    public String toString() {
        return "Suite c/ " + nQuartos + " quartos " + super.toString() + "\n";
    }



}
