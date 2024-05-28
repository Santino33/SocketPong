package co.edu.uptc.models.packages;

import co.edu.uptc.pojos.Table;
import co.edu.uptc.pojos.ElementPojo;

import java.io.Serializable;

public class InfoGame implements Serializable {
    private ElementPojo ballPojo;
    private ElementPojo padle1Pojo;
    private ElementPojo padle2Pojo;
    private Table tableSize;
    private boolean presentBall;
    private boolean presentPadle1;
    private boolean presentPadle2;

    public InfoGame(ElementPojo ballPojo, ElementPojo padle1Pojo, ElementPojo padle2Pojo, Table table) {
        this.ballPojo = ballPojo;
        this.padle1Pojo = padle1Pojo;
        this.padle2Pojo = padle2Pojo;
        this.tableSize = table;
        this.presentBall = true;
        this.presentPadle1 = false;
        this.presentPadle2 = false;
    }

    public Table getTableSize() {
        return tableSize;
    }

    public void setTableSize(Table tableSize) {
        this.tableSize = tableSize;
    }

    public ElementPojo getBallPojo() {
        return ballPojo;
    }

    public void setBallPojo(ElementPojo ballPojo) {
        this.ballPojo = ballPojo;
    }

    public ElementPojo getPadle1Pojo() {
        return padle1Pojo;
    }

    public void setPadle1Pojo(ElementPojo padle1Pojo) {
        this.padle1Pojo = padle1Pojo;
    }

    public ElementPojo getPadle2Pojo() {
        return padle2Pojo;
    }

    public void setPadle2Pojo(ElementPojo padle2Pojo) {
        this.padle2Pojo = padle2Pojo;
    }

    public boolean isPresentBall() {
        return presentBall;
    }

    public void setPresentBall(boolean presentBall) {
        this.presentBall = presentBall;
    }

    public boolean isPresentPadle1() {
        return presentPadle1;
    }

    public void setPresentPadle1(boolean presentPadle1) {
        this.presentPadle1 = presentPadle1;
    }

    public boolean isPresentPadle2() {
        return presentPadle2;
    }

    public void setPresentPadle2(boolean presentPadle2) {
        this.presentPadle2 = presentPadle2;
    }
}
