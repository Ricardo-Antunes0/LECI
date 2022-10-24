package Aula8;

public class Peixe extends Alimento{
    private TipoPeixe tipo;


    public Peixe(TipoPeixe tipo, double proteinas, double calorias, double peso){
        super(proteinas,calorias,peso);
        this.tipo = tipo;
    }

    @Override
    public String toString(){
        return super.toString()+ "peixe [tipo=" + tipo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        Peixe other = (Peixe) obj;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    public TipoPeixe getTipo(){
        return tipo;
    }

    public void setTipo(TipoPeixe tipo){
        this.tipo = tipo;
    }
}
