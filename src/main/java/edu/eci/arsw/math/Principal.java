package edu.eci.arsw.math;

import java.util.Arrays;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class Principal {
    private static final int DIGITS_TO_CALCULATE = 1000000;
    private static final int START_POSITION = 0;

    public static void main(String a[]) {
//        System.out.println(bytesToHex(PiDigits.getDigits(0, 10, 0)));
//        System.out.println(bytesToHex(PiDigits.getDigits(1, 100, 0)));
//        System.out.println(bytesToHex(PiDigits.getDigits(1, 1000000 ,0)));

        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de núcleos: " + numCores);
        System.out.println("\nInicilizando los cálculos: " + DIGITS_TO_CALCULATE + "dígitos de PI:\n");

//        //Experimento con 1 hilo
//        runExperiment (1, "Simple thread");
//
//        //Experiemento de un hilo por core
//        runExperiment(numCores, "Un thread por core");
//
//        //Experimento de dos hilos por core
//        runExperiment(numCores * 2, "Dos threads por core");
//
//        //Experimento con 200 hilos
//        runExperiment(200, "200 threads");

        //Experimento con 500 hilos
        runExperiment(500, "500 threads");
    }
    private static void runExperiment (int numThreads, String experimentName) {
        System.out.println("Experimento de ejecución: " + experimentName + " (" + numThreads + " threads)");

        //JVM
        PiDigits.getDigits(START_POSITION, 1000, numThreads);
        //Experimento en ejecución
        long startTime = System.currentTimeMillis();
        byte[] digits = PiDigits.getDigits(START_POSITION, DIGITS_TO_CALCULATE, numThreads);
        long endTime = System.currentTimeMillis();

        //Tiempo total de ejecución
        long executionTime = endTime - startTime;

        System.out.println("Tiempo de ejecución: " + executionTime + " ms");
        System.out.println("Primeros 20 dígitos: " + bytesToHex(Arrays.copyOf(digits, 10)));
        System.out.println(":)********************************************\n");
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

}
