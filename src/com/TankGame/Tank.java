package com.TankGame;

public class Tank {
    private int x;//x坐标
    private int y;//y坐标
    private int direct;
    private int speed=1;
    public int getDirect() {
        return direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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
    public void moveUp(){
        if (y > 0) { // Check if already at the edge
            y -= speed;
        }
        if (y < 0) { // Correct if overshot
            y = 0;
        }
    }
    public void moveRight(){
        int tankWidth = 0;
        if (getDirect() == 0 || getDirect() == 2) { // Up or Down
            tankWidth = 40;
        } else { // Left or Right
            tankWidth = 60;
        }

        if (x < 1000 - tankWidth) { // Check if already at the edge
            x += speed;
        }
        if (x > 1000 - tankWidth) { // Correct if overshot
            x = 1000 - tankWidth;
        }
    }
    public void moveDown(){
        int tankHeight = 0;
        if (getDirect() == 0 || getDirect() == 2) { // Up or Down
            tankHeight = 60;
        } else { // Left or Right
            tankHeight = 40;
        }

        if (y < 750 - tankHeight) { // Check if already at the edge
             y += speed;
        }
        if (y > 750 - tankHeight) { // Correct if overshot
            y = 750 - tankHeight;
        }
    }
    public void moveLeft(){
        if (x > 0) { // Check if already at the edge
            x -= speed;
        }
        if (x < 0) { // Correct if overshot
            x = 0;
        }
    }

    public Bullet shoot() {
        // Adjust bullet starting position based on tank's direction
        // These are estimates and might need refinement
        // Assuming bullet width/height is small, e.g., 4x4, so center offset is 2
        int bulletX = 0;
        int bulletY = 0;
        int bulletWidth = 4; // example bullet width
        int bulletHeight = 4; // example bullet height

        switch (direct) {
            case 0: // Up
                bulletX = this.x + 20 - (bulletWidth / 2);
                bulletY = this.y;
                break;
            case 1: // Right
                bulletX = this.x + 60;
                bulletY = this.y + 20 - (bulletHeight / 2);
                break;
            case 2: // Down
                bulletX = this.x + 20 - (bulletWidth / 2);
                bulletY = this.y + 60;
                break;
            case 3: // Left
                bulletX = this.x;
                bulletY = this.y + 20 - (bulletHeight / 2);
                break;
        }
        return new Bullet(bulletX, bulletY, this.direct);
    }
}
