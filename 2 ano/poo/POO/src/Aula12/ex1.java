package Aula11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ex1 {
    public static void main(String[] args) throws FileNotFoundException {
    
        Map<String, Map<String, Integer>> dados = new TreeMap<>();
        ArrayList<String> lista = new ArrayList<>();

        File myObj = new File("C:/Users/Ricardo Antunes/Desktop/UNI/3º ano/poo/POO/src/Aula11/aula11_files/major.txt");
        Scanner reader = new Scanner(myObj);
       
        while(reader.hasNextLine()){
            String[] aux = reader.nextLine().split("[\\s@&.?$+-,]+");

            for( String x : aux){
                if(x.length() >= 3){
                    lista.add(x);
                }
            }
        }
        reader.close();

        //For para percorrer as minhas palavras
        for(int i = 0; i < lista.size()-1; i++){
            String atual = lista.get(i);
            String next = lista.get(i+1);

            if(dados.containsKey(atual)){//Caso o meu Hashmap ja contenha a palavra, vou alterar o seu value
                dados.get(atual).put(next, dados.get(atual).getOrDefault(next, 0) + 1);
            }
            else{   //Caso ainda não tenho esta string no hashmap adiciono
                Map<String, Integer> temp = new HashMap<>();
                temp.put(next,1);
                dados.put(atual, temp);
            }

        }
        for (String par : dados.toString().substring(1, dados.toString().length()-2).split("}, ")) {
            System.out.println(par + "}");
        }
    }   
}
