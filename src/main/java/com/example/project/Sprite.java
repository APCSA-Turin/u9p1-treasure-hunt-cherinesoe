package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}//placeholder
    public int getY(){return y;}

    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "[" + (size - 1 - getY()) + "][" + getX() + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
        if (direction.equals("w")) {
            setY(getY() + 1);
        } else if (direction.equals("a")) {
            setX(getX() - 1);
        } else if (direction.equals("s")) {
            setY(getY() - 1);
        } else {
            setX(getX() + 1);
        }
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
