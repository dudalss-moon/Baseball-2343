public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, 3, 0);
        } else if (is2Strike1Ball(guessNumber)) {
            return new GuessResult(false, 2, 0);
        } else if (is1Strike2Ball(guessNumber)) {
            return new GuessResult(false, 1, 2);
        } else {
            return new GuessResult(false, 0, 0);
        }
    }

    private boolean is1Strike2Ball(String guessNumber) {
        return (is1Strike(guessNumber, 0) && guessNumber.charAt(1) == question.charAt(2) && guessNumber.charAt(2) == question.charAt(1))
                || (is1Strike(guessNumber, 1) && guessNumber.charAt(0) == question.charAt(2) && guessNumber.charAt(2) == question.charAt(0))
                || (is1Strike(guessNumber, 2) && guessNumber.charAt(0) == question.charAt(1) && guessNumber.charAt(1) == question.charAt(0));
    }

    private boolean is2Strike1Ball(String guessNumber) {
        return (is1Strike(guessNumber, 0)
                && is1Strike(guessNumber, 1))
                || (is1Strike(guessNumber, 0)
                && is1Strike(guessNumber, 2)
                || (is1Strike(guessNumber, 1)
                && is1Strike(guessNumber, 2)));
    }

    private boolean is1Strike(String guessNumber, int ch) {
        return guessNumber.charAt(ch) == question.charAt(ch);
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
