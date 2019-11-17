package model;
//delete text and add image, get image
public class Card {
    private String effect;
    private int effectAmount;
    private byte[] image;
    public Card(String effect, int effectAmount, byte[] image) {
        this.effect = effect;
        this.image = image;
        this.effectAmount = effectAmount;
    }

    public String getEffect() {
        return effect;
    }

    public int getEffectAmount() { return effectAmount; }

    public byte[] getImage(){ return image; }
}
