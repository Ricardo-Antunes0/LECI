import static java.lang.System.*;


public class DatasPassadas {
  public static void main(String[] args) {

    Data atual = new Data(19,3,2021);
    Data passada = new Data(25,12,2020);

    out.print(passada);
    /*
    while(passada != atual){
      out.printf("%s\n",passada.extenso());
      passada.seguinte();
      if(passada == atual) break;
    }
  */
    do{
      
      out.printf("%s\n",passada.extenso());
      passada.seguinte();
      if(passada == atual) break;
    }while(passada != atual);
  }
}

