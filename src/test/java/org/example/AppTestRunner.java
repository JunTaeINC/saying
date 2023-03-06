package org.example;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String cmd) {
        cmd.stripIndent().trim();
        cmd += "\n종료";

        Scanner scanner = TestUtil.genScanner(cmd);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(scanner).run();

        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        return rs;
    }
}
