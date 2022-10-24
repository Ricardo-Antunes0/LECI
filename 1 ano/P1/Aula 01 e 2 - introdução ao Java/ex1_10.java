import java.util.Scanner;
public class ex1_10 {

static Scanner teclado = new Scanner(System.in);
	
	public static void main (String[] args) {
	
	double temp;
	double f;
	
	System.out.print("Indique uma temperatura: ");
	temp = teclado.nextDouble();
	
	f = ((1.8*temp) + 32);
	
	System.out.printf("%4.1f ºCelsius é equivalente a %4.1f ºFahrenheit\n",temp,f);
	}
}

System.out.print( "GRAUS celsius " )
