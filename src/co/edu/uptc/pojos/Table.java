package co.edu.uptc.pojos;

import java.io.Serializable;

public class Table implements Serializable {
    private int width;
    private int height;

    public Table() {
        this.width = 800;
        this.height = 400;
    }

    public void duplicateSize(){
        this.width *= 2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
