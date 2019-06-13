package cxt.example.lee.liberty.hrd;

class ChessBoard {

    private int[][] playArea;

    ChessBoard(int boardLength, int boardHeigth) {
        playArea = new int[boardLength][boardHeigth];

        for (int i = 0; i < playArea.length; i++)
            for (int j = 0; j < playArea[i].length; j++)
                playArea[i][j] = 0;
    }

    int getBoardValue(int x, int y) {
        return playArea[x][y];
    }

    boolean PutFragment(Fragment fragment) {
        for (int j = 0; j < fragment.getLength(); j++) {
            for (int k = 0; k < fragment.getHeight(); k++) {
                if (playArea[fragment.getxPos() + j][fragment.getyPos() + k] == 0)
                    playArea[fragment.getxPos() + j][fragment.getyPos() + k] = fragment.getValue();
                else
                    return false;
            }
        }
        return true;
    }

    boolean fragmentCanBeMoved(Fragment fragment, int direction) {
        switch (direction) {
            case Fragment.DIRECTION_UP: {
                for (int i = 0; i < fragment.getLength(); i++)
                    if (playArea[fragment.getxPos() + i][fragment.getyPos() - 1] != 0)
                        return false;
                break;
            }
            case Fragment.DIRECTION_DOWN: {
                for (int i = 0; i < fragment.getLength(); i++)
                    if (playArea[fragment.getxPos() + i][fragment.getyPos() + fragment.getHeight()] != 0)
                        return false;
                break;
            }
            case Fragment.DIRECTION_LEFT: {
                for (int i = 0; i < fragment.getHeight(); i++)
                    if (playArea[fragment.getxPos() - 1][fragment.getyPos() + i] != 0)
                        return false;
                break;
            }
            case Fragment.DIRECTION_RIGHT: {
                for (int i = 0; i < fragment.getHeight(); i++)
                    if (playArea[fragment.getxPos() + fragment.getLength()][fragment.getyPos() + i] != 0)
                        return false;
                break;
            }
        }
        return true;
    }

    void moveFragment(Fragment fragment) {
        // Destroy the old fragment.
        for (int i = 0; i < playArea.length; i++)
            for (int j = 0; j < playArea[i].length; j++)
                if (playArea[i][j] == fragment.getValue())
                    playArea[i][j] = 0;
        // Put a new fragment.
        PutFragment(fragment);
    }
}