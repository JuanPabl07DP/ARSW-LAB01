package edu.eci.arsw.threads;

import edu.eci.arsw.math.PiDigits;

/**
 *
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class PiDigitsThread extends Thread {
    private final int start;
    private final int count;
    private byte[] digits;

    public PiDigitsThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void run() {
        this.digits = PiDigits.getDigits(start, count);
    }

    public int getCount() {
        return count;
    }

    public byte[] getDigits() {
        return digits;
    }
}
