public class Tarefa {

    private Data fim;
    private Data inicio;
    private String texto;

    public Tarefa(Data ini, Data fim, String texto){
        inicio = ini;
        this.fim = fim;
        this.texto = texto;
    }

    public Data inicio(){
        return inicio;
    }

    public Data fim(){
        return fim;
    }

    public String texto(){
        return texto;
    }

    public String toString (){
        return String.format(inicio+" " +texto+ " "+fim);
    }


}
