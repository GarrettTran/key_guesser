package revisions;

import base.SecretKey;

public class SecretKeyGuesser2 {
    public String start(SecretKey key) {
        String str = "MMMMMMMMMMMM";
        String prevStr = str;
        int guess = key.guess(str);
        int index = 0;

        while (guess != 12) {
            int prevGuess = guess;

            guess = key.guess(str);

            if (guess > prevGuess) {
                index++;
            } else if (guess < prevGuess) {
                index++;
                str = prevStr;
            }

            prevStr = str;
            str = next(str, index);
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
