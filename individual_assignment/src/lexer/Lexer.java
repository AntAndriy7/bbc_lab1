package lexer;

import token.Token;
import token.TokenType;

import java.util.List;
import java.util.ArrayList;

public class Lexer {

    private int position = 0;
    private final String input;
    private final List<Token> tokens = new ArrayList<>();
    private final List<TokenProcessor> tokenProcessors = List.of(
            new CommentProcessor(),
            new WhitespaceProcessor(),
            new KeywordProcessor(),
            new IdentifierProcessor(),
            new LiteralProcessor(),
            new OperatorProcessor(),
            new PunctuatorProcessor()
    );

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        while (position < input.length()) {
            boolean matched = false;
            for (TokenProcessor processor : tokenProcessors) {
                Token token = processor.process(input, position);
                if (token != null) {
                    if (token.getType() != TokenType.WHITESPACE) {
                        tokens.add(token);
                    }
                    position += token.getValue().length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                tokens.add(new Token(TokenType.UNKNOWN, String.valueOf(input.charAt(position)), position));
                position++;
            }
        }
        tokens.add(new Token(TokenType.EOF, "", position));
        return tokens;
    }
}
