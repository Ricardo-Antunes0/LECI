import static java.lang.System.*;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.Calendar;

public class Data {
  private int dia, mes, ano;

  /** Inicia esta data com o dia de hoje. */
  public Data() {
    // Aqui usamos a classe Calendar da biblioteca standard para obter a data atual.
    Calendar today = Calendar.getInstance();
    dia = today.get(Calendar.DAY_OF_MONTH);
    mes = today.get(Calendar.MONTH) + 1;
    ano = today.get(Calendar.YEAR);
  }

  /** Inicia a data a partir do dia, mes e ano dados. */
  public Data(int dia, int mes, int ano) {
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
  }

  /** Devolve esta data segundo a norma ISO 8601. */
  public String toString() {
    return String.format("%04d-%02d-%02d", ano, mes, dia);
  }

  /** Indica se ano é bissexto. */
  public static boolean bissexto(int ano) {
    return ano%4 == 0 && ano%100 != 0 || ano%400 == 0;
  }

  // Crie métodos para obter o dia, mes e ano da data.
  //...
  public int dia(){
    return dia;
  }

  public int mes(){
    return mes;
  }

  public int ano(){
    return ano;
  }


  /** Dimensões dos meses num ano comum. */
  private static final
  int[] diasMesComum = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  /** Devolve o número de dias do mês dado. */
  public static int diasDoMes(int mes, int ano) {
    
   //fazer UM assert para ver se o mes esta dentro dos valores
    int dia;
    switch (mes){
      case 1: case 3: case 5: case 7: case 8: case 10: case 12:
      dia = diasMesComum[1];
      break;

      case 4: case 6: case 9: case 11:
      dia = diasMesComum[3];
      break;

      default:
        if(bissexto(ano)){
          dia = 29;
        }
        else{
          dia = 28;
          
        }
      break;
    }
    return dia;
  }

  String [] mesExtenso = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
  /** Devolve o mes da data por extenso. */
  public String mesExtenso() {
    String s = "";
    switch(this.mes){
      case 1: 
      s = mesExtenso[0];
      break;

      case 2:
      s = mesExtenso[1];
      break;

      case 3:
      s = mesExtenso[2];
      break;

      case 4:
      s = mesExtenso[3];
      break;
      
      case 5:
      s = mesExtenso[4];
      break;

      case 6:
      s = mesExtenso[5];
      break;

      case 7:
      s = mesExtenso[6];
      break;

      case 8:
      s = mesExtenso[7];
      break;

      case 9:
      s = mesExtenso[8];
      break;

      case 10:
      s = mesExtenso[9];
      break;

      case 11:
      s = mesExtenso[10];
      break;

      case 12:
      s = mesExtenso[11];
      break;
    }
      
    return s;
  }

  /** Devolve esta data por extenso. */
  public String extenso() {
    String f = "";

    f = this.dia+" de "+ mesExtenso()+ " de "+ this.ano;

    return f;
  }

  /** Indica se um terno (dia, mes, ano) forma uma data válida. */
  public static boolean dataValida(int dia, int mes, int ano) {
    
      assert dia > 0 && dia < 32 : "Valor do dia inválido!!!!";
      assert mes > 0 && mes < 13 : "Valor do mês inválido!!!!";
      assert ano > 0;

      return true;
  }


  public void seguinte() {
    
    switch(this.mes){
      case 1: case 3: case 5: case 7: case 8: case 10: case 12:
      
      if(dia == 31){
        if(mes == 12){
          mes = 1;
          ano += 1;
        }
        dia = 1;
        mes += 1;
      }
      else{
        dia += 1;
      }
      break; 

      case 4: case 6: case 9: case 11:
        if(dia == 30){
          dia = 1;
        }
        else{
          dia += 1;
        }
        mes += 1;
      break;
      default: 
        if(bissexto(this.ano)){  //Ver se é preciso por o this ou nao !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
          if(dia == 29){
            dia = 1;
            mes += 1;
          }
          else{
            dia += 1;
          }
        }
        else{
          if(dia == 28){
            dia = 1;
            mes += 1;
          }
          dia += 1;
        }
        break;
    }


  }


}

