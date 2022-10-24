package Aula3;
import java.util.*;


public class Ex7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numb, cont = 0;
        String resp = "";

        Random random = new Random();
        int rand = random.nextInt(100);
        System.out.println("Vamos tentar adivinhar um número (inteiro) entre 1 e 100");

        while(true){
            numb = sc.nextInt();
            cont++;
            if( numb > rand){
                System.out.println("Valor demasiado alto!!");
            }else if (numb < rand){
                System.out.println("Valor demasiado baixo!!");
            }else{ 
                System.out.printf("ACERTOUUUUUUUUU em %d tentativas\n", cont);
                System.out.println("Pretende continuar??");
                System.out.println("Responda com S ou Sim");
                resp = sc.next();
                if (!(resp.equals("S") || resp.equals("Sim"))) break;
                System.out.println("Vamos tentar adivinhar um número (inteiro) entre 1 e 100");
            }
        }
    }
}
