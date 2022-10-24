import java.util.Scanner;
public class ex2_1{
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduza a nota da componente tp1: ");
		double tpg1 = sc.nextDouble();
		System.out.print("Introduza a nota da componente tp2: ");
		double tpg2 = sc.nextDouble();
		System.out.print("Introduza a nota da componente pratica: ");
		double EI = sc.nextDouble();
		
		
		double notaF = ((0.2*tpg1) + (0.3*tpg2) + (0.5*EI));
		
		
		if(notaF >= 9.5){
			System.out.print("Aluno aprovado!!");
		
		} else{
			System.out.print("Aluno reprovado");
		}
	}
}

