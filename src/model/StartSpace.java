package model;
//add public
public class StartSpace extends Space{
    private double goMoney;

    public StartSpace(int number, String name, double goMoney, double[] positions) {
        super(number, name, positions);
        this.goMoney = goMoney;
        action = "start";
    }

    double getGoMoney() {
        return goMoney;
    }
}
