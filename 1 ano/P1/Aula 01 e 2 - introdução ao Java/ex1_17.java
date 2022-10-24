import java.util.Scanner;
import static java.lang.System.*; 

public class ex1_17 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		out.print("Indique o valor dos produtos: ");
		double produtos = sc.nextDouble();
		
		out.print("Indique o desconto fornecido: ");
		double desconto = sc.nextDouble();
		
		out.print("Indique a taxa de iva de 0 a 100: ");
		double iva = sc.nextDouble();
		
		double total = ((produtos - desconto)*1.23);
		
		out.printf("O total liquido é: %f\n",total);
		
		
	}
}

