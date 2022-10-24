import java.util.Scanner;
public class ex2_10 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
	
	int ano, mes,dia;
		
		System.out.print("Indique o ano: ");
		ano = sc.nextInt();
		
		System.out.print("Indique um dia: ");
		dia = sc.nextInt();
		
		do{
			System.out.print("Indique um mes: ");
			mes = sc.nextInt();
		}while( mes > 12 || mes < 1);


	int diaProx = dia + 1;
	int diaAnt = dia - 1;
		
		switch(mes){
			case 1:
				System.out.println("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 3:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 5:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 7:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 8:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 10:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 12:
				System.out.print("O mes tem 31 dias");
				if(dia == 31){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);	
			break;
			case 4:
				System.out.print("O mes tem 30 dias");
				if(dia == 30){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);
				
			break;
			case 6:
				System.out.print("O mes tem 30 dias");
				if(dia == 30){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);
			break;
			case 9:
				System.out.print("O mes tem 30 dias");
				if(dia == 30){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);
			break;
			case 11:
				System.out.print("O mes tem 30 dias");
				if(dia == 30){
					System.out.print("Dia seguinte = 1\n");
					System.out.printf("Dia seguinte - %d\n", diaAnt);
				}
				System.out.printf("Dia seguinte - %d\n", diaProx);
				System.out.printf("Dia anterior - %d\n", diaAnt);
			break;
			case 2:
				if((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0))){
					System.out.print("O mes tem 29 dias");
					if(dia == 29){
						System.out.print("Dia seguinte = 1\n");
						System.out.printf("Dia anterior - %d\n", diaAnt);
					}else{
						System.out.printf("Dia seguinte - %d\n", diaProx);
						System.out.printf("Dia anterior - %d\n", diaAnt);
					}
				}else{	
					System.out.println("O mes tem 28 dias");
					if(dia == 28){
						System.out.print("Dia seguinte = 1\n");
						System.out.printf("Dia anterior - %d\n", diaAnt);
					}
					else{
					System.out.printf("Dia seguinte - %d\n", diaProx);
					System.out.printf("Dia anterior - %d\n", diaAnt);
					}
				}
		}
		
	}
}

