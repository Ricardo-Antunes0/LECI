import java.util.Scanner;
public class ex1_13 {

	public static void main (String[] args) {

		Scanner sc = new Scanner(System.in);	
		double Ax, Ay, Bx, By;
		
		System.out.print("Introduza a posicao x: ");
		Ax= sc.nextDouble();
		System.out.print("Introduza a posicao y: ");
		Ay = sc.nextDouble();
		
		System.out.print("Introduza a posicao x: ");
		Bx = sc.nextDouble();
		System.out.print("Introduza a posicao y: ");
		By = sc.nextDouble();
		
		double dist;
		int escala = 100;
		
		dist = (Math.sqrt(Math.pow((Bx-Ax),2) + Math.pow((By-Ay),2)) * escala);
		
		System.out.printf("A distancia, em linha reta, é %4.1f\n",dist);
		
		
		
		
	}
}

