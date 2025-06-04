public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);

        if (guessNumber.equals(question)) {
            return getCorrectResponse();
        }

        return getWrongResponse(guessNumber);
    }

    private GuessResult getCorrectResponse() {
        return new GuessResult(true, 3, 0);
    }

    private GuessResult getWrongResponse(String guessNumber) {
        int strikeCount = getStrikeCount(guessNumber);
        int ballCount = getBallCount(guessNumber);
        return new GuessResult(false, strikeCount, ballCount);
    }

    private int getStrikeCount(String guessNumber) {
        int result = 0;
        for (int index = 0 ; index < 3; index++) {
            if (guessNumber.charAt(index) != question.charAt(index)) continue;
            result++;
        }
        return result;
    }

    private int getBallCount(String guessNumber) {
        int result = 0;
        for (int guessIndex = 0 ; guessIndex < 3; guessIndex++) {
            for (int answerIndex = 0; answerIndex < 3; answerIndex++) {
                if (guessIndex == answerIndex) continue;
                if (guessNumber.charAt(answerIndex) != question.charAt(guessIndex)) continue;
                result++;
            }
        }
        return result;
    }

    private static void assertIllegalArgument(String guessNumber) {
        if (isEmptyAnswer(guessNumber)) throw new IllegalArgumentException();
        if (!isValidLength(guessNumber)) throw new IllegalArgumentException();
        if (isIncludeCharector(guessNumber)) throw new IllegalArgumentException();
        if (isDuplicatedNumber(guessNumber)) throw new IllegalArgumentException();
    }

    private static boolean isIncludeCharector(String guessNumber) {
        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') return true;
        }
        return false;
    }

    private static boolean isValidLength(String guessNumber) {
        return guessNumber.length() == 3;
    }

    private static boolean isEmptyAnswer(String guessNumber) {
        return guessNumber == null;
    }

    private static boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}