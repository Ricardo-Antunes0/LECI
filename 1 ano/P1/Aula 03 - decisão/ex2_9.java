import java.util.Scanner;
public class ex2_9 {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		char c;
		
		System.out.print("Indique um valor para a temperatura: ");
		double temp = sc.nextDouble();
		
		
		do{
			System.out.println("Indique um caracter entre c ou f");
			c = sc.next().charAt(0);
		}while( c != 'c' && c != 'f');
		
		if (c == 'f'){
			double celsius;
			celsius = ((temp-32)/1.8);
			System.out.printf("A temperatura é: 3.1%f graus celsius \n",celsius);	
		}else{
			double far;
			far = ((1.8*temp) + 32);
			System.out.printf("A temperatura é: 4.1%f graus Fahrenheit \n",far);
		}
		
	}
}

