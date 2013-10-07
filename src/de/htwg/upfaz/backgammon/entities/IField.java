package de.htwg.upfaz.backgammon.entities;

public interface IField {

	/**
	 * Get the value of fieldNr
	 * 
	 * @return the value of fieldNr
	 */
	public abstract int getFieldNr();

	public abstract void setFieldNr(int newFieldNr);

	/**
	 * Set the value of numberStones
	 * 
	 * @param newVar
	 *            the new value of numberStones
	 */
	public abstract void setNumberStones(int newVar);

	/**
	 * Get the value of numberStones
	 * 
	 * @return the value of numberStones
	 */
	public abstract int getNumberStones();

	public abstract int getStoneColor();

	public abstract void setStoneColor(int newColor);

	/**
	 * @return boolean
	 * @param color
	 */
	public abstract boolean isJumpable(int color);

	public abstract boolean isNotJumpable(int color);

	public abstract boolean isEmpty();

	public abstract String toString();

}