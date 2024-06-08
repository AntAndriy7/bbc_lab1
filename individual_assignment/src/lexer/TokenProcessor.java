package lexer;

import token.Token;

public interface TokenProcessor {
    Token process(String input, int position);
}
