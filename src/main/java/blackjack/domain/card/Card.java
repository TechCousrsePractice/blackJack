package blackjack.domain.card;

public class Card {
    private final Value value;
    private final Shape shape;

    public Card(final Value value, final Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public static Card of(final Value value, final Shape shape) {
        return new Card(value, shape);
    }

    public boolean isAce() {
        return this.getValue() == Value.ACE;
    }

    public int getValueNumber() {
        return value.getNumber();
    }

    public Value getValue() {
        return value;
    }

    public String getValueName() {
        return value.getValueName();
    }

    public Shape getShape() {
        return shape;
    }

    public String getShapeName() {
        return shape.getShapeName();
    }
}
