package de.htwg.upfaz.backgammon.controller;

import de.htwg.upfaz.backgammon.entities.Field;
import de.htwg.upfaz.backgammon.entities.IField;
import de.htwg.upfaz.backgammon.entities.IPlayer;
import de.htwg.util.observer.IObservable;

public interface IGame extends IObservable{

	
	public abstract void setGameMap(Field[] newGameMap);


	public abstract Field[] getGameMap();

	public abstract Integer[] rollTheDice();

	public abstract IField[] eatStone(Field[] gameMap, IPlayer plr,
			Integer startNumber, Integer targetNumber);

	public abstract IField[] moveStone(Field[] gameMap, IPlayer plr,
			Integer startNumber, Integer targetNumber);

	public abstract boolean checkForWinner(IField[] gameMap);

	public abstract IField[] takeOutStone(Field[] gameMap, IPlayer plr,
			Integer startNumber, Integer targetNumber);

	public abstract Field[] doSomethingWithStones(Field[] gm, IPlayer plr,
			Integer startNumber, Integer targetNumber, boolean endPhase);

	public abstract Integer getStartNumber();

	public abstract void setStartNumber(Integer number);

	public abstract Integer getTargetNumber();

	public abstract void setTargetNumber(Integer number);

	public abstract boolean checkDirection(IPlayer plr, Integer start,
			Integer target);

	public abstract void renewJumps(Integer start, Integer target);

	public abstract int calcStoneInEndPhase(IPlayer plr, IField[] gm);

	public abstract void setJumps(Integer[] newJumps);

	public abstract Integer[] getJumps();

	public abstract int getWinner();

	public abstract IPlayer getPlayer1();

	public abstract IPlayer getPlayer2();

	public abstract IPlayer getCurrentPlayer();

	public abstract void setCurrentPlayer(IPlayer currentPlayer);

	public abstract String getStatus();

	public abstract void setStatus(final String s);

	public abstract boolean checkStartNumber(Integer number);

	public abstract void printJumpsStatus(Integer[] jumps);

	public abstract String printJumpsString();

	public abstract Integer[] getJumpsT();

	public abstract void setJumpsT(Integer[] j);

	public abstract boolean eatenWhiteCheck(Integer target);

	public abstract boolean checkNormalEndTarget(Integer newTarget);

	public abstract boolean checkEndphaseWhiteTarget(Integer newTarget);

	public abstract boolean checkEndphaseBlackTarget(Integer newTarget);

	public abstract boolean checkStartValidnessLoop();

	public abstract String getCurrentMethodName();

	public abstract void setCurrentMethodName(String newName);

	public abstract boolean isEndPhase();

	public abstract void setEndPhase(boolean endPhase);

	public abstract void setWinner(int winner);

	public abstract int getTurnsNumber();

	public abstract void checkEndPhase();

	public abstract boolean checkIfMovesPossible();

	public abstract int automaticTakeOut();

}