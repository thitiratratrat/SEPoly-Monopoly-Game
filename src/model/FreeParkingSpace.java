package model;

public class FreeParkingSpace extends Space {
    public FreeParkingSpace(int number, String name) {
        super(number, name);
        action = "free parking";
    }
}
