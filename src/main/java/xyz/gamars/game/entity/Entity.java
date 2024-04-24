package xyz.gamars.game.entity;

public class Entity {
    private int x, y;
    private int speed;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addX(int x) {
        this.x += x;
    }
    public void addY(int y) {
        this.y += y;
    }
}
