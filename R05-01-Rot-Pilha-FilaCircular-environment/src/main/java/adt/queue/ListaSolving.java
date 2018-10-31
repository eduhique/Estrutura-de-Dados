package adt.queue;

import java.util.HashMap;
import java.util.Map;

public class ListaSolving<T> extends QueueImpl<T> {

    public ListaSolving(int size) {
        super(size);
    }

    public static void main(String[] args) throws QueueOverflowException {

        Queue<Integer> cq = new ListaSolving<>(15);
        cq.enqueue(new Integer(9)); //0
        cq.enqueue(new Integer(1)); //1
        cq.enqueue(new Integer(2)); //2
        cq.enqueue(new Integer(3)); //3
        cq.enqueue(new Integer(4)); //4
        cq.enqueue(new Integer(4)); //5
        cq.enqueue(new Integer(5)); //6
        cq.enqueue(new Integer(3)); //7
        cq.enqueue(new Integer(3)); //8
        cq.enqueue(new Integer(7)); //9
        cq.enqueue(new Integer(5)); //10
        System.out.println(((ListaSolving<Integer>) cq).indexOf(new Integer(3)));
        System.out.println(((ListaSolving<Integer>) cq).indexOf(new Integer(10)));
        System.out.println(((ListaSolving<Integer>) cq).lastIndexOf(new Integer(3)));
        System.out.println(((ListaSolving<Integer>) cq).lastIndexOf(new Integer(4)));
        System.out.println(((ListaSolving<Integer>) cq).lastIndexOf(new Integer(7)));
        System.out.println(((ListaSolving<Integer>) cq).lastIndexOf(new Integer(5)));

        System.out.println(((ListaSolving<Integer>) cq).completa(3,5,10));


    }

    public int indexOf(T element) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(element)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int lastIndexOf(T element) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(element)) {
                result = i;
            }
        }
        return result;
    }

    public boolean completa(int c1, int c2, int m) {
        boolean result = true;
        int[] array = new int[m];
        int a = 1;
        for (int i = 0; i < array.length; i++) {
            int index = ((c1 * i) + (c2 * i * i)) % m;
            if (array[index] == a) {
                result = false;
                break;
            }
            array[index] = a;
        }

        return result;
    }

    public void findPlus(T[] array, int soma){
        Map<Integer, Integer> mapa = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            
        }
    }
}