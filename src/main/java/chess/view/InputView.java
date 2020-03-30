package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printMoveRequest() {
        System.out.println("움직여주세요.");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
}
