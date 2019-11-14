package model;

public class TaxSpace extends Space {
    private int taxFee;

    public TaxSpace(int number, String name, double[] positions, byte[] image, int taxFee) {
        super(number, name, positions, image);
        action = "pay tax";
        this.taxFee = taxFee;
    }

    public int getTaxFee() {
        return taxFee;
    }
}
