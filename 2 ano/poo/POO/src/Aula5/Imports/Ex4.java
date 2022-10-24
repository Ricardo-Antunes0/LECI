package Aula5.Imports;
import java.util.*;

import Aula3.Ex6;

public class Ex4 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    

        String [] data = leitura();
        int mes = Integer.parseInt(data[0]);
        int ano = Integer.parseInt(data[1]);
        int dia = Integer.parseInt(data[2]);   
     
        int diaMes = calculoMes(mes, ano);

        String meses[] = {"janeiro", "fevereiro", "março", "abril",
        "maio", "junho", "julho", "agosto", "setembro", "outubro",
        "novembro", "dezembro"};

        imprimir(meses[mes-1], ano, dia,diaMes);
    }


public static String[] leitura(){
    System.out.println("Intrudoza uma data composta pelo mês, pelo ano, e ainda pelodia da semana em que começa esse mês (1 = Segunda, 2 = Terça, 3 = Quarta, 4 = Quinta, 5 = Sexta, 6 = Sábado, 7 = Domingo) ");
    System.out.println("Ex: 3/2022/2");
    String line = sc.nextLine();
    
    String [] nums = line.split("/");
    return nums;
}

public static int calculoMes(int mes, int ano){
    switch(mes){
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return 31;
        case 2:
            if(Ex6.bissexto(ano))
                return 28;
            else return 29;
        case 4: case 6: case 9: case 11:
            return 30;
    }
    return 0;
}

public static void imprimir(String mes, int ano,int dia, int diaMes){
    System.out.printf("\t \t %s %d\n",mes ,ano);
    String [] aux = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

    for(int i = 0; i < aux.length; i++){
        System.out.printf("%s\t", aux[i]);
    }

    System.out.print("\n");
    int countDay = 0;

    //Percorrer as colunas para começar na coluna em questão (Ex: 2-> entao avanço 2 colunas)
    for(int i = 0; i < dia; i++){ 
        System.out.print("\t");
        countDay++; //Incremento sempre o meu countDay para saber quandias dias ja avançei
    }

    //For para imprimir todos os dias do mes
    for(int i = 1; i < diaMes; i++){
        System.out.printf("%d\t", i );
        countDay++; //incrementar sempre o countDay para saber quantas colunas andei
        if(countDay == aux.length){ //LIMITE, ou seja, quando o meu countDay = 7, quer dizer que tenho de passar para a linha de baixo
            System.out.print("\n");
            countDay = 0; //Reset no CountDay para assim quando chegar de novo ao 7 saber que tem de mudar de linha
        }
    }
}

}