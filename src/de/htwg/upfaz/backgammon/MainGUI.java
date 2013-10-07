package de.htwg.upfaz.backgammon;

import java.util.Scanner;

import de.htwg.upfaz.backgammon.controller.Game;
import de.htwg.upfaz.backgammon.controller.IGame;
import de.htwg.upfaz.backgammon.gui.BackgammonFrame;
import de.htwg.upfaz.backgammon.gui.GameWithGui;
import de.htwg.upfaz.backgammon.gui.GameWithTui;
import de.htwg.upfaz.backgammon.gui.Tui;

/**
 * Class GUI
 */
public final class MainGUI {

	private static BackgammonFrame bf;
	private static Tui tui;
	private static Integer choiceNumber = 0;
	private static int curPl = 1;
	private static IGame currentGame;

	private MainGUI() {
	}

	public static void main(String[] args) {
		currentGame = new Game();
		System.out.println("Welcome to upfaz backgammon.");

		chooseUI();

		if (getChoiceNumber() == 1) {

			playGameWithTui((Game) currentGame);

		} else {
			playGameWithGui((Game) currentGame);
		}

	}

	private static void setChoiceNumber(Integer newChoice) {
		choiceNumber = newChoice;
	}

	private static Integer getChoiceNumber() {
		return choiceNumber;
	}

	private static void setCurPl(int newCurPl) {
		curPl = newCurPl;
	}

	private static int getCurPl() {
		return curPl;
	}

	private static void chooseUI() {
		String choice = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Choose the UI:");
				System.out.println("\t1.Tui");
				System.out.println("\t2.Gui");
				choice = scanner.nextLine();
				setChoiceNumber(Integer.valueOf(choice));
				if (choice.matches("[1-2]")) {
					break;
				} else {
					System.out.println("Input doesn't match!");
				}
			} catch (Exception e) {
				System.err.println("Exception." + e.getMessage());
			}
		}

	}

	private static void playGameWithTui(Game currentGame) {

		GameWithTui tuiGame = new GameWithTui();
		tui = new Tui(currentGame);
		currentGame.addObserver(tui);
		currentGame.notifyObservers();
		while (currentGame.getWinner() == 0) {

			if (getCurPl() == 1) {
				tuiGame.playTurn(currentGame, currentGame.getPlayer1(), tui);
				setCurPl(2);
			} else {
				tuiGame.playTurn(currentGame, currentGame.getPlayer2(), tui);
				setCurPl(1);
			}
		}

		System.out.println(currentGame.getCurrentPlayer() + " is the winner!");
	}

	private static void playGameWithGui(Game currentGame) {

		GameWithGui guiGame = new GameWithGui();
		bf = new BackgammonFrame(currentGame);
		currentGame.addObserver(bf);
		currentGame.notifyObservers();
		while (currentGame.getWinner() == 0) {

			if (getCurPl() == 1) {
				guiGame.playTurn(currentGame, currentGame.getPlayer1(), bf);
				setCurPl(2);
			} else {
				guiGame.playTurn(currentGame, currentGame.getPlayer2(), bf);
				setCurPl(1);
			}
		}

		bf.winnerDialog();

	}

}
