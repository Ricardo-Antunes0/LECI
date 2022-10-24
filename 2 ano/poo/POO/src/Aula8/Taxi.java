package Aula8;

public class Taxi extends AutomovelLigeiro{
    private int numeroLicenca;

    public Taxi(String matricula, String marca, String modelo, int potencia, int numQuadro, int capacidadeBagageira,
    int numeroLicenca){
        super(matricula,marca,modelo,potencia,numQuadro,capacidadeBagageira);
        this.numeroLicenca = numeroLicenca;
    }

    @Override
    public String toString() {
        return super.toString() + "Taxi [numLicense=" + numeroLicenca + "]";
    }

    public int getNumeroLicenca() {
        return numeroLicenca;
    }

    public void setNumeroLicenca(int numeroLicenca) {
        this.numeroLicenca = numeroLicenca;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numeroLicenca;
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
        Taxi other = (Taxi) obj;
        if (numeroLicenca != other.numeroLicenca)
            return false;
        return super.equals(obj) && true;
    }

}
