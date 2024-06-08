import lexer.Lexer;
import token.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    private static final String INPUT = "input.js";
    private static final String OUTPUT = "tokens.txt";

    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get(INPUT)));
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            Files.write(Paths.get(OUTPUT), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);

            for (Token token : tokens) {
                Files.write(Paths.get(OUTPUT), (token.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("Unable to open file: " + e.getMessage());
        }
    }
}
