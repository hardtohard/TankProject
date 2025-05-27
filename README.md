# TankGame Project

A simple 2D tank game implemented in Java using Swing.

## Description

This is a basic tank game where the player controls a cyan tank and can shoot bullets to destroy enemy tanks (yellow). Enemy tanks move around the map randomly. The game is played on a fixed-size arena.

## Features

*   Player-controlled tank (cyan) with movement (W, A, S, D) and shooting (Spacebar or J).
*   Multiple enemy tanks (yellow) with random movement patterns.
*   Bullets fired by the player can destroy enemy tanks.
*   Tanks are confined within the game arena boundaries.
*   Basic 2D graphics.

## How to Compile and Run

### Prerequisites

*   Java Development Kit (JDK) installed (e.g., version 8 or higher).
*   An IDE like IntelliJ IDEA, Eclipse, or VS Code (optional, can use command line).

### Compilation & Running (from Project Root)

1.  **Compile:**
    Open a terminal or command prompt in the root directory of the project.
    ```bash
    javac -d out src/Main.java src/com/TankGame/*.java
    ```
    This will compile the `.java` files and place the `.class` files into an `out` directory. If the `out` directory doesn't exist, create it first or use a command that does. Some versions of `javac` create it.

2.  **Run:**
    ```bash
    java -cp out Main
    ```

### Running from an IDE

1.  Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse).
2.  Locate `Main.java` (usually in the `src` folder).
3.  Right-click on `Main.java` and select "Run" or "Run Main.main()".

## Controls

*   **W:** Move Up
*   **A:** Move Left
*   **S:** Move Down
*   **D:** Move Right
*   **Spacebar / J:** Shoot

## Known Issues / Future Improvements

*   **Basic Enemy AI:** Enemies move randomly and do not shoot or actively hunt the player.
*   **No Lives/Scoring:** The game currently runs indefinitely until all enemies are destroyed or the player closes it. No scoring or life system.
*   **No Sound Effects.**
*   **No Obstacles:** The map is a plain open arena.
*   **Player tank cannot be destroyed.**
*   **Enemy tanks can overlap.** (No tank-to-tank collision yet)
