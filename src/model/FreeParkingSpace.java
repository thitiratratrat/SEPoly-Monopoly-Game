package model;

public class FreeParkingSpace extends Space{
    FreeParkingSpace(int number, String name) {
        super(number, name);
        action = "free parking";
    }
}
