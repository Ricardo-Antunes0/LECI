package Aula7;
import Aula3.Ex6;
import Aula4.Ex4;
import Aula7.Date;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class DateYMD extends Date{
    private int day, month, year;

    public DateYMD(int day, int month, int year){
        this.set(day,month,year);    
    }
    public DateYMD() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.day = Integer.parseInt(dtf.format(now).split("/")[2]);
        this.month = Integer.parseInt(dtf.format(now).split("/")[1]);
        this.year = Integer.parseInt(dtf.format(now).split("/")[0]);
    }

    public void increment(){
        if(day == monthDays(month, year) && month==12){
            year++;
            day = 1;
            month = 1;
        }
        else if(day == monthDays(month, year)){
            month++;
            day = 1;
        }
        else{day++;}
    }

    public void decrement(){
        if (day == 1 && month == 1){
            day = monthDays(month, year);
            month = 12;  
            year--;
        }
        else if(day == 1){
            day = monthDays(month, year);
            month--;
        }
        else{
            day--;
        }
    }

    @Override
    public String toString(){
        return (year+"-"+month+"-"+day);
    }

    public int getMes(){
        return month;
    }
    
    public int getdia(){
        return day;
    }
    
    public int getYear(){
        return year;
    }

    public void set(int day,int month,int year){
        assert valid(day, month, year): "Data deve ser valido";
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean validMonth(int month){
        return (month < 12 && month > 1);
    }

    public static int monthDays(int month,int year){
        return Ex4.calculoMes(month, year);
    }

    public static boolean leapYear(int year){
        return Ex6.bissexto(year);
    }

    public static boolean valid(int day,int month,int year){
        return (year > 0 && validMonth(month) && (day <= monthDays(month, year) && day > 0));
    }
}