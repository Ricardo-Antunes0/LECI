import static java.lang.System.*;
import java.util.Scanner;
import p2utils.*;

public class Palindrome {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String frase = "";

        Stack <String> pilha = new Stack <>();
        Queue <String> fila = new Queue <>();

        out.println("Introduza uma  frase: ");
        frase = sc.nextLine();
        frase.toLowerCase();
        
        for(String c : frase.split("")){
            if(Character.isDigit(c.charAt(0)) || Character.isLetter(c.charAt(0))){
                pilha.push(c);
                fila.in(c);
            }
        }

        boolean f = false;

        while(!fila.isEmpty()){
            if(fila.peek().equals(pilha.top())){
                pilha.pop();
                fila.out();
            }
            else{
               f = true;
               break;
            }
        }
        
        if(f){ 
            out.printf("%s nao e um palindromo", frase);
        }
        else{ 
            out.printf("%s e um palindromo", frase);
        }
    }
}
