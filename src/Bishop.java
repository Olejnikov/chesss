public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chPosit(line) && chPosit(toLine) && chPosit(column) && chPosit(toColumn) && chessBoard.board[line][column] != null) {
            if (line != toLine && column != toColumn &&
                    (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                    getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn)
            ) {
                if((line == getMax(line, toLine) && column == getMin(column, toColumn)) ||
                        (toLine == getMax(line, toLine) && toColumn == getMin(column,toColumn))
                ) {
                    int l = getMax(line, toLine);
                    int c = getMin(column, toColumn);
                    int toL = getMin(line, toLine);
                    int toC = getMax(column, toColumn);
                    int[][] positions = new int[toC - c][1];
                    for (int i = 1; i < toC - c; i++) {
                        if (chessBoard.board[l - i][c + i] == null) {
                            positions[i - 1] = new int[]{l - i, c + i};
                        } else if (!chessBoard.board[l - i][c + i].color.equals(this.color) && l - i == toLine) {
                            positions[i - 1] = new int[]{l - i, c + i};
                        } else {
                            return false;
                        }
                    }
                    return true;
                } else {
                    int l = getMin(line, toLine);
                    int c = getMin(column, toColumn);
                    int toL = getMax(line, toLine);
                    int toC = getMax(column, toColumn);
                    int[][] positions = new int[toC - c][1];
                    for (int i = 1; i < toC - c; i++) {
                        if (chessBoard.board[l + i][c + i] == null) {
                            positions[i - 1] = new int[]{l + i, c + i};
                        } else if (!chessBoard.board[l + i][c + i].color.equals(this.color) && l + i == toLine) {
                            positions[i - 1] = new int[]{l + i, c + i};
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

}
