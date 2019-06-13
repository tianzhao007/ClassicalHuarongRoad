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
        Fragment.setChessBoard(chessBoard);
        Fragment.addFragment(new Fragment("Cao Cao", Color.WHITE, 2, 2, 1, 0, R.drawable.role_caocao));
        Fragment.addFragment(new Fragment("Zhang Fei", Color.BLUE, 1, 2, 0, 0, R.drawable.role_zhangfei));
        Fragment.addFragment(new Fragment("Huang Zhong", Color.YELLOW, 1, 2, 3, 0, R.drawable.role_huangzhong));
        Fragment.addFragment(new Fragment("Ma Chao", Color.RED, 1, 2, 0, 2, R.drawable.role_machao));
        Fragment.addFragment(new Fragment("Zhao Yun", Color.MAGENTA, 1, 2, 3, 2, R.drawable.role_zhaoyun));
        Fragment.addFragment(new Fragment("Guan Yu", Color.GREEN, 2, 1, 1, 2, R.drawable.role_guanyu));
        Fragment.addFragment(new Fragment("Soldier1", 1, 1, 1, 0, 4, R.drawable.role_soldier1));
        Fragment.addFragment(new Fragment("Soldier2", 2, 1, 1, 3, 4, R.drawable.role_soldier2));
        Fragment.addFragment(new Fragment("Soldier3", 3, 1, 1, 1, 3, R.drawable.role_soldier3));
        Fragment.addFragment(new Fragment("Soldier4", 4, 1, 1, 2, 3, R.drawable.role_soldier4));

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
                        int direction = decideDirection(x, y, (Fragment) Objects.requireNonNull(Fragment.fragmentHashTable.get(prevSelectedValue)));
                        if (direction != Fragment.DIRECTION_DONTMOVE) {
                            Fragment.fragmentHashTable.put(prevSelectedValue, ((Fragment) Fragment.fragmentHashTable.get(prevSelectedValue)).move(direction));
                            view.invalidate();
                        }
                    }
                    return false;
                }
            }

            private int decideDirection(int xPos, int yPos, Fragment fragment) {
                if ((xPos == fragment.getxPos() - 1) && (yPos >= fragment.getyPos() && yPos <= fragment.getyPos() + fragment.getHeight() - 1))
                    return Fragment.DIRECTION_LEFT;
                if ((xPos == fragment.getxPos() + fragment.getLength()) && (yPos >= fragment.getyPos() && yPos <= fragment.getyPos() + fragment.getHeight() - 1))
                    return Fragment.DIRECTION_RIGHT;
                if ((xPos >= fragment.getxPos() && xPos <= fragment.getxPos() + fragment.getLength() - 1) && (yPos == fragment.getyPos() - 1))
                    return Fragment.DIRECTION_UP;
                if ((xPos >= fragment.getxPos() && xPos <= fragment.getxPos() + fragment.getLength() - 1) && (yPos == fragment.getyPos() + fragment.getHeight()))
                    return Fragment.DIRECTION_DOWN;
                return Fragment.DIRECTION_DONTMOVE;
            }
        });
    }

}