package chess.domain.piece;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @ParameterizedTest
    @DisplayName("#move() : should return King as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForMove"})
    void move(Position from, Position to, Team team, Piece expected) {
        Piece king = PieceFactory.createInitializedPiece(PieceType.KING, from, team);

        PiecesState piecesState = TestPiecesState.initialize();
        Piece exPiece = piecesState.getPiece(to);
        Piece moved = king.move(to, exPiece);
        assertThat(moved).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, Team team, boolean expected) {
        Piece king = PieceFactory.createInitializedPiece(PieceType.KING, from, team);

        PiecesState piecesState = TestPiecesState.initialize();

        boolean actual = king.canNotMove(to, piecesState);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(5, 1), Position.of(5, 1), team, true),
                Arguments.of(Position.of(5, 1), Position.of(5, 3), team, true),
                Arguments.of(Position.of(5, 1), Position.of(5, 2), team, true)

        );
    }


    @Test
    @DisplayName("#calculateScore() : should return score of King")
    void calculateScore() {
        //given
        Piece king = PieceFactory.createInitializedPiece(PieceType.KING, Position.of(5, 5), Team.WHITE);
        PiecesState piecesState = TestPiecesState.initialize();
        //when
        Score score = king.calculateScore(piecesState);
        //then
        assertThat(score).isEqualTo(PieceType.KING.getScore());
    }

    private static Stream<Arguments> getCasesForMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 5),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(5, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 5),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(6, 5), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 4),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(6, 4), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(6, 3),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(6, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(5, 3),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(5, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 3),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(4, 3), team, MoveType.MOVED)),
                Arguments.of(Position.of(5, 4),
                        Position.of(4, 4),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(4, 4), team, MoveType.MOVED)),

                Arguments.of(Position.of(5, 4),
                        Position.of(4, 5),
                        team,
                        PieceFactory.createMovedPiece(PieceType.KING, Position.of(4, 5), team, MoveType.MOVED))
        );
    }
}