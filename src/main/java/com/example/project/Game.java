package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;
    private String difficulty;

    public Game(String difficulty){ //the constructor should call initialize() and play()
        // changes size and amount of enemies/treasure based on difficulty
        if (difficulty.equals("easy")) {
            this.size = 5;
            enemies = new Enemy[1];
            treasures = new Treasure[1];
        } else if (difficulty.equals("medium")) {
            this.size = 10;
            enemies = new Enemy[2];
            treasures = new Treasure[2];
        } else if (difficulty.equals("hard")) {
            this.size = 15;
            enemies = new Enemy[3];
            treasures = new Treasure[3];
        }
        this.difficulty = difficulty;
        initialize(difficulty);
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;
        Object interacting = new Dot(0, 0);

        while(playing && !player.getWin()){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();
            System.out.println("Coordinates: " + player.getCoords());
            System.out.println("Treasure Count: " + player.getTreasureCount());
            System.out.println("Player Lives: " + player.getLives());
            System.out.print("Enter a direction (w,a,s,d) or q to quit: ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                playing = false;
            } else if (player.isValid(size, input)) { // makes sure position does not exceed grid
                // only interacts when moving to a position where there is not a dot
                if (input.equals("w")) {
                    if (!(grid.getGrid()[size - 2 - player.getY()][player.getX()] instanceof Dot)) {
                        interacting = grid.getGrid()[size - 2 - player.getY()][player.getX()];
                        player.interact(size, input, treasures.length, interacting);
                    }
                } else if (input.equals("a")) {
                    if (!(grid.getGrid()[size - 1 - player.getY()][player.getX() - 1] instanceof Dot)) {
                        interacting = grid.getGrid()[size - 1 - player.getY()][player.getX() - 1];
                        player.interact(size, input, treasures.length, interacting);
                    }
                } else if (input.equals("s")) {
                    if (!(grid.getGrid()[size - player.getY()][player.getX()] instanceof Dot)) {
                        interacting = grid.getGrid()[size - player.getY()][player.getX()];
                        player.interact(size, input, treasures.length, interacting);
                    }
                } else if (input.equals("d")) {
                    if (!(grid.getGrid()[size - 1 - player.getY()][player.getX() + 1] instanceof Dot)) {
                        interacting = grid.getGrid()[size - 1 - player.getY()][player.getX() + 1];
                        player.interact(size, input, treasures.length, interacting);
                    }
                }
                if ((interacting instanceof Trophy && player.getTreasureCount() == treasures.length) || player.getLives() == 0) {
                    playing = false;
                }
                player.move(input);
                grid.placeSprite(player, input);
            }
        }

        // checks conditions for winning and losing
        if (player.getLives() > 0 && treasures.length == player.getTreasureCount()) {
            grid.win();
        } else {
            grid.gameover();
        }
    }

    public void initialize(String difficulty){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid = new Grid(size);
        player = new Player(0, 0);
        grid.placeSprite(player);
        // initializes variables based on difficulty
        if (difficulty.equals("easy")) {
            Enemy e1 = new Enemy(3, 2);
            enemies[0] = e1;
            Treasure t1 = new Treasure(2,3);
            treasures[0] = t1;
            trophy = new Trophy(4, 4);
            grid.placeSprite(trophy);
            for (Enemy enemy : enemies) {
                grid.placeSprite(enemy);
            }
            for (Treasure treasure : treasures) {
                grid.placeSprite(treasure);
            }
            player.setLives(1);
        } else if (difficulty.equals("medium")) {
            Enemy e1 = new Enemy(3, 4);
            Enemy e2 = new Enemy(8, 6);
            enemies[0] = e1;
            enemies[1] = e2;
            Treasure t1 = new Treasure(2,5);
            Treasure t2 = new Treasure(7, 3);
            treasures[0] = t1;
            treasures[1] = t2;
            trophy = new Trophy(9, 8);
            grid.placeSprite(trophy);
            for (Enemy enemy : enemies) {
                grid.placeSprite(enemy);
            }
            for (Treasure treasure : treasures) {
                grid.placeSprite(treasure);
            }
            player.setLives(2);
        } else if (difficulty.equals("hard")) {
            Enemy e1 = new Enemy(3, 4);
            Enemy e2 = new Enemy(8, 6);
            Enemy e3 = new Enemy(10,12);
            enemies[0] = e1;
            enemies[1] = e2;
            enemies[2] = e3;
            Treasure t1 = new Treasure(3,8);
            Treasure t2 = new Treasure(7, 3);
            Treasure t3 = new Treasure(14, 11);
            treasures[0] = t1;
            treasures[1] = t2;
            treasures[2] = t3;
            trophy = new Trophy(14, 13);
            grid.placeSprite(trophy);
            for (Enemy enemy : enemies) {
                grid.placeSprite(enemy);
            }
            for (Treasure treasure : treasures) {
                grid.placeSprite(treasure);
            }
            player.setLives(3);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean replay = true;
        System.out.print("What gamemode would you like to play? (easy, medium, hard): ");
        String mode = scanner.nextLine();
        Game game = new Game(mode);
        while (replay) {
            System.out.print("Would you like to play again? (y/n): ");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                System.out.print("What gamemode would you like to play? (easy, medium, hard): ");
                mode = scanner.nextLine();
                game = new Game(mode);
            } else {
                replay = false;
                System.out.println("Goodbye!");
            }
        }
    }
}