package model;

public class Card {
    private String effect;
    private String text;
    private int effectAmount;

    public Card(String effect, String text, int effectAmount) {
        this.effect = effect;
        this.text = text;
        this.effectAmount = effectAmount;
    }

    public String getEffect() {
        return effect;
    }

    public String getText() {
        return text;
    }

    public int getEffectAmount() { return effectAmount; }
}
