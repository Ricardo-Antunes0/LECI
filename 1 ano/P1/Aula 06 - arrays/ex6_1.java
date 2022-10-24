import java.util.Scanner;
public class ex6_1 {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N, num;
		
		System.out.print("Indique um numero inteiro: ");
		N = sc.nextInt();
		int [] a = new int [N];
		
		for(int i = 0; i < N; i++){
			System.out.print("Introduza um numero: ");
			 num = sc.nextInt();
			 a[i] = num;		 
		}
		
		for(int j = a.length-1; j >= 0; j--){
			System.out.println(a[j]);	
		}
		
	}
}

