package revisions;

import base.SecretKey;

public class SecretKeyGuesser4 {
    // declare Strings for checking the total number of each character
    static final String[] STRINGS = {"MMMMMMMMMMMM", "OOOOOOOOOOOO", "CCCCCCCCCCCC", "HHHHHHHHHHHH", "AAAAAAAAAAAA"};

    //an array to store that number
    static int[] counts = new int[5];
    //keeping tracks of the total char and we can save 1 count for character A
    int totalChar = 0;

    public String start(SecretKey key) {
        String str = STRINGS[0];
        String prevStr = str;
        int guess = 0;
        int index = 0;

        //calling the guess to get the number of total number of characters
        //by passing only 12 characters each kind;
        //using while cause sometimes
        while (totalChar < 12){

            counts[0] = key.guess(STRINGS[0]);
            totalChar += counts[0];
            if (totalChar >= 12) {
                break;
            }
            counts[1] = key.guess(STRINGS[1]);
            totalChar += counts[1];
            if (totalChar >= 12) {
                break;
            }
            counts[2] = key.guess(STRINGS[2]);
            totalChar += counts[2];
            if (totalChar >= 12) {
                break;
            }
            counts[3] = key.guess(STRINGS[3]);
            totalChar += counts[3];
            if (totalChar >= 12) {
                break;
            }
            //save 1 count
            counts[4] = 12 - totalChar;
            break;
        }

        //initialize guess with the count of 12 'Y' characters
        guess = counts[0];
        while (guess != 12) {
            prevStr = str;
            str = next(prevStr, index);
            int currentGuess = key.guess(str);

            if (guess < currentGuess) {

                //if the index matched, minus 1 in the counts[] array
                char c = str.charAt(index);
                if ( c == 'M'){
                    counts[0]--;
                } else if (c == 'O'){
                    counts[1] --;
                } else if (c == 'C'){
                    counts[2]--;
                } else if (c == 'H'){
                    counts[3]--;
                } else counts[4]--;
                index++;
            } else if (guess > currentGuess) {
                index++;
                str = prevStr;
                currentGuess = guess;
            }

            guess = currentGuess;
        }

        return str;
    }

    static int order(char c) {
        if (c == 'M') return 0;
        if (c == 'O') return 1;
        if (c == 'C') return 2;
        if (c == 'H') return 3;
        return 4;
    }

    static char charOf(int order) {
        // if the value in counts[index] == 0, skip to another condition
        if (order == 0 && counts[0] != 0) {
            return 'M';

            //in case order == 0 but out of value
        } else if ((order == 1 || order == 0) && (counts[1] != 0)) {
            return 'O';

            //in case order == 0 and order == 1 but out of value
        } else if ((order == 2 || order == 1 || order == 0) && (counts[2] != 0 )) {
            return 'C';

            //the same with above case...
        } else if ((order == 3 || order == 2 || order == 1 || order == 0) && (counts[3] != 0)) {
            return 'H';
        } else {
            return 'A';
        }
    }

    // return the next value in 'MOCHA' order, that is
    // M < O < C < H < A
    public String next(String current, int index) {
        if (!(index < current.length())) {
            return current;
        }

        char[] curr = current.toCharArray();

        int order = order(curr[index]);

        // Check if the count for the character is not zero before incrementing
        if (order < 4) {
            curr[index] = charOf(order + 1);
        } else {
            curr[index] = 'M';
        }

        return String.valueOf(curr);
    }
}
