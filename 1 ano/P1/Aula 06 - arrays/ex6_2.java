import java.util.Scanner;
public class ex6_2 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 0; 
		int i = 0;
		int []a = new int [100];
		int cont = 0;
		
			System.out.print("Introduza um numero inteiro positivo: ");
			for(i = 0; i < 1000; i++){
				num = sc.nextInt();
				a[i] = num;	
				if((num < 0) || (i > 100)){
					System.out.println("Numero invalido");
					break;	
				}	
			}
			
			System.out.println("Indique o numero que quer: ");
			int num1 = sc.nextInt();
	
			for(int j: a){
				if(j == num1){
					cont++;
				}
			}
		
		System.out.printf("O numero repetiu %d \n", cont);
		
	}
}

