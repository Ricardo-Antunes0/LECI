import static java.lang.System.*;
import java.util.Scanner;


public class TestData2 {
  static final Scanner in = new Scanner(System.in);

  public static void main(String[] args) {

    Data today = new Data();
    out.printf("today = %s\n", today);

    Data[] dates = {
            Data.parseData("987-12-31"), Data.parseData("988-01-01"),
            Data.parseData("2000-02-29"), Data.parseData("2018-02-27"),
            Data.parseData("2018-2-28"), Data.parseData("2018-03-1")};

    for (Data d: dates) {
      out.printf("%s: %s\n", d, d.extenso());
    }

    // Nenhuma destas strings é uma data válida:
    String[] invalid = {"25/04/1974", "1987-12",
                        "1988-13-01", "1900-02-29" };
    for (String s: invalid) {
      Data d = Data.parseData(s);
      out.printf("%s --> %s\n", s, d);  // d == null
    }

    for (Data d1: dates) {
      out.println();
      for (Data d2: dates) {
        int c = d1.compareTo(d2);
        String op;
        if (c < 0)
          op = "<";
        else if (c > 0)
          op = ">";
        else
          op = "=";
        out.printf("%s %s %s\n", d1, op, d2);
      }
    }
    
  }

}

