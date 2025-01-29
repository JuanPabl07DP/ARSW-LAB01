package edu.eci.arsw.math;
/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for parallel PI digit calculation
 */
public class PiCalcTest {

    // Expected first 80 hexadecimal digits of PI
    private static final byte[] EXPECTED_DIGITS = new byte[]{
            0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8,
            0x8, 0x5, 0xA, 0x3, 0x0, 0x8, 0xD, 0x3,
            0x1, 0x3, 0x1, 0x9, 0x8, 0xA, 0x2, 0xE,
            0x0, 0x3, 0x7, 0x0, 0x7, 0x3, 0x4, 0x4,
            0xA, 0x4, 0x0, 0x9, 0x3, 0x8, 0x2, 0x2,
            0x2, 0x9, 0x9, 0xF, 0x3, 0x1, 0xD, 0x0,
            0x0, 0x8, 0x2, 0xE, 0xF, 0xA, 0x9, 0x8,
            0xE, 0xC, 0x4, 0xE, 0x6, 0xC, 0x8, 0x9,
            0x4, 0x5, 0x2, 0x8, 0x2, 0x1, 0xE, 0x6,
            0x3, 0x8, 0xD, 0x0, 0x1, 0x3, 0x7, 0x7
    };

    public PiCalcTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void shouldCalculateCorrectlyWithSingleThread() {
        // Probar varios rangos con 1 hilo
        for (int start = 0; start < EXPECTED_DIGITS.length; start++) {
            for (int count = 0; count < EXPECTED_DIGITS.length - start; count++) {
                byte[] digits = PiDigits.getDigits(start, count, 1);
                assertEquals(count, digits.length);

                for (int i = 0; i < digits.length; i++) {
                    assertEquals("Desajuste en la posición " + i + " para comenzar=" +
                                    start + " contar=" + count,
                            EXPECTED_DIGITS[start + i], digits[i]);
                }
            }
        }
    }

    @Test
    public void shouldCalculateCorrectlyWithTwoThreads() {
        // Prueba con 2 hilos, centrándose en los límites entre hilos
        int[] testSizes = {16, 32, 48, 64}; // Diferentes tamaños para probar la distribución de hilos

        for (int size : testSizes) {
            byte[] digits = PiDigits.getDigits(0, size, 2);
            assertEquals(size, digits.length);

            for (int i = 0; i < digits.length; i++) {
                assertEquals("Desajuste en la posición " + i + " para el tamaño=" + size,
                        EXPECTED_DIGITS[i], digits[i]);
            }
        }
    }

    @Test
    public void shouldCalculateCorrectlyWithThreeThreads() {
        // Prueba con 3 hilos - número impar para probar distribución desigual
        int[] testSizes = {20, 40, 60}; // Tamaños que no son perfectamente divisibles por 3

        for (int size : testSizes) {
            byte[] digits = PiDigits.getDigits(0, size, 3);
            assertEquals(size, digits.length);

            for (int i = 0; i < digits.length; i++) {
                assertEquals("Desajuste en la posición " + i + " para el tamaño=" + size,
                        EXPECTED_DIGITS[i], digits[i]);
            }
        }
    }

    @Test
    public void shouldHandleOffsetStartWithMultipleThreads() {
        // Cálculos de prueba que no comienzan en 0
        int start = 10;
        int count = 30;

        // Test with different numbers of threads
        for (int threads = 1; threads <= 3; threads++) {
            byte[] digits = PiDigits.getDigits(start, count, threads);
            assertEquals(count, digits.length);

            for (int i = 0; i < digits.length; i++) {
                assertEquals("Desajuste en la posición " + i + " con " + threads + " threads",
                        EXPECTED_DIGITS[start + i], digits[i]);
            }
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithNegativeStart() {
        PiDigits.getDigits(-1, 10, 1);
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithNegativeCount() {
        PiDigits.getDigits(0, -10, 1);
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithZeroThreads() {
        PiDigits.getDigits(0, 10, 0);
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithNegativeThreads() {
        PiDigits.getDigits(0, 10, -1);
    }
}