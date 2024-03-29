package revisions;

import base.SecretKey;

public class SecretKeyGuesser3 {
    public String start(SecretKey key) {
        String str = "MMMMMMMMMMMM";
        String prevStr = str;
        int guess = key.guess(str);
        int index = 0;

        while (guess != 12) {
            prevStr = str;
            str = next(prevStr, index);
            int currentGuess = key.guess(str);

            if (guess < currentGuess) {
                index++;
            } else if (guess > currentGuess) {
                index++;
                str = prevStr;
            }

            guess = currentGuess;
        }

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
