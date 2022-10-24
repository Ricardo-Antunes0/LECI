package Aula10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class ex02 {
    public static void main(String[] args) {
    
        SortedMap<String, List<String>> data = new TreeMap<>();
        add(data, "branco", "cor da neve");
        add(data, "vermelho", "cor do benfica");
        add(data, "verde", "cor da bandeira");
        add(data, "azul", "cor do ceu");
        add(data, "cinzento", "cor do cimento");
        add(data, "cinzento", "cor do nevo");
        System.out.println(data);
        System.out.println(data.keySet());
        System.out.println(data.values());

        System.out.println("Remover");
        //remove(data, "cinzento", "cor do cimento");
        System.out.println(data);

        System.out.println("Change");
        add(data, "cinzento", "cor do cimento");
        change(data,"cinzento", "cor do cimento", "cor do nevo");
        System.out.print(data);

    }

    public static void add(Map<String, List<String>> data, String key, String value){
        if(data.containsKey(key)){
            List<String> k = data.get(key);
            k.add(value);
            data.put(key, k); 
        }
        else{
            data.put(key, new ArrayList<>(Arrays.asList(value)));
        }
    }

    public static void change(Map<String, List<String>> data, String key, String value, String newValue){
        List<String> k = data.get(key);

        if(k.contains(key)){
            k.remove(value);
            k.add(newValue);
        }
    }

    public static void remove(Map<String, List<String>> data, String key, String value){
        for(String x: data.get(key)){
            if(x == value)  data.get(key).remove(value);
        }
    }    

    public static String random(Map<String, List<String>> data, String key){
        return data.get(key).get((int) (Math.random() * (data.get(key).size())));
    }
}

