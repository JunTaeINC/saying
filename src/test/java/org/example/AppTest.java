package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    @DisplayName("스캐너에 키보드가 아닌 문자열을 입력으로 설정")
    public void test1() {
        Scanner scanner = TestUtil.genScanner("안녕");

        String cmd = scanner.nextLine().trim();
        assertThat(cmd).isEqualTo("안녕");
    }

    @Test
    @DisplayName("출력을 모니터에 하지 않고 문자열로 얻기")
    public void test2() {
        // System.out 에 대한 화면출력 금지 시작
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        System.out.print("안녕");

        // 그 동안 출력되지 않던 내용들을 문자열로 반환
        String input = output.toString();

        // System.out 에 대한 화면출력 금지 끝
        TestUtil.clearSetOutToByteArray(output);

        assertThat(input).isEqualTo("안녕");
    }
    // 테스트유틸 테스트 끝

    // 앱 테스트 시작
    @Test
    @DisplayName("프로그램 시작시 타이틀 출력 그리고 종료")
    void test3() {
        String input = AppTestRunner.run("");

        assertThat(input).contains("== 명언 앱 ==")
                .contains("명령) ")
                .contains("명언앱이 종료 되었습니다.")
                .doesNotContain("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("잘못된 명령어 입력에 대한 처리")
    void test4() {
        String input = AppTestRunner.run("종료띠");

        assertThat(input).contains("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("등록을 입력시 명언과 작가를 입력받고, 명언을 생성한다.")
    void test5() {
        String input = AppTestRunner.run("""
                등록
                어쩌라고
                김준태
                """);

        assertThat(input).contains("명언) ")
                .contains("작가) ")
                .contains("1번 명언이 등록 되었습니다.");
    }

    @Test
    @DisplayName("등록 여러개 입력시 1번,2번 순차적으로 등록되는지 검증")
    void test6() {
        String input = AppTestRunner.run("""
                등록
                어쩔티비
                김잼민
                등록
                여보세요
                임창정
                등록
                니가누군데
                김준태
                """);

        assertThat(input).contains("명언) ")
                .contains("작가) ")
                .contains("1번 명언이 등록 되었습니다.")
                .contains("2번 명언이 등록 되었습니다.")
                .contains("3번 명언이 등록 되었습니다.")
                .doesNotContain("4번 명언이 등록 되었습니다.");
    }
}