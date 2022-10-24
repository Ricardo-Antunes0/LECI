package Aula9;
import Aula3.Ex6;
import Aula4.Ex4;

public abstract class Date {

    public static boolean validMonth(int month){
        return (month < 12 && month > 1);
    }

    public static boolean valid(int day,int month,int year){
        return (year > 0 && validMonth(month) && (day <= monthDays(month, year) && day > 0));
    }

    public static int monthDays(int month,int year){
        return Ex4.calculoMes(month, year);
    }

    public static boolean leapYear(int year){
        return Ex6.bissexto(year);
    }

    public abstract void increment();

    public abstract void decrement();


    public abstract String toString();

}