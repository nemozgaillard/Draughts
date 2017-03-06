package parsers;

/*
 * TimeControlTokenizer.java
 *
 * THIS FILE HAS BEEN GENERATED AUTOMATICALLY. DO NOT EDIT!
 *
 * Distributed under the Boost Software License, Version 1.0.
 * See http://www.boost.org/LICENSE_1_0.txt.
 *
 * Copyright (c) 2009 Wieger Wesselink. All rights reserved.
 */

import java.io.Reader;

import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.TokenPattern;
import net.percederberg.grammatica.parser.Tokenizer;

/**
 * A character stream tokenizer.
 *
 * @author   Wieger Wesselink, <wieger at 10x10 dot org>
 * @version  1.0
 */
class TimeControlTokenizer extends Tokenizer {

    /**
     * Creates a new tokenizer for the specified input stream.
     *
     * @param input          the input stream to read
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    public TimeControlTokenizer(Reader input)
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

        pattern = new TokenPattern(TimeControlConstants.UNKNOWN,
                                   "UNKNOWN",
                                   TokenPattern.STRING_TYPE,
                                   "?");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.NOTIME,
                                   "NOTIME",
                                   TokenPattern.STRING_TYPE,
                                   "-");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.MOVES_SECONDS,
                                   "MOVES_SECONDS",
                                   TokenPattern.REGEXP_TYPE,
                                   "[\\d]+/[\\d]+");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.INCREMENTAL,
                                   "INCREMENTAL",
                                   TokenPattern.REGEXP_TYPE,
                                   "[\\d]+\\+[\\d]+");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.SUDDENDEATH,
                                   "SUDDENDEATH",
                                   TokenPattern.REGEXP_TYPE,
                                   "[\\d]+");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.SANDCLOCK,
                                   "SANDCLOCK",
                                   TokenPattern.REGEXP_TYPE,
                                   "\\*[\\d]+");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.COLON,
                                   "COLON",
                                   TokenPattern.STRING_TYPE,
                                   ":");
        addPattern(pattern);

        pattern = new TokenPattern(TimeControlConstants.WHITESPACE,
                                   "WHITESPACE",
                                   TokenPattern.REGEXP_TYPE,
                                   "[ \\t\\n\\r]+");
        pattern.setIgnore();
        addPattern(pattern);
    }
}
