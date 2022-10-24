import java.util.Scanner;

public class ex1_19 {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Indique o seu nome próprio: ");
		String NomePro = sc.nextLine(); //o programa vai ler a seguinte palavra
		
		System.out.print("Qual é o seu apelido: ");
		String apelido = sc.nextLine();
		
		String nomeCompleto = NomePro + (" ") + apelido;
		
		String acronimo = (NomePro.charAt(0)+ "" + apelido.charAt(0));
		
		boolean Maiuscula = false;
		
		if ((Character.isUpperCase(NomePro.charAt(0)) == true) && (Character.isUpperCase(apelido.charAt(0)) == true)){
			Maiuscula = true;
		}
		
		System.out.print(nomeCompleto);
		System.out.print(", ");
		System.out.print(acronimo.toUpperCase());
		System.out.print(", ");
		System.out.print(nomeCompleto.length());
		System.out.print(", ");
		System.out.print(Maiuscula);
		
		
		
		
		
	}
}

