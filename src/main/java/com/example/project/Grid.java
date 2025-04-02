package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];
        // initializes grid with dot in every index
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(j,size - 1 - i);
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        // row and col are swapped in terms of x and y coordinates
        int row = s.getY();
        int col = s.getX();
        grid[size - 1 - row][col] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        Dot d = new Dot(s.getX(), s.getY()) ;
        // places a dot object in position player was in before being moved
        if (direction.equals("w")) {
            d = new Dot(s.getX(), s.getY() - 1);
        } else if (direction.equals("a")) {
            d = new Dot(s.getX() + 1, s.getY());
        } else if (direction.equals("s")) {
            d = new Dot(s.getX(), s.getY() + 1);
        } else if (direction.equals("d")) {
            d = new Dot(s.getX() - 1, s.getY());
        }
        placeSprite(s);
        placeSprite(d);
    }

    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // checks type of object and prints emoji to represent it
                if (grid[i][j] instanceof Dot) {
                    System.out.print("⬛");
                } else if (grid[i][j] instanceof Player) {
                    System.out.print("😭");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print("👻");
                } else if (grid[i][j] instanceof Treasure) {
                    if (grid[i][j] instanceof Trophy) {
                        System.out.print("🏆");
                    } else {
                        System.out.print("⭐");
                    }
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You LOST! 😞");
    }

    public void win(){ //use this method to display a win 
        System.out.println("You WIN! 😆");
    }


}