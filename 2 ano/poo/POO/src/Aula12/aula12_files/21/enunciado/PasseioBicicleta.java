import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.Set;
import java.util.TreeSet;


public class PasseioBicicleta extends Atividade{

    private Set<String> locais;

    public PasseioBicicleta(int numero, String nome, String[] s){
        super(numero,nome);
        locais = new TreeSet<String>(Arrays.asList(s)); 
    }

    public PasseioBicicleta(int numero, String nome){
        super(numero, nome);
        locais = new TreeSet<>();
    }
 
    public void addLocal(String s){
        locais.add(s);
    }


    @Override
    public Collection<String> locais() {
        return locais;
    }


  

}
