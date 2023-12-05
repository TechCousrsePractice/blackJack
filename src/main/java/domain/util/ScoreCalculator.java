package domain.util;

import domain.card.Card;
import domain.card.Symbol;
import java.util.List;

public class ScoreCalculator {
    public static int calculateScore(List<Card> cards) {
        int finalScore = 0;
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = countAceCard(cards);

        finalScore = totalScore;
        if (aceCount > 0 && totalScore + 10 <= 21) {
            finalScore += 10;
        }
        return finalScore;
    }

    private static int countAceCard(List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> card.getSymbol() == Symbol.ACE)
                .count();
    }
}