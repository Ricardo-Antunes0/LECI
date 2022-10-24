
import java.util.Scanner;
public class ex1_14 {
	
	public static void main (String[] args) {
		
	Scanner sc = new Scanner(System.in);
	
	System.out.print("Introduza o valor do cateto 1: ");
	double cateto1 = sc.nextDouble();
	
	System.out.print("Introduza o valor do cateto 2: ");
	double cateto2 = sc.nextDouble();
	
	double hipotenusa;
	double hip2; //hipotenusa ao quadrado
	
	hip2 = Math.pow(cateto1,2) + Math.pow(cateto2,2);
	
	hipotenusa = Math.sqrt(hip2);
	
	double teta, angulo;
	
	teta =(cateto2/hipotenusa);

	angulo = Math.asin(teta);
		
	System.out.printf("A hipotenusa é: %f e o angulo entre o lado A e a hipotenusa é %f\n",hipotenusa,angulo);
		
	}
}

