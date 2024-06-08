package lexer;

public enum State {
    ENTRY,
    SINGLE_LINE_COMMENT,
    MULTI_LINE_COMMENT,
    IDENTIFIER,
    KEYWORD,
    STRING,
    NUMBER,
    OPERATOR,
    ACCEPTING
}