import java.util.Scanner;
public class ex2_4 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int ano, mes,dia;
		
		System.out.print("Indique o ano: ");
		ano = sc.nextInt();
		
		do{
			System.out.print("Indique um mes: ");
			mes = sc.nextInt();
		}while( mes > 12 || mes < 1);
		
		System.out.print("Indique um dia: ");
		dia = sc.nextInt();
		
		switch(mes){
			case 1:
				System.out.print("O mes tem 31 dias");
			
			break;
			case 3:
				System.out.print("O mes tem 31 dias");
			break;
			case 5:
				System.out.print("O mes tem 31 dias");
			break;
			case 7:
				System.out.print("O mes tem 31 dias");
			break;
			case 8:
				System.out.print("O mes tem 31 dias");
			break;
			case 10:
				System.out.print("O mes tem 31 dias");
			break;
			case 12:
				System.out.print("O mes tem 31 dias");
			break;
			case 4:
				System.out.print("O mes tem 30 dias");
			break;
			case 6:
				System.out.print("O mes tem 30 dias");
			break;
			case 9:
				System.out.print("O mes tem 30 dias");
			break;
			case 11:
				System.out.print("O mes tem 30 dias");
			break;
			case 2:
				if((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0))){
					System.out.print("O mes tem 29 dias");
				} else{
					System.out.println("O mes tem 28 dias");
				}
		}
		
		
	}
}

