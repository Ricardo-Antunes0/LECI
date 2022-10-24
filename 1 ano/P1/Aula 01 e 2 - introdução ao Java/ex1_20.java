import java.util.Scanner;
public class ex1_20 {	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t1x, t1y, t2x, t2y;
		
		do{
			System.out.print("Indique 2 valores inteiros: ");
			t1x = sc.nextInt();
			t1y = sc.nextInt();
			if(t1x > t1y) {
				System.out.print("O 1º valor tem de ser > que o 2º\n");
			}
		}while(t1x > t1y);
		
		do{		
			System.out.print("Indique mais 2 valores inteiros: ");
			t2x = sc.nextInt();
			t2y = sc.nextInt();
			if(t1x > t1y) {
				System.out.print("O 1º valor tem de ser > que o 2º\n");
			}
		}while(t2x > t2y);
		
		System.out.print("Intervalo 1: " +t1x+ " " +t1y+ "\n");
		System.out.print("Intervalo 2: "+t2x +" "+t2y+ "\n");
		
		
		System.out.println(cruzamento(t1x, t1y, t2x, t2y));
		
	}
	public static boolean cruzamento(int t1x, int t1y, int t2x, int t2y){
		boolean cruzam;
		if((t1x >= t2x && t1x <= t2y) || (t2x >= t1x && t2x < t1y)){
			cruzam = true;
		}else{
		cruzam = false;
	}
	return cruzam;
	
	
}
}
