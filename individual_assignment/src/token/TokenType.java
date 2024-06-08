package token;

public enum TokenType {
    WHITESPACE,
    KEYWORD,
    IDENTIFIER,
    LITERAL_INTEGER,
    LITERAL_FLOAT,
    LITERAL_STRING,
    COMMENT,
    OPERATOR,
    PUNCTUATOR,
    EOF,
    UNKNOWN
}