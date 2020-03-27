package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IsStayedTest {
    private IsStayed isStayed = new IsStayed();

    @ParameterizedTest
    @DisplayName("#canNotMove() : return boolean as to position 'from' and 'to' isEqual")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        //given
        Initialized initialized = new InitializedPawn("testPawn", from, Team.BLACK);
        Board board = ChessBoard.initiaize();

        boolean canNotMove = isStayed.canNotMove(initialized, to, board);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,1), true),
                Arguments.of(Position.of(1,1), Position.of(1,2), false)
        );
    }
}