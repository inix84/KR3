package me.shulinina.kr3.model;
import java.util.Objects;
public class Socks {
    private final Color color;
    private final Size size;
    private final int cottonPart;
    public Socks(Color color, Size size, int cottonPart) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
    }
    public Color getColor() {
        return color;
    }
    public Size getSize() {
        return size;
    }
    public int getCottonPart() {
        return cottonPart;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && color == socks.color && size == socks.size;
    }
    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }
}