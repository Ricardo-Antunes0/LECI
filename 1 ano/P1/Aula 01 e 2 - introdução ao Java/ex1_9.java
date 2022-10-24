import java.util.Scanner;
public class aula1 {
static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {

		double large;
		double compri;
		double area;
		double perimetro;
	
	
		System.out.print("Indique a largura: ");
		large = sc.nextDouble();
	
		System.out.print("Indique o comprimento");
		compri = sc.nextDouble();

		area = (compri*large);
		perimetro = (large + large + compri + compri);
		
		System.out.printf("A area é %4.1f e o perimetro é %4.1f",area, perimetro);
		
	}
}

