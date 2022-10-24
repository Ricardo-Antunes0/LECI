public abstract class Produto {
    
    private String codigo;
    private int quantidade;
    private double preco;

    private static int numPar = 0;

    public Produto(String codigo, int quantidade, double preco) {
        this(preco);
        this.codigo = codigo;
        this.quantidade = quantidade;
        
    }

    public Produto(double preco){
        this.preco = preco;
        numPar +=2;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public static int getNumPar() {
        return numPar;
    }

    public static void setNumPar(int numPar) {
        Produto.numPar = numPar;
    }

  

  


}
