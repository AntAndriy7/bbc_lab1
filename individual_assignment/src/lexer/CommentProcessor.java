package lexer;

import token.Token;
import token.TokenType;

public class CommentProcessor implements TokenProcessor {

    private State current_state;
    private StringBuilder token_value;

    @Override
    public Token process(String input, int position) {
        int start = position;
        current_state = State.ENTRY;
        token_value = new StringBuilder();

        while (position < input.length()) {
            char current = input.charAt(position);
            if (!processChar(current, input, position)) {
                break;
            }
            token_value.append(current);
            position++;
        }

        if (current_state == State.ACCEPTING) {
            return new Token(TokenType.COMMENT, token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c, String input, int position) {
        switch (current_state) {
            case ENTRY:
                return processEntry(input, position);
            case SINGLE_LINE_COMMENT:
                return processSLC(c);
            case MULTI_LINE_COMMENT:
                return processMLC(c, input, position);
            default:
                return false;
        }
    }

    private boolean processEntry(String input, int position) {
        if (input.startsWith("//", position)) {
            current_state = State.SINGLE_LINE_COMMENT;
            return true;
        } else if (input.startsWith("/*", position)) {
            current_state = State.MULTI_LINE_COMMENT;
            return true;
        }
        return false;
    }

    private boolean processSLC(char c) {
        if (c == '\n') {
            current_state = State.ACCEPTING;
            return false;
        }
        return true;
    }

    private boolean processMLC(char c, String input, int position) {
        if (input.startsWith("*/", position)) {
            token_value.append(c);
            token_value.append(input.charAt(position + 1));
            current_state = State.ACCEPTING;
            return false;
        }
        return true;
    }
}