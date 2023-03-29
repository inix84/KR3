package me.shulinina.kr3.model;
public enum Color {
    BLACK("черный"),
    RED("красный"),
    YELLOW("желтый"),
    BROWN("коричневый"),
    WHITE("белый"),
    BLUE("синий"),
    ORANGE("оранжевый");
    final String translation;
    Color(String translation) {
        this.translation = translation;
    }
    public String getTranslation() {
        return translation;
    }
}