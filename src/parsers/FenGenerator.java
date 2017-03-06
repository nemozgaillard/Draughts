package parsers;

/*
 * FenGenerator.java
 */

import java.io.StringReader;
import java.util.Vector;

import draughts.model.Color;
import draughts.model.FEN;
import draughts.model.Piece;
import draughts.model.Piece.PieceType;
import net.percederberg.grammatica.parser.Node;
import net.percederberg.grammatica.parser.ParseException;
import net.percederberg.grammatica.parser.Parser;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.percederberg.grammatica.parser.Production;
import net.percederberg.grammatica.parser.Token;

/**
 * A class providing callback methods for the parser.
 *
 * @author   Modified by Eric Nemoz-Gaillard, <eric.ng at gmx dot fr>
 * @version  1.2.1
 */
public class FenGenerator extends FenAnalyzer implements FenConstants {
	
	private FEN fen = new FEN();
	private Vector<parsedPiece> parsedPieces = new Vector<parsedPiece> ();
	private Vector<Piece> whitePieces = new Vector<Piece> (); 
	private Vector<Piece> blackPieces = new Vector<Piece> (); 
	
	public FenGenerator() {
		super();
	}
	
	public FEN generateFEN(String fen) throws ParserCreationException, ParserLogException {
		
		Parser parser;
		Node node;
		
		parser = new FenParser(new StringReader(fen), this);
        parser.prepare();
        node = parser.parse();
        node.getValue(0);
		
		return this.fen;
	}
	

    /**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitColor(Token node) throws ParseException {
		// Color encoded according to draughts.model.Color
		node.addValue(Color.valueOfString(node.getImage()));
	    return node;
	}

	/**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitKing(Token node) throws ParseException {
		node.addValue("K");
	    return node;
	}

	/**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitAlphasquare(Token node) throws ParseException {
		node.addValue(node.getImage());
	    return node;
	}

	/**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitAlphaNumericSquares(Production node) throws ParseException {
	    return exitNumericSquares(node);
	}
	

	/**
     * Called when exiting a parse tree node.
     *
     * @param node           the node being exited
     *
     * @return the node to add to the parse tree, or
     *         null if no parse tree should be created
     *
     * @throws ParseException if the node analysis discovered errors
     */
    protected Node exitNumsquare(Token node) throws ParseException {
    	node.addValue(new Integer(node.getImage()));
        return node;
    }

    /**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitNumericSquareRange(Production node) throws ParseException { 
		
		String prefix = "";
		int offset = 0;
		switch ( node.getChildCount() ) {
		case 1:
		case 2:
			node.addValues(getChildValues(node));
			break;
		case 4:
			prefix = "K";
			offset = 1;
		case 3:
	   		int start = (int) node.getChildAt(0 + offset).getValue(0);
	   		int end = (int) node.getChildAt(2 + offset).getValue(0);
	   		for ( Integer i = start ; i <= end ; i++ ) {
	   			if ( prefix == "K" ) {
	   				node.addValue(prefix);
	   			}
	   			node.addValue(i);
	   		}		
		}
	    return node;
	
	}
	

	/**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitNumericSquares(Production node) throws ParseException {
		node.addValues(getChildValues(node));
		PieceType type = PieceType.MAN;
		for ( Object obj : node.getAllValues() ) {
			String label = obj.toString();
			if ( label != "K" ) {
				parsedPieces.add(new parsedPiece(type, label));
				type = PieceType.MAN;
			}
			else {
				type = PieceType.KING;
			}
		}
	    return node;
	}
	

	/**
	 * Called when exiting a parse tree node.
	 *
	 * @param node           the node being exited
	 *
	 * @return the node to add to the parse tree, or
	 *         null if no parse tree should be created
	 *
	 * @throws ParseException if the node analysis discovered errors
	 */
	protected Node exitPosition(Production node) throws ParseException {
		node.addValues(getChildValues(node));
		Color color = (Color) node.getValue(0);
		for ( parsedPiece piece : parsedPieces ) {
			switch ( color ) {				
			case BLACK:
				blackPieces.add(new Piece(color, piece.type, piece.squareLabel));
				break;
			case WHITE:
				whitePieces.add(new Piece(color, piece.type, piece.squareLabel));
			}
		}
		parsedPieces.removeAllElements();
	    return node;
	}

	/**
     * Called when exiting a parse tree node.
     *
     * @param node           the node being exited
     *
     * @return the node to add to the parse tree, or
     *         null if no parse tree should be created
     *
     * @throws ParseException if the node analysis discovered errors
     */
    protected Node exitFen(Production node) throws ParseException {
    	node.addValues(getChildValues(node));
		Color color = (Color) node.getValue(0);
		fen.turn = color;
    	switch ( color ) {
    	case BLACK:
    		fen.playerPieces = blackPieces;
    		fen.opponentPieces = whitePieces;
    		break;
    	case WHITE:
    		fen.playerPieces = whitePieces;
    		fen.opponentPieces = blackPieces;
    	}
        return node;
    }
    
    private class parsedPiece extends Piece {
    
		private PieceType type;
		private String squareLabel;
    	
    	private parsedPiece(PieceType type, String label) { 
    		super();
    		this.type = type;
    		this.squareLabel = label;
    	}
     	
    }
    
    
}
