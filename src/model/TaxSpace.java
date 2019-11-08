package model;

public class TaxSpace extends Space {
    public TaxSpace(int number, String name, double[] positions){
        super(number, name, positions);
        action  = "Pay";
        //amount???
    }
}
