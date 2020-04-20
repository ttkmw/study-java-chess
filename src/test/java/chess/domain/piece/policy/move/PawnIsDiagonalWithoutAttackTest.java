package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.TestPiecesState;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnIsDiagonalWithoutAttackTest {

    private PawnIsDiagonalWithoutAttack pawnIsDiagonalWithoutAttack = new PawnIsDiagonalWithoutAttack();

    @ParameterizedTest
    @DisplayName("#canNotMove : return boolean as to Position 'from', 'to', team and the Piece at the position")
    @MethodSource({"getCasesForCanNotMove"})
    void canNotMove(Position from, Position to, boolean expected) {
        PiecesState piecesState = TestPiecesState.initialize();
        boolean canNotMove = pawnIsDiagonalWithoutAttack.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(2,3), true),
                Arguments.of(Position.of(1,2), Position.of(1,3), false)

        );
    }
}