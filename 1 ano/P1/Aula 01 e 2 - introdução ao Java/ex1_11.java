import java.util.Scanner;

public class ex1_11 {
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {
		
	double dolars, euros;
	double conversao;
	
	System.out.print("Indique uma quantia em dolars: ");
	dolars = sc.nextDouble();
	
	
	System.out.print("Indique uma taxa de conversão: ");
	conversao = sc.nextDouble();
	
	
	
	euros = (dolars*conversao);
	
	System.out.printf("%f dólares equivalem a %f euros", dolars, euros);
		
		
		
	}
}

