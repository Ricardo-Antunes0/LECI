package Aula9;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CollectionTester {
    public static void main(String[] args) {
        int[] DIM = { 1000, 5000, 10000, 20000, 40000, 100000 };
        Collection<Integer> col = new ArrayList<>();
        double Lista[][] = new double[6][3];
        System.out.printf("Collection%12s%9s%9s%9s%9s%9s\n", 1000, 5000, 10000, 20000, 40000, 100000);
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 1:
                    col = new LinkedList<>();
                    break;
                case 2:
                    col = new HashSet<>();
                    break;
                case 3:
                    col = new TreeSet<>();
                    break;
            }
            for (int j = 0; j < 6; j++) {
                double results[] = checkPerformance(col, DIM[j]);
                Lista[j][0] = results[0];
                Lista[j][1] = results[1];
                Lista[j][2] = results[2];
            }
            System.out.println(col.getClass().getSimpleName());
            System.out.printf("add%19.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", Lista[0][0], Lista[1][0],
                    Lista[2][0], Lista[3][0], Lista[4][0], Lista[5][0]);
            System.out.printf("search%16.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", Lista[0][1], Lista[1][1],
                    Lista[2][1], Lista[3][1], Lista[4][1], Lista[5][1]);
            System.out.printf("remove%16.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", Lista[0][2], Lista[1][2],
                    Lista[2][2], Lista[3][2], Lista[4][2], Lista[5][2]);
        }
    }

    private static double[] checkPerformance(Collection<Integer> col, int DIM) {
        double start, stop, add, search, remove; // Add
        start = System.nanoTime(); // clock snapshot before
        for (int i = 0; i < DIM; i++)
            col.add(i);
        stop = System.nanoTime(); // clock snapshot after
        add = (stop - start) / 1e6; // convert to milliseconds
        start = System.nanoTime(); // clock snapshot before
        for (int i = 0; i < DIM; i++) {
            int n = (int) (Math.random() * DIM);
            if (!col.contains(n))
                System.out.println("Not found???" + n);
        }
        stop = System.nanoTime(); // clock snapshot after
        search = (stop - start) / 1e6; // convert nanoseconds to milliseconds
        start = System.nanoTime(); // clock snapshot before
        Iterator<Integer> iterator = col.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        stop = System.nanoTime(); // clock snapshot after
        remove = (stop - start) / 1e6; // convert nanoseconds to milliseconds
        return new double[] { add, search, remove };
    }
}