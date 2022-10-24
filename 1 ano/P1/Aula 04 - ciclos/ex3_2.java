import java.util.Scanner;
public class ex3_2 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int N;
		do{
			System.out.print("Introduza um numero inteiro entre 0 e 100, excluindo os limites: ");
			N = sc.nextInt();
		}while( N <= 0 || N >= 100);
	
	
		System.out.println("-------------------");
		System.out.println("| Tabuada dos ## |");
		System.out.println("-------------------");

		for(int i = 1; i <= 10; i++){
			System.out.printf("%d * %d |  %d\n", N,i, (N*i));
		}
	
	}
}

