package co.edu.uptc.models;

import java.awt.Image;
import java.awt.Rectangle;

import co.edu.uptc.pojos.ElementPojo;
import co.edu.uptc.utils.UtilsProperties;

public class PadleModel extends Rectangle {
    private ElementPojo padlePojo = new ElementPojo();
    private UtilsProperties properties;

    public PadleModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        properties = new UtilsProperties();
        padlePojo.setX(x);
        padlePojo.setY(y);
        padlePojo.setHeight(height);
        padlePojo.setWidth(width);
    }

    public PadleModel(){
        super(0, 0, 0, 0);
        properties = new UtilsProperties();
        padlePojo.setX(0);
        padlePojo.setY(0);
        padlePojo.setHeight(0);
        padlePojo.setWidth(0);
    }

    public void setPadlePojo(ElementPojo padlePojo) {
        this.padlePojo = padlePojo;
    }

    public ElementPojo getPadlePojo() {
        return padlePojo;
    }

    public void setImage(Image image) {
        padlePojo.setImage(image);
    }

    public double getX() {
        return padlePojo.getX();
    }

    public double getY() {
        return padlePojo.getY();
    }

    public double getWidth() {
        return padlePojo.getWidth();
    }

    public double getHeight() {
        return padlePojo.getHeight();
    }

    public void Up() {
        padlePojo.setY(padlePojo.getY() - 10);
        if (padlePojo.getY() <= 5) {
            padlePojo.setY(5);
        }
        y = padlePojo.getY();
    }

    public void Down() {
        padlePojo.setY(padlePojo.getY() + 10);
        if (padlePojo.getY() >= 620) {
            padlePojo.setY(620);
        }
        y = padlePojo.getY();
    }
}
