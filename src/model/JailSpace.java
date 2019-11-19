package model;

public class JailSpace extends Space {
    public JailSpace(int number, String name, double[] positions, byte[] image) {
        super(number, name, positions, image);
        action = "stop";
    }
}

