package Aula5;
import Aula4.Ex4;

public class calendario {
    private int dayStart;
    private int year;

    public calendario(int year, int dayStart){
        assert dayStart >=1 && dayStart <= 7: "Dia da semana deve estar entre 1-domingo e 7-sábado";
        this.dayStart = dayStart;
        this.year = year;
    }

    public int year(){
        return this.year;
    }

    public int firstWeekdayOfYear(){
        return this.dayStart;
    }

    public int firstWeekdayOfMonth(int month){ 
        int dias = dayStart;
        for (int i = 1; i < month; i++) {
            dias += Date.monthDays(i, year);
        }
        while (dias > 7) {
            dias -= 7;
        }
        return dias;
    }
   
 
    public void printMonth(int month){
        //System.out.printf("\t \t %s %d\n",month, year());
        String [] aux = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
    
        for(int i = 0; i < aux.length; i++){
            System.out.printf("%s\t", aux[i]);
        }
        System.out.print("\n");
        int countDay = 0;
        

    
        //Percorrer as colunas para começar na coluna em questão (Ex: 2-> entao avanço 2 colunas)
        for(int i = 0; i < firstWeekdayOfMonth(month); i++){ 
            System.out.print("\t");
            countDay++; //Incremento sempre o meu countDay para saber quandias dias ja avançei
        }
    
        //For para imprimir todos os dias do mes
        for(int i = 1; i <= Ex4.calculoMes(month, year()); i++){
            System.out.printf("%d\t", i );
            countDay++; //incrementar sempre o countDay para saber quantas colunas andei
            if(countDay == aux.length){ //LIMITE, ou seja, quando o meu countDay = 7, quer dizer que tenho de passar para a linha de baixo
                System.out.print("\n");
                countDay = 0; //Reset no CountDay para assim quando chegar de novo ao 7 saber que tem de mudar de linha
            }
        }
        System.out.println();
    }
    
    public void tostring(){
        for(int i = 1; i <= 12; i++){
            printMonth(i);
        }
    }

}
