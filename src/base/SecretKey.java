package base;

public class SecretKey {
    private String correctKey;
    private int counter;

    public SecretKey() {
        // for the real test, your program will not know this
        correctKey = "CHAMOMOCHAHA";
        counter = 0;
    }

    public int guess(String guessedKey) {
        counter++;
        // validation
        if (guessedKey.length() != correctKey.length()) {
            return -1;
        }
        int matched = 0;
        for (int i = 0; i < guessedKey.length(); i++) {
            char c = guessedKey.charAt(i);
            if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') {
                return -1;
            }
            if (c == correctKey.charAt(i)) {
                matched++;
            }
        }
        return matched;
    }

    public int getCounter() {
        return this.counter;
    }
}
