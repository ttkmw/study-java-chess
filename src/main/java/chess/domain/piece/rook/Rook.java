package chess.domain.piece.rook;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Initialized {
    public Rook(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        return new Rook(name, to, team, canNotMoveStrategies);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        return hasHindrancePerpendicularlyInBetween(to, board);
    }
}
