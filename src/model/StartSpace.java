package model;

public class StartSpace extends Space {
    private int goMoney;

    public StartSpace(int number, String name, int goMoney, double[] positions, byte[] image) {
        super(number, name, positions, image);

        this.goMoney = goMoney;
        action = "start";
    }

    public int getGoMoney() {
        return goMoney;
    }
}
