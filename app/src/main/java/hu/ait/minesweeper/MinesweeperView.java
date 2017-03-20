package hu.ait.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class MinesweeperView extends View{

    private Bitmap bmBlank;
    private Bitmap bm0;
    private Bitmap bm1;
    private Bitmap bm2;
    private Bitmap bm3;
    private Bitmap bm4;
    private Bitmap bm5;
    private Bitmap bm6;
    private Bitmap bm7;
    private Bitmap bm8;
    private Bitmap bmFlag;
    private Bitmap bmMine;
    private Bitmap bmMineExploded;
    private Bitmap bmFalseFlag;

    private final int GRID_SIZE = MinesweeperModel.getInstance().getGridSize();

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bmBlank = BitmapFactory.decodeResource(getResources(), R.drawable.blank);
        bm0 = BitmapFactory.decodeResource(getResources(), R.drawable.zero);
        bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        bm5 = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        bm6 = BitmapFactory.decodeResource(getResources(), R.drawable.six);
        bm7 = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        bm8 = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
        bmFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
        bmMine = BitmapFactory.decodeResource(getResources(), R.drawable.mine);
        bmMineExploded = BitmapFactory.decodeResource(getResources(), R.drawable.mine_exploded);
        bmFalseFlag = BitmapFactory.decodeResource(getResources(), R.drawable.false_flag);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bmBlank = Bitmap.createScaledBitmap(bmBlank, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm0 = Bitmap.createScaledBitmap(bm0, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm1 = Bitmap.createScaledBitmap(bm1, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm2 = Bitmap.createScaledBitmap(bm2, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm3 = Bitmap.createScaledBitmap(bm3, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm4 = Bitmap.createScaledBitmap(bm4, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm5 = Bitmap.createScaledBitmap(bm5, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm6 = Bitmap.createScaledBitmap(bm6, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm7 = Bitmap.createScaledBitmap(bm7, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bm8 = Bitmap.createScaledBitmap(bm8, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bmMine = Bitmap.createScaledBitmap(bmMine, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bmFlag = Bitmap.createScaledBitmap(bmFlag, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bmMineExploded = Bitmap.createScaledBitmap(bmMineExploded, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

        bmFalseFlag = Bitmap.createScaledBitmap(bmFalseFlag, getWidth()/GRID_SIZE,
                getHeight()/GRID_SIZE, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGameBoard(canvas);


    }

    private void drawGameBoard(Canvas canvas) {

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                FieldElement temp  = MinesweeperModel.getInstance().getFieldContent(i,j);

                if (!temp.isExposed()) {
                    if (temp.isFlagged()){
                        canvas.drawBitmap(bmFlag, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else {
                        canvas.drawBitmap(bmBlank, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }
                } else {
                    //Mine (clicked)
                    if(temp.isMine() && temp.isDetonated() && !temp.isFlagged()) {
                        canvas.drawBitmap(bmMineExploded, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }
                    //Mine (unclicked)
                    else if(temp.isMine() && !temp.isDetonated() && !temp.isFlagged()) {
                        canvas.drawBitmap(bmMine, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }
                    //Flagged (and is a mine)
                    else if(temp.isMine() && temp.isFlagged()) {
                        canvas.drawBitmap(bmFlag, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }
                    //Flagged (but not mine)
                    else if (!temp.isMine() && temp.isFlagged()) {
                        canvas.drawBitmap(bmFalseFlag, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }
                    //Numbers
                    else if (temp.getNumAdjMines() == 0) {
                        canvas.drawBitmap(bm0, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if (temp.getNumAdjMines() == 1) {
                        canvas.drawBitmap(bm1, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if (temp.getNumAdjMines() == 2) {
                        canvas.drawBitmap(bm2, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if(temp.getNumAdjMines() == 3) {
                        canvas.drawBitmap(bm3, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if (temp.getNumAdjMines() == 4) {
                        canvas.drawBitmap(bm4, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if (temp.getNumAdjMines() == 5) {
                        canvas.drawBitmap(bm5, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if (temp.getNumAdjMines() == 6) {
                        canvas.drawBitmap(bm6, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if(temp.getNumAdjMines() == 7) {
                        canvas.drawBitmap(bm7, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    } else if(temp.getNumAdjMines() == 8) {
                        canvas.drawBitmap(bm8, (i * getWidth()) / GRID_SIZE, (j * getHeight()) / GRID_SIZE, null);
                    }

                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            int tX = ((int) event.getX()) / (getWidth()/GRID_SIZE);
            int tY = ((int) event.getY()) / (getHeight()/GRID_SIZE);

            FieldElement temp  = MinesweeperModel.getInstance().getFieldContent(tX, tY);

            if (!MinesweeperModel.getInstance().userHasLost()
                    && !MinesweeperModel.getInstance().userHasWon()) {
                if (MinesweeperModel.getInstance().isFlagMode()) {
                    if (!temp.isExposed()) {
                        temp.setFlagged(!temp.isFlagged());
                    }
                } else {
                    if (!temp.isExposed() && !temp.isFlagged()) {
                        MinesweeperModel.getInstance().expose(tX, tY);
                    }
                }
            }

            if (MinesweeperModel.getInstance().userHasWon()) {
                ((MainActivity)getContext()).setFlagText("You won!");
            } else if (MinesweeperModel.getInstance().userHasLost()) {
                ((MainActivity)getContext()).setFlagText("You lost :(");
            } else {
                ((MainActivity)getContext()).setFlagText("Good luck!");
            }

            invalidate();
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    public void resetGame() {
        MinesweeperModel.getInstance().resetModel();
        ((MainActivity)getContext()).setFlagText("Good luck!");
        invalidate();
    }

    public void toggleMode(){
        MinesweeperModel.getInstance().toggleMode();
        if (MinesweeperModel.getInstance().isFlagMode()){
            ((MainActivity)getContext()).setToggleText("FLAG MODE");
        } else {
            ((MainActivity)getContext()).setToggleText("REVEAL MODE");
        }
    }

}
