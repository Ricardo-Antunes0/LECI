package Aula2;
import java.util.Scanner;
public class Ex3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double water, temp_i, temp_f;
        do{
            System.out.println("Introduza a quantidade de agua: ");
            water = sc.nextDouble();
        }while(water <= 0);

        System.out.println("Introduza a temperatura inicial: ");
        temp_i = sc.nextDouble();

        System.out.println("Introduza a temperatura final: ");
        temp_f = sc.nextDouble();

        double energ = water * (temp_f - temp_i) * 4184;
        System.out.printf("A energia necessaria é: %.2f", energ);
        sc.close();
    }   
}
