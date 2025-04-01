package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x,y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public void setLives(int newLives){numLives = newLives;}
    public boolean getWin(){return win;}

    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }

    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        // uses else if statements for each direction and moves x or y coordinate depending on input
        if (direction.equals("w")) {
            setY(getY() + 1);
        } else if (direction.equals("a")) {
            setX(getX() - 1);
        } else if (direction.equals("s")) {
            setY(getY() - 1);
        } else if (direction.equals("d")) {
            setX(getX() + 1);
        }
    }

    // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    public void interact(int size, String direction, int numTreasures, Object obj) {
        boolean trophyFound = false;
        if (direction.equals("w")) {
            if (obj instanceof Treasure) {
                if (obj instanceof Trophy && treasureCount < numTreasures) {
                    move("s");
                } else if (obj instanceof Trophy && treasureCount == numTreasures) {
                    trophyFound = true;
                } else {
                    treasureCount++;
                }
            } else if (obj instanceof Enemy) {
                numLives--;
            }
        } else if (direction.equals("a")) {
            if (obj instanceof Treasure) {
                if (obj instanceof Trophy && treasureCount < numTreasures) {
                    move("d");
                } else if (obj instanceof Trophy && treasureCount == numTreasures) {
                    trophyFound = true;
                } else {
                    treasureCount++;
                }
            } else if (obj instanceof Enemy) {
                numLives--;
            }
        } else if (direction.equals("s")) {
            if (obj instanceof Treasure) {
                if (obj instanceof Trophy && treasureCount < numTreasures) {
                    move("w");
                } else if (obj instanceof Trophy && treasureCount == numTreasures) {
                    trophyFound = true;
                } else {
                    treasureCount++;
                }
            } else if (obj instanceof Enemy) {
                numLives--;
            }
        } else if (direction.equals("d")) {
            if (obj instanceof Treasure) {
                if (obj instanceof Trophy && treasureCount < numTreasures) {
                    move("a");
                } else if (obj instanceof Trophy && treasureCount == numTreasures) {
                    trophyFound = true;
                } else {
                    treasureCount++;
                }
            } else if (obj instanceof Enemy) {
                numLives--;
            }
        }
        if ((treasureCount == numTreasures) && trophyFound && (numLives > 0)) {
            win = true;
        } else {
            win = false;
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) {
            // returns false if desired y coord is greater than max y coord
            if (getY() + 1 > size - 1) {
                return false;
            }
        } else if (direction.equals("a")) {
            // returns false if desired x coord is less than min x coord
            if (getX() - 1 < 0) {
                return false;
            }
        } else if (direction.equals("s")) {
            // returns false if desired y coord is less than min y coord
            if (getY() - 1 < 0) {
                return false;
            }
        } else if (direction.equals("d")) {
            // returns false if desired x coord is greater than max x coord
            if (getX() + 1 > size - 1) {
                System.out.println(getX() + 1);
                return false;
            }
        }
        return true;
    }
}



