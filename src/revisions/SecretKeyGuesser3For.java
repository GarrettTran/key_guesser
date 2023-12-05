package revisions;

import base.SecretKey;

public class SecretKeyGuesser3For {
    public String start(SecretKey key) {
        String str = "MMMMMMMMMMMM";
        String prevStr = str;
        int guess = key.guess(str);

        // this has O(n) complexity (n = 12 in this case)
        for (int i = 0; i < 12; i++) {
            // this has O(4) complexity (as only 5 character, and we don't need to take a
            // full cycle back to M)
            for (int y = 0; y < 4; y++) {
                prevStr = str;
                str = next(prevStr, i);
                int currentGuess = key.guess(str);

                if (guess < currentGuess) {
                    guess = currentGuess;
                    break;
                } else if (guess > currentGuess) {
                    str = prevStr;
                    guess = currentGuess;
                    break;
                }
            }
        }
        // total of O(4n) => O(n) time complexity

        return str;
    }

    static int order(char c) {
        if (c == 'M') {
            return 0;
        } else if (c == 'O') {
            return 1;
        } else if (c == 'C') {
            return 2;
        } else if (c == 'H') {
            return 3;
        }
        return 4;
    }

    static char charOf(int order) {
        if (order == 0) {
            return 'M';
        } else if (order == 1) {
            return 'O';
        } else if (order == 2) {
            return 'C';
        } else if (order == 3) {
            return 'H';
        }
        return 'A';
    }

    // return the next value in 'MOCHA' order, that is
    // M < O < C < H < A
    public String next(String current, int index) {
        if (!(index < current.length())) {
            return current;
        }

        char[] curr = current.toCharArray();

        if (order(curr[index]) < 4) {
            // increase this one and stop
            curr[index] = charOf(order(curr[index]) + 1);
        } else {
            curr[index] = 'M';
        }

        return String.valueOf(curr);
    }
}
