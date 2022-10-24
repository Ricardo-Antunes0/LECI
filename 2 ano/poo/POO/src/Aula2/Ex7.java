package Aula2;
import java.util.Scanner;
import java.lang.Math;
public class Ex7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double p1x, p1y, p2x, p2y;

        System.out.println("Intrudoza as coordenadas do ponto p1: ");
        System.out.println("X: ");
        p1x = sc.nextDouble();
        System.out.println("Y: ");
        p1y = sc.nextDouble();

        System.out.println("Intrudoza as coordenadas do ponto p2: ");
        System.out.println("X: ");
        p2x = sc.nextDouble();
        System.out.println("Y: ");
        p2y = sc.nextDouble();

        double dist = Math.sqrt(Math.pow(p2x-p1x,2)+ Math.pow(p2y-p1y,2));  
        System.out.printf("Distancia entre os 2 pontos é: %f\n", dist);
        sc.close();
   } 
}
