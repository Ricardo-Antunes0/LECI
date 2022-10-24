import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class SortInts
{
  public static void main(String[] args) throws IOException {
  
    if(args.length == 0)
      out.print("java -ea Sortints file1.txt");

    SortedListInt hf = new SortedListInt ();

    File f;

    for(int i = 0; i < args.length; i++){
      f = new File(args[i]);

      assert f.isFile(): "It must be a file!!";

      hf = insertNumbers(hf, new Scanner(f));
      assert hf.isSorted();

      hf.print();
    }

  }

  public static SortedListInt insertNumbers(SortedListInt hf, Scanner sc){

    if(!sc.hasNext()) return hf;
    try{
      int elem = Integer.parseInt(sc.nextLine());
      hf.insert(elem);
    }catch(NumberFormatException e){

    }
    return insertNumbers(hf, sc);
  }
}

