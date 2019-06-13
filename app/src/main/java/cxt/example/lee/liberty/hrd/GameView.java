package cxt.example.lee.liberty.hrd;

import android.annotation.SuppressLint;
import android.view.View;

import java.util.Objects;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;


public class GameView extends View {

    private ChessBoard chessBoard;

    @SuppressLint("ClickableViewAccessibility")
    public GameView(Context context) {
        super(context);

        chessBoard = new ChessBoard(4, 5);
        Chess.setChessBoard(chessBoard);
        Chess.addFragment(new Chess("Cao Cao", Color.WHITE, 2, 2, 1, 0, R.drawable.role_caocao));
        Chess.addFragment(new Chess("Zhang Fei", Color.BLUE, 1, 2, 0, 0, R.drawable.role_zhangfei));
        Chess.addFragment(new Chess("Huang Zhong", Color.YELLOW, 1, 2, 3, 0, R.drawable.role_huangzhong));
        Chess.addFragment(new Chess("Ma Chao", Color.RED, 1, 2, 0, 2, R.drawable.role_machao));
        Chess.addFragment(new Chess("Zhao Yun", Color.MAGENTA, 1, 2, 3, 2, R.drawable.role_zhaoyun));
        Chess.addFragment(new Chess("Guan Yu", Color.GREEN, 2, 1, 1, 2, R.drawable.role_guanyu));
        Chess.addFragment(new Chess("Soldier1", 1, 1, 1, 0, 4, R.drawable.role_soldier1));
        Chess.addFragment(new Chess("Soldier2", 2, 1, 1, 3, 4, R.drawable.role_soldier2));
        Chess.addFragment(new Chess("Soldier3", 3, 1, 1, 1, 3, R.drawable.role_soldier3));
        Chess.addFragment(new Chess("Soldier4", 4, 1, 1, 2, 3, R.drawable.role_soldier4));

        this.setOnTouchListener(new OnTouchListener() {
            private int xPos;
            private int yPos;
            private int currentSelectedValue = 0;
            private int prevSelectedValue = 0;

            public boolean onTouch(View view, MotionEvent motion) {

                xPos = (int) motion.getX();
                yPos = (int) motion.getY();

                int x = xPos / 240;
                int y = yPos / 240;

                if (y == 5)
                    return false;

                else {
                    prevSelectedValue = currentSelectedValue;
                    currentSelectedValue = chessBoard.getBoardValue(x, y);
                    if ((currentSelectedValue != prevSelectedValue) && currentSelectedValue == 0) {
                        int direction = decideDirection(x, y, (Chess) Objects.requireNonNull(Chess.fragmentHashTable.get(prevSelectedValue)));
                        if (direction != Chess.DIRECTION_DONTMOVE) {
                            Chess.fragmentHashTable.put(prevSelectedValue, ((Chess) Chess.fragmentHashTable.get(prevSelectedValue)).move(direction));
                            view.invalidate();
                        }
                    }
                    return false;
                }
            }

            private int decideDirection(int xPos, int yPos, Chess fragment) {
                if ((xPos == fragment.getxPos() - 1) && (yPos >= fragment.getyPos() && yPos <= fragment.getyPos() + fragment.getHeight() - 1))
                    return Chess.DIRECTION_LEFT;
                if ((xPos == fragment.getxPos() + fragment.getLength()) && (yPos >= fragment.getyPos() && yPos <= fragment.getyPos() + fragment.getHeight() - 1))
                    return Chess.DIRECTION_RIGHT;
                if ((xPos >= fragment.getxPos() && xPos <= fragment.getxPos() + fragment.getLength() - 1) && (yPos == fragment.getyPos() - 1))
                    return Chess.DIRECTION_UP;
                if ((xPos >= fragment.getxPos() && xPos <= fragment.getxPos() + fragment.getLength() - 1) && (yPos == fragment.getyPos() + fragment.getHeight()))
                    return Chess.DIRECTION_DOWN;
                return Chess.DIRECTION_DONTMOVE;
            }
        });
    }

}