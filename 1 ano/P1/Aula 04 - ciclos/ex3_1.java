import java.util.Scanner;
public class ex3_1 {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner (System.in);
	
		System.out.print("Indique o numero de vezes que quer a mensagem impressa: ");
		int msg = sc.nextInt();
	
		for(int i = 0; i < msg; i++){
			System.out.println("P1 é fixe");	
		}
	
	
	}
}

