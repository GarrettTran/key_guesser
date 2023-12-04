import base.SecretKey;
import revisions.SecretKeyGuesser;
import revisions.SecretKeyGuesser1;
import revisions.SecretKeyGuesser2;
import revisions.SecretKeyGuesser3;

/**
 * Main
 */
public class Main {
    static void baseline() {
        SecretKey key = new SecretKey();

        SecretKeyGuesser guesser = new SecretKeyGuesser();

        long startTime = System.nanoTime();
        String result = guesser.start(key);
        long endTime = System.nanoTime();

        float duration = (float) (endTime - startTime) / 1000;

        System.out.printf("[B] Result: %s, Count: %d, Duration: %.2fμs\n", result, key.getCounter(), duration);
    }

    static void revisionOne() {
        SecretKey key = new SecretKey();

        SecretKeyGuesser1 guesser = new SecretKeyGuesser1();

        long startTime = System.nanoTime();
        String result = guesser.start(key);
        long endTime = System.nanoTime();

        float duration = (float) (endTime - startTime) / 1000;

        System.out.printf("[1] Result: %s, Count: %d, Duration: %.2fμs\n", result, key.getCounter(), duration);
    }

    static void revisionTwo() {
        SecretKey key = new SecretKey();

        SecretKeyGuesser2 guesser = new SecretKeyGuesser2();

        long startTime = System.nanoTime();
        String result = guesser.start(key);
        long endTime = System.nanoTime();

        float duration = (float) (endTime - startTime) / 1000;

        System.out.printf("[2] Result: %s, Count: %d, Duration: %.2fμs\n", result, key.getCounter(), duration);
    }

    static void revisionThree() {
        SecretKey key = new SecretKey();

        SecretKeyGuesser3 guesser = new SecretKeyGuesser3();

        long startTime = System.nanoTime();
        String result = guesser.start(key);
        long endTime = System.nanoTime();

        float duration = (float) (endTime - startTime) / 1000;

        System.out.printf("[3] Result: %s, Count: %d, Duration: %.2fμs\n", result, key.getCounter(), duration);
    }

    public static void main(String[] args) {
        baseline();
        revisionOne();
        revisionTwo();
        revisionThree();
    }
}
