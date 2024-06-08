package lexer;

import token.Token;
import token.TokenType;

import java.util.Set;

public class PunctuatorProcessor implements TokenProcessor {

    private static final Set<String> PUNCTUATORS = Set.of(".", ":", ",", ";", "(", ")", "[", "]", "{", "}");

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
            return new Token(TokenType.PUNCTUATOR, token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c) {
        switch (current_state) {
            case ENTRY:
                return processEntry(c);
            default:
                return false;
        }
    }

    private boolean processEntry(char c) {
        if (PUNCTUATORS.contains(Character.toString(c))) {
            current_state = State.ACCEPTING;
            return true;
        }
        return false;
    }
}
