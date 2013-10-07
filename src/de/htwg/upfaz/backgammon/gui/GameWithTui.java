package de.htwg.upfaz.backgammon.gui;

import de.htwg.upfaz.backgammon.controller.IGame;
import de.htwg.upfaz.backgammon.entities.Field;
import de.htwg.upfaz.backgammon.entities.IPlayer;

/**
 * Class Game
 */
public class GameWithTui {

	/**
	 * @return Field[]
	 * @param gameMap
	 */
	public void playTurn(IGame currentGame, IPlayer plr, Tui tui) {

		currentGame.setCurrentPlayer(plr);
		currentGame.setEndPhase(false);
		currentGame.checkEndPhase();
		currentGame.setStatus("");
		currentGame.setStatus("Player " + plr.toString() + ", it's your Turn!");
		currentGame.setJumps(currentGame.rollTheDice());

		currentGame.setJumpsT(currentGame.getJumps());

		for (int index = currentGame.automaticTakeOut(); index < currentGame.getTurnsNumber(); index++) {

			currentGame.checkEndPhase();
			if (!currentGame.checkIfMovesPossible()) {
				currentGame.setStatus("No moves available");
				return;
			}

			

			tui.printField(currentGame.getGameMap());

			if (!getStartAndTargetNumbers(currentGame,
					currentGame.getGameMap(), plr, tui)) {
				index--;
				continue;
			}

			currentGame.setGameMap(currentGame.doSomethingWithStones(
					currentGame.getGameMap(), plr,
					currentGame.getStartNumber(),
					currentGame.getTargetNumber(), currentGame.isEndPhase()));
			currentGame.renewJumps(currentGame.getStartNumber(),
					currentGame.getTargetNumber());
			currentGame.setStartNumber(0);
			currentGame.setTargetNumber(0);

			// check for winner
			if (currentGame.checkForWinner(currentGame.getGameMap())) {
				currentGame.setStatus("Player " + plr.toString()
						+ " is the winner!");
				currentGame.setWinner(plr.getColor() + 1);
				return;
			}
			currentGame.printJumpsStatus(currentGame.getJumps());
		}
	}

	public boolean getStartAndTargetNumbers(IGame currentGame, Field[] gm,
			IPlayer plr, Tui tui) {

		currentGame.setGameMap(gm);
		if (plr.getColor() == 0
				&& currentGame.getGameMap()[25].getNumberStones() > 0) {

			// TO ADD: check if is possible to play turn
			currentGame.setStartNumber(25);
			// get targetNumber
			currentGame.setTargetNumber(tui
					.getTargetWhileEatenWhite(currentGame));

		} else if (plr.getColor() == 1
				&& currentGame.getGameMap()[24].getNumberStones() > 0) {

			currentGame.setStartNumber(24);

			// get targetNumber
			currentGame.setTargetNumber(tui.getTargetWhileEatenBlack(
					currentGame.getGameMap(), plr, currentGame.getJumps()));

		} else if (currentGame.isEndPhase()) {
			tui.getStartNumber(currentGame);
			tui.getEndTarget(currentGame);

		} else {

			// get startNumber

			tui.getStartNumber(currentGame);

			if (!currentGame.checkStartValidnessLoop()) {
				currentGame.setStatus("You can not play with this stone!");
				return false;
			}

			// get targetNumber
			tui.getEndTarget(currentGame);

		}

		// check direction
		if (currentGame.getTargetNumber() < 24
				&& !currentGame.checkDirection(plr,
						currentGame.getStartNumber(),
						currentGame.getTargetNumber())) {
			return false;
		}
		currentGame.setStatus("Setting startNumber to "
				+ currentGame.getStartNumber() + " and targetNumber to "
				+ currentGame.getTargetNumber());
		return true;

	}

}
