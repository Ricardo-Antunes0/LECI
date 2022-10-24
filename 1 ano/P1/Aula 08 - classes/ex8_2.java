import java.util.Scanner;

public class ex8_2 {
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {
	
	Ponto2D [] pontos = new Ponto2D [100];
	
	int j = 0; 
	for(int i = 0; i < pontos.length; i++){
		pontos[i] = lerPonto();	
		j++;
		if(pontos[i].x == 0.0 && pontos[i].y == 0.0){
			break;
		}
	}
		
		
		
		
	}
	
	public static Ponto2D lerPonto(){
		Ponto2D ponto = new Ponto2D();
		
		System.out.print("Introduza um ponto: ");
		System.out.println("Coordenada x: ");
		ponto.x = sc.nextDouble();
		System.out.println("Coordenada y: ");
		ponto.y = sc.nextDouble();	
		
		return ponto;
	}
	
	public static void EscreverPonto(Ponto2D k){
		System.out.printf("%f, %f",k.x, k.y);	
	}
	
	public static double distancia(Ponto2D k){
		
		double dist = 0;
		
		dist = Math.sqrt(Math.pow((0.0 - k.x),2) + Math.pow((0.0 - k.y),2));
		
		return dist; 
		
	}
	
	
	
}

class Ponto2D {
	double x;
	double y;
}

