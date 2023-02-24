package bullscows;


public class Grading {

    private boolean isActive;
    int cows;
    int bulls;
    String grade;
    StringBuilder secretCode;

    boolean hasOwn;

    String userAnswer;



    public Grading(StringBuilder secretCode) {
        this.isActive = true;
        this.secretCode = secretCode;
        this.bulls = 0;
        this.cows = 0;
        this.grade = "";
        this.hasOwn = false;
        this.userAnswer = "";
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setUserAnswer(String userAns) {
        userAnswer = userAns;
    }

    public boolean isHasOwn() {
        return hasOwn;
    }

    private void setActive(boolean b) {
        isActive = b;
    }
    public void setHasOwn(boolean o) {
        hasOwn = o;
    }

    public String getGrade() {
        return grade;
    }

    public void generateGrade() {
        calcGrade(secretCode, userAnswer);
        printResult();
    }

    //require further functional decomposition
    private void printResult() {
        //print singular and plural methods
        String bullS = "bull";
        String cowS = "cow";
        if (bulls > 1) {
            bullS += "s";
        } else if (cows > 1) {
            cowS += "s";
        }

        //method to calculate the grade
        if (bulls != 0 && cows != 0) {

            System.out.printf("Grade: %d %s and %d %s.", bulls, bullS, cows, cowS, secretCode);
        } else if (cows != 0) {

            System.out.printf("Grade: %d %s.", cows, cowS, secretCode);
        } else if (bulls == secretCode.length()) {

            System.out.printf("Grade: %d %s%n", bulls, bullS);
            System.out.println("Congratulations! You guessed the secret code.");
            setActive(false);
            setHasOwn(true);
        } else if (bulls != 0) {

            System.out.printf("Grade: %d %s.", bulls, bullS, secretCode);
        } else {
            System.out.println("Grade: None.");
        }
        //for manual debugging -> The secret code is %s.
    }


    //grading method , updating the cows and bulls values
    private void calcGrade(StringBuilder secretCode, String userAnswer) {
        String[] strs = userAnswer.split("");

        String[] strs2 = secretCode.toString().split("");

        //method to calculate bulls and cows
        for (int firstArrayIndex = 0; firstArrayIndex < strs.length; firstArrayIndex++) {
            for (int secondArrayIndex = 0; secondArrayIndex < strs2.length; secondArrayIndex++) {
                if (secondArrayIndex == firstArrayIndex && strs[firstArrayIndex].equals(strs2[secondArrayIndex])) {
                    bulls++;
                    break;
                } else if (strs[firstArrayIndex].equals(strs2[secondArrayIndex])) {
                    cows++;
                    break;
                }
            }
        }

    }


}