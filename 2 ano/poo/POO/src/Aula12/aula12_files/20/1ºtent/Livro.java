import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Livro extends Produto{

    private String titulo;
    private Collection<Autor> autores;

    public Livro(String titulo, double preco) {
        super(preco);
        this.titulo = titulo;
        this.autores = new TreeSet<>();
    }

    public Livro(String titulo, double preco, List<Autor> lista){
        super(preco);
        this.titulo = titulo;
        this.autores = lista;
    }
   
   

}