import java.util.Scanner;
import java.util.*;
public class ex1_18 {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		char caracterMin;
			
		System.out.print("Escreva um caracter minusculo: ");
		caracterMin = sc.nextLine().charAt(0);
			
		System.out.print("O caracter em maiuscula é: ");
		System.out.print(Character.toUpperCase(caracterMin));
		
		
	}
}

