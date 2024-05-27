package co.edu.uptc.models.packages;

import co.edu.uptc.pojos.ElementPojo;

import java.io.Serializable;

public class InfoPadle implements Serializable {
    private ElementPojo padlePojo;

    public InfoPadle(ElementPojo padlePojo) {
        this.padlePojo = padlePojo;
    }

    public ElementPojo getPadlePojo() {
        return padlePojo;
    }

    public void setPadlePojo(ElementPojo padlePojo) {
        this.padlePojo = padlePojo;
    }
}
