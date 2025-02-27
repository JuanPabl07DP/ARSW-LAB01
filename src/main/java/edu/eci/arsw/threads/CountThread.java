package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author  Nicolas Bernal Fuquene
 */
public class CountThread extends Thread {
    private int A;
    private int B;

    public CountThread(int A, int B){
        this.A = A;
        this.B = B;
    }

    @Override
    public void run(){
        for (int i = A; i <= B; i++) {
            System.out.println("Número a imprimir: " + i);
        }
    }

    public static void main(String[] args) {
        CountThread obj = new CountThread(0, 20);
        obj.start();
    }
}

