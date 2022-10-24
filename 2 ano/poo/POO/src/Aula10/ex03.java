package Aula10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ex03 {
    public static void main(String[] args) {
        String x = "Hello world";

        System.out.print(imprimir(x));
    }

    public static String imprimir(String x){
        Map<Character,ArrayList<Integer>> d = new HashMap<>();
        
        for(int i = 0; i < x.length(); i++){

            if(d.containsKey(x.charAt(i))){
                ArrayList<Integer> k = d.get(x.charAt(i));
                k.add(i);
                d.put(x.charAt(i),k);
            }
            else{
                d.put(x.charAt(i),new ArrayList<>(Arrays.asList(i)));
            }
            //introduzir nova key e introduzir novo valor
            
        }

        return d.toString();
    }
}


