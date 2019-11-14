package model;

public class MoveSpace extends Space {
    private int amount;
    public MoveSpace(int number, String name, double[] positions, byte[] image) {
            super(number, name, positions, image);
            this.amount = 8;
            action = "Go";
    }

    public int getAmount(){
        return this.amount;
    }
}

