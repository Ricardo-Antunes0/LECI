import p2utils.*;

public class SupermarketOrdering {

    private Queue <Order> fila;
    private HashTable <Integer> encomendas;
    private int size;

    public  SupermarketOrdering(){
        fila = new Queue <Order>(); 
        encomendas = new HashTable <>(0);
        size = encomendas.size();
    }
    
    public void enterOrder(Order e){

        String name = e.prodName;
        fila.in(e);

        size++;
        if(!encomendas.contains(name)){
            encomendas.set(name, e.quantity);
        }
        else{
            encomendas.set(name, encomendas.get(name) + e.quantity);
        }
    }

    public Order serveOrder(){

        if(fila.size() == 0) return new Order("","",0);
       
        Order OldestOrder = fila.peek();
        fila.out();

        String name = OldestOrder.prodName;
        if(encomendas.contains(name)){
            encomendas.set(name, encomendas.get(name) - OldestOrder.quantity);
        }
       
        return OldestOrder; 
    }

//Em vez de remover a quantidade dos produtos, devo aumentar. Dito pelo sor

    public int query(String prodName){
        return encomendas.get(prodName);
    }

    public void displayOrders() {
    
		String [] keys = encomendas.keys();

		for(int i = 0; i < size; i++) {
			String product = keys[i];
			System.out.printf("%10s -> %10d \n", product, encomendas.get(product));
        }
    }

    public int numOrders(){
        return size;
    }


}
