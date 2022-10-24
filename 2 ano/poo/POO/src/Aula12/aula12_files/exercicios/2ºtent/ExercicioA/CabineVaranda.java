package ExercicioA;

public class CabineVaranda extends Cabine implements InterfaceExtra{
    
    private TipoVaranda tipo;
    private Extra extra;

    public CabineVaranda(int numero, int maxCapacity, TipoVaranda tipo){
        super(numero, maxCapacity);
        this.tipo = tipo;
    }

    public TipoVaranda getTipoVaranda(){
        return tipo;
    }

    public void setTipoVaranda(TipoVaranda tipo){
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
        CabineVaranda other = (CabineVaranda) obj;
        if (extra != other.extra)
            return false;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "Cabine com Varanda "+tipo+ " "+ super.toString() + "\n";
    }


}
