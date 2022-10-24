/*
 * JAM, out-2018
 *
 * Nesta classe deve definir novas funções e testá-las na função main.
 */

public class Functions {

	public static void main (String args[]) {
		// Testar função sqr:
		System.out.printf("sqr(%f) = %f\n", 10.1, sqr(10.1));
		System.out.printf("sqr(%f) = %f\n", -2.0, sqr(-2.0));

		// Invoque as funções pedidas no enunciado para as testar:
		// Por exemplo, para testar func f (problema 5.2):
		System.out.printf("f(%d) = %f\n", 5, f(5));

		// Testar as restantes funções desenvolvidas
		System.out.printf("max(%f,%f) = %f\n", 3.1, 5.2, max(3.1, 5.2));
		System.out.printf("max(%d,%d) = %d\n", 3, 2, max(3, 2));
		
		
		
		//int ano = getIntPos("Ano? ");
		//System.out.printf("ano = %d\n", ano);

	}

	/*
	 * sqr - calcula o quadrado de um número (real).
	 */
	public static double sqr(double x) {
		double y;	// variavel para resultado
		y = x*x;	// calculo do resultado a partir dos dados
		return y;	// devolver o resultado
	}

	// Defina as funções pedidas no enunciado:
	public static double f (int x){
		double n;
		n = 1.0/(1+(x*x));
		
		return n;
	}
	
	public static double max(double x, double y){
		double k;
		if(x > y){
			k = x;
			return k;
		}
		else if( y > x){
			k = y;
			return k;
		} else{
		 k = x = y;
		}
	return k;
	}
	
	public static double max(int x, int y){
		double k;
		
		
	}
}
