import static java.lang.System.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
// import java.util.*;   // => "error: reference to LinkedList is ambiguous"
// java.util.LinkedList colide com p2utils.LinkedList!
import p2utils.*;

public class FilterLines
{
  public static void main(String[] args) throws IOException
  {
    if (args.length != 1) {
      err.printf("Usage: java -ea FilterLines text-file\n");
      exit(1);
    }
    File fil = new File(args[0]);

    // Criar listas para as linhas curtas, médias e longas.
    //...
    LinkedList<String> n1 = new LinkedList<>(); //Linhas curtas
    LinkedList<String> n2 = new LinkedList<>(); //Linhas medias
    LinkedList<String> n3 = new LinkedList<>(); //Linhas longas

    Scanner sf = new Scanner(fil);
    // exceções poderiam ser intercetadas e mostrar mensagem de erro.
    while (sf.hasNextLine()) {
      String line = sf.nextLine();
      // Guardar linha na lista apropriada, consoante o tamanho.
      if(line.length() < 20 ){
          n1.addLast(line);
      }
      else if(line.length() >= 20 && line.length() < 40){
          n2.addLast(line);
      }
      else {
          n3.addLast(line);
      }
    }
    sf.close();

    // Escrever conteúdo das listas...
    n3.print();
    out.println("Curtas---|---------|---------|---------|---------\n");
    for(int i = 0; i < n1.size();i++){
      out.println(n1.get(i));
    }
    out.println("Médias---|---------|---------|---------|---------\n");
    for(int i = 0; i < n2.size();i++){
      out.println(n2.get(i));
    }
    out.println("Longas---|---------|---------|---------|---------");
    for(int i = 0; i < n3.size();i++){
      out.println(n3.get(i));
    }
  }

}
