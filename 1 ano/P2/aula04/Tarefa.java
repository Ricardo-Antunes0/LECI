public class Tarefa {

    private Data fim;
    private Data inicio;
    private String texto;

    public Tarefa(Data ini, Data fim, String texto){
        assert ini.compareTo(fim) <= 0 : "a data de fim da tarefa nao pode ser anterior `a data de inıcio";
        assert  texto.length() > 0;
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



}
