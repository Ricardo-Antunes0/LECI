package Aula9;

public class DateND extends Date {
    private int days;

    public DateND(int days){
        this.days = days;
    }

    public void increment(){
       days++;
    }

    public void decrement(){
        days--;
    }

    @Override
    public String toString(){
        return String.format("%s", ndToYmd().toString());
    }

    private Object ndToYmd() {
        DateYMD temp = new DateYMD(1, 1, 2000);
        if (this.days >= 0) {
            for (int i = this.days; i > 0; i--) {
                temp.increment();
            }
        } else {
            for (int i = this.days; i < 0; i++) {
                temp.decrement();
            }
        }
        return temp;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
