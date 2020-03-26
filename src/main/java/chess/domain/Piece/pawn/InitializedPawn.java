package chess.domain.Piece.pawn;

import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.Position;

public class InitializedPawn extends Initialized {

    protected InitializedPawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        return null;
    }

    @Override
    protected boolean canMove(Position to, Piece exPiece) {
        return false;
    }
}