package draughts.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * part of the CONTROLLER component in a MVC pattern for this application
 */
public class Match {
	
	private Player currentPlayer;

	private PDN gameData;
	public Player firstplayer;
	public Player opponent;
	public Vector<Move> moves = null;
	
	public Match(GameType gameType, Player firstplayer, Player opponent) {
		
		gameData = new PDN(gameType);
		gameData.event = "Match on a computer using a great software. ";
		gameData.date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		this.currentPlayer = firstplayer;
		this.firstplayer = firstplayer;
		this.opponent = opponent;
		
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public PDN getGameData() {
		return gameData;
	}
	
	public void togglePlayers() {
		currentPlayer = ( currentPlayer == firstplayer ) ? opponent : firstplayer;
	}

	@Override
	public String toString() {
		return gameData.toString();
	}
	
}
