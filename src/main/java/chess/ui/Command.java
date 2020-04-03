package chess.ui;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    static Command of(String value) {
        for (Command command : values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }

        throw new IllegalArgumentException(String.format("%s에 해당하는 명령어를 찾을 수 없습니다.", value));
    }

    public boolean isNotMove() {
        return this != MOVE;
    }

    public boolean isNotStart() {
        return this != START;
    }

    public boolean isNotEnd() {
        return this != END;
    }

    public boolean isNotStatus() {
        return this != STATUS;
    }
}