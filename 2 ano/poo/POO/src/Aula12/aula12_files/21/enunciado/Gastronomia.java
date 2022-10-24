import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Gastronomia extends Atividade {
    private List<Restaurante> restaurantes;

    public Gastronomia(int numero, String nome){
        super(numero, nome);
        restaurantes = new ArrayList<>();
    }

    public Collection<String> locais(){
        Collection<String> c = new ArrayList<>();
        for(Restaurante r : restaurantes){
            c.add(r.toString());
        }
        return c;
    }

    public Gastronomia(int numero, String nome, List<Restaurante> lista){
        super(numero, nome);
        restaurantes = lista;
    }

    public void add(Restaurante x){
        restaurantes.add(x);
    }


    public int totalRestaurantes(){
        return restaurantes.size();
    }

    public List<Restaurante> getLista(){
        return restaurantes;
    }


    //Ver se apartir daqui é preciso
    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((restaurantes == null) ? 0 : restaurantes.hashCode());
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
        Gastronomia other = (Gastronomia) obj;
        if (restaurantes == null) {
            if (other.restaurantes != null)
                return false;
        } else if (!restaurantes.equals(other.restaurantes))
            return false;
        return true;
    }
}