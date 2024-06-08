package lexer;

import token.Token;
import token.TokenType;

import java.util.Set;

public class OperatorProcessor implements TokenProcessor {

    private static final Set<String> OPERATORS = Set.of(
            "+", "-", "*", "/", "%", "++", "--", "**", "=", "+=", "-=", "*=", "/=", "%=", "**=", "==", "!=",
            ">", "<", ">=", "<=", "===", "!==", "&&", "||", "!", "&", "|", "^", "~", "<<", ">>", ">>>"
    );

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

        if (current_state == State.ACCEPTING && OPERATORS.contains(token_value.toString())) {
            return new Token(TokenType.OPERATOR, token_value.toString(), start);
        } else {
            return null;
        }
    }

    private boolean processChar(char c) {
        switch (current_state) {
            case ENTRY:
                return processEntry(c);
            case OPERATOR:
                return processOperator(c);
            default:
                return false;
        }
    }

    private boolean processEntry(char c) {
        for (String operator : OPERATORS) {
            if (operator.charAt(0) == c) {
                current_state = State.OPERATOR;
                return true;
            }
        }
        return false;
    }

    private boolean processOperator(char c) {
        String possibleOperator = token_value.toString() + c;
        for (String operator : OPERATORS) {
            if (operator.startsWith(possibleOperator)) {
                return true;
            }
        }
        current_state = State.ACCEPTING;
        return false;
    }
}