package Aula2;
import java.util.Scanner;
import java.lang.Math;

public class Ex8 {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double cat1, cat2, hip, ang;

        System.out.println("Introduza os valores dos catetos: ");
        while(true){
            cat1 = sc.nextDouble();
            if (cat1 >= 0) break;
            System.out.println("O valor tem de ser positivo!!");
        }
        
        while(true){
            cat2 = sc.nextDouble();
            if (cat2 >= 0) break;
            System.out.println("O valor tem de ser positivo!!");
        }

        hip = Math.sqrt(Math.pow(cat1,2)+ Math.pow(cat2,2));
        ang = Math.toDegrees(Math.atan(cat2/cat1));

        System.out.printf("Hipotenusa: %.2f e o valor do angulo: %.2f\n",hip, ang);

   } 
}
