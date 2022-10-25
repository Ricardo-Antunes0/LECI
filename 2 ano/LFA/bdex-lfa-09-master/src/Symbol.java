public class Symbol {
    private String name;
    private Type type;
    private boolean valDefined;

    public Symbol(String name, Type type) {
        if(name == null || type == null) throw new IllegalArgumentException();
        this.name = name;
        this.type = type;
        valDefined = false;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean getValDefined() {
        return valDefined;
    }

    public void setValDefined() {
        valDefined = true;
    }

    public void setSymbolName(String name){
        this.name=name;
    }

    @Override
    public String toString(){
        return name+":"+type.toString();
    }

}
