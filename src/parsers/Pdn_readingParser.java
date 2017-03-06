package parsers;

/*
 * Pdn_readingParser.java
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
import net.percederberg.grammatica.parser.ProductionPattern;
import net.percederberg.grammatica.parser.ProductionPatternAlternative;
import net.percederberg.grammatica.parser.RecursiveDescentParser;
import net.percederberg.grammatica.parser.Tokenizer;

/**
 * A token stream parser.
 *
 * @author   Wieger Wesselink, <wieger at 10x10 dot org>
 * @version  1.5
 */
class Pdn_readingParser extends RecursiveDescentParser {

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_1 = 3001;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_2 = 3002;

    /**
     * A generated production node identity constant.
     */
    private static final int SUBPRODUCTION_3 = 3003;

    /**
     * Creates a new parser with a default analyzer.
     *
     * @param in             the input stream to read from
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    public Pdn_readingParser(Reader in) throws ParserCreationException {
        super(in);
        createPatterns();
    }

    /**
     * Creates a new parser.
     *
     * @param in             the input stream to read from
     * @param analyzer       the analyzer to use while parsing
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    public Pdn_readingParser(Reader in, Pdn_readingAnalyzer analyzer)
        throws ParserCreationException {

        super(in, analyzer);
        createPatterns();
    }

    /**
     * Creates a new tokenizer for this parser. Can be overridden by a
     * subclass to provide a custom implementation.
     *
     * @param in             the input stream to read from
     *
     * @return the tokenizer created
     *
     * @throws ParserCreationException if the tokenizer couldn't be
     *             initialized correctly
     */
    protected Tokenizer newTokenizer(Reader in)
        throws ParserCreationException {

        return new Pdn_readingTokenizer(in);
    }

    /**
     * Initializes the parser by creating all the production patterns.
     *
     * @throws ParserCreationException if the parser couldn't be
     *             initialized correctly
     */
    private void createPatterns() throws ParserCreationException {
        ProductionPattern             pattern;
        ProductionPatternAlternative  alt;

        pattern = new ProductionPattern(Pdn_readingConstants.PDN_FILE,
                                        "PdnFile");
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.GAME, 1, 1);
        alt.addProduction(SUBPRODUCTION_1, 0, -1);
        alt.addProduction(Pdn_readingConstants.GAME_SEPARATOR, 0, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME_SEPARATOR,
                                        "GameSeparator");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.ASTERISK, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.RESULT1, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.RESULT2, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME,
                                        "Game");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_2, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.GAME_BODY, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME_HEADER,
                                        "GameHeader");
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.PDN_TAG, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME_BODY,
                                        "GameBody");
        alt = new ProductionPatternAlternative();
        alt.addProduction(SUBPRODUCTION_3, 1, -1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.PDN_TAG,
                                        "PdnTag");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.LBRACKET, 1, 1);
        alt.addToken(Pdn_readingConstants.IDENTIFIER, 1, 1);
        alt.addToken(Pdn_readingConstants.STRING, 1, 1);
        alt.addToken(Pdn_readingConstants.RBRACKET, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME_MOVE,
                                        "GameMove");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.MOVENUMBER, 0, 1);
        alt.addProduction(Pdn_readingConstants.MOVE, 1, 1);
        alt.addProduction(Pdn_readingConstants.MOVE_STRENGTH, 0, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.VARIATION,
                                        "Variation");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.LPAREN, 1, 1);
        alt.addProduction(Pdn_readingConstants.GAME_BODY, 1, 1);
        alt.addToken(Pdn_readingConstants.RPAREN, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.MOVE,
                                        "Move");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.NUMERICMOVE, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.ALPHANUMERICMOVE, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.ELLIPSES, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.GAME_RESULT,
                                        "GameResult");
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.RESULT1, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.RESULT2, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.RESULT1,
                                        "Result1");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.WIN1, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.DRAW1, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.LOSS1, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.RESULT2,
                                        "Result2");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.WIN2, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.DRAW2, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.LOSS2, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.DOUBLEFORFEIT, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(Pdn_readingConstants.MOVE_STRENGTH,
                                        "MoveStrength");
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.MOVESTRENGTH1, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.MOVESTRENGTH2, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_1,
                                        "Subproduction1");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.GAME_SEPARATOR, 1, 1);
        alt.addProduction(Pdn_readingConstants.GAME, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_2,
                                        "Subproduction2");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.GAME_HEADER, 1, 1);
        alt.addProduction(Pdn_readingConstants.GAME_BODY, 0, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);

        pattern = new ProductionPattern(SUBPRODUCTION_3,
                                        "Subproduction3");
        pattern.setSynthetic(true);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.GAME_MOVE, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addProduction(Pdn_readingConstants.VARIATION, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.COMMENT, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.SETUP, 1, 1);
        pattern.addAlternative(alt);
        alt = new ProductionPatternAlternative();
        alt.addToken(Pdn_readingConstants.NAG, 1, 1);
        pattern.addAlternative(alt);
        addPattern(pattern);
    }
}
