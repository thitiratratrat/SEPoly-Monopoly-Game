package model;

public class TaxSpace extends Space {
    private double taxFee = 0.07;

    public TaxSpace(int number, String name, double[] positions, byte[] image) {
        super(number, name, positions, image);
        action = "pay tax";
    }

    public double getTaxFee() {
        return taxFee;
    }
}
