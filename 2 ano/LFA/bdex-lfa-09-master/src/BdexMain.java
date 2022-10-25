import java.io.*;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;

import Structure.*;
import error.*;

public class BdexMain {
   public static void main(String[] args) {
      if (args.length == 0) {
         System.err.println("Usage: antlr4-java -ea BdexMain <source-file>");
         System.exit(1);
      }
      try {
         CharStream input = CharStreams.fromStream(new FileInputStream(args[0]));
         // create a lexer that feeds off of input CharStream:
         BdexLexer lexer = new BdexLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         BdexParser parser = new BdexParser(tokens);
         // replace error listener:
         parser.removeErrorListeners(); // remove ConsoleErrorListener
         parser.addErrorListener(new ErrorHandlingListener());
         //BVisitor visitor0 = new BVisitor();
         // begin parsing at main rule:
         ParseTree tree = parser.main();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            BdexSemanticCheck semantic = new BdexSemanticCheck();
            BdexCompiler compiler = new BdexCompiler();
            semantic.visit(tree);
            ST result = compiler.visit(tree);
            result.add("name", "Output");
            try {
               //PrintWriter pw = new PrintWriter(new File("Output.java"));
               PrintWriter pw = new PrintWriter(new OutputStreamWriter( new FileOutputStream(new File("Output.java")),StandardCharsets.UTF_8), true);
               pw.print(result.render());
               pw.close();
            } catch (IOException e) {
               System.err.println("Unable to write to file Output.java!");
               System.exit(3);
            }
         }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException f) {
         f.printStackTrace();
         System.exit(1);
      }
   }
}
