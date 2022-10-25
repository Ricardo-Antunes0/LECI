import Structure.*;

import org.stringtemplate.v4.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.awt.List;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

 public class BdexCompiler extends BdexBaseVisitor<ST> {
    protected int varCount = 0;
    protected STGroup stg = null;
    public SymbolTable variables = new SymbolTable();

    @Override public ST visitMain(BdexParser.MainContext ctx) {
       stg = new STGroupFile("templates.stg");
       ST res = stg.getInstanceOf("module");
       for(int i = 0; i < ctx.stat().size(); i++)
          res.add("stat", visit(ctx.stat(i)));
       return res;
    }

    @Override public ST visitStatAddLine(BdexParser.StatAddLineContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatAddColumn(BdexParser.StatAddColumnContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatRemoveColumn(BdexParser.StatRemoveColumnContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatRemoveLine(BdexParser.StatRemoveLineContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatPutValue(BdexParser.StatPutValueContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatPrint(BdexParser.StatPrintContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatAssign(BdexParser.StatAssignContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatIterate(BdexParser.StatIterateContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatIncrement(BdexParser.StatIncrementContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatSaveTable(BdexParser.StatSaveTableContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitStatLoadTable(BdexParser.StatLoadTableContext ctx) {
       return visitChildren(ctx);
    }

    @Override public ST visitCreateTable(BdexParser.CreateTableContext ctx) {
      ST res =  visit(ctx.create_table());
      ctx.tableVar = ctx.create_table().tableVar;
      ctx.eType = new TableType();
      return res;
    }

    @Override public ST visitExtractTable(BdexParser.ExtractTableContext ctx) {
       ST res = visit(ctx.extract_table());
       ctx.tableVar = ctx.extract_table().tableVar;
       ctx.eType = new TableType();
       return res;
    }

    @Override public ST visitJoinTables(BdexParser.JoinTablesContext ctx) {
      ST res = visit(ctx.join_tables());
      ctx.tableVar = ctx.join_tables().tableVar;
      ctx.eType = new TableType();
      return res;
    }

    @Override public ST visitVariableTable(BdexParser.VariableTableContext ctx) {
       ctx.tableVar = ctx.VAR().getText();
       ctx.eType = new TableType();
       return null;
    }

    @Override public ST visitCreateTableNoLine(BdexParser.CreateTableNoLineContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.columns()).render());
       ST createTable = stg.getInstanceOf("createTable");
       ctx.tableVar = newVarName();
       createTable.add("tablename", ctx.tableVar);
       createTable.add("columnArray", ctx.columns().varName);
       res.add("stat", createTable.render());
       return res;
    }

    @Override public ST visitCreateTableLine(BdexParser.CreateTableLineContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.columns()).render());
       res.add("stat", visit(ctx.line).render());
       ST createTable = stg.getInstanceOf("createTable");
       createTable.add("numLines", ctx.line.varName);
       ctx.tableVar = newVarName();
       createTable.add("tablename", ctx.tableVar);
       createTable.add("columnArray", ctx.columns().varName);
       res.add("stat", createTable.render());
       return res;
    }

    @Override public ST visitCreateTableValues(BdexParser.CreateTableValuesContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.columns()).render());
       res.add("stat", visit(ctx.values()).render());
       ST createTable = stg.getInstanceOf("createTable");
       createTable.add("valueArray", ctx.values().varName);
       ctx.tableVar = newVarName();
       createTable.add("tablename", ctx.tableVar);
       createTable.add("columnArray", ctx.columns().varName);
       res.add("stat", createTable.render());
       return res;
    }

    @Override public ST visitAddLineValues(BdexParser.AddLineValuesContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.values()).render());
       if(ctx.index != null) {
          res.add("stat", visit(ctx.index).render());
          ST addLineIndex = stg.getInstanceOf("addLine");
          addLineIndex.add("valuesVar", ctx.values().varName);
          addLineIndex.add("lineIndex", ctx.index.varName);
          addLineIndex.add("tablename", ctx.tablename.getText());
          res.add("stat", addLineIndex.render());
       } else {
          ST addLineLast = stg.getInstanceOf("addLine");
          addLineLast.add("valuesVar", ctx.values().varName);
          addLineLast.add("tablename", ctx.tablename.getText());
          res.add("stat", addLineLast.render());
       }
       return res;
    }

    @Override public ST visitAddLineIndexOmission(BdexParser.AddLineIndexOmissionContext ctx) {
       ST res = stg.getInstanceOf("stats");
       if(ctx.index != null) {
          res.add("stat", visit(ctx.index).render());
          ST addLineIndex = stg.getInstanceOf("addLine");
          addLineIndex.add("lineIndex", ctx.index.varName);
          addLineIndex.add("tablename", ctx.tablename.getText());
          res.add("stat", addLineIndex.render());
       } else {
          ST addLineLast = stg.getInstanceOf("addLine");
          addLineLast.add("tablename", ctx.tablename.getText());
          res.add("stat", addLineLast.render());
       }
       return res;
    }

    @Override public ST visitAddColumnOmissionPlace(BdexParser.AddColumnOmissionPlaceContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.place).render());
       String placeColumnVarName = ctx.place.varName;
       res.add("stat", visit(ctx.to).render());
       String columnToBeAddedVarName = ctx.to.varName;
       ST addColumnOmissionPlace = null;
       switch (ctx.where.getText()) {
          case "before":
             addColumnOmissionPlace = stg.getInstanceOf("addColumnBeforePlace");
             addColumnOmissionPlace.add("tablename", ctx.tablename.getText());
             addColumnOmissionPlace.add("placeColumn", placeColumnVarName);
             addColumnOmissionPlace.add("toColumn", columnToBeAddedVarName);
             res.add("stat", addColumnOmissionPlace.render());
             break;
          case "after":
             addColumnOmissionPlace = stg.getInstanceOf("addColumnAfterPlace");
             addColumnOmissionPlace.add("tablename", ctx.tablename.getText());
             addColumnOmissionPlace.add("placeColumn", placeColumnVarName);
             addColumnOmissionPlace.add("toColumn", columnToBeAddedVarName);
             res.add("stat", addColumnOmissionPlace.render());
             break;
       }
       return res;
    }

    @Override public ST visitAddColumnValuesPlace(BdexParser.AddColumnValuesPlaceContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.place).render());
       String placeColumnVarName = ctx.place.varName;
       res.add("stat", visit(ctx.to).render());
       String columnToBeAddedVarName = ctx.to.varName;
       res.add("stat", visit(ctx.values()).render());
       String valuesVarName = ctx.values().varName;
       ST addColumnValuesPlace = null;
       switch (ctx.where.getText()) {
          case "before":
             addColumnValuesPlace = stg.getInstanceOf("addColumnBeforePlace");
             addColumnValuesPlace.add("tablename", ctx.tablename.getText());
             addColumnValuesPlace.add("placeColumn", placeColumnVarName);
             addColumnValuesPlace.add("toColumn", columnToBeAddedVarName);
             addColumnValuesPlace.add("values", valuesVarName);
             res.add("stat", addColumnValuesPlace.render());
             break;
          case "after":
             addColumnValuesPlace = stg.getInstanceOf("addColumnAfterPlace");
             addColumnValuesPlace.add("tablename", ctx.tablename.getText());
             addColumnValuesPlace.add("placeColumn", placeColumnVarName);
             addColumnValuesPlace.add("toColumn", columnToBeAddedVarName);
             addColumnValuesPlace.add("values", valuesVarName);
             res.add("stat", addColumnValuesPlace.render());
             break;
       }
       return res;
    }

    @Override public ST visitAddColumnOmissionFL(BdexParser.AddColumnOmissionFLContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.to).render());
       ST addColumnValuesFL = null;
       if(ctx.where != null) {
          switch(ctx.where.getText()) {
             case "first":
                addColumnValuesFL = stg.getInstanceOf("addColumnFLFirst");
                addColumnValuesFL.add("tablename", ctx.tablename.getText());
                addColumnValuesFL.add("toColumn", ctx.to.varName);
                res.add("stat", addColumnValuesFL.render());
                break;
             case "last":
                addColumnValuesFL = stg.getInstanceOf("addColumnFLNoWhere");
                addColumnValuesFL.add("tablename", ctx.tablename.getText());
                addColumnValuesFL.add("toColumn", ctx.to.varName);
                res.add("stat", addColumnValuesFL.render());
                break;
          }
       } else {
          addColumnValuesFL = stg.getInstanceOf("addColumnFLNoWhere");
          addColumnValuesFL.add("tablename", ctx.tablename.getText());
          addColumnValuesFL.add("toColumn", ctx.to.varName);
          res.add("stat", addColumnValuesFL.render());
       }
       return res;
    }

    @Override public ST visitAddColumnValuesFL(BdexParser.AddColumnValuesFLContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.to).render());
       res.add("stat", visit(ctx.values()).render());
       ST addColumnValuesFL = null;
       if(ctx.where != null) {
          switch(ctx.where.getText()) {
             case "first":
                addColumnValuesFL = stg.getInstanceOf("addColumnFLFirst");
                addColumnValuesFL.add("tablename", ctx.tablename.getText());
                addColumnValuesFL.add("toColumn", ctx.to.varName);
                addColumnValuesFL.add("values", ctx.values().varName);
                res.add("stat", addColumnValuesFL.render());
                break;
             case "last":
                addColumnValuesFL = stg.getInstanceOf("addColumnFLNoWhere");
                addColumnValuesFL.add("tablename", ctx.tablename.getText());
                addColumnValuesFL.add("toColumn", ctx.to.varName);
                addColumnValuesFL.add("values", ctx.values().varName);
                res.add("stat", addColumnValuesFL.render());
                break;
          }
       } else {
          addColumnValuesFL = stg.getInstanceOf("addColumnFLNoWhere");
          addColumnValuesFL.add("tablename", ctx.tablename.getText());
          addColumnValuesFL.add("toColumn", ctx.to.varName);
          addColumnValuesFL.add("values", ctx.values().varName);
          res.add("stat", addColumnValuesFL.render());
       }
       return res;
    }

    @Override public ST visitRemove_column(BdexParser.Remove_columnContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.column()).render());
      String column = ctx.column().varName;
      ST remover = stg.getInstanceOf("removeColumn");
      remover.add("tablename", ctx.tablename.getText());
      remover.add("column", column);
      res.add("stat", remover.render());
      return res;
    }

    @Override public ST visitRemove_line(BdexParser.Remove_lineContext ctx) {
      ST res = stg.getInstanceOf("stats");
      ST remover = stg.getInstanceOf("removeLine");
      if(ctx.index!=null){
         res.add("stat", visit(ctx.index).render());
         remover.add("tablename",ctx.tablename.getText());
         remover.add("index",ctx.index.varName);
      }else{
         remover.add("tablename",ctx.tablename.getText());
      }
      res.add("stat", remover.render());
      return res;
    }

    @Override public ST visitPut_value(BdexParser.Put_valueContext ctx) {
       ctx.varName = newVarName();
       ctx.eType = ctx.expr(1).eType;
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.column()).render());
       res.add("stat", visit(ctx.expr(0)).render());
       res.add("stat", visit(ctx.expr(1)).render());
       ST putValue = stg.getInstanceOf("putValue");
       putValue.add("tablename", ctx.tablename.getText());
       putValue.add("column", ctx.column().varName);
       putValue.add("line", ctx.expr(0).varName);
       putValue.add("value", ctx.expr(1).varName);
       putValue.add("returnVar", ctx.varName);
       res.add("stat", putValue.render());
       return res;
    }

    @Override public ST visitExtractTableColumns(BdexParser.ExtractTableColumnsContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.columns()).render());
       ST extract = stg.getInstanceOf("extractTableColumnsAnd2Expr");
       ctx.tableVar=newVarName();
       extract.add("returnTable", ctx.tableVar);
       extract.add("tablename", ctx.reftable.getText());
       extract.add("columnArray", ctx.columns().varName);
       res.add("stat", extract.render());
       return res;
    }

    @Override public ST visitExtractTableColumnsInterval(BdexParser.ExtractTableColumnsIntervalContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.column(0)).render());
      res.add("stat", visit(ctx.column(1)).render());
      ST extract = stg.getInstanceOf("extractTable2Column2Expr");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("c1", ctx.column(0).varName);
      extract.add("c2", ctx.column(1).varName);
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitExtractTableLines(BdexParser.ExtractTableLinesContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.values()).render());
      ST extract = stg.getInstanceOf("extractTableLines");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("values", ctx.values().varName);
      extract.add("integerArray", newVarName());
      extract.add("loopVar", newVarName());
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitExtractTableLineBreak(BdexParser.ExtractTableLineBreakContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST extract = stg.getInstanceOf("extractTableColumnsAnd2Expr");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("e1", ctx.expr(0).varName);
      extract.add("e2", ctx.expr(1).varName);
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitExtractTableBothColumns(BdexParser.ExtractTableBothColumnsContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.columns()).render());
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST extract = stg.getInstanceOf("extractTableColumnsAnd2Expr");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("columnArray", ctx.columns().varName);
      extract.add("e1", ctx.expr(0).varName);
      extract.add("e2", ctx.expr(1).varName);
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitExtractTableBothInterval(BdexParser.ExtractTableBothIntervalContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.column(0)).render());
      res.add("stat", visit(ctx.column(1)).render());
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST extract = stg.getInstanceOf("extractTable2Column2Expr");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("c1", ctx.column(0).varName);
      extract.add("c2", ctx.column(1).varName);
      extract.add("e1", ctx.expr(0).varName);
      extract.add("e2", ctx.expr(1).varName);
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitExtractTableContains(BdexParser.ExtractTableContainsContext ctx) {
      ST res = stg.getInstanceOf("stats");
      res.add("stat", visit(ctx.values()).render());
      ST extract = stg.getInstanceOf("extractTableContains");
      ctx.tableVar=newVarName();
      extract.add("returnTable", ctx.tableVar);
      extract.add("tablename", ctx.reftable.getText());
      extract.add("values", ctx.values().varName);
      res.add("stat", extract.render());
      return res;
    }

    @Override public ST visitJoin_tables(BdexParser.Join_tablesContext ctx) {
       ST res = stg.getInstanceOf("stats");
       ST tableArray = stg.getInstanceOf("tableArrayInit");
       String tableArrayVar = newVarName();
       tableArray.add("var", tableArrayVar);
       tableArray.add("size", ctx.table().size());
       res.add("stat", tableArray.render());
       for(int i = 0; i < ctx.table().size(); i++) {
          if(visit(ctx.table(i)) != null)
             res.add("stat", visit(ctx.table(i)).render());
          ST tableArrayAdd = stg.getInstanceOf("arrayAdd");
          tableArrayAdd.add("var", tableArrayVar);
          tableArrayAdd.add("index", i);
          tableArrayAdd.add("value", ctx.table(i).tableVar);
          res.add("stat", tableArrayAdd.render());
       }
       ST joinTables = stg.getInstanceOf("joinTables");
       ctx.tableVar = newVarName();
       joinTables.add("returnTable", ctx.tableVar);
       joinTables.add("tableArray", tableArrayVar);
       res.add("stat", joinTables.render());
       return res;
    }

    @Override public ST visitPrintVar(BdexParser.PrintVarContext ctx) {
       Symbol var = variables.getSymbol(ctx.VAR().getText());
       ST res = null;
       if(var.getType().conformsTo(new TableType())) {
          res = stg.getInstanceOf("printTable");
          res.add("tablename", ctx.VAR().getText());
       } else {
          res = stg.getInstanceOf("print");
          if(ctx.it==null){
            res.add("expr", ctx.VAR().getText());
          }else{
            res.add("expr", ctx.VAR().getText()+".getValue("+ ctx.it.getText()+")");
          }
       }
       return res;
    }

    @Override public ST visitPrintTable(BdexParser.PrintTableContext ctx) {
       ST res = stg.getInstanceOf("printTable");
       res.add("stat", visit(ctx.table()).render());
       res.add("tablename", ctx.table().tableVar);
       return res;
    }

    @Override public ST visitPrintExpr(BdexParser.PrintExprContext ctx) {
       ST res = stg.getInstanceOf("print");
         res.add("stat", visit(ctx.expr()).render());
         res.add("type", ctx.expr().eType.name());
         res.add("expr", ctx.expr().varName);
      return res;
    }

    @Override public ST visitPrintAssign(BdexParser.PrintAssignContext ctx) {
       ST res = null;
       visit(ctx.assign());
       if(ctx.assign().eType.conformsTo(new TableType())) {
          res = stg.getInstanceOf("printTable");
          res.add("stat", visit(ctx.assign()).render());
          res.add("tablename", ctx.assign().varName);
       } else {
          res = stg.getInstanceOf("print");
          res.add("stat", visit(ctx.assign()).render());
          res.add("type", ctx.assign().eType.name());
          res.add("expr", ctx.assign().varName);
       }
       return res;
    }

    @Override public ST visitPrintIterate(BdexParser.PrintIterateContext ctx) {
        ST res = stg.getInstanceOf("printIterate");
        res.add("var", newVarName());
        res.add("value", visit(ctx.iterate()).render());
        return res;
    }

    @Override public ST visitDeclaration(BdexParser.DeclarationContext ctx) {
       ctx.eType = ctx.tipo().res;
       ctx.varName = ctx.VAR().getText();
       ST res = stg.getInstanceOf("decl");
       res.add("type", ctx.eType);
       res.add("var", ctx.varName);
       variables.put(new Symbol(ctx.varName, ctx.eType), null);
       return res;
    }

    @Override public ST visitAssignDeclarationTable(BdexParser.AssignDeclarationTableContext ctx) {
       ST res = stg.getInstanceOf("stats");
       ctx.varName = ctx.VAR().getText();
       ctx.eType = ctx.tipo().res;
       variables.put(new Symbol(ctx.varName, ctx.eType), true);
       ST ass = stg.getInstanceOf("decl");
       ass.add("type", ctx.eType);
       ass.add("var", ctx.varName);
       if(visit(ctx.table())!=null){
         res.add("stat",visit(ctx.table()).render());
         ass.add("value", ctx.table().tableVar);
         res.add("stat", ass.render());
       }else{
         ass.add("value", ctx.table().tableVar);
         res.add("stat", ass.render());
       }
       return res;
    }

    @Override public ST visitAssignDeclarationExpr(BdexParser.AssignDeclarationExprContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ctx.eType = ctx.tipo().res;
       variables.put(new Symbol(ctx.varName, ctx.eType), true);
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.expr()).render());
       ST decl = stg.getInstanceOf("decl");
       decl.add("type", ctx.eType.name());
       decl.add("var", ctx.varName);
       decl.add("value", ctx.expr().varName);
       res.add("stat", decl.render());
       return res;
    }

    @Override public ST visitAssignDeclarationPut(BdexParser.AssignDeclarationPutContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ctx.eType = ctx.tipo().res;
       variables.put(new Symbol(ctx.varName, ctx.eType), true);
       String typing;
       if(ctx.eType.toString().equals("Text"))
         typing="String";
       else if(ctx.eType.toString().equals("Real"))
         typing="Double";
       else
         typing="Integer";
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.put_value()).render());
       ST decl = stg.getInstanceOf("decl");
       decl.add("var", ctx.varName);
       decl.add("type", ctx.eType.name());
       decl.add("value", "("+typing+")"+ctx.put_value().varName);
       res.add("stat", decl.render());
       return res;
    }

    @Override public ST visitAssignDeclarationIterator(BdexParser.AssignDeclarationIteratorContext ctx) {
       ctx.eType = new IteratorType();
       ctx.varName = ctx.VAR().getText();
       variables.put(new Symbol(ctx.varName, ctx.eType), true);
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.iterator()).render());
       ST decl = stg.getInstanceOf("decl");
       decl.add("var", ctx.varName);
       decl.add("type", ctx.eType.name());
       decl.add("value", ctx.iterator().varName);
       res.add("stat", decl.render());
       return res;
    }

    @Override public ST visitAssignDeclarationIterate(BdexParser.AssignDeclarationIterateContext ctx) {
        ctx.eType = ctx.tipo().res;
        ctx.varName = ctx.VAR().getText();
        variables.put(new Symbol(ctx.varName, ctx.eType), true);
        ST res = stg.getInstanceOf("decl");
        res.add("type", ctx.eType.name());
        res.add("var", ctx.varName);
        if(!ctx.eType.name().equals("Object")) {
            String typing = "";
            switch (ctx.eType.name()) {
                case "Int":
                    typing = "Integer";
                    break;
                case "Real":
                    typing = "Double";
                    break;
                case "Text":
                    typing = "String";
                    break;
            }
            res.add("value", "(" + typing + ") " + visit(ctx.iterate()).render());
        } else {
            res.add("value", visit(ctx.iterate()).render());
        }
        return res;
    }

    @Override public ST visitAssignDeclarationTupple(BdexParser.AssignDeclarationTuppleContext ctx) {
      ctx.varName = ctx.VAR().getText();
      ctx.eType = ctx.tipo().res;
      ST res = stg.getInstanceOf("stats");
      ArrayList<String> names = new ArrayList<>();
      ArrayList<Type> tipos = new ArrayList<>();
      for(BdexParser.ExprContext n : ctx.expr()){
         if(n != null)
            res.add("stat", visit(n).render());
            names.add(n.varName);
            tipos.add(n.eType);
      }

      ST listNames = stg.getInstanceOf("list");
      String namesName = newVarName();
      listNames.add("var",namesName);
      listNames.add("type","Object");
      res.add("stat",listNames.render());
      for(String n : names){
         ST adding = stg.getInstanceOf("listAdd");
         adding.add("varList",namesName);
         adding.add("var", n);
         res.add("stat",adding.render());
      }
      variables.put(new Symbol(ctx.varName, ctx.eType), tipos.toArray(new Type[0]));

      ST listTypes = stg.getInstanceOf("list");
      String typesName = newVarName();
      listTypes.add("var",typesName);
      listTypes.add("type","Type");
      res.add("stat",listTypes.render());
      for(Type t : tipos){
         ST adding = stg.getInstanceOf("listAdd");
         adding.add("varList",typesName);
         if(t.toString().equals("Int")){
            adding.add("var", "new "+t+"egerType()");
         }else{
            adding.add("var", "new "+t+"Type()");
         }
         res.add("stat",adding.render());
      }

      ST tup = stg.getInstanceOf("declarationTupple");
      tup.add("returnTupple", ctx.varName);
      tup.add("values", namesName);
      tup.add("types", typesName);
      res.add("stat", tup.render());

      return res;
    }

    @Override public ST visitAssignTable(BdexParser.AssignTableContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ctx.eType = new TableType();
       variables.put(new Symbol(ctx.varName, ctx.eType), null);
       ST res = stg.getInstanceOf("assign");
       if(visit(ctx.table())!=null){
         res.add("stat", visit(ctx.table()).render());
       }
       res.add("var", ctx.varName);
       res.add("value", ctx.table().tableVar);
       return res;
    }

    @Override public ST visitAssignExpr(BdexParser.AssignExprContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ctx.eType = ctx.expr().eType;
       variables.put(new Symbol(ctx.varName, ctx.eType), null);
       ST res = stg.getInstanceOf("assign");
       res.add("stat", visit(ctx.expr()).render());
       res.add("var", ctx.VAR().getText());
       res.add("value", ctx.expr().varName);
       return res;
    }

    @Override public ST visitAssignPut(BdexParser.AssignPutContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ctx.eType = ctx.put_value().eType;
       variables.put(new Symbol(ctx.varName, ctx.eType), null);
       String typing;
       if(ctx.eType.toString().equals("Text"))
         typing="String";
       else if(ctx.eType.toString().equals("Real"))
         typing="Double";
       else
         typing="Integer";
       ST res = stg.getInstanceOf("assign");
       res.add("stat", visit(ctx.put_value()).render());
       res.add("var", ctx.VAR().getText());
       res.add("value", "("+typing+")"+ctx.put_value().varName);
       return res;
    }

    @Override public ST visitAssignIterator(BdexParser.AssignIteratorContext ctx) {
        ctx.eType = new IteratorType();
        ctx.varName = ctx.VAR().getText();
        variables.put(new Symbol(ctx.varName, ctx.eType), true);
        ST res = stg.getInstanceOf("assign");
        res.add("stat", visit(ctx.iterator()).render());
        res.add("var", ctx.varName);
        res.add("value", ctx.iterator().varName);
        return res;
    }

    @Override public ST visitAssignIterate(BdexParser.AssignIterateContext ctx) {
        ctx.varName = ctx.VAR().getText();
        Symbol s = variables.getSymbol(ctx.varName);
        ctx.eType = s.getType();
        variables.put(new Symbol(ctx.varName, ctx.eType), true);
        ST res = stg.getInstanceOf("assign");
        res.add("var", ctx.varName);
        if(!ctx.eType.name().equals("Object")) {
            String typing = "";
            switch (ctx.eType.name()) {
                case "Int":
                    typing = "Integer";
                    break;
                case "Real":
                    typing = "Double";
                    break;
                case "Text":
                    typing = "String";
                    break;
            }
            res.add("value", "(" + typing + ") " + visit(ctx.iterate()).render());
        } else {
            res.add("value", visit(ctx.iterate()).render());
        }
        return res;
    }

    @Override public ST visitTipo(BdexParser.TipoContext ctx) {
       return null;
    }

    @Override public ST visitIteratorTable(BdexParser.IteratorTableContext ctx) {
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("iteratorTable");
       res.add("var", ctx.varName);
       res.add("tablename", ctx.tablename.getText());
       return res;
    }

    @Override public ST visitIteratorLine(BdexParser.IteratorLineContext ctx) {
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.index).render());
       ST iteratorLine = stg.getInstanceOf("iteratorLine");
       iteratorLine.add("var", ctx.varName);
       iteratorLine.add("tablename", ctx.tablename.getText());
       iteratorLine.add("line", ctx.index.varName);
       res.add("stat", iteratorLine.render());
       return res;
    }

    @Override public ST visitIteratorColumn(BdexParser.IteratorColumnContext ctx) {
        ctx.varName = newVarName();
        ST res = stg.getInstanceOf("stats");
        res.add("stat", visit(ctx.method).render());
        ST iteratorColumn = stg.getInstanceOf("iteratorColumn");
        iteratorColumn.add("var", ctx.varName);
        iteratorColumn.add("tablename", ctx.tablename.getText());
        iteratorColumn.add("column", ctx.method.varName);
        res.add("stat", iteratorColumn.render());
        return res;
    }

    @Override public ST visitIterate(BdexParser.IterateContext ctx) {
       ST res = stg.getInstanceOf("iterate");
       res.add("var", ctx.VAR().getText());
       return res;
    }

    @Override public ST visitLoad_table(BdexParser.Load_tableContext ctx) {
       String tablename = ctx.tablename.getText();
       String filename = ctx.filename.getText();
       ST res = stg.getInstanceOf("loadTable");
       res.add("tablename", tablename);
       res.add("filename", filename);
       res.add("var", newVarName());
       variables.put(new Symbol(tablename, new TableType()), true);
       return res;
    }

    @Override public ST visitSave_table(BdexParser.Save_tableContext ctx) {
       String tablename = ctx.tablename.getText();
       String filename = ctx.filename.getText();
       filename = filename.substring(1, filename.length()-1);
       if(!filename.contains(".csv") && !filename.contains(".txt"))
          filename += ".csv";
       ST res = stg.getInstanceOf("saveTable");
       res.add("tablename", tablename);
       res.add("filename", "\"" + filename + "\"");
       res.add("f", newVarName());
       res.add("pw", newVarName());
       res.add("columnNames", newVarName());
       res.add("firstLine", newVarName());
       res.add("tableMap", newVarName());
       res.add("i", newVarName());
       res.add("c", newVarName());
       res.add("line", newVarName());
       res.add("e", newVarName());
       return res;
    }

    @Override public ST visitColumns(BdexParser.ColumnsContext ctx) {
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("stats");
       ST columnArrayInit = stg.getInstanceOf("columnArrayInit");
       columnArrayInit.add("var", ctx.varName);
       columnArrayInit.add("size", ctx.column().size());
       res.add("stat", columnArrayInit.render());
       for(int i = 0; i < ctx.column().size(); i++) {
          res.add("stat", visit(ctx.column(i)).render());
          ST columnArrayAdd = stg.getInstanceOf("arrayAdd");
          columnArrayAdd.add("var", ctx.varName);
          columnArrayAdd.add("index", i);
          columnArrayAdd.add("value", ctx.column(i).varName);
          res.add("stat", columnArrayAdd.render());
       }
       return res;
    }

    @Override public ST visitColumn(BdexParser.ColumnContext ctx) {
       ctx.eType = new Type(ctx.type.getText());
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("newColumn");
       res.add("var", ctx.varName);
       res.add("type", ctx.eType);
       res.add("name", ctx.TEXT().getText());
       return res;
    }

    @Override public ST visitValues(BdexParser.ValuesContext ctx) {
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("stats");
       ST objectArrayInit = stg.getInstanceOf("objectArrayInit");
       objectArrayInit.add("var", ctx.varName);
       objectArrayInit.add("size", ctx.expr().size());
       res.add("stat", objectArrayInit.render());
       for(int i = 0; i < ctx.expr().size(); i++) {
          res.add("stat", visit(ctx.expr(i)).render());
          ST objectArrayAdd = stg.getInstanceOf("arrayAdd");
          objectArrayAdd.add("var", ctx.varName);
          objectArrayAdd.add("index", i);
          objectArrayAdd.add("value", ctx.expr(i).varName);
          res.add("stat", objectArrayAdd.render());
       }
       return res;
    }

    @Override public ST visitExprVar(BdexParser.ExprVarContext ctx) {
       ST res = stg.getInstanceOf("decl");
       ctx.varName = newVarName();
       Symbol s = variables.getSymbol(ctx.VAR().getText());
       if(ctx.it==null){
          ctx.eType = s.getType();
          res.add("type", ctx.eType);
          res.add("var", ctx.varName);
          res.add("value", ctx.VAR().getText());
       }else{
          if(ctx.eType.toString().equals("Text")){
            res.add("var", "String "+ctx.varName);
            res.add("value", "(String)"+ctx.VAR().getText()+".getValue("+ ctx.it.getText()+")");
         }else if(ctx.eType.toString().equals("Int")){
            res.add("var", "Integer "+ctx.varName);
            res.add("value", "(Integer)"+ctx.VAR().getText()+".getValue("+ ctx.it.getText()+")");
          }else if(ctx.eType.toString().equals("Real")){
            res.add("var", "Double "+ctx.varName);
            res.add("value", "(Double)"+ctx.VAR().getText()+".getValue("+ ctx.it.getText()+")");
         }else if(ctx.eType.toString().equals("Boolean")){
            res.add("var", "Boolean "+ctx.varName);
            res.add("value", "(Boolean)"+ctx.VAR().getText()+".getValue("+ ctx.it.getText()+")");
         }
       }
       return res;
    }

    @Override public ST visitExprAddSub(BdexParser.ExprAddSubContext ctx) {
       ctx.varName = newVarName();
       visit(ctx.expr(0));
       visit(ctx.expr(1));
       ctx.eType = fetchType(ctx.expr(0).eType, ctx.expr(1).eType);
       return binaryExpression(ctx, visit(ctx.expr(0)).render(), visit(ctx.expr(1)).render(), ctx.eType.name(), ctx.varName,
               ctx.expr(0).varName, ctx.op.getText(), ctx.expr(1).varName);
    }

    @Override public ST visitExprBin(BdexParser.ExprBinContext ctx) {
       ctx.varName = newVarName();
       ctx.eType = fetchType(ctx.expr(0).eType, ctx.expr(1).eType);
       return binaryExpression(ctx, visit(ctx.expr(0)).render(), visit(ctx.expr(1)).render(), ctx.eType.name(), ctx.varName,
               ctx.expr(0).varName, ctx.op.getText(), ctx.expr(1).varName);
    }

    @Override public ST visitExprParentesis(BdexParser.ExprParentesisContext ctx) {
       ST res = visit(ctx.expr());
       ctx.varName = ctx.expr().varName;
       ctx.eType = ctx.expr().eType;
       return res;
    }

    @Override public ST visitExprConcat(BdexParser.ExprConcatContext ctx) {
       ctx.eType = new TextType();
       ctx.varName = newVarName();
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.expr()).render());
       ST concat = stg.getInstanceOf("concat");
       concat.add("newVar", ctx.varName);
       if(ctx.it==null){
         concat.add("var", ctx.VAR().getText());
       }else{
         concat.add("var", "((String)"+ctx.VAR().getText()+".getValue("+ ctx.it.getText()+"))");
       }
       concat.add("expr", ctx.expr().varName);
       res.add("stat", concat.render());
       return res;
    }

    @Override public ST visitExprUnary(BdexParser.ExprUnaryContext ctx) {
       ST res = stg.getInstanceOf("stats");
       res.add("stat", visit(ctx.expr()).render());
       ST decl = stg.getInstanceOf("decl");
       ctx.varName = newVarName();
       ctx.eType = ctx.expr().eType;
       decl.add("type", ctx.eType.name());
       decl.add("var", ctx.varName);
       decl.add("value", ctx.op.getText() + ctx.expr().varName);
       res.add("stat", decl.render());
       return res;
    }

    @Override public ST visitExprIncrement(BdexParser.ExprIncrementContext ctx) {
       ctx.varName = ctx.increment().varName;
       return visit(ctx.increment());
    }

    @Override public ST visitExprReal(BdexParser.ExprRealContext ctx) {
       ST res = stg.getInstanceOf("decl");
       ctx.varName = newVarName();
       ctx.eType = new RealType();
       res.add("type", "Real");
       res.add("var", ctx.varName);
       res.add("value", Double.parseDouble(ctx.REAL().getText()));
       return res;
    }

    @Override public ST visitExprText(BdexParser.ExprTextContext ctx) {
       ST res = stg.getInstanceOf("decl");
       ctx.varName = newVarName();
       ctx.eType = new TextType();
       res.add("type", "Text");
       res.add("var", ctx.varName);
       res.add("value", ctx.TEXT().getText());
       return res;
    }

    @Override public ST visitExprInput(BdexParser.ExprInputContext ctx) {
       ST res = visit(ctx.input());
       ctx.eType = ctx.input().eType;
       ctx.varName = ctx.input().varName;
       return res;
    }

    @Override public ST visitExprInteger(BdexParser.ExprIntegerContext ctx) {
       ST res = stg.getInstanceOf("decl");
       ctx.varName = newVarName();
       ctx.eType = new IntegerType();
       res.add("type", "Int");
       res.add("var", ctx.varName);
       res.add("value", Integer.parseInt(ctx.INTEGER().getText()));
       return res;
    }

    @Override public ST visitExprMultDivMod(BdexParser.ExprMultDivModContext ctx) {
       ctx.varName = newVarName();
       visit(ctx.expr(0));
       visit(ctx.expr(1));
       ctx.eType = fetchType(ctx.expr(0).eType, ctx.expr(1).eType);
       return binaryExpression(ctx, visit(ctx.expr(0)).render(), visit(ctx.expr(1)).render(), ctx.eType.name(), ctx.varName,
               ctx.expr(0).varName, ctx.op.getText(), ctx.expr(1).varName);
    }

    @Override public ST visitInputInt(BdexParser.InputIntContext ctx) {
       ctx.varName = newVarName();
       ctx.eType = new IntegerType();
       ST res = stg.getInstanceOf("input");
       res.add("type", "Int");
       res.add("var", ctx.varName);
       return res;
    }

    @Override public ST visitInputReal(BdexParser.InputRealContext ctx) {
       ctx.varName = newVarName();
       ctx.eType = new RealType();
       ST res = stg.getInstanceOf("input");
       res.add("type", "Real");
       res.add("var", ctx.varName);
       return res;
    }

    @Override public ST visitInputText(BdexParser.InputTextContext ctx) {
       ctx.varName = newVarName();
       ctx.eType = new TextType();
       ST res = stg.getInstanceOf("input");
       res.add("type", "Text");
       res.add("var", ctx.varName);
       return res;
    }
    
    @Override public ST visitExprBoolean(BdexParser.ExprBooleanContext ctx) {
         ST res = stg.getInstanceOf("decl");
         ctx.varName = newVarName();
         ctx.eType = new BooleanType();
         res.add("type", "Boolean");
         res.add("var", ctx.varName);
         res.add("value", ctx.BOOLEAN().getText());
         return res;
     }

    @Override public ST visitPreIncrement(BdexParser.PreIncrementContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ST res = stg.getInstanceOf("preIncrement");
       res.add("preffix", ctx.op.getText());
       res.add("var", ctx.VAR().getText());
       return res;
    }

    @Override public ST visitPosIncrement(BdexParser.PosIncrementContext ctx) {
       ctx.varName = ctx.VAR().getText();
       ST res = stg.getInstanceOf("postIncrement");
       res.add("var", ctx.VAR().getText());
       res.add("suffix", ctx.op.getText());
       return res;
    }

    protected String newVarName() {
       varCount++;
       return "v" + varCount;
    }

    protected ST binaryExpression(ParserRuleContext ctx, String e1Stats, String e2Stats, String type, String var, String e1Var, String op, String e2Var) {
       ST res = stg.getInstanceOf("binaryExpression");
       res.add("stat", e1Stats);
       res.add("stat", e2Stats);
       res.add("type", type);
       res.add("var", var);
       res.add("e1", e1Var);
       res.add("op", op);
       res.add("e2", e2Var);
       return res;
    }

    private Type fetchType(Type t1, Type t2) {
       Type res = null;
       if (t1.isNumeric() && t2.isNumeric()) {
          if ("Real".equals(t1.name()))
             res = t1;
          else if ("Real".equals(t2.name()))
             res = t2;
          else
             res = t1;
       }
       else if ("Boolean".equals(t1.name()) && "Boolean".equals(t2.name()))
          res = t1;
       else if ("Table".equals(t1.name()) && "Table".equals(t2.name()))
          res = t1;
       return res;
    }
 }
 
