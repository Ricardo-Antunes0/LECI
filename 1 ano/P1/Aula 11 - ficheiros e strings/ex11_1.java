import java.util.Scanner;
public class ex11_1 {
	static Scanner sc = new Scanner (System.in);
	public static void main (String[] args) {
	
	String frase = "";
	int min = 0;
	int max = 0;
	int digito = 0;
	int vogal = 0;
	int consoante = 0;
	
	System.out.print("Introduza uma frase: ");
	frase = sc.nextLine();
	
	for(int i = 0; i < frase.length(); i++){
		
		if(Character.isUpperCase(frase.charAt(i))){
			max++;
		} 
		if(Character.isLowerCase(frase.charAt(i))){
			min++;
		} 
		if(Character.isDigit(frase.charAt(i))){
			digito++;
		} 
		if(isVowel(frase.charAt(i))){
			vogal++;
		}
		if(isVowel(frase.charAt(i)) == false){
			consoante++;
		}
	}
	
	System.out.println("Análise de uma frase");
	System.out.printf("Frase de entrada ->%s\n", frase);
	System.out.printf("Número de caracteres minúsculos -> %d\n",min);
	System.out.printf("Número de caracteres maiúsculos -> %d\n",max);
	System.out.printf("Número de caracteres numéricos  -> %d\n",digito);
	System.out.printf("Número de caracteres vogais     -> %d\n",vogal);	
	System.out.printf("Número de caracteres consoantes -> %d\n",consoante);
		
	}
	public static boolean isVowel (char c){
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
			return true;
		}
		else{
			return false;
		}
	}
}

