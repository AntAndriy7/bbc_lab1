package lexer;

import token.Token;
import token.TokenType;

import java.util.Set;

public class KeywordProcessor implements TokenProcessor {

    private static final Set<String> KEYWORDS = Set.of(
            "await", "break", "case", "catch", "class", "const", "continue", "debugger", "default", "delete", "do", "else",
            "enum", "export", "extends", "false", "finally", "for", "function", "if", "implements", "import", "in",
            "instanceof", "interface", "let", "new", "null", "package", "private", "protected", "public", "return",
            "super", "switch", "static", "this", "throw", "try", "true", "typeof", "var", "void", "while", "with", "yield"
    );

    private State current_state;

    @Override
    public Token process(String input, int position) {
        int start = position;
        current_state = State.ENTRY;
        StringBuilder token_value = new StringBuilder();

        while (position < input.length()) {
            char current = input.charAt(position);
            if (!processChar(current)) {
                break;
            }
            token_value.append(current);
            position++;
        }

        if (current_state == State.ACCEPTING && KEYWORDS.contains(token_value.toString())) {
            return new Token(TokenType.KEYWORD, token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c) {
        switch (current_state) {
            case ENTRY:
                return processEntry(c);
            case KEYWORD:
                return processKeyword(c);
            default:
                return false;
        }
    }

    private boolean processEntry(char c) {
        if (Character.isLetter(c)) {
            current_state = State.KEYWORD;
            return true;
        }
        return false;
    }

    private boolean processKeyword(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        current_state = State.ACCEPTING;
        return false;
    }
}