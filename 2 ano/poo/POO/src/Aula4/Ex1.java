package Aula4;
import java.util.*;
import static java.lang.System.*;

public class Ex1 {
   public static void main(String[] args) {
       
    Scanner sc = new Scanner(System.in);

    out.println("Introduza uma frase: ");
    String frase = sc.nextLine();

    out.printf("Frase convertida para minusculas: %s\n", frase.toLowerCase());
    
    out.printf("Ultimo caracter da frase: %s\n", frase.toLowerCase().charAt(frase.length()-1));
    out.printf("Tres 1ºs caracteres %s, %s, %s\n", frase.toLowerCase().charAt(0), frase.toLowerCase().charAt(1), frase.toLowerCase().charAt(2));
    } 
}
