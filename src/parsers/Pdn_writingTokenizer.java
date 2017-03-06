package parsers;

/*
 * Pdn_writingTokenizer.java
 *
 * THIS FILE HAS BEEN GENERATED AUTOMATICALLY. DO NOT EDIT!
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt.
 *
 * Copyright (c) 2009-2012 Wieger Wesselink.
 */

import java.io.Reader;

import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.TokenPattern;
import net.percederberg.grammatica.parser.Tokenizer;

/**
 * A character stream tokenizer.
 *
 * @author   Wieger Wesselink, <wieger at 10x10 dot org>
 * @version  1.5
 */
class Pdn_writingTokenizer extends Tokenizer {

    /**
     * Creates a new tokenizer for the specified input stream.
     *
     * @param input          the input stream to read
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    public Pdn_writingTokenizer(Reader input)
        throws ParserCreationException {

        super(input, false);
        createPatterns();
    }

    /**
     * Initializes the tokenizer by creating all the token patterns.
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    private void createPatterns() throws ParserCreationException {
        TokenPattern  pattern;

        pattern = new TokenPattern(Pdn_writingConstants.MOVENUMBER,
                                   "MOVENUMBER",
                                   TokenPattern.REGEXP_TYPE,
                                   "[0-9]+\\.(\\.\\.)?");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.NUMERICMOVE,
                                   "NUMERICMOVE",
                                   TokenPattern.REGEXP_TYPE,
                                   "[1-9][0-9]?(\\s*[-x]\\s*[1-9][0-9]?)+");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.ALPHANUMERICMOVE,
                                   "ALPHANUMERICMOVE",
                                   TokenPattern.REGEXP_TYPE,
                                   "([a-h][1-8](\\s*[x:]\\s*[a-h][1-8])+)|([a-h][1-8]\\s*[-]?\\s*[a-h][1-8])");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.MOVESTRENGTH1,
                                   "MOVESTRENGTH1",
                                   TokenPattern.REGEXP_TYPE,
                                   "[!?]+");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.MOVESTRENGTH2,
                                   "MOVESTRENGTH2",
                                   TokenPattern.REGEXP_TYPE,
                                   "\\([!?]+\\)");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.NAG,
                                   "NAG",
                                   TokenPattern.REGEXP_TYPE,
                                   "\\$[0-9]+");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.LPAREN,
                                   "LPAREN",
                                   TokenPattern.STRING_TYPE,
                                   "(");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.RPAREN,
                                   "RPAREN",
                                   TokenPattern.STRING_TYPE,
                                   ")");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.LBRACKET,
                                   "LBRACKET",
                                   TokenPattern.STRING_TYPE,
                                   "[");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.RBRACKET,
                                   "RBRACKET",
                                   TokenPattern.STRING_TYPE,
                                   "]");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.ASTERISK,
                                   "ASTERISK",
                                   TokenPattern.STRING_TYPE,
                                   "*");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.SETUP,
                                   "SETUP",
                                   TokenPattern.REGEXP_TYPE,
                                   "/[^\\/]*/");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.STRING,
                                   "STRING",
                                   TokenPattern.REGEXP_TYPE,
                                   "\"([^\"]|\\\\\")*\"");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.COMMENT,
                                   "COMMENT",
                                   TokenPattern.REGEXP_TYPE,
                                   "\\{([^}]|(\\\\\\}))*\\}");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.IDENTIFIER,
                                   "IDENTIFIER",
                                   TokenPattern.REGEXP_TYPE,
                                   "[A-Z][a-zA-Z0-9_]*");
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.WHITESPACE,
                                   "WHITESPACE",
                                   TokenPattern.REGEXP_TYPE,
                                   "[ \\t\\n\\r]+");
        pattern.setIgnore();
        addPattern(pattern);

        pattern = new TokenPattern(Pdn_writingConstants.LINECOMMENT,
                                   "LINECOMMENT",
                                   TokenPattern.REGEXP_TYPE,
                                   "%.*");
        pattern.setIgnore();
        addPattern(pattern);
    }
}
