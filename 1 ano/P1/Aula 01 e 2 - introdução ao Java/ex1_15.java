import java.util.Scanner;

public class ex1_15 {
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduza a nota do teste prático1: ");
		double pr1 = sc.nextDouble();
		
		System.out.print("Introduza a nota do teste prático 2: ");
		double pr2 = sc.nextDouble();
			
		System.out.print("Introduza a nota do teste teorico-prático 1: ");
		double tp1 = sc.nextDouble();
		System.out.print("Introduza a nota do teste teorico-prático 2: ");
		double tp2 = sc.nextDouble();
		
		double notaF;
		
		notaF = ((0.25*pr1) + (0.35*pr2) + (0.1*tp1) + (0.1*tp2));	
		
		System.out.printf("A nota final do aluno é: %3.1f\n",notaF);
			
			
			
			
	}
}

