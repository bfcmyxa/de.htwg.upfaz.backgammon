package de.htwg.upfaz.backgammon.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import de.htwg.upfaz.backgammon.controller.IGame;
import de.htwg.upfaz.backgammon.gui.Constances;
import de.htwg.util.observer.IObserver;

public class BackgammonFrame extends JFrame implements MouseListener,
		MouseMotionListener, IObserver {

	private static final long serialVersionUID = 1L;

	private IGame currentGame;
	private StatusPanel statusPanel;
	private String status = "";
	private Integer result = -1;

	public BackgammonFrame(IGame gm) {
		Container pane;
		DrawTest test;
		currentGame = gm;

		setTitle("Upfaz  backgammon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constances.DEFAULT_X, Constances.DEFAULT_Y);
		setResizable(false);
		pane = getContentPane();
		pane.setLayout(new BorderLayout());

		// bp = new BackgroundPanel();
		// pane.add(bp);
		// dices = new Dices(currentGame.getJumps());
		// pane.add(dices);
		// ds = new DrawStones(currentGame.getGameMap());
		// pane.add(ds);

		test = new DrawTest(currentGame);
		pane.add(test);
		statusPanel = new StatusPanel();
		pane.add(statusPanel, BorderLayout.SOUTH);

		addMouseListener(this);
		addMouseMotionListener(this);

		setVisible(true);
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		setResult(getClickedField(x, y));
		mouseHandler(e);
	}

	private Integer getLeft(int x) {
		Integer left = null;
		Integer candidate = 0;

		for (int i = Constances.LEFT_BORDER; i <= Constances.LEFT_CENTER_BORDER; i += Constances.FIELD_SIZE_PXL) {
			left = calcLeft(i, x, candidate);
			if (left != null) {
				break;
			}
			candidate++;
		}
		if (left == null) {
			for (int i = Constances.RIGHT_CENTER_BORDER; i <= Constances.RIGHT_BORDER; i += Constances.FIELD_SIZE_PXL) {
				left = calcLeft(i, x, candidate);
				if (left != null) {
					break;
				}
				candidate++;
			}
		}

		return left;

	}

	private Integer getClickedField(int x, int y) {

		Integer left = getLeft(x);
		Integer output = -1;

		if (y <= Constances.UPPER_CENTER_BORDER && y >= Constances.UPPER_BORDER
				&& left != null) {
			output = 11 - left;
		} else if (y >= Constances.DOWN_CENTER_BORDER
				&& y <= Constances.DOWN_BORDER && left != null) {
			output = 12 + left;
		}

		output = getClickedEndfield(output, x, y);

		return output;
	}

	private Integer getClickedEndfield(Integer oldOutput, int x, int y) {

		Integer newOutput = oldOutput;

		if (x > Constances.LEFT_OUT_BORDER && y < Constances.RIGHT_OUT_BORDER) {
			if (y < 340) {
				newOutput = Constances.FIELD_END_BLACK;
			} else {
				newOutput = Constances.FIELD_END_WHITE;
			}
		}
		return newOutput;
	}

	private Integer calcLeft(int i, int x, Integer candidate) {
		Integer returnValue = null;

		if (x >= i && x <= (i + Constances.FIELD_STEP)) {
			returnValue = candidate;
		}

		return returnValue;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseHandler(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseHandler(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// do nothing

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// do nothing

	}

	@Override
	public void update() {

		statusPanel.setText(status);
		repaint();
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer newResult) {
		result = newResult;
	}

	public Integer getTargetWhileEatenWhite(IGame currentGame) {
		currentGame.setCurrentMethodName("getTargetWhileEatenWhite");
		Integer targetNumber = getResult();
		try {

			while (targetNumber == -1 || targetNumber > 6) {
				Thread.sleep(Constances.TIME_TO_SLEEP_IN_MS);
				targetNumber = getResult();
			}
		} catch (Exception e) {
			System.out.println("getTargetWhileEatenWhite");
		}

		return targetNumber;
	}

	public Integer getTargetWhileEatenBlack(IGame currentGame) {
		currentGame.setCurrentMethodName("getTargetWhileEatenBlack");
		Integer targetNumber = getResult();
		try {

			while (targetNumber >= Constances.FIELD_EATEN_BLACK
					|| targetNumber < 18) {
				Thread.sleep(Constances.TIME_TO_SLEEP_IN_MS);
				targetNumber = getResult();
			}
		} catch (Exception e) {
			System.out.println("getTargetWhileEatenBlack");
		}

		return targetNumber;

	}

	public void getStartNumber(IGame currentGame) {
		currentGame.setCurrentMethodName("getStartNumber");
		Integer startNumber = getResult();
		try {

			while (startNumber < 0
					|| startNumber >= Constances.FIELD_EATEN_BLACK) {
				Thread.sleep(Constances.TIME_TO_SLEEP_IN_MS);
				startNumber = getResult();
			}
		} catch (Exception e) {
			System.out.println("getStartNumber");
		}

		if (currentGame.checkStartNumber(startNumber)) {
			currentGame.setStartNumber(startNumber);
		} else {
			System.out
					.println("You can't move this piece or there is no pieces");
			setResult(-1);
			getStartNumber(currentGame);
		}
	}

	private Integer getTargetResult(Integer oldTarget) {

		Integer newTarget = oldTarget;
		try {

			while (newTarget < 0
					|| newTarget == Constances.FIELD_EATEN_BLACK
					|| newTarget == Constances.FIELD_EATEN_WHITE
					|| newTarget == currentGame.getStartNumber()
					|| ((newTarget == Constances.FIELD_END_BLACK || newTarget == Constances.FIELD_END_WHITE) && !currentGame
							.isEndPhase())) {
				Thread.sleep(Constances.TIME_TO_SLEEP_IN_MS);
				newTarget = getResult();
			}
		} catch (Exception e) {
			System.out.println("getTargetResult: " + e);
		}
		return newTarget;
	}

	private void checkColorProblem(Integer targetNumber) {
		if (!currentGame.getGameMap()[targetNumber].isJumpable(currentGame
				.getCurrentPlayer().getColor())) {
			System.out.println("Can't jump this Field (Color problem)");
			setResult(-1);
			getEndNumber(currentGame);
		}
	}

	private void checkTargetValidness(Integer targetNumber) {
		if (currentGame.checkNormalEndTarget(targetNumber)) {
			currentGame.setTargetNumber(targetNumber);
		} else {
			setResult(-1);
			getEndNumber(currentGame);
		}
	}

	private void checkEndphaseBlack(Integer targetNumber){
		// endphase black
		if (currentGame.checkEndphaseBlackTarget(targetNumber)) {
			currentGame.setTargetNumber(targetNumber);
		} else {
			setResult(-1);
			getEndNumber(currentGame);
		}
	}
	
	private void checkEndphaseWhite(Integer targetNumber){
		// endphase white
		if (currentGame.checkEndphaseWhiteTarget(targetNumber)) {
			currentGame.setTargetNumber(targetNumber);
		} else {
			setResult(-1);
			getEndNumber(currentGame);
		}
	}
	
	public void getEndNumber(IGame currentGame) {
		currentGame.setCurrentMethodName("getEndNumber");
		Integer targetNumber = getResult();
		try {

			targetNumber = getTargetResult(targetNumber);

			if (targetNumber >= 0
					&& targetNumber < Constances.FIELD_EATEN_BLACK) {

				checkColorProblem(targetNumber);
				checkTargetValidness(targetNumber);

			} else if (targetNumber == Constances.FIELD_END_BLACK) {
				
				checkEndphaseBlack(targetNumber);
				
			} else if (targetNumber == Constances.FIELD_END_WHITE) {
				
				checkEndphaseWhite(targetNumber);
			}

		} catch (Exception e) {
			System.out.println("getEndNumber");
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseHandler(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseHandler(e);
	}

	private void mouseHandler(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		status = "x = " + x + ", y = " + y + "\t start = "
				+ currentGame.getStartNumber() + ", target = "
				+ currentGame.getTargetNumber() + ", result = " + getResult()
				+ "; Current player: " + currentGame.getCurrentPlayer() + "; "
				+ currentGame.printJumpsString() + "; Method: "
				+ currentGame.getCurrentMethodName();
		update();
	}

	public void noMovesDialog() {
		JOptionPane.showMessageDialog(this, "No possible moves available",
				"Bad luck", JOptionPane.WARNING_MESSAGE);
	}

	public void winnerDialog() {
		JOptionPane.showMessageDialog(this, currentGame.getCurrentPlayer()
				+ " is the winner!", "Congratulations!",
				JOptionPane.PLAIN_MESSAGE);
	}

}
