package Aula6;

import Aula5.Date;

public class Test1 {
    public static void main(String[] args) {
   Aluno al = new Aluno ("Andreia Melo", 9855678,
    new Date(18, 7, 1990), new Date(1, 9, 2018));
   Bolseiro bls = new Bolseiro ("Igor Santos", 8976543, new Date(11, 5, 1985), 900);
   bls.setMontante(1050);
   
   System.out.println("Aluno: " + al.getNome());
   System.out.println(al);
   
   System.out.println("Bolseiro: " + bls.getNome() + ", NMec: "
   + bls.getNMec() + ", Bolsa: " + bls.getMontante());
   System.out.println(bls);
    }
   }
