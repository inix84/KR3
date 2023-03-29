package me.shulinina.kr3.model;
public class Transaction {
    private Color color;
    private Size size;
    private int cottonPart;
    private int quantity;
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public int getCottonPart() {
        return cottonPart;
    }
    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}