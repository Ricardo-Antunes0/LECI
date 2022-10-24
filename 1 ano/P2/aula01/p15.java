import static java.lang.System.*;
import java.util.Scanner;

public class p15 {
	public static final Scanner in = new Scanner(System.in);
	public static void main (String[] args) {
	
	double num;
	int cont = 0;
	//double a [] = new double [100];	
	double sum = 0;
	double med = 0;
		
	out.println("Introduza uma sequencia de numeros");
	do{
		num = in.nextDouble();
		//a[cont] = num;
		sum += num;
		cont++;
	}while(num != 0); //se o numero for igual a 0 acaba
	
	//for(int i = 0; i < cont-1; i++){
		//sum += a[i];
	//}
		
	med = sum/(cont-1);	
	
	out.printf("Soma  = %f\n",sum);		
	out.printf("Média = %f\n",med);	
	}
}

