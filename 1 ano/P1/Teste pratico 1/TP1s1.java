/* NUMREO, NOME:
// 98275, Ricardo Fernandes Antunes


 * Teste Prático 1 - Sessão 1
 *  JAM, 4-dez-2020
 *  Calcular máximos de uma função representada por um array de inteiros.
 */
import java.util.Scanner;

public class TP1s1 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
	
        int[] funcao = {11, 5, 12, 5, 5, 13, 10, 6}; // array com pontos da função
        int[] posMax = new int[funcao.length]; // array com posições dos máximos

        // código para preencher array funcao, lendo os valores do teclado
        System.out.printf("Introduza %d numeros inteiros:%n",funcao.length);
// alinea 1)
         funcao = new int[8];
         for(int i = 0; i < funcao.length; i++){ //ciclo for para percorrer o meu array funcao
            funcao[i] = teclado.nextInt();   // O valor lido (que é inteiro) é guardado no array
         }


        // código para calcular o máximo do array funcao e a posição em que ocorre
        int imax = 0;
// alinea 2)
      for(int i = 0; i < 7; i++){ //Ciclo for para percorrer o array funcao
         if(funcao[imax] < funcao[i+1]){ // se o valor na posicao imax for menor que o valor da posicao seguinte entao
            imax = i+1;  //a posicao imax passa a ser i+1 
         }
      } //Assim, o imax e a posicao que contem o maximo do array
      
      //Escrever no ecra o maximo do array funcao e a sua posicao
      System.out.printf("Maximo = %d, na posicao %d \n",funcao[imax], imax);
     

        int[] deriv = derivada(funcao);
        printArray(deriv,deriv.length,"Derivada:");
        int numMaximos = maximos(deriv,posMax);
        printArray(posMax,numMaximos,"Posicoes dos maximos:");
        // imprimir os valores dos maximos
        for (int i = 0; i < numMaximos; i++)
            System.out.printf("Maximo %d = %d%n", i+1,funcao[posMax[i]]);
    }

    // Escreve no ecrã um array de inteiros
    static void printArray(int[] arr, int num, String mensagem) {
        System.out.println(mensagem);
        if (num > arr.length) num = arr.length;
        String linha = "[";
        for (int k=0; k<num; k++) {
            linha = linha + arr[k] + " ";
        }
        System.out.println(linha+"]");
    }

// alinea 3)
    /*  Argumento arr: -array de inteiros com valores de uma função
     *  Retorno:       -array de inteiros com a derivada do arr
     */
    public static int[] derivada(int[] arr) {
        int[] d = new int[0]; //deve fazer um novo new com o tamanho adequado
         
         d = new int[arr.length-1]; //O array d é menor na dimensao relativamente ao array com os valores
         
         for(int i = 0; i < arr.length-1; i++){ //Ciclo for para percorrer o array com os pontos
            d[i] = (arr[i+1]-arr[i]); //Formula da derivada dos pontos
         }  
         //Este ciclo começa em 0 e acaba em 6.
         //Caso o i fosse 7, a derivada seria (arr[8]-arr[7])
         //o que nao e possivel, pois passa o tamanho do array.
         


	return d;
    }
// alinea 4)
    /*  Argumento arr:      -array de inteiros com valores da derivada
     *  Argumento posZeros: -array de inteiros com resultados dos zeros do arr
     *  Retorno:            -numero de zeros encontrados
     */
    public static int maximos(int[] arr, int[] posZeros) {
        int nzeros = 0;

         for(int i = 0; i < arr.length-1; i++){ //ciclo for para percorrer o meu array onde i < 7
            if(arr[i] > 0 && arr[i+1] < 0){// ver se o valor atual é > 0 e o proximo é < 0.
               posZeros[nzeros] = i+1; //Guarda no array posZeros, a posicao onde foi encontrada a mudança de sinal
               nzeros++; //Foi encontrado um zero, logo o numero de zeros aumenta.
            }
         }


        return nzeros;
    }
}