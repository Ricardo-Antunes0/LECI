package ExercicioA;

public class CabineJanela extends Cabine implements InterfaceExtra{
    private Janela tipo;
    private Extra extra;

    public CabineJanela(int numero, int maxCapacity,Janela tipo, String[] ocupantes){
        super(numero, maxCapacity, ocupantes);
        this.tipo = tipo;
    }   

    public CabineJanela(int numero, int maxCapacity,Janela tipo){
        super(numero, maxCapacity);
        this.tipo = tipo;
    }

    public Janela getTipoDeJanela(){
        return tipo;
    }

    public void setNumero(Janela tipo){
        this.tipo = tipo;
    }

    public void Extra(Extra e){
        extra = e;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + ((extra == null) ? 0 : extra.hashCode());
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
        CabineJanela other = (CabineJanela) obj;
        if (extra != other.extra)
            return false;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "Cabine com Janela "+tipo+ " "+ super.toString() + "\n";
    }


}
