package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {

    @Override
    public char convert(int color) {
        if (color <= 28) {
            return '▇';
        } else if (color <= 56) {
            return '●';
        } else if (color <= 84) {
            return '◉';
        } else if (color <= 112) {
            return '◍';
        } else if (color <= 140) {
            return '◎';
        } else if (color <= 168) {
            return '○';
        } else if (color <= 196) {
            return '☉';
        } else if (color <= 224) {
            return '◌';
        } else return '-';
    }
}
