package Aula3;
import java.util.*;
import java.lang.Math;
import Aula3.Ex1;

public class Ex8 {
    public static void main(String[] args) {
        double notas[][] = new double [16][2];
        double nota;

        for(int i = 0; i < notas.length; i++){
            notas[i][0] = Math.random()*20;
            notas[i][1] = Math.random()*20;
        }

        System.out.println("NotaT \t NotaP \t Pauta");
        for(int k = 0; k < notas.length; k++){
            nota = Ex1.NotaFinal(notas[k][0],notas[k][1]);
            System.out.printf("%2.1f \t %2.1f \t %2.0f\n",notas[k][0], notas[k][1], nota);
        }
    }    
}
