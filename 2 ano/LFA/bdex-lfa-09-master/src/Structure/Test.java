package Structure;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		Column[] cArray = new Column [6];
		cArray[0]= new Column("eu","Text");
		cArray[1]= new Column("tu","Int");
		cArray[2]= new Column("ele","Real");
		cArray[3]= new Column("nos","Int");
		cArray[4]= new Column("vos","Real");
		cArray[5]= new Column("eles","Text");
		
		Object[] obj1 = new Object[6];
		obj1[0]="String1";
		obj1[1]=1;
		obj1[2]=2.0;
		obj1[3]=3;
		obj1[4]=4.0;
		obj1[5]="String5";
		
		Object[] obj2 = new Object[9];
		obj2[0]="String1";
		obj2[1]=1;
		obj2[2]=2.0;
		obj2[3]=3;
		obj2[4]=4.0;
		obj2[5]="String5";
		obj2[6]="String6";
		obj2[7]=7;
		obj2[8]=8.0;
		
		Object[] obj3 = new Object[3];
		obj3[0]=0;
		obj3[1]=1;
		obj3[2]=2;
		
		System.out.println("-------------------CREATE--------------------");
		System.out.println("T1");
		Table t1 = new Table(Arrays.copyOfRange(cArray, 0, 3));
		t1.printTable();
		
		System.out.println("T2");
		Table t2 = new Table(Arrays.copyOfRange(cArray, 0, 3), 5);
		t2.printTable();
		
		System.out.println("T3");
		Table t3 = new Table(cArray, obj1);
		t3.printTable();
		
		
		//SHOULD FAIL
		/*Table t4 = new Table(cArray, obj2);
		t4.printTable();*/
		System.out.println("-------------------ADD/REMOVE COLUMNS/LINES--------------------");
		System.out.println("T1");
		t1.addColumnIndex(new Column("intruso","Real"), 1);
		t1.addColumnLast(new Column("intrusa","Real"));
		t1.printTable();
		
		System.out.println("T2");
		t2.addColumnIndex(new Column("intruso","Int"), obj3, 1);
		t2.printTable();
		System.out.println("Removed Column: "+t2.removeColumnIndex(2));
		Column intrusor = new Column("intrusoCopy","Int");
		t2.addColumnLast(intrusor, obj3);
		t2.printTable();
		System.out.println("Removed Intrusor Column: "+t2.removeColumn(intrusor));
		t2.printTable();
		System.out.println("Removed Line"+t2.removeLineLast());
		System.out.println("Removed Line"+t2.removeLine(2));
		//System.out.println("Removed Line"+t2.removeLine(3)); //SHOULD FAIL!
		System.out.println("Removed Intrusor Column: "+t2.removeColumn(new Column("intruso","Int")));
		t2.printTable();
		//t2.addLineLast(Arrays.copyOfRange(obj2, 4, 6));	//SHOULD FAIL!
		t2.putValue(cArray[0], 1, "Success!");
		t2.printTable();

		System.out.println("-------------------EXTRACT--------------------\nT5\n");
		Object[] obj4 = {"Linha 1", 1, 1.0,"Linha 2", 2, 2.0, "", 0, 0.0, "Linha 4", 4, 4.0};
		Table t5 = new Table(Arrays.copyOfRange(cArray, 0, 3), obj4);
		t5.printTable();
		//t5.extractColumn(Arrays.copyOfRange(cArray, 1, 3)).printTable();
		//t5.extractColumns(1, 2).printTable();
		//int[] pos = {0,1,2};
		//t5.extractColumns(pos).printTable();
		t5.extractLine(1, 3).printTable();;
		//t5.extractLine(pos).printTable();
		//t5.extractBoth(pos, Arrays.copyOfRange(pos, 1, 3)).printTable();


		System.out.println("-------------------JOIN TABLES--------------------");
		Table[] tables = {t5, t2, t1};
		Table n = Table.joinTables(tables);
		System.out.println("T1");
		t1.printTable();
		System.out.println("T2");
		t2.printTable();
		System.out.println("T5");
		t5.printTable();
		n.printTable();

		System.out.println("-------------------Getters--------------------");

		Object[] obj5 = {"1º", 2, 1.0,"2º", 4, 2.5, "3º", 4, 4.0};
		Table t6 = new Table(Arrays.copyOfRange(cArray, 0, 3), obj5);

		System.out.println("T6");
		t6.printTable();
		System.out.println("Columns");
		System.out.println(t6.getNames());
		t6.removeColumnIndex(2);
		System.out.println(t6.getNames());//Should be [eu (Text), tu (Int), ele (Real)];
		System.out.println("Lines");
		t6.addLineLast();
		System.out.println(t6.getNumberLines()); //Should be 4

		System.out.println("T3");
		t3.printTable();
		System.out.println("Columns");
		System.out.println(t3.removeColumnIndex(2));
		System.out.println(t3.getNames()); //eu (Text) tu (Int) nos (Int) vos (Real) eles (Text);
		System.out.println("Lines");
		System.out.println(t3.getNumberLines()); //1 line;
		t3.addLineIndex(1);
		System.out.println(t3.getNumberLines()); //2 lines

		System.out.println("T3");
		t3.printTable();
	}

}
