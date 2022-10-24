package Aula2;
import java.util.Scanner;
public class Ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int temp;
        while(true){
            System.out.println("Insira um tempo em segundos: ");
            temp = sc.nextInt();
            if (temp >= 0)
                break;
            System.out.print("Try again!!!!\n");
        }
        int m = temp / 60;
        int s = temp % 60;
        int h = m / 60;
        m = m % 60;

        System.out.printf("%d:%d:%d \n",h,m,s);

    }
}
