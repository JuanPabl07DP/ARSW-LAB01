package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author  Nicolas Bernal Fuquene
 */
public class Main {

    public static void main(String a[]) throws InterruptedException {
        //System.out.println(bytesToHex(PiDigits.getDigits(0, 10)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 100)));
        //System.out.println(bytesToHex(PiDigits.getDigits(1, 1000000)));

        PiDigits pi = new PiDigits();

        //Experimento con 1 hilo
        runExperiment(pi, 1, 1000000, 1);

        //Experimento con tantos hilos como núcleos de procesamiento
        int cores = Runtime.getRuntime().availableProcessors();
        runExperiment(pi, 1, 1000000, cores);

        //Experimento con tantos hilos como el doble de nucleos de procesamiento
        runExperiment(pi, 1, 1000000, cores*2);

        //Experimento con 200 hilos
        runExperiment(pi, 1, 1000000, 200);

        //Experimento con 500 hilos
        runExperiment(pi, 1, 1000000, 500);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<hexChars.length;i=i+2){
            //sb.append(hexChars[i]);
            sb.append(hexChars[i+1]);
        }
        return sb.toString();
    }

    private static void runExperiment(PiDigits pi, int start, int count, int threads) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        String x =bytesToHex(pi.getDigits(start, count, threads));
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        //Thread.sleep(20000);

        System.out.println("The experiment used " + threads + " threads");
        System.out.println("Total time taken: "+totalTime+" ms");
        System.out.println(x);
        System.out.println("---------------------------------------");
    }

}
