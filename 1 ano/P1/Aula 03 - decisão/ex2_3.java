import java.util.Scanner;
public class ex2_3 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int idade;
		
		System.out.print("Indique a sua idade: ");
		idade = sc.nextInt();
		
		if(idade < 6){
			System.out.print("O seu bilhete é isento de pagamento");	
		} 
		else if(idade >= 6 && idade <= 12){
			System.out.print("Bilhete de criança");	
		}
		else if( idade >= 13 && idade <= 65){
			System.out.print("Bilhete normal");
		}
		else{
			System.out.print("Bilhete de 3ª idade");
		}
	}
}

