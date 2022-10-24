package Aula6;

import java.util.Arrays;


public class Conjunto {
    private int vetor[];
    private int size;

    public Conjunto(){
        vetor = new int[10];
        size = 0;
    }

    public void AddSpace(){
        vetor = Arrays.copyOf(vetor, vetor.length + 10);
    }

    public void insert(int n){
        if(!contains(n)){
            if(size == vetor.length)    AddSpace();
            vetor[size] = n;
            size++;
        }
    }

    public boolean contains(int n){
        for(int i = 0; i < vetor.length; i++){
            if(vetor[i] == n)   return true;
        }
        return false;
    }   

    public void remove(int n){ 
        if(contains(n)){
            int index = 0;
            for (int value : vetor) {
                if (value != n) {
                    vetor[index] = value;
                    index++;
                }
            }
            size--;
        }
    }

    public void empty(){
        vetor = new int[10];
        size = 0;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < size; i++){
            if(i == size)   s+= vetor[i];
            s += vetor[i] + " ";
        }
        return s;
    }

    public int size(){
        return size;
    }

    public Conjunto combine(Conjunto add){
        Conjunto novo = new Conjunto();

        for(int i = 0; i < size; i++){
            novo.insert(vetor[i]);
        }

        for(int i = 0; i < add.size; i++){
            novo.insert(add.vetor[i]);   
        }
        return novo;
    }
    
    public Conjunto subtract(Conjunto dif){
        Conjunto novo = new Conjunto();

        for(int i = 0; i < size; i++){
            if(!dif.contains(vetor[i])) novo.insert(vetor[i]);
        }

        return novo;
    }
    
    public Conjunto intersect(Conjunto inter){
        Conjunto novo = new Conjunto();

        for(int i = 0; i < inter.size; i++){
            if(contains(inter.vetor[i]))    novo.insert(inter.vetor[i]);
        }
        return novo;
    }

}
