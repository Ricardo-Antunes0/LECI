package Aula11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class ex2 {
  public static void main(String[] args){
      
    Map<String, String> companhia = saveCompanhias();
    List<Voo> voos = getVoos(companhia);
    System.out.println(flightsTable(voos)); // a)
    writeInfopublico(flightsTable(voos)); // b)
    System.out.println(averagesTable(getAverages(voos))); // c)
  }

  public static Map<String,String> saveCompanhias(){
    Map<String,String> companhia = new HashMap<>();

    try {
      Scanner reader = new Scanner(new File("C:/Users/Ricardo Antunes/Desktop/UNI/3º ano/poo/POO/src/Aula11/aula11_files/companhias.txt"));
      reader.nextLine();
      while(reader.hasNextLine()){
        String []auxiliar = reader.nextLine().split("\t");
        companhia.put(auxiliar[0], auxiliar[1]);  
        //key = sigla
        //value = companhia
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return companhia;
  }

  public static List<Voo> getVoos(Map<String,String> companhia){
    List<Voo> voos = new ArrayList<>();

    try {
      Scanner reader = new Scanner(new File("C:/Users/Ricardo Antunes/Desktop/UNI/3º ano/poo/POO/src/Aula11/aula11_files/voos.txt"));
      reader.nextLine();
      while(reader.hasNextLine()){
        String []auxiliar = reader.nextLine().split("\t");
        companhia.putIfAbsent(auxiliar[1].substring(0, 2), "Unknown Airline");
        LocalTime atraso = auxiliar.length == 4 ? LocalTime.parse(auxiliar[3]) : LocalTime.of(0, 0);
        voos.add(new Voo(LocalTime.parse(auxiliar[0]),auxiliar[1], companhia.get(auxiliar[1].substring(0,2)), auxiliar[2],  atraso));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return voos; 
  }

  public static String flightsTable(List<Voo> voos) {
    StringBuilder stb = new StringBuilder("Hora   Voo       Companhia           Origem                Atraso   Obs\n");
    for (Voo x : voos) {
      stb.append(String.format("%s%n", x.toString()));
    }
    return stb.toString();
  } 
  
  public static void writeInfopublico(String str) {
    File aux = new File("C:/Users/Ricardo Antunes/Desktop/UNI/3º ano/poo/POO/src/Aula11/Infopublico.txt");
    try (FileWriter myWriter = new FileWriter(aux)) {
        myWriter.write(str);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public static Map<String, LocalTime> getAverages(List<Voo> voos) {
    Map<String, Pair<Integer, Integer>> inf = new HashMap<>();
    for (Voo voo : voos) {
        if (inf.putIfAbsent(voo.getCompanhia(), new Pair<>(voo.getAtraso().toSecondOfDay(), 1)) != null) {
            inf.put(voo.getCompanhia(),
                    new Pair<>(inf.get(voo.getCompanhia()).getValue0() + voo.getAtraso().toSecondOfDay(),
                            1 + inf.get(voo.getCompanhia()).getValue1()));
        }
    }
    // por cada companhia, dividir segundos por ocorrencias -> meter no dicionario
    Map<String, LocalTime> averages = new TreeMap<>();
    for (String companhia : inf.keySet()) {
        averages.put(companhia,
                LocalTime.ofSecondOfDay(inf.get(companhia).getValue0() / inf.get(companhia).getValue1()));
    }
    return averages;
  }

  public static String averagesTable(Map<String, LocalTime> mapa) {
    StringBuilder stb = new StringBuilder(String.format("Companhia%-11sAtraso médio\n", " "));
    for (String companhia : mapa.keySet()) {
      stb.append(String.format("%-20s%s\n", companhia, mapa.get(companhia)));
    }
    return stb.toString();
  }

}
