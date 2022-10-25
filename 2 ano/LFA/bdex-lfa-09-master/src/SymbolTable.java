import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map <Symbol, Object> variables;

    public SymbolTable() {
        variables = new HashMap<>();
    }

    public void put(Symbol s, Object o) {
        Symbol aux = getSymbolName(s.getName());
        if(aux==null){
            variables.put(s, o);
        }else{
            variables.remove(aux);
            variables.put(s, o);
        }
    }

    private Symbol getSymbolName(String s){
        return getSymbol(s);
    }

    public Object get(Symbol s) {
        return variables.get(s);
    }

    public Symbol getSymbol(String name){
        Symbol aux = null;
        for(Symbol s : variables.keySet()){
            if(s.getName().equals(name)){
                aux = s;
            }
        }
        return aux;
    }

    public boolean containsKey(Symbol s) {
        return variables.containsKey(s);
    }

    public boolean containsValue(Object o) {
        return variables.containsValue(o);
    }

    public void remove(Symbol s){
        variables.remove(s);
    }

    public int size() {
        return variables.size();
    }

}
