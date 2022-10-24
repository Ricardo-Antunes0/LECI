package Aula5;
import java.io.IOException;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        calendario op = new calendario(0,0);
        int input, mes, diaDaSemana, ano;

        do{
            System.out.println("Calendar operations:");
            System.out.println("1 - create new calendar");
            System.out.println("2 - print calendar month");
            System.out.println("3 - print calendar");
            System.out.print("0 - exit\n> ");
            input = sc.nextInt();

            switch (input) {
                    case 1:
                    System.out.print("Ano: ");
                    ano = sc.nextInt();
                    System.out.print("Dia da semana [1;7]: ");
                    diaDaSemana = sc.nextInt();
                    op = new calendario(ano, diaDaSemana);
                    break;
                case 2:
                    System.out.print("Mes: ");
                    mes = sc.nextInt();
                    op.printMonth(mes);
                    break;
                case 3:
                    op.tostring();
                    break;
                case 0:
                    System.out.println("Saiu do programa!");
                    break;
            }
        }while(input != 0);
    }

}       

