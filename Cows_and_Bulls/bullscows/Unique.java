package bullscows;


import java.util.Random;

public class Unique {

    private boolean isError;

    private boolean isActive;

    private StringBuilder secretCode;


    static Random rand = new Random();


    public Unique() {
        this.isActive = false;
        this.isError = false;
        this.secretCode = new StringBuilder("0");
    }
    public Unique(int inputA, int inputB) {
        if (inputA > 0 && inputB >= inputA && inputB < 37) {
            if (inputB > 10) {
                this.secretCode = numLet(checkUnique(uniqueNum(), inputA), inputB);
            } else {
                this.secretCode = checkUnique(uniqueNum(), inputA);
            }
            this.isActive = true;
            this.isError = false;

            // The secret is prepared: ****
            System.out.print("The secret is prepared: " + starS(inputA) + " ");
            // (0-9, a-f)
            System.out.println("(" + oToNine(inputB) + ", " + aTZ(inputB) + ")");
            System.out.println("Okay, let's start a game!");
        } else if (inputB > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            isActive = true;
            isError = true;
        }  else {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols..%n", inputA, inputB);
            isActive = true;
            isError = true;
        }

    }


    public boolean isError() {
        return isError;
    }

    //method for random num + letters
    private StringBuilder numLet(StringBuilder nums, int inputB) {
        //StringBuilder numAndLetter = new StringBuilder();
        char[] charsInEnglish = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int x = 0;
        while (x < nums.length() / 2) {
            //at every step, select a random letter from the char array and place it in a random index of the already
            // set StringBuilder, in each step of the while loop
            nums.setCharAt(rand.nextInt(nums.length()), charsInEnglish[rand.nextInt(inputB - 11)]);
            // running two for loops to look for similar/repetition of characters of letters only. We check for the
            // characters when the i and j indexes are not same, (we look from 0 for both i and also j as we want to
            // check the beginning also the later entries esp the later entries if they have same value af the previous
            // entries) Using j = 0 and j != i lets us check the whole string-builder thoroughly at every iteration.
            // if similar found, we recall that index and change that old index with a new character. Generally the
            // previous characters are changed and at risk of being duplicated by future index characters.
            for (int i = 0; i < nums.length(); i++) {
                for (int j = 0; j < nums.length(); j++) {
                    if (j != i && nums.charAt(i) == nums.charAt(j)) {
                        //nums.replace(j, j + 1, String.valueOf(charsInEnglish[rand.nextInt(inputB - 11)]));
                        nums.setCharAt(j, charsInEnglish[rand.nextInt(inputB - 11)]);

                    }
                }
            }
            x++;
        }

        //return numAndLetter;
        System.out.println(nums);
        return nums;
    }


    //method to generate -> The secret is prepared: **** (0-9, a-f).
    // 0-9
    private static StringBuilder oToNine(int inputB) {
        int[] ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        StringBuilder zeroToNine = new StringBuilder();
        if (inputB > 0 && inputB - 10 < 1) {
            zeroToNine.append("0-" + ints[inputB - 1]);
        } else if (inputB > 0 && inputB - 10 > 0) {
            zeroToNine.append("0-10");
        }
        /*
        else {
            zeroToNine.append("");
        }
         */
        return zeroToNine;
    }
    //a-f
    private static StringBuilder aTZ(int inputB) {
        char[] charsInEnglish = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder aToz = new StringBuilder();
        if (inputB > 10) {
            aToz.append("a-" + charsInEnglish[inputB - 11]);
        }
        /*
        else {
            aToz.append("");
        }
         */
        return aToz;
    }

    // perhaps using loops where the number of * is equal to the length of the code
    private StringBuilder starS(int codeLength) {
        StringBuilder starCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            starCode.append("*");
        }
        return starCode;
    }


    public boolean isActive() {
        return isActive;
    }


    public StringBuilder getSecretCode() {
        return secretCode;
    }

    private static int uniquelength(StringBuilder revUnique) {
        return revUnique.length();
    }

    private static StringBuilder reversedSet(StringBuilder unique) {
        /*
        int uniqLastIndex = unique.length() - 1;
        if (unique.charAt(uniqLastIndex) == '0') {
            unique.deleteCharAt(uniqLastIndex);
        }
        */

        return  unique.reverse();
    }

    private static StringBuilder checkUnique(StringBuilder unique, int input) {
        StringBuilder finalUniq = new StringBuilder();
        if (uniquelength(unique) > input) {
            for (int i = 0; i < input; i++) {
                finalUniq.append(unique.charAt(i));
            }

        } else if (input > uniquelength(unique)) {

            finalUniq = checkUnique(uniqueNum(unique), input);

        } else {
            finalUniq.append(unique);
        }
        return finalUniq;
    }

    //actual random and unique number generator with args
    private static StringBuilder uniqueNum(StringBuilder str) {
        StringBuilder sb1 = new StringBuilder();
        str.append(rand.nextLong());

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    str.setCharAt(j, ' ');
                }
            }

        }

        for (int i = 0; i < str.length(); i++ ) {
            if (str.charAt(i) != ' ') {
                sb1.append(str.charAt(i));
            }
        }

        return reversedSet(sb1);
    }

    //actual random and unique number generator no args
    private static StringBuilder uniqueNum() {

        StringBuilder sb2 = new StringBuilder();
        //StringBuilder sb =  new StringBuilder(String.valueOf(System.nanoTime()));
        StringBuilder sb =  new StringBuilder(String.valueOf(rand.nextLong()));
        for (int i = 0; i < sb.length(); i++) {
            for (int j = i + 1; j < sb.length(); j++) {
                if (sb.charAt(i) == sb.charAt(j)) {
                    sb.setCharAt(j, ' ');
                }
            }

        }

        for (int i = 0; i < sb.length(); i++ ) {
            if (sb.charAt(i) != ' ') {
                sb2.append(sb.charAt(i));
            }
        }
        return reversedSet(sb2);
    }


}