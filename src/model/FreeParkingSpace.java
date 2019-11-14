package model;

public class FreeParkingSpace extends Space {
    public FreeParkingSpace(int number, String name, double[] positions, byte[] image) {
        super(number, name, positions, image);
        action = "free parking";
    }
}
