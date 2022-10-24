package Aula3;
import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double mont, tjm;
        System.out.println("Intrudoza o montante investido: ");
        while(true){
            mont = sc.nextInt(); 
            if(mont %1000 == 0 && mont >= 0) break;
            System.out.println("Numero tem de ser positivo e multiplo de 1000!!!!");
        }
        System.out.println("Finalmente conseguiste");
        System.out.println("Indique a taxa de juro mensal ([0..5]): ");
        
        while(true){
            tjm = sc.nextInt();
            if( tjm >= 0 && tjm <= 5) break;
            System.out.println("Numero deve estar entre [0..5]\n");
        }
        
        for(int i = 0; i < 12; i++){
            mont += mont * (tjm/100);
            System.out.printf("%d mes -> %.2f \n", i+1, mont);
        }
    }
    
}
