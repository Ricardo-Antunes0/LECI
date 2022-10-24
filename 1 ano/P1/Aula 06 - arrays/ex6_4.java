import java.util.Scanner;
public class ex6_4 {
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {
		
		System.out.println("Determine o número de notas: ");
		int N = sc.nextInt();
		
		int [] notas = new int [N];
		
		System.out.printf("Introduza as %d notas: \n",N);
	
		for(int i = 0; i < notas.length; i++){
			do {
				notas[i] = sc.nextInt();
			} while((notas[i] > 20) || (notas[i] < 0));
		}
		histograma(notas, N);
		
	}
	public static void histograma ( int [] notas, int N){
		for(int i = 20; i >= 0; i--){
			System.out.printf("%d | ",i);
			for(int j = 0; j < notas.length; j++){
				if(notas[j] == i){
					System.out.print("*");	
				}		
			}
			System.out.print("\n");	
		}	
	}
}

