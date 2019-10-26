package model;

public class Card {
    private String action;
    private String text;

    Card(String action, String text) {
        this.action = action;
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public String getText() {
        return text;
    }
}
