package co.edu.uptc.models;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import co.edu.uptc.pojos.ElementPojo;
import co.edu.uptc.utils.Utils;
import co.edu.uptc.views.DashBoard.DirectionEnum;

public class BallModel extends Rectangle {
    private int speed;
    private int xVelocity;
    private int yVelocity;
    private int intialSpeed = 2;
    private DirectionEnum direction = DirectionEnum.LEFT;
    private ElementPojo ballPojo = new ElementPojo();
    private boolean running = false;

    public BallModel(int x, int y, int height, int width) {
        super(x, y, height, width);
        Random random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * intialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * intialSpeed);

        ballPojo.setX(x);
        ballPojo.setY(y);
        ballPojo.setHeight(height);
        ballPojo.setWidth(width);
        speed = 10;
    }

    public BallModel(){
        super(0, 0, 0, 0);
        ballPojo.setX(0);
        ballPojo.setY(0);
        ballPojo.setHeight(0);
        ballPojo.setWidth(0);
        speed = 0;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void stopElement() {
        this.running = false;
    }

    public ElementPojo getballPojo() {
        return ballPojo;
    }

    public void setImage(Image image) {
        ballPojo.setImage(image);
    }

    public double getX() {
        return ballPojo.getX();
    }

    public double getY() {
        return ballPojo.getY();

    }

    public double getWidth() {
        return ballPojo.getWidth();
    }

    public double getHeight() {
        return ballPojo.getHeight();
    }

    public void startElement() {
        this.running = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Utils.sleep(speed);
                while (running) {
                    try {
                        Thread.sleep(speed);
                        move();
                    } catch (InterruptedException e) {

                    }
                }
            }
        });
        thread.start();
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
        ballPojo.setX(x);
        ballPojo.setY(y);

        if (ballPojo.getY() <= 0) {
            setYDirection(-yVelocity);
        }
        if (ballPojo.getY() >= 740) {
            setYDirection(-yVelocity);
        }
        /*
         * if (direction == DirectionEnum.LEFT) {
         * left();
         * }
         * if (direction == DirectionEnum.RIGHT) {
         * right();
         * }
         * if (direction == DirectionEnum.UP) {
         * up();
         * }
         * if (direction == DirectionEnum.DOWN) {
         * down();
         * }
         */
    }

    public ElementPojo getBallPojo() {
        return ballPojo;
    }

    public void setBallPojo(ElementPojo ballPojo) {
        this.ballPojo = ballPojo;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public boolean getRunning() {
        return running;
    }

    public void left() {
        ballPojo.setX(ballPojo.getX() - 10);
        if (ballPojo.getX() <= 1) {
            direction = DirectionEnum.RIGHT;
        }

    }

    public void right() {
        ballPojo.setX(ballPojo.getX() + 10);
        if (ballPojo.getX() >= 1500) {
            direction = DirectionEnum.LEFT;
        }
    }

    public void up() {
        ballPojo.setY(ballPojo.getY() - 10);
        if (ballPojo.getY() <= 1) {
            direction = DirectionEnum.DOWN;
        }
    }

    public void down() {
        ballPojo.setY(ballPojo.getY() + 10);
        if (ballPojo.getY() >= 720) {
            direction = DirectionEnum.UP;
        }
    }
}
