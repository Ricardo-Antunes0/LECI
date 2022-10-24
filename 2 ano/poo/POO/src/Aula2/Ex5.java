package Aula2;
import java.util.Scanner;
public class Ex5 {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double v1, d1, v2, d2;

            System.out.println("Insira a velocidade: ");
            v1 = sc.nextDouble();
            v1 = Math.abs(v1);

        while(true){
            System.out.println("Insira a distancia: ");
            d1 = sc.nextDouble();
            if(d1 > 0)
                break;
            System.out.println("Try again");
        }

            System.out.println("Insira a velocidade: ");
            v2 = sc.nextDouble();
            v2 = Math.abs(v2);
            
        while(true){
            System.out.println("Insira a distancia");
            d2 = sc.nextDouble();
            if(d2 > 0)
                break;
            System.out.println("Try again");
        }
        double vm = (2* v1 * v2)/(v1 + v2);
        System.out.printf("A velocidade média é %f \n",vm);
        sc.close();
   } 
}
