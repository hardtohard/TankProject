package com.TankGame;

public class EnemyTank extends Tank{
    private int steps = 0; // Counter for steps taken in current direction
    private int maxSteps = 60; // Move for roughly 1 second (60 steps * ~16-20ms per step if game loop is ~50-60fps)
                               // Or if game loop is 50ms, this is 3 seconds. Adjust as needed.

    public EnemyTank(int x, int y) {
        super(x, y);
        setDirect((int) (Math.random() * 4)); // Start with a random direction
        this.maxSteps = (int) (Math.random() * 80 + 20); // Initialize maxSteps to a random value
        // Consider setting a default speed if not already handled, though Tank.java has speed=1 by default for Tanks
        // If we want enemies to have a different default speed than player, we can set it here.
        // For now, they will use the Tank's default speed or whatever is set after creation.
    }

    public void update() {
        // Basic AI: move in current direction, then change direction
        if (steps >= maxSteps) {
            // Change direction randomly (0-3)
            setDirect((int) (Math.random() * 4));
            steps = 0; // Reset step counter
            maxSteps = (int) (Math.random() * 80 + 20); // Move for a random duration (e.g. 1 to 5 seconds if 50ms loop)
        } else {
            steps++;
        }

        // Move the tank based on its current direction
        // Tank.java's move methods already use 'this.speed'
        switch (getDirect()) {
            case 0: // Up
                moveUp();
                break;
            case 1: // Right
                moveRight();
                break;
            case 2: // Down
                moveDown();
                break;
            case 3: // Left
                moveLeft();
                break;
        }
    }
}
