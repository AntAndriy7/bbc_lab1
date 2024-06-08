package lexer;

import token.Token;
import token.TokenType;

public class LiteralProcessor implements TokenProcessor {

    private State current_state;
    private StringBuilder token_value;

    @Override
    public Token process(String input, int position) {
        int start = position;
        current_state = State.ENTRY;
        token_value = new StringBuilder();

        while (position < input.length()) {
            char current = input.charAt(position);
            if (!processChar(current)) {
                break;
            }
            token_value.append(current);
            position++;
        }

        if (current_state == State.ACCEPTING) {
            return new Token(determineType(), token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c) {
        switch (current_state) {
            case ENTRY:
                return processEntry(c);
            case STRING:
                return processString(c);
            case NUMBER:
                return processNumber(c);
            default:
                return false;
        }
    }

    private boolean processEntry(char c) {
        if (c == '\"' || c == '\'') {
            current_state = State.STRING;
            return true;
        } else if (Character.isDigit(c)) {
            current_state = State.NUMBER;
            return true;
        }
        return false;
    }

    private boolean processString(char c) {
        if (c == '\"' || c == '\'') {
            current_state = State.ACCEPTING;
        }
        return true;
    }

    private boolean processNumber(char c) {
        if (Character.isDigit(c)) {
            return true;
        } else if (c == '.' && !token_value.toString().contains(".")) {
            return true;
        } else {
            current_state = State.ACCEPTING;
            return false;
        }
    }

    private TokenType determineType() {
        if (token_value.charAt(0) == '\"' || token_value.charAt(0) == '\'') {
            return TokenType.LITERAL_STRING;
        } else if (token_value.toString().contains(".")) {
            return TokenType.LITERAL_FLOAT;
        } else {
            return TokenType.LITERAL_INTEGER;
        }
    }
}
