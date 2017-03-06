package draughts.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import draughts.controller.Draughts;
import draughts.model.GameType;

/**
 * part of the VIEW component in a MVC pattern for this application
 * MenuBar bar implementation using an enum to create a singleton pattern
 */
enum MenuBar {
	
	SINGLETON;
	
	private final JMenuBar menuBar;
	
	private final JMenu fileMenu;
	private final JMenuItem newMatch;
	private final JMenu selectGameType;
	private final JMenuItem loadMatch;
	private final JMenuItem saveMatch;
	private final JMenuItem quitGame;
	
	private final JMenu editMenu;
	private final JMenuItem undoMove;
	
	private final JMenu viewMenu;
	private final JMenuItem toggleDisplayLabels;
	private boolean displayLabels = false;
	private final JMenuItem minimalSize;
	private final JMenuItem halfSize;
	private final JMenuItem normalSize;
	private final JMenuItem fullSize;
	
	private final JMenu helpMenu;
	private final JMenuItem help;
	private final JMenuItem about; 
	
	private MenuBar() {
		
		/*
		 *  File menu
		 */
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		// New Game sub-menu
		newMatch = new JMenuItem("New Game", KeyEvent.VK_N);
		newMatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		newMatch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.newMatch(); }});
		// Select Game Type menu
		selectGameType = new JMenu("Select a Game type");
		selectGameType.setMnemonic(KeyEvent.VK_G);
		// Dynamically add game-types to sub-menu
		int i = 0;
		JMenuItem selectType[] = new JMenuItem[GameType.values().length];
		for ( GameType gameType : GameType.values() ) {
			selectType[i] = new JMenuItem(gameType.getDesignationAndNumber(), KeyEvent.VK_0 + i);
			selectType[i].addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.changeGame(gameType); }});
			selectGameType.add(selectType[i]);
			i++;
		}
		// Load Game sub-menu
		loadMatch = new JMenuItem("Open A File (pdn format)", KeyEvent.VK_O);
		loadMatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		loadMatch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.loadFile(); }});
		// Save Game sub-menu
		saveMatch = new JMenuItem("Save Current Match", KeyEvent.VK_S);
		saveMatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveMatch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.saveFile(); }});
		// Save Game sub-menu
		quitGame = new JMenuItem("Quit", KeyEvent.VK_Q);
		quitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		quitGame.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.quit(); }});
		// Adds above sub-menus to File menu
		fileMenu.add(newMatch);
		fileMenu.add(selectGameType);
		fileMenu.addSeparator();
		fileMenu.add(loadMatch);
		fileMenu.add(saveMatch);
		fileMenu.addSeparator();
		fileMenu.add(quitGame);
		
		/*
		 *  Edit menu
		 */
		editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		// Undo Move sub-menu
		undoMove = new JMenuItem("Undo Last Move", KeyEvent.VK_Z);
		undoMove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		undoMove.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.undoMove(); }});
		// Adds above sub-menus to Edit menu
		editMenu.add(undoMove);

		/*
		 *  View menu
		 */
		viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		// displayLabelsMenu
		toggleDisplayLabels = new JMenuItem(String.format("%s square labels", displayLabels ? "Hide" : "Show"), KeyEvent.VK_L);
		toggleDisplayLabels.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		toggleDisplayLabels.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				displayLabels = !displayLabels;
				toggleDisplayLabels.setText(String.format("%s square labels", displayLabels ? "Hide" : "Show"));
				Draughts.displayLabels(displayLabels); }});
		// setHalfSizeMenu
		minimalSize = new JMenuItem("Set size to minimal size (0)", KeyEvent.VK_0);
		minimalSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_DOWN_MASK));
		minimalSize.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.changeFrameSize(MainFrameSize.MINIMAL); }});
		// setHalfSizeMenu
		halfSize = new JMenuItem("Set size to 1/2 of screen size (1)", KeyEvent.VK_1);
		halfSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		halfSize.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.changeFrameSize(MainFrameSize.HALF); }});
		// setNormalSizeMenu
		normalSize = new JMenuItem("Set size to 3/4 of screen size (2)", KeyEvent.VK_2);
		normalSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		normalSize.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.changeFrameSize(MainFrameSize.NORMAL); }});
		// setMaxSizeMenu
		fullSize = new JMenuItem("Set size to screen size (3)", KeyEvent.VK_3);
		fullSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		fullSize.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.changeFrameSize(MainFrameSize.FULL); }});		
		// Adds above sub-menus to View menu
		viewMenu.add(toggleDisplayLabels);
		viewMenu.addSeparator();
		viewMenu.add(minimalSize);
		viewMenu.add(halfSize);
		viewMenu.add(normalSize);
		viewMenu.add(fullSize);
		
		
		/*
		 *  Help menu
		 */
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		// Help sub-menu
		help = new JMenuItem("Help", KeyEvent.VK_H);
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		help.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.help(); }});
		// About sub-menu
		about = new JMenuItem("About", KeyEvent.VK_A);
		about.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent ae) { 
				Draughts.about(); }});
		// Adds above sub-menus to Help menu
		helpMenu.add(help);
		helpMenu.add(about);
		
		// Adds above menus to the menuBar
		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);

	}
	
	public JMenuBar get() {
		return menuBar;
	}

	public Dimension getSize() {
		return menuBar.getSize();
	}
	
}
