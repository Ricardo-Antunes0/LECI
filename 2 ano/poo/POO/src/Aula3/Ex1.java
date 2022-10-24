package Aula3;
import java.util.Scanner;
import java.lang.Math;

public class Ex1 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
     
        double notaP1, notaP2, notaT1, notaT2, notaP, notaT, notaF;
        

        System.out.println("Introduza as notas da componente pratica: ");

        notaP1 = LerNotas();
        while(notaP1 < 0 || notaP1 > 20){
            System.out.println("A nota tem de estar entre [0..20]");
            notaP1 = LerNotas();    
        }
        notaP2 = LerNotas();
        while(notaP2 < 0 || notaP2 > 20){
            System.out.println("A nota tem de estar entre [0..20]");
            notaP2 = LerNotas();
        }
        
        System.out.print("Introduza as notas da componente teorica: ");
        while(true){
            notaT1 = LerNotas();
            if( notaT1 >= 0 && notaT1<=20) break;
            System.out.print("A nota tem de estar entre [0..20]");
        }

        while(true){
            notaT2 = LerNotas();
            if(notaT2 >= 0 && notaT2<=20) break;
            System.out.print("A nota tem de estar entre [0..20]");
        }

        notaP = (notaP1 + notaP2) / 2;
        notaT = (notaT1 + notaT2) / 2;
        notaF = NotaFinal(notaP, notaT);
        
        System.out.printf("A sua nota final é: %3.0f", notaF);
    }

public static double LerNotas(){
    double x;
    x = sc.nextDouble();
    return x;       

}

public static double NotaFinal(double notaP, double notaT){
    double notaF;
    if (notaT < 7.0 || notaP < 7.0) notaF = 66;
    else notaF = Math.round(notaP) * 0.6 + Math.round(notaT) * 0.4;
    return notaF;
}



}