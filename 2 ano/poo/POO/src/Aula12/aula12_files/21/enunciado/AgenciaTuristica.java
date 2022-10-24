import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgenciaTuristica {
    private String nome, endereço;
    private Set<Atividade> conjAtividades;

    public AgenciaTuristica(String nome, String endereço){
        this.nome = nome;
        this.endereço = endereço;
        this.conjAtividades = new HashSet<>();
    }

    public void add(Atividade x){
        conjAtividades.add(x);
    }

    public int totalItems(){
        int count = 0;
        
        for(Atividade x : conjAtividades){
            count += x.locais().size();
        }
        return count;
    }

    public Set<Atividade> atividades(){
        return conjAtividades;
    }

    public Collection<String> getAllItems(){  
        Collection<String> local = new TreeSet<>(); 
        
        for(Atividade x : conjAtividades){
            for(String s: x.locais()){
                local.add(s);
            }
        }
        
        return local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Set<Atividade> getConjAtividades() {
        return conjAtividades;
    }

    public void setConjAtividades(Set<Atividade> conjAtividades) {
        this.conjAtividades = conjAtividades;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((conjAtividades == null) ? 0 : conjAtividades.hashCode());
        result = prime * result + ((endereço == null) ? 0 : endereço.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        AgenciaTuristica other = (AgenciaTuristica) obj;
        if (conjAtividades == null) {
            if (other.conjAtividades != null)
                return false;
        } else if (!conjAtividades.equals(other.conjAtividades))
            return false;
        if (endereço == null) {
            if (other.endereço != null)
                return false;
        } else if (!endereço.equals(other.endereço))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append(String.format("Agencia Turistica %s\n\tAtividades: ", nome));
            stb.append(conjAtividades);
        return stb.toString();
    }
}


