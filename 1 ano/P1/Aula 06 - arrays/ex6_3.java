import java.util.Scanner;
public class ex6_3 {
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {
		
		int opcao;
		int a[] = new int [50];
		int num;
	do{
		System.out.println("1 - Ler uma sequência de números inteiros");
		System.out.println("2 - Escrever a sequência");
		System.out.println("3 - Calcular o máximo da sequência");
		System.out.println("4 - Calcular o mínimo da sequência");
		System.out.println("5 - Calcular a média da sequência ");
		System.out.println("6 - Detetar se é uma sequência só constituída por números pares");
		System.out.println("10 - Terminar o programa");
		System.out.println("Opçao -> ");
		opcao = sc.nextInt();
		
		int [] nums = lerSequencia();
		
		switch (opcao){
			case 1:
				lerSequencia();
				break;
			case 2:
				sequencia(nums);
				break;
			case 3:
				max(nums);
				break;
			case 4:
				min(nums);
				break;
			case 5:
				media(nums);
				break;
			case 6:
				pares(nums);
				break;
			case 10:
				terminarprograma();
				break;
			
		}
		
	}while(opcao != 1 || opcao != 2 || opcao != 3 || opcao != 4 || opcao != 5 || opcao != 6 || opcao != 10);	
	
	}
	
	public static int [] lerSequencia(){
		int a[] = new int [50];
		int x;
		
		for(int i = 0; i <= 50; i++){
			x = sc.nextInt();
			a[i] = x;
			if(x == 0){
				System.out.print("Numero igual a 0");
				break;
			}
		}
		return a;
	}
	
	public static void sequencia(int [] a){
		for(int i = 0; i <= 50; i++){
			System.out.println(a[i]);
		}
	}
	
	
	public static int max(int [] a){
		int maximo = 0;
		
		for(int i = 0; i <= 50; i++){
			if(a[i] < a[i+1]){
				maximo = a[i+1];
			}
		}	
		return maximo;
	}
	
	public static int min(int [] a){
		int minimo = 0;
		
		for(int i = 0; i <= 50; i++){
			if(a[i] < a[i+1]){
				minimo = a[i];
			}
		}	
		
		return minimo;
	}
	
	public static int media(int [] a){
		int med = 0;
		int count = 0;
		int mediat = 0;
		
		for(int i = 0; i <= 50; i++){
			med += (a[i] + a[i+1]);
			count++;
		}
		mediat = med/count;
		System.out.print(mediat);
		return mediat;
	}
	
	public static boolean pares(int [] a){
		
		boolean par = false;
		
		for(int i = 0; i <= 50; i++){
			if(a[i] % 2 == 0){
				par = true;
			}	
		}
		return par;
	}
	
	
	public static void terminarprograma(){
		System.exit(0);	
	}
	
	
	
	
	
}

