package Aula5.Imports;
import java.util.Scanner;
public class Ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String data = "";
        int mes, ano;

        System.out.println("Introduza o mes e o ano: ");
        System.out.println("Data deve conter / e numeros (12/2022)");
        do{
            data = sc.nextLine();
            String [] dia = data.split("/");
            mes = Integer.parseInt(dia[0]);
            ano = Integer.parseInt(dia[1]);

        }while((mes< 0 || mes>12) || ano < 0);

        switch(mes){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                System.out.printf("%d/%d", mes, ano);
                System.out.println(" tem 31 dias");
                break;
            case 2: System.out.printf("%d/%d ",mes, ano); 
                if(bissexto(ano)){
                    System.out.print(" tem 28 dias."); break;}
                else {
                    System.out.print(" tem 29 dias."); break;}
            case 4: case 6: case 9: case 11:
                System.out.printf("%d/%d", mes, ano);
                System.out.println(" tem 30 dias");
                break;
        }
    }
    public static boolean bissexto(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }
}
