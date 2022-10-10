public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
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

        if (chPosit(line) && chPosit(column) && chPosit(toLine) && chPosit(toColumn)) {
            if (column == toColumn) {

                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
                    if (chessBoard.board[i][column] != null) {
                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return false;
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return true;
                        else if (i != toLine && i != line) return false;
                    }
                }

                if (chessBoard.board[toLine][column] != null) {
                    if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this) return false;
                    else return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
                } else return true;

            } else if (line == toLine) {

                for (int i = getMin(toColumn, column); i < getMax(column, toColumn); i++) {
                    if (chessBoard.board[line][i] != null) {
                        if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return false;
                        else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return true;
                        else if (i != toLine && i != column) return false;
                    }
                }

                if (chessBoard.board[toLine][toColumn] != null) {
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this) return false;
                    else return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this;
                } else return true;
            } else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

}