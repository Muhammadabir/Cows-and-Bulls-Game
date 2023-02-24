package bullscows;
import java.util.Scanner;
public class Main {
    static String a;
    static String b;
    static int intLength;
    static int charLength;

    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);

        Unique newGame = new Unique();
        while (!newGame.isActive()) {
            try {
                System.out.println("Input the length of the secret code:");
                a = in.next();
                intLength = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                System.out.println("Error: \"" + a + "\" isn't a valid number.");
                return;
            }
            try {
                System.out.println("Input the number of possible symbols in the code:");
                b = in.next();
                charLength = Integer.parseInt(b);
                newGame = new Unique(intLength, charLength);
            } catch (NumberFormatException e) {
                System.out.println("Error: \"" + b + "\" isn't a valid number.");
                return;
            }




        }

        if (!newGame.isError()) {
            Grading startGame = new Grading(newGame.getSecretCode());
            int count = 1;
            while (!startGame.isHasOwn()) {

                System.out.printf("Turn %d:%n", count);
                startGame.setUserAnswer(in.next());
                startGame.generateGrade();
                System.out.println(startGame.getGrade());
                if (!startGame.isHasOwn()) {
                    count++;
                    startGame.setBulls(0);
                    startGame.setCows(0);
                }

            }
        }



    }

}