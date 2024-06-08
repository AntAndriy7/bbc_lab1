package lexer;

import token.Token;
import token.TokenType;

public class IdentifierProcessor implements TokenProcessor {

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

        if (current_state == State.ACCEPTING) {
            return new Token(TokenType.IDENTIFIER, token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c) {
        switch (current_state) {
            case ENTRY:
                return processEntry(c);
            case IDENTIFIER:
                return processIdentifier(c);
            default:
                return false;
        }
    }

    private boolean processEntry(char c) {
        if (Character.isLetter(c) || c == '_') {
            current_state = State.IDENTIFIER;
            return true;
        }
        return false;
    }

    private boolean processIdentifier(char c) {
        if (Character.isLetterOrDigit(c) || c == '_') {
            return true;
        }
        current_state = State.ACCEPTING;
        return false;
    }
}