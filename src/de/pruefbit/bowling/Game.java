package de.pruefbit.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame += 1) {
            if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex += 1;
            } else {
                score += sumOfFrameRolls(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private int strikeBonus(int index) {
        return rolls.get(index + 1) + rolls.get(index + 2);
    }

    private int spareBonus(int index) {
        return rolls.get(index + 2);
    }

    private int sumOfFrameRolls(int index) {
        return rolls.get(index) + rolls.get(index + 1);
    }

    private boolean isStrike(int index) {
        return rolls.get(index) == 10;
    }

    private boolean isSpare(int index) {
        return (sumOfFrameRolls(index)) == 10;
    }
}
