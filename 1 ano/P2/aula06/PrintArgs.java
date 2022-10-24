import static java.lang.System.*;

public class PrintArgs {

  public static void main(String[] args) {

    //int n = 0;
    printArray(args, args.length);
  }

  /** Imprime as N primeiras strings do array, uma por linha. */
  public static void printArray(String[] array, int N) {
  // Implemente de forma recursiva...
    
    if(N > 0){
      printArray(array, N-1);
      out.println(array[N-1]);
    }
 // para imprimir ao contrario é so passar o print para cima da chamada da funcao
 // out.println(array[N-1]);
 // printArray(array, N-1);
  }

}

