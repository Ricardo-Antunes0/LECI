package Aula3;
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double [] lista = new double[100]; 
        System.out.println("Introduza numeros: ");
        int i = 0;
        double min = 100000;
        double max = -1000000;
        double media = 0;
        while(true){
            lista[i] = sc.nextDouble();
        
            if(min > lista[i]) min = lista[i];
            if(max < lista[i]) max = lista[i];

            media += lista[i];

            if(lista[0] == lista[i] && i > 0) break;
            i++;
        }

        System.out.printf("Maximo:  %.1f\nMinimo: %.1f\n", max, min);
        System.out.printf("Media: %.1f\nTotal de numeros lidos: %d\n",media/(i+1), i+1);
        
    }
    
}
