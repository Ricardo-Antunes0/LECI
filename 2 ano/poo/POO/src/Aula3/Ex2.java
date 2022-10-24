package Aula3;
import java.util.Scanner;
public class Ex2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        System.out.println("Introduza um numero inteiro positivo: ");
       
        while(true){
            N = sc.nextInt();
            if(N > 0) break;
            System.out.print("Numero deve ser positivo!!!!!!!");
        }

        for(int i = N; i >= 0; i--){
            System.out.println(i);
        }
    }

}
