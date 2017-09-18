package hu.ait.minesweeper;

import java.util.Random;

public class MinesweeperModel {

    private static MinesweeperModel instance = null;

    private final int GRID_SIZE = 9;
    private final int NUM_MINES = 10;

    private boolean flagMode;
    private boolean gameLost;

    private FieldElement[][] model = new FieldElement[GRID_SIZE][GRID_SIZE];

    private MinesweeperModel() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                model[i][j] = new FieldElement();
            }
        }
        generateMinefield();
        flagMode = false;
        gameLost = false;
    }

    public static MinesweeperModel getInstance() {
        if (instance==null){
            instance = new MinesweeperModel();
        }

        return instance;
    }

    private void placeMine(){
        Random randX = new Random();
        Random randY= new Random();

        int x = randX.nextInt(GRID_SIZE);
        int y = randY.nextInt(GRID_SIZE);

        if (!model[x][y].isMine()) {
            model[x][y].setNumAdjMines(-1);
            model[x][y].setMine(true);
        } else{
            placeMine();
        }
    }

    private boolean inBounds(int x, int y){
        return (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE);
    }

    private void generateNumbers(){
        int gridValue;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (!model[i][j].isMine()) {
                    gridValue = 0;

                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (inBounds(i + k, j + l) && model[i+k][j+l].isMine()){
                                gridValue++;
                            }
                        }
                    }

                    model[i][j].setNumAdjMines(gridValue);

                }
            }
        }
    }

    private void generateMinefield(){
        for (int i = 0; i < NUM_MINES; i++) {
            placeMine();
        }

        generateNumbers();

    }

    public FieldElement getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void resetModel(){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                model[i][j].reset();
            }
        }
        generateMinefield();
        gameLost = false;
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public void toggleMode() {
        flagMode = !flagMode;
    }

    public boolean isFlagMode(){
        return flagMode;
    }

    public void expose(int x, int y) {
        model[x][y].setExposed(true);
        if (model[x][y].getNumAdjMines() == 0){
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (inBounds(x + i, y + j) && !model[x+i][y+j].isExposed()
                            && !model[x+i][y+j].isFlagged()){
                        expose(x+i, y+j);
                    }
                }
            }
        }

        if (model[x][y].isMine()){
            gameLost = true;
            model[x][y].setDetonated(true);
            detonateMines();
        }

    }

    private void detonateMines() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (model[i][j].isMine()) {
                    model[i][j].setExposed(true);
                } else if (model[i][j].isFlagged()) {
                    model[i][j].setExposed(true);
                }
            }
        }
    }

    private void revealMines() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (model[i][j].isMine()) {
                    model[i][j].setFlagged(true);
                    model[i][j].setExposed(false);
                } else {
                    model[i][j].setExposed(true);
                }
            }
        }
    }

    public boolean userHasLost() {
        return gameLost;
    }

    public boolean userHasWon() {
        int numUncovered = 0;
        int numMinesFlagged = 0;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (model[i][j].isExposed()){
                    numUncovered++;
                }
            }
        }

        if(numUncovered == (GRID_SIZE*GRID_SIZE - NUM_MINES)) {
            revealMines();
            return true;
        } else {
            return false;
        }


    }



}
