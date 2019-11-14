package model;

public class StartSpace extends Space {
    private int goMoney;

    public StartSpace(int number, String name, int goMoney, double[] positions) {
        super(number, name, positions);

        this.goMoney = goMoney;
        action = "start";
    }

    public int getGoMoney() {
        return goMoney;
    }
}
