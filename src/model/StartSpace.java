package model;

public class StartSpace extends Space {
    private double goMoney;

    StartSpace(int number, String name, double goMoney) {
        super(number, name);
        this.goMoney = goMoney;
        action = "start";
    }

    double getGoMoney() {
        return goMoney;
    }
}
