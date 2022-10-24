package Aula2;
import java.util.Scanner;
public class Ex4 {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        double investido, txmensal;
        System.out.print("Introduza o montante investido: \n");
        investido = sc.nextDouble();

        System.out.print("Introduza a taxa de juro mensal: \n");
        txmensal = sc.nextDouble();

        for(int i = 0; i < 3; i++){
           investido += investido * (txmensal/100);
        }
       System.out.printf("O valor total ao fim de 3 meses é: %.3f", investido);

   } 
}
