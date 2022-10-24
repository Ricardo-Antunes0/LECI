package Aula4;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduza uma frase: ");
        String line = sc.nextLine();

        System.out.printf("Acronimo: %s\n",acronimo(line));

    }
    
public static String acronimo(String a){
    String s = "";
    String [] aux;

    aux = a.split(" "); 
    for(int i = 0; i < aux.length; i++){
        if(aux[i].length() > 3) s += aux[i].charAt(0);
    }
    return s;
}

}