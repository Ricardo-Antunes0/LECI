package Aula10;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class ex04 {
    public static void main(String[] args) throws IOException{

        List<String> x = new ArrayList<>();
        Scanner input = new Scanner(new FileReader("words.txt"));

        System.out.print("Palavras: ");
        
        while (input.hasNext()) {
            String word = input.next();
            if(word.length()> 2)
                x.add(word);
            
            System.out.print(word+" ");
        }

        System.out.print("\nAcabam com 's': ");
        for(String i: x){
            if(i.endsWith("s"))
                System.out.print(i+" ");
        }

        System.out.print("\nStrings que só contem caracteres que sejam letras: ");

        Iterator<String> it = x.iterator();

        while (it.hasNext()) {
            if (!it.next().matches("[a-zA-Z]+")){
                it.remove();
            }
        }
        System.out.println(x);
    }
}
