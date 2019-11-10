import de.pruefbit.bowling.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    private Game g;

    @Test
    void gutter_game() {
        int rolls = 20;
        int pins = 0;
        rollMany(rolls, pins);
        assertEquals(0, g.score());
    }

    @Test
    void all_ones() {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }

    @Test
    void ten_pairs_of_nine_plus_null() {
        rollManyFrames(10, 9, 0);
        assertEquals(90, g.score());
    }

    @Test
    void one_spare() {
        rollSpare();
        g.roll(3);
        rollMany(17, 0);
        assertEquals(16, g.score());
    }

    @Test
    void all_spares() {
        rollMany(21, 5);
        assertEquals(150, g.score());
    }

    @Test
    void one_strike() {
        rollStrike(); // strike
        g.roll(3);
        g.roll(4);
        rollMany(16, 0);
        assertEquals(24, g.score());
    }

    @Test
    void perfect_game() {
        rollMany(12, 10);
        assertEquals(300, g.score());
    }

    @BeforeEach
    void setUp() {
        g = new Game();
    }

    private void rollMany(int rolls, int pins) {
        for (int n = 0; n < rolls; n += 1) {
            g.roll(pins);
        }
    }

    private void rollManyFrames(int frames, int firstRoll, int secondRoll) {
        for (int n = 0; n < frames; n += 1) {
            g.roll(firstRoll);
            g.roll(secondRoll);
        }
    }

    private void rollSpare() {
        g.roll(5);
        g.roll(5); // spare
    }

    private void rollStrike() {
        g.roll(10);
    }
}