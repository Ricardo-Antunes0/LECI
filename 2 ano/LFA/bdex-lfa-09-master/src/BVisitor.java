import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import Structure.*;
import error.ErrorHandling;

public class BVisitor extends BdexBaseVisitor<Object> {
   static SymbolTable variables = new SymbolTable();

   @Override public Object visitMain(BdexParser.MainContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Object visitStatAddLine(BdexParser.StatAddLineContext ctx) {
      return visit(ctx.add_line());
   }

   @Override public Object visitStatAddColumn(BdexParser.StatAddColumnContext ctx) {
      return visit(ctx.add_column());
   }

   @Override public Object visitStatRemoveColumn(BdexParser.StatRemoveColumnContext ctx) {
      return visit(ctx.remove_column());
   }

   @Override public Object visitStatRemoveLine(BdexParser.StatRemoveLineContext ctx) {
      return visit(ctx.remove_line());
   }

   @Override public Object visitStatPutValue(BdexParser.StatPutValueContext ctx) {
      return visit(ctx.put_value());
   }
   
   @Override public Object visitStatPrint(BdexParser.StatPrintContext ctx) {
      return visit(ctx.print());
   }

   @Override public Object visitStatAssign(BdexParser.StatAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public Object visitStatIterate(BdexParser.StatIterateContext ctx) {
      return visit(ctx.iterate());
   }

   @Override public Object visitStatIncrement(BdexParser.StatIncrementContext ctx) {
      return visit(ctx.increment());
   }

   @Override public Object visitStatSaveTable(BdexParser.StatSaveTableContext ctx) {
      return visit(ctx.save_table());
   }

   @Override public Object visitStatLoadTable(BdexParser.StatLoadTableContext ctx) {
      return visit(ctx.load_table());
   }

   //TABLE
   @Override public Object visitCreateTable(BdexParser.CreateTableContext ctx){
      return (Table) visit(ctx.create_table());
   }

   @Override public Object visitExtractTable(BdexParser.ExtractTableContext ctx){
      return (Table) visit(ctx.extract_table());
   }

   @Override public Object visitJoinTables(BdexParser.JoinTablesContext ctx){
      return (Table) visit(ctx.join_tables());
   }

   @Override public Object visitVariableTable(BdexParser.VariableTableContext ctx){
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(variables.containsKey(aux)){
         return (Table) variables.get(aux);
      }else{
         System.err.println("No such variable name with type Table");
      }
      return null;
   }

   
   //CREATE TABLE
   
   /**
    * Cria uma tabela com as colunas pretendidas, sem nenhuma linha.
    * @param ctx
    * @return
    */
   @Override public Object visitCreateTableNoLine(BdexParser.CreateTableNoLineContext ctx) {
      Table tmp = null;
   	Column [] array = (Column[]) visit(ctx.columns());
      tmp = new Table(array);
      return tmp;
   }

   /** 
    * Cria uma tabela com as colunas desejadas e com um número de linhas específico.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitCreateTableLine(BdexParser.CreateTableLineContext ctx) {
   	Table tmp = null;
      Integer c = null;
      try{
         c = (Integer) visit(ctx.line);
      }catch(ClassCastException e){
         System.err.println("The expression result must be of the type Int");
      }
   	Column [] array = (Column[])visit(ctx.columns());
   	tmp = new Table(array, c);
      return tmp;
   }
   
   
   /** 
    * Cria uma tabela com as colunas pretendidas e adiciona valores nelas.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitCreateTableValues(BdexParser.CreateTableValuesContext ctx) {
   	Table tmp = null;
   	Column[] array = (Column[]) visit(ctx.columns());
   	Object[] values = (Object[]) visit(ctx.values());

   	if(values.length % array.length != 0) {
   	   System.err.println("Must provide values % columns = 0");
   	   return null;
   	}
   	tmp = new Table(array, values);
   	return tmp;
   }

   //ADD
   
   /** 
    * Numa tabela específica, é adicionada uma linha no índice pretendido com os respetivos valores.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddLineValues(BdexParser.AddLineValuesContext ctx) {
      Table tmp = null;
      Integer c = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux) ){
         tmp = (Table) variables.get(aux);
         Object [] values = (Object[]) visit(ctx.values());
         if(ctx.index==null){
            tmp.addLineLast(values);
         }else{
            try{
               c = (Integer) visit(ctx.index);
               tmp.addLineIndex(c, values);
            }catch(ClassCastException e){
               System.err.println("The expression result must be of the type Int");
               return null;
            }
         }
         variables.put(aux, tmp);
      }
      return tmp;
   }
 
   /** 
    * Numa tabela específica, é adicionada uma linha no índice pretendido com valores por omissão.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddLineIndexOmission(BdexParser.AddLineIndexOmissionContext ctx) {
      Table tmp = null;
      Integer c = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)){
         tmp = (Table) variables.get(aux);
         if(ctx.index==null){
            tmp.addLineLast();
         }else{
            try{
               c = (Integer)visit(ctx.index);
            }catch(ClassCastException e){
               System.err.println("The expression result must be of the type Int");
            }
            tmp.addLineIndex(c);
         }
         variables.put(aux, tmp);
      }
      return tmp;
   }
   
   
   /** 
    * É Adicionada à tabela uma coluna antes ou depois de uma certa coluna da tabela. 
    * São adicionados à coluna valores por omissão.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddColumnOmissionPlace(BdexParser.AddColumnOmissionPlaceContext ctx) {
      Table tmp = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)) {
         tmp = (Table) variables.get(aux);
         int index = tmp.getNames().indexOf((Column) visit(ctx.place));
         switch(ctx.where.getText()){
            case "after":
               tmp.addColumnIndex((Column) visit(ctx.to), index+1);
               break;
            case "before":
               if(index == 0)
                  tmp.addColumnIndex((Column) visit(ctx.to), index);
               else
                  tmp.addColumnIndex((Column) visit(ctx.to), index-1);
               break;
         }

         variables.put(aux, tmp);
      }
      return tmp;
   }

   /** 
    * Adiciona à tabela uma coluna antes ou depois de uma certa coluna da tabela. 
    * São adicionados à coluna valores por omissão.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddColumnValuesPlace(BdexParser.AddColumnValuesPlaceContext ctx) {
      Table tmp = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)) {
         tmp = (Table) variables.get(aux);
         Object [] values = (Object[]) visit(ctx.values());
         int index = tmp.getNames().indexOf((Column) visit(ctx.place));
         switch(ctx.where.getText()){
            case "after":
               tmp.addColumnIndex((Column) visit(ctx.to), values, tmp.getNames().indexOf((Column) visit(ctx.place))+1);
               break;
            case "before":
               if(index == 0)
                  tmp.addColumnIndex((Column) visit(ctx.to), values, index);
               else
                  tmp.addColumnIndex((Column) visit(ctx.to), values, index-1);
               break;
         }
         variables.put(aux, tmp);
      }
      return tmp;
   }

   /** 
    * Adiciona à tabela uma coluna antes da 1ª ou da última coluna.
    * São adicionados à coluna valores por omissão.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddColumnOmissionFL(BdexParser.AddColumnOmissionFLContext ctx) {
      Table tmp = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)) {
         tmp = (Table) variables.get(aux);
         if(ctx.where!=null){
            switch(ctx.where.getText()){
               case "last":
                  tmp.addColumnLast((Column) visit(ctx.to));
                  break;
               case "first":
                  tmp.addColumnIndex((Column) visit(ctx.to), 0);
                  break;
            }
         }else{
            tmp.addColumnLast((Column) visit(ctx.to));
         }
         variables.put(aux, tmp);
      }
      return tmp;
   }

   
   /** 
    * Adiciona à tabela uma coluna antes da 1ª ou da última coluna, com valores introduzidos pelo utilizador.
    * São adicionados à coluna valores por omissão.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitAddColumnValuesFL(BdexParser.AddColumnValuesFLContext ctx) {
      Table tmp = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux) ) {
         tmp = (Table) variables.get(aux);
         Object [] values = (Object[]) visit(ctx.values());
         if(ctx.where!=null){
            switch(ctx.where.getText()){
               case "last":
                  tmp.addColumnLast((Column) visit(ctx.to), values);
                  break;
               case "first":
                  tmp.addColumnIndex((Column) visit(ctx.to), values, 0);
                  break;
            }
         }else{
            tmp.addColumnLast((Column) visit(ctx.to), values);
         }
         variables.put(aux, tmp);
      }
      return tmp;
   }

   //REMOVE
   
   /** 
    * Remove uma coluna específica da tabela.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitRemove_column(BdexParser.Remove_columnContext ctx) {
      List<Object> list=null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)){
         Table tmp = (Table) variables.get(aux);
         list = tmp.removeColumn((Column) visit(ctx.column()));
         variables.put(aux, tmp);
      }
      return list;
   }

   /** 
    * Remove a linha escolhida pelo utilizador.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitRemove_line(BdexParser.Remove_lineContext ctx) {
      List<Object> list=null;    

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)){
         Table tmp = (Table) variables.get(aux);
         if(ctx.index == null){
            list = tmp.removeLineLast();
         }else{
            list = tmp.removeLine(Integer.parseInt(ctx.index.getText()));
         }
         variables.put(aux, tmp);
      }
      return list;
   }

   //PUT
   
   /** 
    * Adiciona à tabela um valor numa coluna e linha específica.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitPut_value(BdexParser.Put_valueContext ctx) {
      Object item = null;

      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)){
         Table tmp = (Table) variables.get(aux);
         item = tmp.putValue( (Column) visit(ctx.column()), (Integer) visit(ctx.expr(0)), visit(ctx.expr(1)));
         variables.put(aux, tmp);
      }else{
         System.err.println("No variable Table with that name.");
      } 
      return item;
   }

   //EXTRACT TABLE

   /** 
    * Extrai todos os elementos da coluna pretendida pelo utilizador.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableColumns(BdexParser.ExtractTableColumnsContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());

      Table tmp = null;
      if(variables.containsKey(aux)) {
         tmp = ((Table)variables.get(aux)).extractColumns( (Column[]) visit(ctx.columns()));
      } else {
         System.out.println("An error ocurred when extracting table lines. ");
      }
      return tmp;
   }

   /** 
    * Extrai os elementos das colunas selecionadas pelo utilizador.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableColumnsInterval(BdexParser.ExtractTableColumnsIntervalContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());

      Table tmp = null;
      if(variables.containsKey(aux)) {
         tmp = ((Table)variables.get(aux)).extractColumns((Column) visit(ctx.column(0)), (Column) visit(ctx.column(1)));
      } else {
         System.err.println("An error ocurred when extracting table columns. ");
      }
      return tmp;
   }

  
   /** 
    * Extrai a linha que contêm o valor a eliminar.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableLines(BdexParser.ExtractTableLinesContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());

      Table tmp = null;
      if(variables.containsKey(aux) && (variables.get(aux) instanceof Table) ) {
         Object[] val =  (Object[]) visit(ctx.values());
         Integer[] intArray = Arrays.asList(val).toArray(new Integer[0]);
         tmp = ((Table)variables.get(aux)).extractLine( intArray );
      } else {
         System.err.println("An error ocurred when extracting table lines. ");
      }
      return tmp;
   }

   /** 
    * Extrai os elementos das linhas selecionadas pelo utilizador.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableLineBreak(BdexParser.ExtractTableLineBreakContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());

      Table tmp = null;
      Integer start;
      Integer finish;
      try{
         start = (Integer) visit(ctx.expr(0));
         finish = (Integer) visit(ctx.expr(1));
      }catch(ClassCastException e){
         System.err.println("The expresions must provide a Int");
         return tmp;
      }

      if(variables.containsKey(aux)) {
         tmp = ((Table)variables.get(aux)).extractLine(start, finish);
      } else {
         System.err.println("An error ocurred when extracting table lines. ");
      }
      return tmp;
   }
   
   /** 
    * Extrai os elementos contidos nas linhas de uma certa coluna.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableBothColumns(BdexParser.ExtractTableBothColumnsContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());
      
      Table tmp = null;
      Integer start, finish;
      try{
         start = (Integer) visit(ctx.expr(0));
         finish = (Integer) visit(ctx.expr(1));
      }catch(ClassCastException e){
         System.err.println("The expresions must provide a Int");
         return tmp;
      }

      if(variables.containsKey(aux)) {
         tmp=((Table)variables.get(aux)).extractBoth((Column[]) visit(ctx.columns()), start, finish);
      } else {
         System.err.println("An error ocurred when extracting table lines. ");
      }
      return tmp;
   }
    
   /** 
    * Extrai os elementos contidos nas linhas de certas colunas.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitExtractTableBothInterval(BdexParser.ExtractTableBothIntervalContext ctx) {
      Symbol aux = variables.getSymbol(ctx.reftable.getText());
      
      Table tmp = null;
      Integer start, finish;
      Column c1, c2 = null;  

      try{
         start = (Integer) visit(ctx.expr(0));
         finish = (Integer) visit(ctx.expr(1));
         c1 = (Column) visit(ctx.column(0));
         c1 = (Column) visit(ctx.column(1));
      }catch(ClassCastException e){
         System.err.println("The expresions must provide a Int");
         return tmp;
      }

      if(variables.containsKey(aux)) {
         tmp=((Table)variables.get(aux)).extractBoth(c1, c2, start, finish);
      } else {
         System.err.println("An error ocurred when extracting table lines. ");
      }
      return tmp;
   }

   //JOIN TABLES

   /** 
    * Junta as tabelas introduzidas pelo utilizador.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitJoin_tables(BdexParser.Join_tablesContext ctx) {
      List<Table> aux = new ArrayList<>();
      for(BdexParser.TableContext n : ctx.table()){
         if(n != null){
            aux.add((Table) visit(n));
         }
      }
      return Table.joinTables(aux.toArray(new Table[0]));
   }

   //PRINT

   /** 
    * A partir da variável associada a uma certa tabela, esta é imprimida.
    * @param ctx
    * @return variável
    */ 
   @Override public Object visitPrintVar(BdexParser.PrintVarContext ctx) {
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(aux!=null) {
         if(variables.containsKey(aux) && aux.getType().toString().equals("Table") ) {
            Table tmp = (Table)variables.get(aux);
            tmp.printTable();
         } else if(variables.containsKey(aux) && aux.getType().toString().equals("Iterator") ) {
            System.out.println(variables.get(aux).toString());
         } else {
            System.out.println(variables.get(aux));
         }
      }else{
         System.err.println("No variable with that name");
      }
      return aux;
   }
   
   /** 
    * Imprime uma tabela.
    * @param ctx
    * @return a tabela
    */ 
   @Override public Object visitPrintTable(BdexParser.PrintTableContext ctx) {
      Table tmp = null;
      tmp = (Table) visit(ctx.table());
      if(tmp!=null){
         tmp.printTable();
      }else{
         System.err.println("The given table is null");
      }
      return tmp;
   }

   /** 
    * Imprime uma expressão.
    * @param ctx
    * @return a tabela
    */ 
   @Override public Object visitPrintExpr(BdexParser.PrintExprContext ctx) {
      Object res = null;
      res = (Object) visit(ctx.expr());
      if(res!=null){
         System.out.println(res);
      }else{
         System.err.println("The given expression is null");
      }
      return res;
   }

   /** 
    * Imprime uma tabela ou a um valor associada a uma variável.
    * @param ctx
    * @return a tabela
    */
   @Override public Object visitPrintAssign(BdexParser.PrintAssignContext ctx) {
      Object tmp = null;
      tmp = (Object) visit(ctx.assign());
      if(tmp!=null){
         if(tmp instanceof Table){
            Table res = (Table) tmp;
            res.printTable();
            return res;
         }else{
            System.out.println(tmp);
            return tmp;
         }
      }else{
         System.err.println("The assign/print went wrong");
         return tmp;
      }
   }

   //ASSIGN
   //Atribui-se uma tabela a uma variável.
   @Override public Object visitAssignTable(BdexParser.AssignTableContext ctx) {
      Object tmp = null;
      tmp = visit(ctx.table());
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(tmp != null && variables.containsKey(aux) && aux.getType().toString().equals("Table")){
         variables.put(aux,tmp);
         return variables.get(aux);
      }else{
         System.err.println("No variable Table with that name");
      }
      return null;
   }

   //Atribui uma expressão a uma variável.
   @Override public Object visitAssignExpr(BdexParser.AssignExprContext ctx) {
      Object tmp = null;
      tmp = visit(ctx.expr());
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(tmp != null && variables.containsKey(aux)){
         if(tmp instanceof Integer && aux.getType().toString().equals("Int")){
            variables.put(aux,tmp);
         }else if(tmp instanceof Double && aux.getType().toString().equals("Real")){
            variables.put(aux,tmp);
         }else if(tmp instanceof String && aux.getType().toString().equals("Text")){
            variables.put(aux,tmp);
         }else{
            System.err.println("The variable type doesn't correspond with assigned value");
            return null;
         }
      }else{
         System.err.println("No variable with that name");
         return null;
      }
      return variables.get(aux);
   }
   
   //Atribui um valor que queremos adicionar à tabela a uma variável.
   @Override public Object visitAssignPut(BdexParser.AssignPutContext ctx) {
      Object tmp = null;
      tmp = visit(ctx.put_value());
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(tmp != null && variables.containsKey(aux)){
         if(tmp instanceof Integer && aux.getType().toString().equals("Int")){
            variables.put(aux,tmp);
         }else if(tmp instanceof Double && aux.getType().toString().equals("Real")){
            variables.put(aux,tmp);
         }else if(tmp instanceof String && aux.getType().toString().equals("Text")){
            variables.put(aux,tmp);
         }else{
            System.err.println("The variable type doesn't correspond with assigned value");
         }
      }else{
         System.err.println("No variable with that name");
         return null;
      }
      return variables.get(aux);
   }

   @Override public Object visitAssignIterator(BdexParser.AssignIteratorContext ctx) { 
      Object tmp = null;
      tmp = (Iterator<Object>)visit(ctx.iterator());
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(tmp != null && variables.containsKey(aux)) {
         variables.put(aux, tmp);
      }
      return variables.get(aux);
   }

   @Override public Object visitAssignIterate(BdexParser.AssignIterateContext ctx) { 
      Object tmp = null;
      tmp = visit(ctx.iterate());
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(tmp != null && variables.containsKey(aux)){
         if(tmp instanceof Integer && aux.getType().toString().equals("Int")){
            variables.put(aux,tmp);
         }else if(tmp instanceof Double && aux.getType().toString().equals("Real")){
            variables.put(aux,tmp);
         }else if(tmp instanceof String && aux.getType().toString().equals("Text")){
            variables.put(aux,tmp);
         }else{
            System.err.println("The variable type doesn't correspond with assigned value");
            return null;
         }
      }else{
         System.err.println("No variable with that name");
         return null;
      }
      return variables.get(aux);
   }

   @Override public Object visitAssignDeclarationTable(BdexParser.AssignDeclarationTableContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      Table item = (Table) visit(ctx.table());
      aux.setValDefined();
      variables.put(aux, item);
      return item;
   }
   // Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
   //    variables.put(aux, null);

   @Override public Object visitAssignDeclarationExpr(BdexParser.AssignDeclarationExprContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      Object item = visit(ctx.expr());
      aux.setValDefined();
      variables.put(aux, item);
      return item;
   }

   @Override public Object visitAssignDeclarationPut(BdexParser.AssignDeclarationPutContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      Object item = visit(ctx.put_value());
      aux.setValDefined();
      variables.put(aux, item);
      return item;
   }

   @Override public Object visitAssignDeclarationIterator(BdexParser.AssignDeclarationIteratorContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      Object item = visit(ctx.iterator());
      aux.setValDefined();
      variables.put(aux, item);
      return item;
   }

   @Override public Object visitAssignDeclarationIterate(BdexParser.AssignDeclarationIterateContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      Object item = visit(ctx.iterate());
      variables.put(aux, item);
      return item;
   }

   @Override public Object visitAssignDeclarationTupple(BdexParser.AssignDeclarationTuppleContext ctx) {
      Symbol s = new Symbol(ctx.VAR().getText(), new TuppleType());

      ArrayList<Object> tmp = new ArrayList<>();
      ArrayList<Type> aux = new ArrayList<>();
      for(BdexParser.ExprContext n : ctx.expr()){
         if(n != null)
            tmp.add(visit(n));
            aux.add(n.eType);
      }

      s.setValDefined();
      variables.put(s, new Tupple(tmp, aux));
      return variables.get(s);
   }

   @Override public Object visitDeclaration(BdexParser.DeclarationContext ctx) {
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      variables.put(aux, null);
      return aux;
   }
   
   //ITERATE
   //Itera a tabela pretendida pelo utilizador.
   @Override public Object visitIteratorTable(BdexParser.IteratorTableContext ctx) {
      String tablename = ctx.tablename.getText();
      Symbol s = variables.getSymbol(tablename);
      if (s == null) {
         System.err.println("Invalid table!");
         return null;
      }
      if(!s.getType().conformsTo(new TableType())) {
         System.err.println("Variable not Table type!");
         return null;
      }
      Table table = (Table) variables.get(s);
      List<Object> list = new ArrayList<>();
      Map<Column, ArrayList<Object>> tableMap = table.getMap();
      // table.printTable();
      for(Column key : table.getNames()) {
         for(int i = 0; i < table.getNumberLines(); i++) {
            list.add(tableMap.get(key).get(i));
         }
      }

      return list.iterator();
   }
   
   //Itera uma certa linha de uma tabela escolhida pelo utilizador.
   @Override public Object visitIteratorLine(BdexParser.IteratorLineContext ctx) {
      String tablename = ctx.tablename.getText();
      Symbol s = variables.getSymbol(tablename);
      if (s == null) {
         System.err.println("Invalid table!");
         return null;
      }
      if(!s.getType().conformsTo(new TableType())) {
         System.err.println("Variable not Table type!");
         return null;
      }
      int lineIndex = (Integer) visit(ctx.index);
      Table table = (Table) variables.get(s);
      List<Object> line = new ArrayList<>();
      Map<Column, ArrayList<Object>> tableMap = table.getMap();
      for(Column key: table.getNames())
         line.add(tableMap.get(key).get(lineIndex));
      return line.iterator();
   }

   //Itera uma certa coluna de uma tabela escolhida pelo utilizador.
   @Override public Object visitIteratorColumn(BdexParser.IteratorColumnContext ctx) {
      String tablename = ctx.tablename.getText();
      Symbol s = variables.getSymbol(tablename);
      if (s == null) {
         System.err.println("Invalid table!");
         return null;
      }
      if(!s.getType().conformsTo(new TableType())) {
         System.err.println("Variable not Table type!");
         return null;
      }
      Column c = (Column) visit(ctx.method);
      List<Object> list = new ArrayList<>();
      Table table = (Table) variables.get(s);
      list = table.getMap().get(c);
      return list.iterator();
   }

   @Override public Object visitIterate(BdexParser.IterateContext ctx) {
      String name = ctx.VAR().getText();
      Symbol s = variables.getSymbol(name);
      if(s == null) {
         return null;
      }
      Iterator<Object> it = (Iterator<Object>) variables.get(s);
      Object res = null;
      if(it.hasNext()) {
         res = it.next();
         variables.put(s, it);
      } else {
         System.err.println("Iterator does not have next. ");
         return null;   
      }
      return res;
   }

   //FILES
   //Lê uma tabela de um ficheiro CSV
   @Override public Object visitLoad_table(BdexParser.Load_tableContext ctx) {
      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      if(variables.containsKey(aux)) {
         System.err.println("Variable with that name already exists!");
         return null;
      }
      String[] args = new String[1];
      String filename = ctx.filename.getText();
      if(!filename.contains(".csv") && !filename.contains(".txt")) {
         System.err.println("Invalid file!");
         return null;
      }
      args[0] = filename.substring(1, filename.length()-1);
      Table table = TableMain.load(args);
      variables.put(aux, table);
      return table;
   }
   
   //Guarda uma tabela num ficheiro CSV
   @Override public Object visitSave_table(BdexParser.Save_tableContext ctx) {
      Symbol aux = variables.getSymbol(ctx.tablename.getText());
      String filename = ctx.filename.getText();
      filename = filename.substring(1, filename.length()-1);
      if (aux==null || !aux.getType().toString().equals("Table")) {
         System.err.println("Table doesn't exist!");
         return null;
      }
      Table table = (Table) variables.get(aux);
      if(!filename.contains(".csv") && !filename.contains(".txt"))
         filename += ".csv";
      try {
         java.io.File file = new java.io.File(filename);
         if(!file.createNewFile()) {
            System.err.println("File with same name already exists!");
            return null;
         }
         java.io.PrintWriter pw = new java.io.PrintWriter(file);
         List<Column> columnNames = table.getNames();
         String firstLine = columnNames.stream()
                 .map(x -> x.getName())
                 .collect(java.util.stream.Collectors.joining(","));
         pw.write(firstLine + "\n");
         int numLines = table.getNumberLines();
         Map<Column, ArrayList<Object>> tableMap = table.getMap();
         for(int i = 0; i < numLines; i++) {
            String line = "";
            for(Column c: columnNames)
               line += tableMap.get(c).get(i) + ",";
            pw.write(line.substring(0, line.length()-1) + "\n");
         }
         pw.write('\n');
         pw.close();
      } catch (java.io.IOException e) {
         System.err.println("Unable to write to file!");
         return null;
      }
      return filename;
   }

   //COLUMNS
   @Override public Object visitColumns(BdexParser.ColumnsContext ctx) {
      List<Column> aux = new ArrayList<>();
      for(int i = 0; i < ctx.column().size(); i++)
         aux.add((Column) visit(ctx.column(i)));
      return aux.toArray(new Column[0]);
   }

   @Override public Object visitColumn(BdexParser.ColumnContext ctx){
      Column tmp = null;
      tmp = new Column(ctx.TEXT().getText().replaceAll("\"", ""), ctx.type.getText());
      return tmp;
   }

   //VALUES
   @Override public Object visitValues(BdexParser.ValuesContext ctx) {
      List<Object> aux = new ArrayList<>();
      for(BdexParser.ExprContext n : ctx.expr()){
         if(n != null)
            aux.add((Object) visit(n));
      }
      Object[] res = new Object[aux.size()];
      res = aux.toArray();

      return res;
   }

   @Override public Object visitExprVar(BdexParser.ExprVarContext ctx) {
      Object res = null;
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(aux!=null){
         res = variables.get(aux);
      }
      if(res==null){
      	System.err.println("ERROR: No Assigned Value");
      }

      if(ctx.it==null){
         return res;
      }else{
         Integer index = (Integer) Integer.parseInt(ctx.it.getText());
         Tupple tmp = (Tupple) res;
         return tmp.getValue(index);
      }
   }

   @Override public Object visitExprAddSub(BdexParser.ExprAddSubContext ctx) {
      Object res0 = null;
      res0 = visit(ctx.expr(0));
      Object res1 = null;
      res1 = visit(ctx.expr(1));
      Object res = null;
      if(res0 instanceof Integer && res1 instanceof Integer){
         Integer op1=(Integer) res0;
         Integer op2=(Integer) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "+":
               res = op1 + op2;
               break;
            case "-":
               res = op1 - op2;
               break;
            }
         }
         return res;
      }else if(res0 instanceof Double && res1 instanceof Double){
         Double op1=(Double) res0;
         Double op2=(Double) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "+":
               res = op1 + op2;
               break;
            case "-":
               res = op1 - op2;
               break;
            }
         }
         return res;
      }else if(res0 instanceof Integer && res1 instanceof Double){
         Integer op1=(Integer) res0;
         Double op2=(Double) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "+":
               res = op1 + op2;
               break;
            case "-":
               res = op1 - op2;
               break;
            }
         }
         return res;
      } else if(res0 instanceof Double && res1 instanceof Integer){
         Double op1=(Double) res0;
         Integer op2=(Integer) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "+":
               res = op1 + op2;
               break;
            case "-":
               res = op1 - op2;
               break;
            }
         }
         return res;
      } else{
         System.err.println("Must provide either Int or Real type");
         return null;
      }
   }

   @Override public Object visitExprBin(BdexParser.ExprBinContext ctx) {
      Object res0 = null;
      res0 = visit(ctx.expr(0));
      Object res1 = null;
      res1 = visit(ctx.expr(1));
      boolean res = false;
      if(res0 instanceof Integer && res1 instanceof Integer){
         Integer op1=(Integer) res0;
         Integer op2=(Integer) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
               case "<":
                  res = op1 < op2;
                  break;
               case "<=":
                  res = op1 <= op2;
                  break;
               case ">":
                  res = op1 > op2;
                  break;
               case ">=":
                  res = op1 >= op2;
                  break;
               case "==":
                  res = op1.equals(op2);
                  break;
               case "!=":
                  res = !(op1.equals(op2));
                  break;
            }
         }
         return res;
      }else if(res0 instanceof Double && res1 instanceof Double){
         Double op1=(Double) res0;
         Double op2=(Double) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
               case "<":
                  res = op1 < op2;
                  break;
               case "<=":
                  res = op1 <= op2;
                  break;
               case ">":
                  res = op1 > op2;
                  break;
               case ">=":
                  res = op1 >= op2;
                  break;
               case "==":
                  res = op1.equals(op2);
                  break;
               case "!=":
                  res = !(op1.equals(op2));
                  break;
            }
         }
         return res;
      }else if(res0 instanceof Boolean && res1 instanceof Boolean){
         Boolean op1=(Boolean) res0;
         Boolean op2=(Boolean) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
               case "and":
                  res = op1 && op2 ;
                  break;
               case "or":
                  res = op1 || op2 ;
                  break;
            }
         }
         return res;
      }else{
         System.err.println("Must provide either Int or Real type");
         return null;
      }
   }

   @Override public Object visitExprParentesis(BdexParser.ExprParentesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Object visitExprIncrement(BdexParser.ExprIncrementContext ctx) {
      Integer res = null;
      res = (Integer) visit(ctx.increment());
      if(res==null){
         System.err.println("The increment didn't work");
      }
      return res;
   }

   @Override public Object visitExprUnary(BdexParser.ExprUnaryContext ctx) {
      Object res = null;
      res = visit(ctx.expr());
      if(res instanceof Integer){
         Integer aux=(Integer) res;
         switch(ctx.op.getText()){
            case "+":
               break;
            case "-":
               aux= -aux;
               break;
         }
         return aux;
      }else if(res instanceof Double){
         Double aux=(Double) res;
         switch(ctx.op.getText()){
            case "+":
               break;
            case "-":
               aux= -aux;
               break;
         }
         return aux;
      }else{
         System.err.println("Must provide either Int or Real type");
         return res;
      }
   }

   @Override public Object visitExprText(BdexParser.ExprTextContext ctx) {
      Object res = null;
      res = (String) ctx.TEXT().getText().replaceAll("\"", "");
      return res;
   }

   @Override public Object visitExprConcat(BdexParser.ExprConcatContext ctx) {
      String arg = null;
      
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      if(aux==null){
         System.err.println("Variable not declared");
         return null;
      }

      String s1 = null;
      if(ctx.it!=null){
         if(!aux.getType().toString().equals("Tupple")){
            System.err.println("The given variable is not Tupple");
            return null;
         }
         Tupple tmp = (Tupple) variables.get(aux);
         Type val = tmp.getType(Integer.parseInt(ctx.it.getText()));
         if(!((Type)val).conformsTo(new TextType())){
            System.err.println("Element "+ ctx.it.getText() +" of Tupple "+ctx.VAR().getText()+ " is not Text.");
            return null;
         }
         s1 = (String) tmp.getValue(Integer.parseInt(ctx.it.getText()));
      }else{
         if(!aux.getType().toString().equals("Text")){
            System.err.println("The given variable is not Text");
            return null;
         }
         s1 = (String) variables.get(aux);
      }

      return s1.concat((String) visit(ctx.expr()));
   }

   @Override public Object visitExprInteger(BdexParser.ExprIntegerContext ctx) {
      Integer res = null;
      res = Integer.parseInt(ctx.INTEGER().getText());
      return res;
   }

   @Override public Object visitExprReal(BdexParser.ExprRealContext ctx) {
      Double res = null;
      res = Double.parseDouble(ctx.REAL().getText());
      return res;
   }

   @Override public Object visitExprMultDivMod(BdexParser.ExprMultDivModContext ctx) {
      Object res0 = null;
      res0 = visit(ctx.expr(0));
      Object res1 = null;
      res1 = visit(ctx.expr(1));
      Object res = null;
      if(res0 instanceof Integer && res1 instanceof Integer){
         Integer op1=(Integer) res0;
         Integer op2=(Integer) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "*":
               res = op1 * op2;
               break;
            case "/":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 / op2;
               }
               break;
            case "%":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 % op2;
               }
               break;
            }
         }
         return res;
      }else if(res0 instanceof Double && res1 instanceof Double){
         Double op1=(Double) res0;
         Double op2=(Double) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "*":
               res = op1 * op2;
               break;
            case "/":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 / op2;
               }
               break;
            case "%":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 % op2;
               }
               break;
            }
         }
         return res;
      } else if(res0 instanceof Integer && res1 instanceof Double){
         Integer op1=(Integer) res0;
         Double op2=(Double) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "*":
               res = op1 * op2;
               break;
            case "/":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 / op2;
               }
               break;
            case "%":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 % op2;
               }
               break;
            }
         }
         return res;
      } else if(res0 instanceof Double && res1 instanceof Integer){
         Double op1=(Double) res0;
         Integer op2=(Integer) res1;
         if(op1!=null && op2!=null){
            switch(ctx.op.getText()){
            case "*":
               res = op1 * op2;
               break;
            case "/":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 / op2;
               }
               break;
            case "%":
               if(op2==0){
                  System.err.println("ERROR: DIVIDED BY ZERO");
               }else{
                  res = op1 % op2;
               }
               break;
            }
         }
         return res;
      }else{
         System.err.println("Must provide either Int or Real type");
         return null;
      }
   }

   @Override public Object visitExprInput(BdexParser.ExprInputContext ctx) {
      Object res = visit(ctx.input());
      if(res instanceof Integer){
         return (Integer) res;
      }else if(res instanceof Double){
         return (Double) res;
      }else if(res instanceof String){
         return (String) res;
      }else{
         System.err.println("Can't recognize input type.");
         return null;
      }
   }

   @Override public Object visitInputInt(BdexParser.InputIntContext ctx) {
      Scanner ler = new Scanner(System.in);
      Integer res=null;
      try{
            res = Integer.parseInt(ler.nextLine().trim());
      }catch(NumberFormatException e){
         System.err.println("The input value must be a Int number.");
      }
      ler.close();
      return res;
   }

   @Override public Object visitInputReal(BdexParser.InputRealContext ctx) {
      Scanner ler = new Scanner(System.in);
      Double res=null;
      try{
         res = Double.parseDouble(ler.nextLine().trim());
      }catch(NumberFormatException e){
         System.err.println("The input value must be a Real number.");
      }
      ler.close();
      return res;
   }

   @Override public Object visitInputText(BdexParser.InputTextContext ctx) {
      Scanner ler = new Scanner(System.in);
      System.out.println("Input: ");
      String val = null;
      if(ler.hasNext())
         val = ler.next();
      ler.close();
      System.out.println(val);
      return val;
   }

   @Override public Object visitPreIncrement(BdexParser.PreIncrementContext ctx) {
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      Integer item = null;
      if(aux!=null && aux.getType().toString().equals("Int")){
         item = (Integer) variables.get(aux);   
         switch(ctx.op.getText()) {
            case "++":
               item++;
               break;
            case "--":
               item--;
               break;
         }   
         variables.put(aux, item);
      }else{
            System.err.println("Your variable must be an Integer");
      }
      return item;
   }

   @Override public Object visitPosIncrement(BdexParser.PosIncrementContext ctx) {
      Symbol aux = variables.getSymbol(ctx.VAR().getText());
      Integer item = null;
      if(aux!=null && aux.getType().toString().equals("Int")){
         item = (Integer) variables.get(aux);   
         switch(ctx.op.getText()) {
            case "++":
               item++;
               break;
            case "--":
               item--;
               break;
         }   
         variables.put(aux, item);
      }else{
            System.err.println("Your variable must be an Integer");
      }
      return item-1;
   }
}
