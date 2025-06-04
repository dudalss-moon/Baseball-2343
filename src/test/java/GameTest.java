import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertThat(game).isNotNull();
    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        }
        catch (IllegalArgumentException e) {

        }
    }

    private void generateQuestion(String questionNumber) {
        game.question = questionNumber;
    }

    private static void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int ball) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(ball);
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnMatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolvedResultIfUnMatchedNumberWithTwoStrike() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("129"), false, 2, 0);
    }

    @Test
    void returnSolvedResultIfUnMatchedNumberWithTwoBall() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("251"), false, 0, 2);
    }

}