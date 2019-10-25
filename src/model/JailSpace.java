package model;

public class JailSpace extends Space {
    JailSpace(int number, String name) {
        this.number = number;
        this.name = name;
        action = "go to jail";
    }
}
