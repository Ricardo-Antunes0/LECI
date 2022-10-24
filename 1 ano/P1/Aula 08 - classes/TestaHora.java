import java.util.Scanner;

public class TestaHora {
  static final Scanner sc = new Scanner(System.in);
  
  public static void main(String[] args) {
    Hora inicio;  // tem de definir o novo tipo Hora!
    Hora fim;
    
    inicio = new Hora();
    inicio.h = 9;
    inicio.m = 23;
    inicio.s = 5;
    
    System.out.print("Começou às ");
    printHora(inicio);  // crie esta função!
    System.out.println(".");
    System.out.println("Quando termina?");
    fim = lerHora();  // crie esta função!
    System.out.print("Início: ");
    printHora(inicio);
    System.out.print(" Fim: ");
    printHora(fim);
  }
  
  public static void printHora(Hora inicio){
	  
	  System.out.printf("%d:%d:%d\n",inicio.h,inicio.m,inicio.s);
  }
  
  public static Hora lerHora(){
	  Hora h = new Hora();
	  do{
		System.out.println("Insira um hora: ");
		h.h = sc.nextInt();
		h.m = sc.nextInt();
		h.s = sc.nextInt();
	  }while((h.h < 0 || h.h >= 24) && (h.m < 0 || h.m >=60) && (h.s < 0 || h.s >=60));
	
	return h;  
  }
  
}

class Hora {
	int h;
	int m;
	int s;	
}

/**
EXEMPLO do pretendido:
$ java TestaHora
Começou às 09:23:05.
Quando termina?
horas? 11
minutos? 72
minutos? 7
segundos? 2
Início: 09:23:05 Fim: 11:07:02.
**/
