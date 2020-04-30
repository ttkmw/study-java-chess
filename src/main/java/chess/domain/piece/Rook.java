package chess.domain.piece;

import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private static final Score score = new Score(5);

    public Rook(Team team) {
        super(team, Team.convertName("r", team));
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        return score;
    }

    private static final List<CanNotMoveStrategy> DEFAULT_CAN_NOT_MOVE_STRATEGIES = Collections.unmodifiableList(Arrays.asList(
            new IsStayed(),
            new HasHindrancePerpendicularlyInBetween(),
            new IsAttackingSameTeam(),
            new IsHeadingDiagonalDirection())
    );

    @Override
    protected List<CanNotMoveStrategy> constituteStrategies(Position from) {
        return DEFAULT_CAN_NOT_MOVE_STRATEGIES;
    }
}
