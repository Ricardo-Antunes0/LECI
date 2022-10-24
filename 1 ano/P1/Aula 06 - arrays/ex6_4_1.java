import java.util.Scanner;
public class ex6_4_1 {
	static Scanner sc = new Scanner (System.in);
	public static void main (String[] args) {
		
		System.out.println("Introduza o número de notas: ");
		int N = sc.nextInt();
		
		
		double [] a = new double [N];
		for(int i = 0; i < N; i++){
			do{
				System.out.println("Introduza uma nota entre 0 e 20: ");
				a[i] = sc.nextDouble();
			}while (a[i] < 0 || a[i] > 20);
		}
		histograma(a,N);
		
	}
	
	public static void histograma( double [] a,int N){
		System.out.println("Histograma de notas");
		System.out.println("-------------------------------------------------------");
		
		for(int i = 20; i >= 0; i--){
				System.out.printf("%3d  | ",i);
			for(int j = 0; j < a.length; j++){
				if(a[j] == i){
					System.out.print("*");
				}
			}
			System.out.print("\n");
		}
	}
}

