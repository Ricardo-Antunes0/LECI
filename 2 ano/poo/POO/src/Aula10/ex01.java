package Aula10;

import java.util.HashMap;
import java.util.Map;

public class ex01 {
    public static void main(String[] args) {
    
    Map<String, String> data = new HashMap<>();
    add(data, "branco", "cor da neve");
    add(data, "vermelho", "cor do benfica");
    add(data, "verde", "cor da bandeira");
    add(data, "azul", "cor do ceu");
    add(data, "cinzento", "cor do cimento");
    System.out.println(data);
    System.out.println(data.keySet());
    System.out.println(data.values());

    }

    public static void add(Map<String,String>data, String s, String s1){
        data.put(s, s1);
    }

    public static void change(Map<String,String>data, String s, String s1, String newS1){
        data.replace(s, s1, newS1);
    }

    public static void remove(Map<String,String>data, String s, String s1){
        data.remove(s,s1);
    }    
}
