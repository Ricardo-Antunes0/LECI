import static java.lang.System.*;
import java.util.Scanner;

public class p14 {
	public static final Scanner in = new Scanner(System.in);
	public static void main (String[] args) {
	
	String frase, newFrase;		
	do{	
		out.print("Introduza uma frase: ");
		frase = in.nextLine();	
	}while(frase.isEmpty());	
	newFrase = frase.replace("r","").replace("l","u").replace("L","U").replace("R","");;	
		
	out.print(newFrase);
	}
}

