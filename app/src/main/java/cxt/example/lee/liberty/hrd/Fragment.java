package cxt.example.lee.liberty.hrd;

import java.util.Hashtable;

public class Fragment {

    private String name;
    private int length;
    private int height;
    private int xPos = 0;
    private int yPos = 0;
    private int value;
    private int mPicture;
    static final int DIRECTION_DONTMOVE = 0;
    static final int DIRECTION_UP = 1;
    static final int DIRECTION_DOWN = 2;
    static final int DIRECTION_LEFT = 3;
    static final int DIRECTION_RIGHT = 4;

    static Hashtable<Integer, Fragment> fragmentHashTable;
    private static ChessBoard chessBoard;

    Fragment(String name, int value, int length, int height, int xPos, int yPos, int mPicture) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.value = value;
        this.xPos = xPos;
        this.yPos = yPos;
        this.mPicture = mPicture;
    }

    static boolean addFragment(Fragment fragment) {
        if (fragmentHashTable == null)
            fragmentHashTable = new Hashtable<Integer, Fragment>();
        if (fragmentHashTable.containsKey((Integer) fragment.getValue()))
            return false;
        if (!chessBoard.PutFragment(fragment))
            return false;
        fragmentHashTable.put(fragment.getValue(), fragment);
        return true;
    }

    static void setChessBoard(ChessBoard chessBoard) {
        Fragment.chessBoard = chessBoard;
    }

    Fragment move(int direction) {
        if (chessBoard.fragmentCanBeMoved(this, direction))
            switch (direction) {
                case Fragment.DIRECTION_UP: {
                    this.setyPos(this.getyPos() - 1);
                    break;
                }
                case Fragment.DIRECTION_DOWN: {
                    this.setyPos(this.getyPos() + 1);
                    break;
                }
                case Fragment.DIRECTION_LEFT: {
                    this.setxPos(this.getxPos() - 1);
                    break;
                }
                case Fragment.DIRECTION_RIGHT: {
                    this.setxPos(this.getxPos() + 1);
                    break;
                }
            }
        chessBoard.moveFragment(this);
        return this;
    }

    public String getName() {
        return name;
    }

    int getHeight() {
        return height;
    }

    int getLength() {
        return length;
    }

    private void setxPos(int xPos) {
        this.xPos = xPos;
    }

    private void setyPos(int yPos) {
        this.yPos = yPos;
    }

    int getxPos() {
        return xPos;
    }

    int getyPos() {
        return yPos;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    int getPicture() {
        return this.mPicture;
    }
}
