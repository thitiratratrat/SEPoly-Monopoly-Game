package model;

public class FreeParkingSpace extends Space {
    public FreeParkingSpace(int number, String name, double[] positions) {
        super(number, name, positions);
        action = "free parking";
    }
}
