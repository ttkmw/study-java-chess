package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Pawn;
import chess.domain.piece.team.Team;

import java.util.List;

public class MovedPawn extends Pawn {


    public static final double MAX_DISTANCE = Math.sqrt(2);

    public MovedPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    public MovedPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score, MoveType moveType) {
        super(name, position, team, canNotMoveStrategies, score, moveType);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        Piece exPiece = board.getPiece(to);
        moveType = moveType.update(this, exPiece);
        return PieceFactory.createMovedPiece(MovedPawn.class, to, team, moveType);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        return hasHindranceStraightInBetween(to, board);
    }
}