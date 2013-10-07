package de.htwg.upfaz.backgammon.entities;

/**
 * Class Field
 */
public class Field implements IField {

	//
	// Fields
	//

	private int fieldNr;
	private int numberStones;
	private int stoneColor;

	/* for to string method */
	private static final int TEN_STONES = 10;

	//
	// Constructors
	//
	public Field(int fn) {
		fieldNr = fn;
		numberStones = 0;
		stoneColor = -1;
	};

	@Override
	public int getFieldNr() {
		return fieldNr;
	}

	@Override
	public void setFieldNr(int newFieldNr) {
		fieldNr = newFieldNr;
	}

	@Override
	public void setNumberStones(int newVar) {
		numberStones = newVar;
		if (newVar == 0) {
			stoneColor = -1;
		}
	}

	@Override
	public int getNumberStones() {
		return numberStones;
	}

	@Override
	public int getStoneColor() {
		return stoneColor;
	}

	@Override
	public void setStoneColor(int newColor) {
		stoneColor = newColor;
	}

	@Override
	public boolean isJumpable(int color) {
		if (getNumberStones() <= 1 || color == getStoneColor()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isNotJumpable(int color) {
		if (getNumberStones() > 1 && color != getStoneColor()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return numberStones == 0;
	}

	@Override
	public String toString() {

		StringBuilder b = new StringBuilder();
		if (getNumberStones() == 0) {
			return "   ";
		}
		if (getNumberStones() < TEN_STONES) {
			b.append("0");
		}
		b.append(getNumberStones());
		if (getStoneColor() == 0) {
			b.append("w");
		} else {
			b.append("b");
		}

		return b.toString();
	}
}
