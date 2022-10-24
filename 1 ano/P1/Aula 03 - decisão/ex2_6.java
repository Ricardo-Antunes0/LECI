import java.util.Scanner;
public class ex2_6 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Indique um numero inteiro: ");
		int num = sc.nextInt();
		
		if((num%2 == 0)){
			System.out.print("Par");
		}
		else{
			System.out.print("Impar");
		}
		
		
	}
}

