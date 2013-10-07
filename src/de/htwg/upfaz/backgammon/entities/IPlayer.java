package de.htwg.upfaz.backgammon.entities;

public interface IPlayer {

	/**
	 * Set the value of color
	 * 
	 * @param newVar
	 *            the new value of color
	 */
	public abstract void setColor(int newVar);

	/**
	 * Get the value of color
	 * 
	 * @return the value of color
	 */
	public abstract int getColor();

	public abstract String toString();

}