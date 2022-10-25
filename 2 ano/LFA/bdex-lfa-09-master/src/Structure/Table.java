package Structure;

import java.util.*;
import java.util.stream.IntStream;

public class Table{
    private Map<Column, ArrayList<Object>> map;
    private List<Column> names;
    private int lines;

    //Create Methods
    public Table(Column[] cArray, Integer n){
        Map<Column, ArrayList<Object>> auxMap = new HashMap<>();
        List<Column> auxNames = new ArrayList<>(); 
        for(Column c : cArray){
            auxNames.add(c);
            ArrayList<Object> auxValues = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(c.getType().equals("Int")){
                    auxValues.add(0);
                }else if(c.getType().equals("Real")){
                    auxValues.add(0.0);
                }else{
                    auxValues.add("");
                }
            }
            auxMap.put(c, auxValues);
        }
        this.map=auxMap;
        this.names=auxNames;
        this.lines=n;
    }

    public Table(Column[] cArray){
        Map<Column, ArrayList<Object>> auxMap = new HashMap<>();
        List<Column> auxNames = new ArrayList<>(); 
        for(Column c : cArray){
            auxNames.add(c);
            ArrayList<Object> auxValues = new ArrayList<>();
            auxMap.put(c, auxValues);
        }
        this.map=auxMap;
        this.names=auxNames;
        this.lines=0;
    }

    public Table(Column[] cArray, Object[] values){
        assert(values.length % cArray.length==0): "You need to provide  n� of values % n� of columns equals 0.";
        Map<Column, ArrayList<Object>> auxMap = new HashMap<>();
        List<Column> auxNames = new ArrayList<>();
        int posNames=0;
        
        for(Column c : cArray){
            auxNames.add(c);
            ArrayList<Object> auxValues = new ArrayList<>();
            auxMap.put(c, auxValues);
        }
        this.names=auxNames;
        this.map=auxMap;
        
        for(Object obj : values){
        	Column c = names.get(posNames);
        	ArrayList<Object> auxValues = map.get(c);
        	if(c.getType().equals("Int")){
        		try {
        			Integer aux = (Integer) obj;
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }else if(c.getType().equals("Real")){
                try {
                    Double aux =((Number) obj).doubleValue();
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                    }
            }else{
               try  {
                    String aux = (String) obj;
                    auxValues.add(aux);
               } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
               }
            }
        	map.put(c, auxValues);
        	posNames++;
        	if(posNames==names.size()) posNames=0;
        }
        /*map.entrySet().forEach( entry -> {
            System.out.println( entry.getKey() + " => " + entry.getValue() );
        });*/
        this.lines=map.get(cArray[0]).size();
    }

    //ADD COLUMN METHODS
    
    //Adds a Column at the end of the table (with values of omission!)
    public void addColumnLast(Column c){
    	assert(!map.containsKey(c)): "Table already has that column name!" ;
        
        ArrayList<Object> auxValues = new ArrayList<>();
        if(c.getType().equals("Int")){
            for(int i=0; i<getNumberLines(); i++){
                auxValues.add(0);
            }
        }else if(c.getType().equals("Real")){
            for(int i=0; i<getNumberLines(); i++){
            	auxValues.add(0.0);
            }
        }else{
            for(int i=0; i<getNumberLines(); i++){
            	auxValues.add("");
            }
        }

        names.add(c);
        map.put(c, auxValues);
    }
    
    //Adds a Column at the end of the table with the given values
    public void addColumnLast(Column c, Object[] values){
    	assert(!map.containsKey(c)): "Table already has that column name!" ;
        assert(values.length<=getNumberLines()): "You must provide n� values < n� lines of table" ;

        ArrayList<Object> auxValues = new ArrayList<>();

        if(c.getType().equals("Int")){
            for(int i=0; i<values.length; i++){
                try {
                    Integer aux = (Integer) values[i];
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }else if(c.getType().equals("Real")){
            for(int i=0; i<values.length; i++){
                try {
                    // Double aux = (Double) values[i];
                    // auxValues.add(aux);
                    Double aux =((Number) values[i]).doubleValue();
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }else{
            for(int i=0; i<values.length; i++){
                try {
                    String aux = (String) values[i];
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }

        for(int j=values.length-1; j<getNumberLines(); j++){
            if(c.getType().equals("Int")){
            	auxValues.add(0);
            }else if(c.getType().equals("Real")){
                auxValues.add(0.0);
            }else{
                auxValues.add("");
            }
        }

        names.add(c);
        map.put(c, auxValues);
    }
    
    //Adds a Column at the index position of the table (with values of omission!)
    public void addColumnIndex(Column c, int index) {
    	assert(!map.containsKey(c)): "Table already has that column name!" ;
        
        ArrayList<Object> auxValues = new ArrayList<>();
        if(c.getType().equals("Int")){
            for(int i=0; i<getNumberLines(); i++){
                auxValues.add(0);
            }
        }else if(c.getType().equals("Real")){
            for(int i=0; i<getNumberLines(); i++){
            	auxValues.add(0.0);
            }
        }else{
            for(int i=0; i<getNumberLines(); i++){
            	auxValues.add("");
            }
        }

        if(index <= names.size()){
            names.add(index, c);
        }else{
            names.add(c);
        }  
        map.put(c, auxValues);
    }
    
    //Adds a Column at the index position of the table with the given values
    public void addColumnIndex(Column c, Object[] values, int index) {
    	assert(!map.containsKey(c)): "Table already has that column name!" ;
        assert(values.length<=getNumberLines()): "You must provide n� values < n� lines of table" ;

        ArrayList<Object> auxValues = new ArrayList<>();
        if(c.getType().equals("Int")){
            for(int i=0; i<values.length; i++){
                try {
                    Integer aux = (Integer) values[i];
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }else if(c.getType().equals("Real")){
            for(int i=0; i<values.length; i++){
                try {
                    Double aux =((Number) values[i]).doubleValue();
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }else{
            for(int i=0; i<values.length; i++){
                try {
                    String aux = (String) values[i];
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }
        }

        for(int j=values.length; j<getNumberLines(); j++){
            if(c.getType().equals("Int")){
            	auxValues.add(0);
            }else if(c.getType().equals("Real")){
                    auxValues.add(0.0);
            }else{
                    auxValues.add("");
            }
        }
        if(index < names.size()){
            names.add(index, c);
        }else{
            names.add(c);
        }  
        map.put(c, auxValues);
    }
    
    //ADD LINE METHODS
    
    //Adds line at the given index of the table (with values of omission!)
    public void addLineIndex(int index){
        // assert(index<getNumberLines()): "The table hasn't that many lines!";

        for(Column c : map.keySet()) {
        	ArrayList<Object> auxValues = map.get(c);
        	 if(c.getType().equals("Int")){
                 for(int i=0; i<=index; i++){
                     auxValues.add(i, 0);
                 }
             }else if(c.getType().equals("Real")){
                 for(int i=0; i<=index; i++){
                 	auxValues.add(i, 0.0);
                 }
             }else{
                 for(int i=0; i<=index; i++){
                 	auxValues.add(i, "");
                 }
             }
        	 map.put(c, auxValues);
        }
        lines++;
    }
    
    //Adds line at the end of the table (with values of omission!)
    public void addLineLast(){
        for(Column c : map.keySet()) {
        	ArrayList<Object> auxValues = map.get(c);
        	 if(c.getType().equals("Int")){
                     auxValues.add(0);
             }else if(c.getType().equals("Real")){
                 	auxValues.add(0.0);
             }else{
                 	auxValues.add("");
             }
        	 map.put(c, auxValues);
        }
        lines++;
    }

    //Adds line at the given index of the table with the given values
    public void addLineIndex(int index, Object[] values) {
    	assert(index<getNumberLines()): "The table hasn't that many lines!";
    	assert(values.length % names.size()==0): "You need to provide  n� of values % n� of columns equals 0.";

    	int posNames=0;
    	for(Object obj : values){
        	Column c = names.get(posNames);
        	ArrayList<Object> auxValues = map.get(c);
        	if(c.getType().equals("Int")){
        		try {
        			Integer aux = (Integer) obj;
                    if(index>=auxValues.size()){
                        auxValues.add(aux);
                    }else{
                        auxValues.add(index, aux);
                    }
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column ");
                    return;
                }
            }else if(c.getType().equals("Real")){
                try {
                    Double aux =((Number) obj).doubleValue();
                    if(index>=auxValues.size()){
                        auxValues.add(aux);
                    }else{
                        auxValues.add(index, aux);
                    }
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column ");
                    return;
                    }
            }else{ 
               try  {
                    String aux = (String) obj;
                    if(index>=auxValues.size()){
                        auxValues.add(aux);
                    }else{
                        auxValues.add(index, aux);
                    }
               } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column ");
                    return;
               }
            }
        	map.put(c, auxValues);
        	posNames++;
        	if(posNames==names.size()) posNames=0;
        }
    	lines++;
    }
    
    //Adds line at the end of the table with the given values
    public void addLineLast(Object[] values) {
    	assert(values.length % names.size()==0): "You need to provide  n� of values % n� of columns equals 0.";
    	
    	int posNames=0;
    	for(Object obj : values){
        	Column c = names.get(posNames);
        	ArrayList<Object> auxValues = map.get(c);
        	if(c.getType().equals("Int")){
        		try {
        			Integer aux = (Integer) obj;
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                }
            }else if(c.getType().equals("Real")){
                try {
                    Double aux =((Number) obj).doubleValue();
                    auxValues.add(aux);
                } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
                    }
            }else{
               try  {
                    String aux = (String) obj;
                    auxValues.add(aux);
               } catch (ClassCastException e) {
                    System.err.println("FAIL! The values you provide must be the same type of your Column");
                    return;
               }
            }
            //System.out.println(auxValues.size());
            //System.out.println(auxValues);
        	map.put(c, auxValues);
        	posNames++;
        	if(posNames==names.size()) posNames=0;
        }
    	lines++;
    }
    
    //REMOVE METHODS
    
    //removes a Column from the table
    //returns all values of that Column
    public List<Object> removeColumn(Column c){
        assert(names.contains(c)): "Table doesn't have that column name!";
        assert(names.size()>=1): "Table only has 1 column left! ";
        names.remove(c);
        return map.remove(c);
    }

    //removes a Column from the table at the given index
    //returns all values of that Column
    public List<Object> removeColumnIndex(int index){
        assert(names.size()>index): "Table doesn't have that column name!";
        assert(names.size()>=1): "Table only has 1 column left! ";
        return map.remove(names.remove(index));
    }

    //removes last Column from the table
    //returns all values of that Column
    public List<Object> removeColumnLast(){
        return removeColumnIndex(names.size()-1);
    }

    //removes a line from the table at the given index
    //returns all values of that Line
    public List<Object> removeLine(int index){
    	assert(index<getNumberLines()): "The table hasn't that many lines!";
    	ArrayList<Object> aux = new ArrayList<>();
    	for(Column c : map.keySet()) {
    		ArrayList<Object> auxValues = map.get(c);
    		aux.add(auxValues.remove(index));
    		map.put(c, auxValues);
    	}
    	lines--;
    	return aux;
    } 

    //removes last line from the table
    //returns all values of that Line
    public List<Object> removeLineLast(){
        return removeLine(getNumberLines()-1);
    }

    //PUT METHOD
    
    //replaces a values from the table of the given index and column
    //returns null or value that was replaced
    public Object putValue(Column c, int index, Object obj) {
    	assert(index<=getNumberLines()): "The table hasn't that many lines!";
    	assert(names.contains(c)): "Table doesn't have that column name!";
    	
    	ArrayList<Object> auxValues = map.get(c);
    	Object removed;
    	if(c.getType().equals("Int")){
    		try {
    			Integer aux = (Integer) obj;
                removed=auxValues.remove(index);
                auxValues.add(index,aux);
            } catch (ClassCastException e) {
                System.err.println("FAIL! The values you provide must be Int, the same type of your Column");
                return null;
            }
        }else if(c.getType().equals("Real")){
            try {
                // // Double aux = (Double) obj;
                removed=auxValues.remove(index);
                auxValues.add(index,obj);
            } catch (ClassCastException e) {
                System.err.println("FAIL! The values you provide must be Real, the same type of your Column");
                return null;
                }
        }else{
           try  {
                String aux = (String) obj;
                removed=auxValues.remove(index);
                auxValues.add(index,aux);
           } catch (ClassCastException e) {
                System.err.println("FAIL! The values you provide must be Text, the same type of your Column");
                return null;
           }
        }
    	return removed;
    }

    public Object putValue(int indexColumn, int indexLine, Object obj){
        assert(indexColumn < names.size()): "The table hasn't that many columns!";
        return putValue(names.get(indexColumn), indexLine, obj);
    }
    
    //EXTRACT METHODS 
    public Table extractColumns(Column c1, Column c2){
        assert(names.contains(c1) && names.contains(c2)):"The table must contains the columns";
        ArrayList<Column> aux = new ArrayList<>();
        for(int i=names.indexOf(c1); i<=names.indexOf(c2); i++){
            aux.add(names.get(i));
        }
        return extractColumns(aux.toArray(new Column[0]));
    }
    
    //Return new table with the given columns names
    public Table extractColumns(Column[] col){
        Table tmp = new Table();
        for(Column c : col){
            if(this.names.contains(c)){
                tmp.names.add(c);
                tmp.map.put(c, this.map.get(c));
            }
        }
        tmp.lines=this.lines;
        return tmp;
    }


    //Return new table with the given index line
    public Table extractLine(Integer[] index){
        Table tmp = new Table();
        tmp.names=this.names;
        tmp.lines = index.length;
        for(Column c : tmp.getNames()){
            ArrayList<Object> auxValues = new ArrayList<>();
            tmp.map.put(c, auxValues);
        }

        for(Column c : tmp.getNames()){
            ArrayList<Object> aux = tmp.map.get(c);
            for(int pos : index){
                aux.add(this.map.get(c).get(pos));
            }
            tmp.map.put(c, aux);
        }
        return tmp;
    }

    //Return new table with the lines of the interval
    public Table extractLine(int start, int finish){
        assert(start>=0 && finish<=this.lines && start<=finish): "Expected values between 0 and "+this.lines;
        Integer[] numbers = IntStream.rangeClosed(start, finish).boxed().toArray(Integer[]::new);
        return extractLine(numbers);
    }

    //Returns new table with the given index of columns and lines
    private Table extractBoth(Integer[] index, Integer[] line){
        Table tmp = new Table();
        for(int posCol : index){
            tmp.names.add(this.names.get(posCol));
            ArrayList<Object> aux = new ArrayList<>();
            for(int posLine : line){
                aux.add(this.map.get(this.names.get(posCol)).get(posLine));
            }
            tmp.map.put(this.names.get(posCol), aux);
        }
        tmp.lines=line.length;
        return tmp;
    }

    //Returns new table with the given index of columns and the lines interval
    private Table extractBoth(Integer[] index, int start, int finish){
        Integer[] numbers = IntStream.rangeClosed(start, finish).boxed().toArray(Integer[]::new);
        return extractBoth(index, numbers);
    }

    public Table extractBoth(Column[] c, Integer[] line){
        List<Integer> numbers = new ArrayList<>();
        for(Column tmp : c){
            numbers.add(this.names.indexOf(tmp));
        }
        
        return extractBoth(numbers.stream().toArray(Integer[]::new), line);
    }

    public Table extractBoth(Column[] c, int start, int finish){
        List<Integer> numbers = new ArrayList<>();
        for(Column tmp : c){
            numbers.add(this.names.indexOf(tmp));
        }
        return extractBoth(numbers.stream().toArray(Integer[]::new), start, finish);
    }

    public Table extractBoth(Column c1, Column c2, Integer[] line){
        ArrayList<Column> aux = new ArrayList<>();
        for(int i=names.indexOf(c1); i<=names.indexOf(c2); i++){
            aux.add(names.get(i));
        }
        return extractBoth(aux.toArray(new Column[0]), line);
    }

    public Table extractBoth(Column c1, Column c2, int start, int finish){
        ArrayList<Column> aux = new ArrayList<>();
        for(int i=names.indexOf(c1); i<=names.indexOf(c2); i++){
            aux.add(names.get(i));
        }
        return extractBoth(aux.toArray(new Column[0]), start, finish);
    }
    
    public Table extractContains(Object[] values){
        Set<Integer> aux = new LinkedHashSet<>();
        for(int i = 0 ; i<getNumberLines(); i++){
            for(Column c : names){
                for(Object obj : values){
                    if(obj.equals(map.get(c).get(i))){
                        aux.add(i);
                    }
                }
            }
        }
        return extractLine(aux.toArray(Integer[]::new));
    }

    //JOIN TABLES
    //Creates a new table from the given tables
    //return new table
    //NOTE: if the tables have columnn in commun, the values mantained are from the table on the array!!! BEWARE!!!! 
    public static Table joinTables(Table[] t){
        Table newTable = new Table();

        t = Arrays.stream(t).filter(Objects::nonNull).toArray(Table[]::new);

        for(Table table : t){
            if(table.getNumberLines()>newTable.lines) newTable.lines=table.getNumberLines();
            for(Column c : table.getNames()){
                if(!newTable.names.contains(c)) newTable.names.add(c);
            }
        }

        for(Table table : t){
            for(Column c : table.getMap().keySet()){
                if(!newTable.map.containsKey(c)){
                    if(table.getNumberLines()>newTable.lines) {newTable.lines=table.getNumberLines();} 
                    newTable.map.put(c,table.getMap().get(c));
                }else{
                    ArrayList<Object> tmp = newTable.map.get(c);
                    tmp.addAll(table.getMap().get(c));
                    if(tmp.size()>newTable.lines){newTable.lines=tmp.size();}
                    newTable.map.put(c, tmp);
                }    
            }
        }
        resizeTable(newTable);
        return newTable;
    }
    
    
    //GETTERS
    public List<Column> getNames(){
        return names;
    }

    public int getNumberLines(){
        return lines;
    }

    public Map<Column, ArrayList<Object>> getMap(){
        return map;
    }

    //PRINT TABLE
    public void printTable(){
    	System.out.print("\t");
        for(int i=0; i<names.size(); i++){
            System.out.print(names.get(i).toString()+"\t");
        }
        System.out.println();
        for(int i=0; i<getNumberLines(); i++){
        	System.out.print(i+"\t");
            for(int j=0; j<names.size(); j++){
                ArrayList<Object> aux = map.get(names.get(j));
                System.out.print(aux.get(i)+"\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    //private methods (don't mess with)
    private Table(){
        this.map= new HashMap<>();
        this.names=new ArrayList<>();
        this.lines=0;
    }

    private static void resizeTable(Table t){
        for(Column c : t.names){
            t.map.put(c, resizedColumn(c, t.getMap().get(c), t.lines));
        }
    }

    private static ArrayList<Object> resizedColumn(Column c, ArrayList<Object> column, int nLines){
        ArrayList<Object> aux = column;
        if(c.getType().equals("Int")){
            for(int i = aux.size(); i<nLines; i++){
                    aux.add(0);
            }
        }else if(c.getType().equals("Real")){
            for(int i = aux.size(); i<nLines; i++){
                    aux.add(0.0);
            }
        }else{
            for(int i = aux.size(); i<nLines; i++){
                    aux.add("");
            }
        }
        return aux;   
    }

    public Iterator iterator() {
        return (this).new TableIterator();
    }

    public Iterator lineIterator(int line) {
        return (this).new LineIterator(line);
    }

    public Iterator columnIterator(Column column) {
        return (this).new ColumnIterator(column);
    }

    class TableIterator implements Iterator {
        private List<Object> list = null;
        private int index = 0;

        TableIterator() {
            list = new ArrayList<>();
            for(int i = 0; i < getNumberLines(); i++) {
                for(Column c: getNames()) {
                    list.add(getMap().get(c).get(i));
                }
            }
        }

        public boolean hasNext() {
            return index < list.size();
        }

        public Object next() {
            if(!hasNext())
                index = 0;
            Object r = list.get(index);
            index++;
            return r;
        }
    }

    class LineIterator implements Iterator {
        private List<Object> list = null;
        int index = 0;

        LineIterator(int line) {
            list = new ArrayList<>();
            for(Column c: getNames()) {
                list.add(getMap().get(c).get(line));
            }
        }

        public boolean hasNext() {
            return index < list.size();
        }

        public Object next() {
            if(!hasNext())
                index = 0;
            Object r = list.get(index);
            index++;
            return r;
        }
    }

    class ColumnIterator implements Iterator {
        private List<Object> list = null;
        int index = 0;

        ColumnIterator(Column column) {
            list = getMap().get(column);
        }

        public boolean hasNext() {
            return index < list.size();
        }

        public Object next() {
            if(!hasNext())
                index = 0;
            Object r = list.get(index);
            index++;
            return r;
        }
    }
}


