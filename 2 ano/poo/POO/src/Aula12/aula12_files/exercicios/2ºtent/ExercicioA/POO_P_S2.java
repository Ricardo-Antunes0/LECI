package ExercicioA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class POO_P_S2 {

    public static void main(String[] args) {
        POO_P_S2 ap = new POO_P_S2();
        ap.alinea1();
        ap.alinea2();
    }

    
    public void alinea1() { 
    Cruzeiro cruz = criarCruzeiro(); 
    //imprime os produtos ordenados por data
    System.out.println(cruz); }
     

    
    public void alinea2(){ 
        Cruzeiro cruz = criarCruzeiro();  
        //coloque o código da alínea 2 aqui }

        try(PrintWriter pwm = new PrintWriter(new File("C:/Users/ricar/Desktop/UNI/3º ano/POO/PreviousTests/2ºtent/ExercicioA/SeaPrincess-2017.txt"))){
            int totalCab = 0;
            int cabVazias = 0;
            int nSuites = 0;
            int nOcupantesSuite = 0;

            for(Cabine c : cruz.getCabinesList()){
                totalCab++;
                if(c.getOcupantes().size() == 0){
                    cabVazias++;
                }
                
                if(c instanceof Suite){
                    Suite suite = (Suite) c;
                    if(c.getOcupantes().size() != 0){
                        nOcupantesSuite += c.getOcupantes().size();
                        nSuites += suite.getNumQuartos();
                    }
                }
            }
            double media = (double) nOcupantesSuite/nSuites;
            double percentagem = (double)(cabVazias/totalCab)*100;
            pwm.printf("Percentagem de cabines disponiveis para venda: %1.1f%n%n", percentagem);
            pwm.printf("Média pessoas por quarto nas Suites Ocupadas: %1.1f%n%n", media);
            pwm.println("Cabines ocupadas ordenadas por Número:" + "\n");

            for(Cabine c : cruz.getCabinesList()){
                if(c.getOcupantes().size() != 0){
                    pwm.print(c);
                }
            }

        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }

    }

    public Cruzeiro criarCruzeiro() {
        String[] aux = {"Lisboa", "Barcelona", "Rodes" , "Southampton"};
        Cruzeiro cr2 = new Cruzeiro("See Princess", aux, "22/01/2017");
        cr2.setDuracao(11);

        CabineJanela ccj = new CabineJanela(17, 2, Janela.INTERIOR);
        ccj.setOcupante(new String("Maria Luz;Manuel Luz").split(";")); cr2.add(ccj);
        cr2.add(new CabineJanela(15, 4, Janela.INTERIOR, new String ("António Campos;Maria Campos;Marina Mota").split(";")));
        cr2.add(new CabineJanela(25, 2, Janela.INTERIOR, "Anonymos1;Anonymos2".split(";")));
        cr2.add(new CabineJanela(4, 4, Janela.EXTERIOR, new String("Ursula Magnusson and Matts Magnusson and Miki Rosberg and Charles Sean").split(" and ")));
        Suite suite1 = new Suite(100, 2); suite1.setnQuartos(3);
        suite1.setCapMax(2*3); cr2.add(suite1);
        Suite s = new Suite(102, 6); s.setnQuartos(3); cr2.add(s);
        s.setOcupante(new String( "A. Jolie:B. Pitt:Shiloh:Knox Leon" ).split( ":" ));
        CabineVaranda cab = new CabineVaranda(21, 1, TipoVaranda.LIVRE);
        cab.setTipoVaranda(TipoVaranda.OBSTRUIDA);
        cab.Extra(Extra.DESPORTO);
        try {
            cab.setOcupante(new String("Paulo Portas;Júlia Portas" ).split( ";" ));
            cr2.add(cab);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Não adicionado devido a excesso de ocupantes !!");
        }

        Cabine eo = new CabineJanela(1, 4, Janela.EXTERIOR, "Marcelo R. de Sousa".split(";"));
        cr2.add(eo);
        cr2.add(new CabineJanela(130, 4, Janela.EXTERIOR));
        cr2.add(new CabineJanela(131, 4, Janela.INTERIOR));

        return cr2;
     }
    
}