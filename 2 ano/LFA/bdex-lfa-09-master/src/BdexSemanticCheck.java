import org.antlr.v4.runtime.*;
import org.xml.sax.ErrorHandler;

import Structure.*;
import error.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;

public class BdexSemanticCheck extends BdexBaseVisitor<Boolean> {
   private final RealType realType = new RealType();
   private final IntegerType integerType = new IntegerType();
   private final TextType textType = new TextType();
   private final BooleanType booleanType = new BooleanType();
   public SymbolTable variables = new SymbolTable();

   @Override public Boolean visitMain(BdexParser.MainContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatAddLine(BdexParser.StatAddLineContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatAddColumn(BdexParser.StatAddColumnContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatRemoveColumn(BdexParser.StatRemoveColumnContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatRemoveLine(BdexParser.StatRemoveLineContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatPutValue(BdexParser.StatPutValueContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatPrint(BdexParser.StatPrintContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatAssign(BdexParser.StatAssignContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatIterate(BdexParser.StatIterateContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatIncrement(BdexParser.StatIncrementContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatSaveTable(BdexParser.StatSaveTableContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitStatLoadTable(BdexParser.StatLoadTableContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitCreateTable(BdexParser.CreateTableContext ctx) {
      return visit(ctx.create_table());
   }

   @Override public Boolean visitExtractTable(BdexParser.ExtractTableContext ctx) {
      return visit(ctx.extract_table());
   }

   @Override public Boolean visitJoinTables(BdexParser.JoinTablesContext ctx) {
      return visit(ctx.join_tables());
   }

   @Override public Boolean visitVariableTable(BdexParser.VariableTableContext ctx) {
      String name = ctx.VAR().getText();
      return checkVar(name, "Table");
   }

   @Override public Boolean visitCreateTableNoLine(BdexParser.CreateTableNoLineContext ctx) {
      Boolean res = visit(ctx.columns());
      return res;
   }

   @Override public Boolean visitCreateTableLine(BdexParser.CreateTableLineContext ctx) {
      Boolean res = visit(ctx.columns()) && visit(ctx.expr());
      if (!ctx.expr().eType.conformsTo(new IntegerType())) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr().getText()+" is not Int!");
         return false;
      }

      return res;
   }

   @Override public Boolean visitCreateTableValues(BdexParser.CreateTableValuesContext ctx) {
      Boolean res = visit(ctx.columns()) && visit(ctx.values());
      return res;
   }

   @Override public Boolean visitAddLineValues(BdexParser.AddLineValuesContext ctx) {
      Boolean res = visit(ctx.values());
      String tablename = ctx.tablename.getText();
      if(ctx.index!=null){
         res = res && visit(ctx.expr());
         if (!ctx.index.eType.isInteger()) {
            ErrorHandling.printError(ctx, "type expression "+ctx.index.getText()+" is not Int!");
            return false;
         }
      }
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitAddLineIndexOmission(BdexParser.AddLineIndexOmissionContext ctx) {
      String tablename = ctx.tablename.getText();
      if(ctx.index!=null){
         Boolean res = visit(ctx.expr());
         if (!ctx.index.eType.isInteger()) {
            ErrorHandling.printError(ctx, "type expression "+ctx.index.getText()+" is not Int!");
            return false;
         }
         return checkVar(tablename, "Table") && res;
      }
      return checkVar(tablename, "Table");
   }

   @Override public Boolean visitAddColumnOmissionPlace(BdexParser.AddColumnOmissionPlaceContext ctx) {
      Boolean res = visit(ctx.column(0)) && visit(ctx.column(1));
      String tablename = ctx.tablename.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitAddColumnValuesPlace(BdexParser.AddColumnValuesPlaceContext ctx) {
      Boolean res = visit(ctx.column(0)) && visit(ctx.column(1)) && visit(ctx.values());
      String tablename = ctx.tablename.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitAddColumnOmissionFL(BdexParser.AddColumnOmissionFLContext ctx) {
      Boolean res = visit(ctx.column());
      String tablename = ctx.tablename.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitAddColumnValuesFL(BdexParser.AddColumnValuesFLContext ctx) {
      Boolean res = visit(ctx.column()) && visit(ctx.values());
      String tablename = ctx.tablename.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitRemove_column(BdexParser.Remove_columnContext ctx) {
      Boolean res = visit(ctx.column());
      String tablename = ctx.tablename.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitRemove_line(BdexParser.Remove_lineContext ctx) {
      String tablename = ctx.tablename.getText();
      if(ctx.index!=null){
         Boolean res = visit(ctx.expr());
         if (!ctx.index.eType.isInteger()) {
            ErrorHandling.printError(ctx, "type expression "+ctx.index.getText()+" is not Int!");
            return false;
         }
         return checkVar(tablename, "Table") && res;
      }
      return checkVar(tablename, "Table");
   }

   @Override public Boolean visitPut_value(BdexParser.Put_valueContext ctx) {
      Boolean res = visit(ctx.column()) && visit(ctx.expr(0)) && visit(ctx.expr(1));
      String tablename = ctx.tablename.getText();
      if (!ctx.expr(0).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(0).getText()+" is not Int!");
         return false;
      }
      if(!ctx.column().eType.conformsTo(ctx.expr(1).eType)){
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(1).getText()+" is not "+ ctx.column().eType.toString() +"!");
         return false;
      }
      ctx.eType = ctx.expr(1).eType;
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitExtractTableColumns(BdexParser.ExtractTableColumnsContext ctx) {
      Boolean res = visit(ctx.columns());
      String tablename = ctx.reftable.getText();
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitExtractTableColumnsInterval(BdexParser.ExtractTableColumnsIntervalContext ctx) {
      Boolean res = visit(ctx.column(0)) && visit(ctx.column(1));
      String tablename = ctx.reftable.getText();
      return checkVar(tablename, "Table") && res;
   }

   //MISSING SOMETHING
   @Override public Boolean visitExtractTableLines(BdexParser.ExtractTableLinesContext ctx) {
      Boolean res = visit(ctx.values());
      String tablename = ctx.reftable.getText();
      return checkVar(tablename, "Table") && res;
   }
      
   @Override public Boolean visitExtractTableLineBreak(BdexParser.ExtractTableLineBreakContext ctx) {
      Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
      String tablename = ctx.reftable.getText();
      if (!ctx.expr(0).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(0).getText()+" is not Int!");
         return false;
      }
      if (!ctx.expr(1).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(1).getText()+" is not Int!");
         return false;
      }
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitExtractTableBothColumns(BdexParser.ExtractTableBothColumnsContext ctx) {
      Boolean res = visit(ctx.columns()) && visit(ctx.expr(0)) && visit(ctx.expr(1));
      String tablename = ctx.reftable.getText();
      if (!ctx.expr(0).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(0).getText()+" is not Int!");
         return false;
      }
      if (!ctx.expr(1).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(1).getText()+" is not Int!");
         return false;
      }
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitExtractTableBothInterval(BdexParser.ExtractTableBothIntervalContext ctx) {
      Boolean res = visit(ctx.column(0)) && visit(ctx.column(1)) && visit(ctx.expr(0)) && visit(ctx.expr(1));
      String tablename = ctx.reftable.getText();
      if (!ctx.expr(0).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(0).getText()+" is not Int!");
         return false;
      }
      if (!ctx.expr(1).eType.isInteger()) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr(1).getText()+" is not Int!");
         return false;
      }
      return checkVar(tablename, "Table") && res;
   }

   @Override public Boolean visitExtractTableContains(BdexParser.ExtractTableContainsContext ctx) {
      Boolean res = visit(ctx.values());
      String tablename = ctx.reftable.getText();
      return checkVar(tablename, "Table") && res;
   }

   //MAY WORK NEED TO TEST
   @Override public Boolean visitJoin_tables(BdexParser.Join_tablesContext ctx) {
      Boolean res = true; //visit(ctx.table());
      for(BdexParser.TableContext n : ctx.table()){
         if(n != null){
            res = res && visit(n);
         }
      }
      return res;
   }

   @Override public Boolean visitPrintVar(BdexParser.PrintVarContext ctx) {
      Boolean res = true;
      String var = ctx.VAR().getText();
      Symbol aux = variables.getSymbol(var);
      if(aux == null ) {
         ErrorHandling.printError(ctx, "Variable "+ var +" not declared");
         return false;
      }
      if(!aux.getValDefined()){
         ErrorHandling.printError(ctx, "Variable "+ var +" value null");
         return false;
      }
      return res;
   }

   @Override public Boolean visitPrintTable(BdexParser.PrintTableContext ctx) {
      Boolean res = visit(ctx.table());
      return res;
   }

   @Override public Boolean visitPrintExpr(BdexParser.PrintExprContext ctx) {
      Boolean res = visit(ctx.expr());
      return res;
   }

   @Override public Boolean visitPrintAssign(BdexParser.PrintAssignContext ctx) {
      Boolean res = visit(ctx.assign());
      return res;
   }

   @Override public Boolean visitPrintIterate(BdexParser.PrintIterateContext ctx) {
      Boolean res = visit(ctx.iterate());
      return res;
   }

   @Override public Boolean visitDeclaration(BdexParser.DeclarationContext ctx) {
      Boolean res = visit(ctx.tipo());
      Symbol aux = new Symbol(ctx.VAR().getText(), ctx.tipo().res);
      if(variables.getSymbol(ctx.VAR().getText())!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      variables.put(aux, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationTable(BdexParser.AssignDeclarationTableContext ctx) {
      Boolean res = visit(ctx.tipo());
      String name = ctx.VAR().getText();
      Symbol s = new Symbol(name,  ctx.tipo().res);
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+name+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(new TableType())){
         ErrorHandling.printError(ctx, "Variable "+name + " not type Table.");
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationExpr(BdexParser.AssignDeclarationExprContext ctx) {
      Boolean res = visit(ctx.expr());
      String name = ctx.VAR().getText();
      Symbol s = new Symbol(name,  ctx.tipo().res);
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+name+" not declared.");
         return false;
      }
      if(ctx.expr().eType==null){
         ErrorHandling.printError(ctx, "The expression "+ctx.expr().getText()+" has type null.");
         return false;
      }
      if(!s.getType().conformsTo(ctx.expr().eType)){
         ErrorHandling.printError(ctx, "Given type: " + ctx.expr().eType.toString() + "\nRequires Type: " + s.getType().toString());
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationPut(BdexParser.AssignDeclarationPutContext ctx) {
      Boolean res = visit(ctx.put_value());
      String name = ctx.VAR().getText();
      Symbol s = new Symbol(name, ctx.tipo().res);
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+name+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(ctx.put_value().eType)){
         ErrorHandling.printError(ctx, "type expression "+ctx.put_value().getText()+" is not "+ s.getType().toString() +"!");
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationIterator(BdexParser.AssignDeclarationIteratorContext ctx) {
      Boolean res = visit(ctx.tipo());
      String name = ctx.VAR().getText();
      Symbol s = new Symbol(name, new IteratorType());
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+name+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(new IteratorType())){
         ErrorHandling.printError(ctx, "Symbol " +name+ "is not of type Iterator. ");
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationIterate(BdexParser.AssignDeclarationIterateContext ctx) {
      Boolean res = visit(ctx.tipo());
      String name = ctx.VAR().getText();
      Symbol s = new Symbol(name, ctx.tipo().res);
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared.");
         return false;
      }
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+name+" not declared.");
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return res;
   }

   @Override public Boolean visitAssignDeclarationTupple(BdexParser.AssignDeclarationTuppleContext ctx) {
      String name = ctx.VAR().getText();

      Symbol ver = variables.getSymbol(name);
      if(variables.getSymbol(name)!=null){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" already declared has another Type.");
         return false;
      }
      if(ver != null && ver.getType().conformsTo(new TuppleType())){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" is already a Tupple.");
         return false;
      }

      Symbol s = new Symbol(name, new TuppleType());

      Boolean res = true;
      List<Type> tmp = new ArrayList<>();
      for(BdexParser.ExprContext n : ctx.expr()){
         if(n != null)
            res= res && visit(n);
            tmp.add(n.eType);
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, tmp.stream().toArray(Type[] ::new));
      return res;
   }

   @Override public Boolean visitAssignTable(BdexParser.AssignTableContext ctx) {
      Boolean res = visit(ctx.table());
      Symbol s = variables.getSymbol(ctx.VAR().getText());
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(new TableType())){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText() + " not type Table.");
         return false;
      }
      if(s.getValDefined()==false){
         variables.remove(s);
         s.setValDefined();
         variables.put(s, null);
      }
      return res;
   }

   @Override public Boolean visitAssignExpr(BdexParser.AssignExprContext ctx) {
      Boolean res = visit(ctx.expr());
      Symbol s = variables.getSymbol(ctx.VAR().getText());
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(ctx.expr().eType)) {
         ErrorHandling.printError("Given type: " + ctx.expr().eType + "\nRequires Type: " + s.getType());
         return false;
      }
      if(s.getValDefined()==false){
         variables.remove(s);
         s.setValDefined();
         variables.put(s, null);
      }
      return res;
   }

   @Override public Boolean visitAssignPut(BdexParser.AssignPutContext ctx) {
      Boolean res = visit(ctx.put_value());
      Symbol s = variables.getSymbol(ctx.VAR().getText());
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(new TableType())){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText() + " must be type Int, Real or Text.");
         return false;
      }
      if(s.getValDefined()==false){
         variables.remove(s);
         s.setValDefined();
         variables.put(s, null);
      }
      return res;
   }

   @Override public Boolean visitAssignIterator(BdexParser.AssignIteratorContext ctx) {
      Boolean res = visit(ctx.iterator());
      Symbol s = variables.getSymbol(ctx.VAR().getText());
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" not declared.");
         return false;
      }
      if(!s.getType().conformsTo(new IteratorType())){
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText() + " must be type Int, Real or Text.");
         return false;
      }
      if(s.getValDefined()==false){
         variables.remove(s);
         s.setValDefined();
         variables.put(s, null);
      }
      return res;
   }

   @Override public Boolean visitAssignIterate(BdexParser.AssignIterateContext ctx) {
      Boolean res = visit(ctx.iterate());
      Symbol s = variables.getSymbol(ctx.VAR().getText());
      if(s == null) {
         ErrorHandling.printError(ctx, "Variable "+ctx.VAR().getText()+" not declared.");
         return false;
      }
      if(s.getValDefined()==false){
         variables.remove(s);
         s.setValDefined();
         variables.put(s, null);
      }
      return res;
   }

   //WORK TO DO ON ITERATE
   @Override public Boolean visitIteratorTable(BdexParser.IteratorTableContext ctx) {
      Boolean res = true;
      String var = ctx.tablename.getText();
      Symbol aux = variables.getSymbol(var);
      if(aux == null) {
         ErrorHandling.printError(ctx, "Symbol is null.");
         return false;
      }
      if(!aux.getType().conformsTo(new TableType())) {
         ErrorHandling.printError(ctx, "Symbol is not of type Iterator. ");
         return false;
      }
      return res;
   }

   @Override public Boolean visitIteratorLine(BdexParser.IteratorLineContext ctx) {
      Boolean res = visit(ctx.index);
      String var = ctx.tablename.getText();
      Symbol aux = variables.getSymbol(var);
      if(aux == null) {
         ErrorHandling.printError(ctx, "Symbol is null.");
         return false;
      }
      if(!aux.getType().conformsTo(new TableType())) {
         ErrorHandling.printError(ctx, "Symbol is not of type Table. ");
         return false;
      } 
      return res;
   }

   // @Override public Boolean visitIteratorColumn(BdexParser.IteratorColumnContext ctx) {
   @Override public Boolean visitIteratorColumn(BdexParser.IteratorColumnContext ctx) {
      String var = ctx.tablename.getText();
      String c = ctx.method.getText();
      Symbol aux = variables.getSymbol(var);
      if(aux == null) {
         ErrorHandling.printError(ctx, "Symbol is null.");
         return false;
      }
      if(!aux.getType().conformsTo(new TableType())) {
         ErrorHandling.printError(ctx, "Symbol is not of type Table. ");
         return false;
      }
      return true;
   }
   
   @Override public Boolean visitIterate(BdexParser.IterateContext ctx) {
      String name = ctx.VAR().getText();
      Symbol s = variables.getSymbol(name);
      if(s == null) {
         ErrorHandling.printError(ctx, "Symbol " + name + "does not exist.");
         return false;
      }
      if(!s.getType().conformsTo(new IteratorType())) {
         ErrorHandling.printError(ctx, "Symbol " + name + " is not of type Iterator. ");
         return false;
      }
      return true;
   }

   @Override public Boolean visitLoad_table(BdexParser.Load_tableContext ctx) {
      String fileName = null;
      fileName = ctx.filename.getText();
      Symbol s = new Symbol(ctx.tablename.getText(), new TableType());
      File f = new File(fileName.substring(1, fileName.length()-1));
      if(!f.isFile()) {
         ErrorHandling.printError(ctx,"File " + fileName + " does not exist.");
         return false;
      }
      variables.remove(s);
      s.setValDefined();
      variables.put(s, null);
      return visit(ctx.VAR());
   }

   @Override public Boolean visitSave_table(BdexParser.Save_tableContext ctx) {
      String tablename = ctx.tablename.getText();
      Symbol aux = variables.getSymbol(tablename);
      if(aux == null) {
         ErrorHandling.printError(ctx,"Table " + tablename + " does not exist. ");
         return false;
      }
      return true;
   }

   @Override public Boolean visitColumns(BdexParser.ColumnsContext ctx) {
      Boolean res = true;
      for(int i = 0; i < ctx.column().size(); i++)
         res = res && visit(ctx.column(i));
      return res;
   }

   @Override public Boolean visitColumn(BdexParser.ColumnContext ctx) {
      if(ctx.type.getText().equals("Real")) {
         ctx.eType = realType;
      } else if (ctx.type.getText().equals("Int")) {
         ctx.eType = integerType;
      } else {
         ctx.eType = textType;
      }
      return true;
   }

   @Override public Boolean visitValues(BdexParser.ValuesContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Boolean visitExprVar(BdexParser.ExprVarContext ctx) {
      String name = ctx.VAR().getText();
      Symbol aux = variables.getSymbol(name);
      if(!variables.containsKey(aux)){
         ErrorHandling.printError("Variable "+name+" not declared.");
         return false;
      }
      if(aux.getValDefined()==false){
         ErrorHandling.printError("Variable "+name+ " value is null.");
         return false;
      }
      if(ctx.it!=null){
         if(!aux.getType().conformsTo(new TuppleType())){
            ErrorHandling.printError("Variable "+name+ " not Tupple.");
            return false;
         }
         Type[] tmp = (Type[]) variables.get(aux);
         ctx.eType = tmp[Integer.parseInt(ctx.it.getText())];
         return true;
      }
      ctx.eType= aux.getType();
      return true;
   }

   @Override public Boolean visitExprAddSub(BdexParser.ExprAddSubContext ctx) {
      Boolean res = visit(ctx.expr(0)) && checkNumericType(ctx, ctx.expr(0).eType)
               && visit(ctx.expr(1)) && checkNumericType(ctx, ctx.expr(1).eType);
      if(!res){
         if(ctx.expr(0).eType==null){
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(0).getText()+" has type null.");
            return false;
         }
         if(!checkNumericType(ctx, ctx.expr(0).eType))
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(0).getText()+" needs to be numeric.");
         if(ctx.expr(1).eType==null){
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(1).getText()+" has type null.");
            return false;
         }
         if(!checkNumericType(ctx, ctx.expr(1).eType))
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(1).getText()+" needs to be numeric.");      
      }
      ctx.eType = fetchType(ctx.expr(0).eType, ctx.expr(1).eType);
      return res;
   }

   @Override public Boolean visitExprBin(BdexParser.ExprBinContext ctx) {
      Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
		String op=ctx.op.getText();
		boolean arit=op.matches("< | > | <= | >= ");
		boolean logic=op.matches("and | or | == | !=");

      if(!res) 
         return false;

         if (!ctx.expr(0).eType.conformsTo(ctx.expr(1).eType)) {
            ErrorHandling.printError(ctx,"The expression "+ ctx.expr(0).getText() + " and " +ctx.expr(1).getText()+" are not the same type");
            res=false;
         }
         if(arit){
            if( !checkNumericType(ctx, ctx.expr(0).eType) || !checkNumericType(ctx, ctx.expr(1).eType)){
               ErrorHandling.printError(ctx,"The expression "+ ctx.expr(0).getText() + " and " +ctx.expr(1).getText()+" aren't numeric types");
               res=false;
            }
         }
         if(logic){
            if( !ctx.expr(0).eType.isBoolean() || !ctx.expr(1).eType.isBoolean()){
               ErrorHandling.printError(ctx,"The expression "+ ctx.expr(0).getText() + " and " +ctx.expr(1).getText()+" aren't Boolean");
               res=false;
            }
         }
      ctx.eType=booleanType;   
      return res;   
   }

   @Override public Boolean visitExprParentesis(BdexParser.ExprParentesisContext ctx) {
      Boolean res = visit(ctx.expr());
      if(res)
         ctx.eType = ctx.expr().eType;
      return res;
   }

   @Override public Boolean visitExprConcat(BdexParser.ExprConcatContext ctx) {
      Boolean res = visit(ctx.expr());
      String name = ctx.VAR().getText();
      if( ctx.expr().eType != textType ) {
         ErrorHandling.printError(ctx, "type expression "+ctx.expr().getText()+" is not Text!");
         return false;
      }
      if(ctx.it!=null){
         Symbol aux = variables.getSymbol(name);
         if(!aux.getType().conformsTo(new TuppleType())){
            ErrorHandling.printError("Variable "+name+ " not Tupple.");
            return false;
         }
         Type[] tmp = (Type[]) variables.get(aux);
         if(!tmp[Integer.parseInt(ctx.it.getText())].conformsTo(new TextType())){
            ErrorHandling.printError("Element "+ ctx.it.getText() +" of Tupple "+name+ " is not Text.");
            return false;
         }
         ctx.eType = tmp[Integer.parseInt(ctx.it.getText())];
         return res && checkVar(name, "Tupple");
      }
      ctx.eType = ctx.expr().eType;
      return res && checkVar(name, "Text");
   }

   @Override public Boolean visitExprUnary(BdexParser.ExprUnaryContext ctx) {
      Boolean res = visit(ctx.expr()) && checkNumericType(ctx, ctx.expr().eType);
      if(res)
         ctx.eType = ctx.expr().eType;
      return res;
   }

   @Override public Boolean visitExprIncrement(BdexParser.ExprIncrementContext ctx) {
      Boolean res = visit(ctx.increment());
      ctx.eType = integerType;
      return res;
   }

   @Override public Boolean visitExprReal(BdexParser.ExprRealContext ctx) {
      ctx.eType = realType;
      return true;
   }

   @Override public Boolean visitExprText(BdexParser.ExprTextContext ctx) {
      ctx.eType = textType;
      return true;
   }

   @Override public Boolean visitExprInteger(BdexParser.ExprIntegerContext ctx) {
      ctx.eType = integerType;
      return true;
   }
   
   @Override public Boolean visitExprBoolean(BdexParser.ExprBooleanContext ctx) {
      ctx.eType = booleanType;
      return true;
   }

   @Override public Boolean visitExprMultDivMod(BdexParser.ExprMultDivModContext ctx) {
      Boolean res = visit(ctx.expr(0)) && checkNumericType(ctx, ctx.expr(0).eType)
                  && visit(ctx.expr(1)) && checkNumericType(ctx, ctx.expr(1).eType);
      if(!res){
         if(ctx.expr(0).eType==null){
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(0).getText()+" has type null.");
            return false;
         }
         if(!checkNumericType(ctx, ctx.expr(0).eType))
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(0).getText()+" needs to be numeric.");
         if(ctx.expr(1).eType==null){
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(1).getText()+" has type null.");
            return false;
         }
         if(!checkNumericType(ctx, ctx.expr(1).eType))
            ErrorHandling.printError(ctx, "The expression "+ctx.expr(1).getText()+" needs to be numeric.");      
      }
      ctx.eType = fetchType(ctx.expr(0).eType, ctx.expr(1).eType);
      return res;
   }

   @Override public Boolean visitExprInput(BdexParser.ExprInputContext ctx) {
      Boolean res = visit(ctx.input());
      ctx.eType=ctx.input().eType;
      return res;
   }

   @Override public Boolean visitInputReal(BdexParser.InputRealContext ctx){
      ctx.eType = realType;
      return true;
   }

   @Override public Boolean visitInputInt(BdexParser.InputIntContext ctx){
      ctx.eType = integerType;
      return true;
   }

   @Override public Boolean visitInputText(BdexParser.InputTextContext ctx){
      ctx.eType = textType;
      return true;
   }

   @Override public Boolean visitPreIncrement(BdexParser.PreIncrementContext ctx) {
      Boolean res = true;
      String var = ctx.VAR().getText();
      return checkVar(var, "Int") && res;
   }

   @Override public Boolean visitPosIncrement(BdexParser.PosIncrementContext ctx) {
      Boolean res = true;
      String var = ctx.VAR().getText();
      return checkVar(var, "Int") && res;
   }

   private Boolean checkNumericType(ParserRuleContext ctx, Type t) {
      Boolean res = true;
      if(!t.isNumeric()) {
         System.err.println("Not a numeric operand");
         res = false;
      }
      return res;
   }

   private Type fetchType(Type t1, Type t2)
   {
      Type res = null;
      if (t1.isNumeric() && t2.isNumeric())
      {
         if ("Real".equals(t1.name()))
            res = t1;
         else if ("Real".equals(t2.name()))
            res = t2;
         else
            res = t1;
      }
      else if ("Boolean".equals(t1.name()) && "Boolean".equals(t2.name()))
         res = t1;
      return res;
   }

   private Boolean checkVar(String name, String type){
      Symbol s = variables.getSymbol(name);
      if(s == null) {
         ErrorHandling.printError("Variable "+name+" not declared.");
         return false;
      }
      if( (type.equals("Table") && !s.getType().conformsTo(new TableType())) || 
          (type.equals("Int") && !s.getType().conformsTo(new IntegerType())) ||
          (type.equals("Real") && !s.getType().conformsTo(new RealType()))   ||
          (type.equals("Boolean") && !s.getType().conformsTo(new BooleanType()))|| 
           type.equals("Tupple") && !s.getType().conformsTo(new TuppleType())    )    
          {
            ErrorHandling.printError("Variable "+name + " not type "+type+".");
            return false;
          }
      if(s.getValDefined()==false){
         ErrorHandling.printError("Variable "+name+ " value is null.");
         return false;
      }
      return true;
   }
}

