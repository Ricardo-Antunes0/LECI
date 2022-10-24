package Aula3;
import java.util.Scanner;
public class Ex3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, count = 0;

        System.out.println("Introduza um numero inteiro positivo: ");

        while(true){
            N = sc.nextInt();
            if(N > 0) break;
            System.out.println("Numero deve ser positivo!!!!!!!");
        }
        
        for(int i = 1; i <= N; i++){
            if (N % i == 0) count++;
        }

        if(count == 2){
            System.out.printf("O numero %d é primo \n",N);
        }
        else{
            System.out.printf("O numero %d não é primo \n", N);
        }
    }    
}

