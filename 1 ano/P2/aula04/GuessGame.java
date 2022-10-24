import static java.lang.System.*;

public class GuessGame {

  private int numAttempts;
  private int tentative;
  private int secretNumber; // to hold the secret
  private int min;
  private int max;
  
  //...

  public GuessGame(int min, int max) {
    //...
    assert min <= max : "O minimo tem que ser menor que o maximo!";
    this.min = min;
    this.max = max;
    secretNumber = (int)(Math.random() * (max - min + 1)) + min;
  }
  public int min() {
    //...
    return min;
  }

  public int max() {
    //...
    return max;
  }
  public int numAttempts() {
    //...
    return numAttempts;
  }


  public boolean validAttempt(int n) {
    //...
    // assert n >= min && n <= max: "n tem que estar entre o dominio";
    return n >= min && n <= max;
  }

  public boolean finished() {
    //...
    return secretNumber == tentative;
  }
  
  public void play(int n) {
    //.
    assert validAttempt(n);
    assert !finished();
    
        numAttempts++;
		    tentative = n;
      }
    }
  }

  public boolean attemptIsHigher() {
    //...
    return tentative > secretNumber;
  }

  public boolean attemptIsLower() {
    //...
    return tentative < secretNumber;
  }

  /**
   * Simple tests of the GuessGame class.
   * This method tests the functionality of the GuessGame class.
   * It should be used by the programmer to test and debug the class.
   * It is not meant to be called in client programs.
   *
   * To run from the command line, use:
   *   java -ea GuessGame
   */
  public static void main(String[] args) {
    requireEA();
    out.println("Starting tests.");
    GuessGame game = new GuessGame(1, 10);
    // initial tests:
    assert !game.finished() : "game should not be finished yet";
    assert game.min() == 1;
    assert game.max() == 10;
    assert game.numAttempts() == 0 : "there should be no attempts yet";
    for(int i = -10; i <= 20; i++) {
      assert game.validAttempt(i) == (i >= 1 && i <= 10);
    }
    // trying all wrong answers:
    int nplay = 0; // how may times play was called
    for(int n = 1; n <= 10; n++) {
      if (n != game.secretNumber) {
        game.play(n); // make a wrong guess
        nplay++;
        assert game.numAttempts() == nplay;
        assert !game.finished() : "game should not be finished yet";
        assert (n < game.secretNumber) == game.attemptIsLower();
        assert (n > game.secretNumber) == game.attemptIsHigher();
      }
    }
    // make the right guess:
    game.play(game.secretNumber);
    nplay++;
    assert game.finished() : "game should be finished now";
    assert game.numAttempts() == nplay;
    out.println("No error detected!");
  }
  /** Check if program is being run with -ea, exit if not. */
  static void requireEA() {
    boolean ea = false;
    assert ea = true; // assert with a side-effect, on purpose!
    if (!ea) {
      err.println("This program must be run with -ea!");
      exit(1);
    }
  }
}

