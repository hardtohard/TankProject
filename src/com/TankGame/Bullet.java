package com.TankGame;

public class Bullet {
    private int x;
    private int y;
    private int direct; // 0: up, 1: right, 2: down, 3: left
    private int speed = 5;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void update() {
        switch (direct) {
            case 0: // Up
                y -= speed;
                break;
            case 1: // Right
                x += speed;
                break;
            case 2: // Down
                y += speed;
                break;
            case 3: // Left
                x -= speed;
                break;
        }
    }
}
