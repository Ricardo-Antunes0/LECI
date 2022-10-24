package Aula2;
import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double x;

        System.out.println("Intrudoza uma distancia: ");
        while(true){
            x = sc.nextDouble();
            if (x >= 0) break;
            System.out.println("Distancia tem de ser positiva!!");
        }

        double milhas = x / 1.609; 
        System.out.printf("valor em milhas: %f", milhas);
 
        sc.close();

    }
}