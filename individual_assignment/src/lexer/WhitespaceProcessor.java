package lexer;

import token.Token;
import token.TokenType;

public class WhitespaceProcessor implements TokenProcessor {
    @Override
    public Token process(String input, int position) {
        if (Character.isWhitespace(input.charAt(position))) {
            int start = position;
            while (position < input.length() && Character.isWhitespace(input.charAt(position))) {
                position++;
            }
            return new Token(TokenType.WHITESPACE, input.substring(start, position), start);
        }
        return null;
    }
}
