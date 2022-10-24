import java.util.Scanner;
import java.util.Random;
public class ex1_21 {
	
	public static void main (String[] args) {
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		int num;
		int dif = 0;
		
		do{
			System.out.print("Introduza um numero entre 1 e 20: ");
			 num = sc.nextInt();
		}while( num > 20 || num < 1);
		int numero = random.nextInt(20);
		System.out.print(numero);
		System.out.print("\n");
		
		if(num == numero){
			System.out.print("Parabens, acertou no numero!!!!");
		}
		else if ( num > numero){
			dif = num - numero;
		} else{
			dif = numero - num;
		}
			
		System.out.printf("Nao conseguiu acertar no numero, faltavam lhe %d\n", dif);
		
	}
}

