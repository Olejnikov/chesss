public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chPosit(line) && chPosit(column) && chPosit(toLine) && chPosit(toColumn) && chessBoard.board[line][column] != null) {
            if(column == toColumn) {
                int direction;
                int start;

                if(this.color == "White") {
                    direction = 1;
                    start = 1;
                } else {
                    direction = - 1;
                    start = 6;
                }

                if(line + direction == toLine) return chessBoard.board[toLine][toColumn] == null;
                if(line == start && line + 2 * direction == toLine)
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + direction][column] == null;

            } else {
                if((column - 1 == toColumn || column + 1 == toColumn) && (line + 1 == toLine || line - 1 == toLine))
                    return chessBoard.board[toLine][toColumn] != null && !chessBoard.board[toLine][toColumn].color.equals(this.color);
            }
        }
        return false;
    }

    @Override
    public String getSymbol () {
        return "P";
    }
}