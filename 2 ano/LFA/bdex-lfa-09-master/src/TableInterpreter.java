import java.util.ArrayList;
import java.util.List;
import Structure.*;

public class TableInterpreter extends TableBaseVisitor<Object> {
   static Table table = null;
   List<String> columnNames = new ArrayList<>();
   List<String> columnTypes = new ArrayList<>();
   int numLines = 0;

   @Override public Object visitFile(TableParser.FileContext ctx) {
      visitChildren(ctx);
//    Uncomment for testing
//    if(table != null)
//       table.printTable();
      return table;
   }

   @Override public Object visitLine(TableParser.LineContext ctx) {
      numLines++;
      List<Object> line = new ArrayList<>();
      for(int i = 0; i < ctx.field().size(); i++) {
         Object field = visit(ctx.field(i));
         if(field != null)
            line.add(field);
      }
      if(numLines == 1)
         line.forEach(x -> columnNames.add((String) x));
      else if(numLines == 2) {
         Column[] columns = new Column[columnNames.size()];
         for(int i = 0; i < columnNames.size(); i++) {
            Object value = line.get(i);
            if(value instanceof Integer)
               columnTypes.add("Int");
            else if(value instanceof Double)
               columnTypes.add("Real");
            else
               columnTypes.add("Text");
            columns[i] = new Column(columnNames.get(i), columnTypes.get(i));
         }
         table = new Table(columns, line.toArray());
      } else {
         if(table != null) {
            if(line.size() == columnNames.size())
               table.addLineLast(line.toArray());
         }
      }
      return line;
   }

   @Override public Object visitField(TableParser.FieldContext ctx) {
      String res = "";
      if(ctx.TEXT() != null)
         res = ctx.TEXT().getText().trim();
      else if(ctx.STRING() != null) {
         res = ctx.STRING().getText().trim();
         res = res.substring(1, res.length()-1);
         res = res.replace("\"\"", "\"");
      }
      try {
         Integer value = Integer.parseInt(res);
         return value;
      } catch(NumberFormatException i) {
         try {
            Double value = Double.parseDouble(res);
            return value;
         } catch(NumberFormatException d) {
            return res;
         }
      }
   }
}