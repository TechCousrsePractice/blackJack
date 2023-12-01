package domain.user;

public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return this.name;
    }

    public double getBettingMoney() {
        return this.bettingMoney;
    }
}
