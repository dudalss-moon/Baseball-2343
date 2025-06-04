public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, 3, 0);
        } else {
            int strikeCount = getStrikeCount(guessNumber);
            int result = getBallCount(guessNumber);
            return new GuessResult(false, strikeCount, result);
        }
    }

    private int getBallCount(String guessNumber) {
        int ballCount = 0;
        for (int index = 0; index < 3; index++) {
            if (guessNumber.charAt(index) == question.charAt(index)) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                if (guessNumber.charAt(i) == question.charAt(index)) {
                    ballCount++;
                }
            }
        }
        return ballCount;
    }

    private int getStrikeCount(String guessNumber) {
        int strikeCount = 0;
        for (int index = 0; index < 3; index++) {
            if (guessNumber.charAt(index) == question.charAt(index)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    private boolean is1Strike(String guessNumber, int index) {
        return guessNumber.charAt(index) == question.charAt(index);
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        if (isIncludeCharacter(guessNumber)) {
            throw new IllegalArgumentException();
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isIncludeCharacter(String guessNumber) {
        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
