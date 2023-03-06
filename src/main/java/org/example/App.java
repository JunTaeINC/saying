package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    void run() {
        System.out.println("== 명언 앱 ==");
        int idNumber = 1;
        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine();
            List<명언저장소> 명언집 = new ArrayList<>();

            if (input.isEmpty()) continue;

            switch (input) {
                case "종료":
                    System.out.println("명언앱이 종료 되었습니다.");
                    return;
                case "등록":
                    System.out.print("명언) ");
                    String saying = scanner.nextLine().trim();
                    System.out.print("작가) ");
                    String author = scanner.nextLine().trim();
                    System.out.printf("%d번 명언이 등록 되었습니다.\n", idNumber);
                    명언집.add(new 명언저장소(idNumber, saying, author));
                    idNumber++;
                    break;

                default:
                    System.out.printf("'%s'은(는) 올바르지 않은 명령입니다.\n", input);
                    break;
            }
        }

    }
}
