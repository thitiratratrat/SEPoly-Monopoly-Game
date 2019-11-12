package model;

public class StartSpace extends Space {
    private int goMoney;

    public StartSpace(int number, String name, int goMoney) {
        super(number, name);
        this.goMoney = goMoney;
        action = "start";
    }

    public int getGoMoney() {
        return goMoney;
    }
}
