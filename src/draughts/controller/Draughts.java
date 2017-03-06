package draughts.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import draughts.model.Board;
import draughts.model.FEN;
import draughts.model.GameType;
import draughts.model.Match;
import draughts.model.PDN;
import draughts.model.Player;
import draughts.view.MainFrame;
import draughts.view.MainFrameSize;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import parsers.FenGenerator;

/**
 * Main class - part of the CONTROLLER component in a MVC pattern for this
 * application. <br>
 */
public class Draughts {
	
	private static MainFrame draughtsGUI;
	private static GameType gameType;
	private static Board board;
	private static PDN gameData;
	private static Match match;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startGUI();
			}
		});
		
	}
	
	/**
	 * Displays the main Draughts Window GUI. <br>
	 * <i>Displayed board: empty International draughts board. </i><br>
	 */
	public static void startGUI() {
		gameType = GameType.INTERNATIONAL;
		startGUI(gameType);
	}
	
	/**
	 * Displays the main Draughts Window GUI. <br>
	 * <i>Displayed board: an empty board of type {@code gameType}.
	 * 
	 * @param gameType
	 *            : the type of the draughts game.
	 * @see {@link #GameType}
	 */
	public static void startGUI(GameType gameType) {
		gameData = new PDN(gameType, false);
		startGUI(gameData);
	}
	
	/**
	 * Displays the main Draughts Window GUI. <br>
	 * 
	 * @param gameData
	 *            - a PDN object containing pertinent informations for the game:
	 *            <br>
	 *            Event, Site, Date, Round, White, Black, [Result],
	 *            <b>gameType</b>, [FEN]
	 * 
	 * @see {@link #GameType}
	 * @see {@link #PDN}
	 */
	public static void startGUI(PDN pdnGameData) {
		
		gameType = gameData.gameType;
		board = new Board(gameType);
		gameData = pdnGameData;
		draughtsGUI = new MainFrame(board);

	}

	public static void newMatch() {
		
		FEN fen = new FEN();
		FenGenerator generator = new FenGenerator();
		try {
			fen = generator.generateFEN(FEN.getSartingPosition(gameType));
		} catch (ParserCreationException | ParserLogException e) {
			e.printStackTrace();
		}
		Player firstPlayer = new Player(null, fen.turn, true, fen.playerPieces);
		Player opponent = new Player(null, fen.turn.getOppositeColor() , false, fen.opponentPieces);
		
		// TODO Fix Problem here
		board.addPieces(fen.playerPieces);
		board.addPieces(fen.opponentPieces);		
		match = new Match(gameType, firstPlayer, opponent);
		// TODO continue implementation of newMatch()
	}

	public static void changeGame(GameType gameType) {

		if (Draughts.gameType != gameType) {
			proposeToSaveBeforeAction();
			Draughts.gameType = gameType;
			board = new Board(gameType);
			draughtsGUI.changeBoard(board);
		}

	}

	/**
	 * Loads a draughts match from an existing file. <br>
	 */
	public static void loadFile() {
		
		proposeToSaveBeforeAction();
		File file = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter pdnFilter = new FileNameExtensionFilter("pdn files (*.pdn)", "pdn");
		chooser.addChoosableFileFilter(pdnFilter);
		int returnValue = chooser.showOpenDialog(null);
		if ( returnValue == JFileChooser.APPROVE_OPTION ) {
			file = chooser.getSelectedFile();
		}
		PDN pdnGame = PDN.parsePDNFile(file);
		if (pdnGame != null) {
			startGUI(pdnGame);
		}
		
	}

	/**
	 * Saves a draughts match into a file. <br>
	 * <i>File overwriting is not allowed. </i>
	 */
	public static void saveFile() {

		if (match != null) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter pdnFilter = new FileNameExtensionFilter("pdn files (*.pdn)", "pdn");
			chooser.addChoosableFileFilter(pdnFilter);
			chooser.setFileFilter(pdnFilter);
			// No File overwriting...
			File destinationFile = new File(chooser.getSelectedFile().getAbsolutePath());
			while (destinationFile.exists()) {
				JOptionPane.showMessageDialog(
						null, 
						"File already exists. \n\rPlease, choose another file. ", 
						"Warning!", JOptionPane.WARNING_MESSAGE
						);
				destinationFile = new File(chooser.getSelectedFile().getAbsolutePath());
			}
			PrintWriter writer;
			try {
				writer = new PrintWriter(destinationFile);
				writer.write(match.toString());
				writer.close();
				JOptionPane.showMessageDialog(
						null, 
						"File successfully saved. ", 
						"Success!", 
						JOptionPane.INFORMATION_MESSAGE
						);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(
						null, 
						"A problem occured while saving the file.  \n\rPlease try again. ", 
						"Error!", JOptionPane.ERROR_MESSAGE
						);
			}
		} else {
			JOptionPane.showMessageDialog(
					null, 
					"There is no file to save at this time. ", 
					"Information",
					JOptionPane.INFORMATION_MESSAGE
					);
		}
	}

	public static void quit() {
		proposeToSaveBeforeAction();
		System.exit(0);
	}

	public static void undoMove() {
		// TODO Auto-generated method stub
	}

	public static void displayLabels(boolean displayLabels) {
		draughtsGUI.displayLabels(displayLabels);
		
	}

	public static void changeFrameSize(MainFrameSize ratio) {
		draughtsGUI.setFrameSize(ratio);
	}

	public static void help() {
		// TODO Auto-generated method stub
	}

	public static void about() {
		JOptionPane.showMessageDialog(
				null, 
				"Description of the sofware goes here. ", "About Draughts",
				JOptionPane.INFORMATION_MESSAGE
				);
	}

	private static void proposeToSaveBeforeAction() {

		if (match != null) {
			
			int returnValue = JOptionPane.showConfirmDialog(
					null, 
					"A match is currently ongoing. \r\nWould you like to save it first ?", "Warning!",
					JOptionPane.YES_NO_OPTION
					);
			 if ( returnValue == JOptionPane.OK_OPTION ) { saveFile(); }

		}

	}

}
