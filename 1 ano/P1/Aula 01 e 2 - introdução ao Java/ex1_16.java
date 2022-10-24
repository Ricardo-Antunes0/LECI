import java.util.Scanner;
public class ex1_16 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
						
		double despesaM, despesa1,despesa2,despesa3,despesa4;
						
		System.out.print("Indique quanto gastou no 1º dia: ");
		despesa1 = sc.nextDouble();
		
		for(int i = 1; i < 5; i++){

			
		}
		
		
			
		double total = despesa1 * 0.2;
		despesa2 = despesa1 + total;
		total = despesa2 * 0.2;
		despesa3 = despesa2 + total;
		total = despesa3 * 0.2;
		despesa4 = despesa3 + total;
		
		despesaM = (despesa1 + despesa2 + despesa3 + despesa4)/4;
	
		System.out.printf("O turista gastou em media %3.1f",despesaM);
		
		
	}
}

