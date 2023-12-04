package revisions;

import base.SecretKey;

public class SecretKeyGuesser {
    public String start(SecretKey key) {
        // brute force key guessing
        String str = "MMMMMMMMMMMM";
        while (key.guess(str) != 12) {
            str = next(str);
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
    public String next(String current) {
        char[] curr = current.toCharArray();
        for (int i = curr.length - 1; i >= 0; i--) {
            if (order(curr[i]) < 4) {
                // increase this one and stop
                curr[i] = charOf(order(curr[i]) + 1);
                break;
            }
            curr[i] = 'M';
        }
        return String.valueOf(curr);
    }
}
