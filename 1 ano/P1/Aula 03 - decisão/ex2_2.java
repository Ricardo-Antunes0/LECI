import java.util.Scanner;
public class ex2_2 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduza 2 numeros reais: \n");
		double n1 = sc.nextDouble();
		double n2 = sc.nextDouble();
		
		if(n1 > n2){
			System.out.printf("O numero %3.1f é o maior dos 2", n1);	
		}
		else if(n2 > n1){
			System.out.printf("O numero %3.1f é o maior dos 2",n2);	
			
		} else {
			System.out.print("Os numeros sao iguais");
		}
		
	}
}

