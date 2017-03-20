package hu.ait.minesweeper;


public class FieldElement {

    private boolean isMine;
    private boolean isExposed;
    private boolean isFlagged;
    private boolean isDetonated;
    private int numAdjMines;


    public void FieldElement(){
        reset();
    }

    public void reset() {
        isMine = false;
        isDetonated = false;
        isFlagged = false;
        isExposed = false;
        numAdjMines = 0;
    }

    public int getNumAdjMines() {
        return numAdjMines;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isDetonated() {
        return isDetonated;
    }

    public void setDetonated(boolean detonated) {
        if (!isMine) {
            detonated = false;
        } else {
            isDetonated = detonated;
        }
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setNumAdjMines(int numAdjMines) {
        this.numAdjMines = numAdjMines;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
