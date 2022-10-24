package Aula4;
import java.util.*;
import static java.lang.System.*;

public class Ex2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza uma frase: ");
        String s = sc.nextLine();

        System.out.printf("1 -> Contem %d caracteres numericos\n",Count(s));
        System.out.printf("2 -> Contem %d espaços\n",CountSpace(s));
        if(lowerCase(s)) System.out.println("3 -> A frase só contem minúsculas!!");
        else System.out.println("3 -> Não contem só minusculas");
        System.out.println("4 -> String onde varios espacos sao substituidos por um unico: ");
        System.out.println(spaces(s));
        if(palindromo(s)) System.out.println("5 -> É um palindromo");
        else System.out.println("5 -> Não é um palindromo");
    }
    
public static int Count(String a){
    int count = 0;

    for(int i=0; i < a.length(); i++){
        if(Character.isDigit(a.charAt(i))) count++;
    }
    return count;  
}

public static int CountSpace(String a){
    int count = 0;

    for(int i = 0; i < a.length(); i++){
        if(Character.compare(a.charAt(i), ' ') == 0) count++; 
    }
    return count;
}

public static boolean lowerCase(String a){
    for(int i = 0; i < a.length(); i++){
        if(!Character.isLowerCase(a.charAt(i))) return false;
    }
    return true;
}

public static String spaces(String a){
    return a.replaceAll("\\s+", " ");
}

public static boolean palindromo(String a){
    String pal = "";
    for (int i = a.length()-1; i >= 0; i--) {
        pal = pal + a.charAt(i);
    }
    if (pal.toLowerCase().equals(a.toLowerCase())) {
        return true;
    } else {
        return false;
    }
}

}