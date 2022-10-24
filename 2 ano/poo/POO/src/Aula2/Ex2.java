package Aula2;
import java.util.Scanner;
public class Ex2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x;
        do{
            System.out.println("Introduza uma temperatura: ");
            x = sc.nextDouble();
        }while(x <= 0);

        double f = (1.8 * x) + 32;
        System.out.printf("Conversao para graus Fahrenheit: %f", f);
        sc.close();
    }
}