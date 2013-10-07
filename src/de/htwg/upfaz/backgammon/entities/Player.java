package de.htwg.upfaz.backgammon.entities;

/**
 * Class Player
 */
public class Player implements IPlayer {

	private int color;

	public Player(int clr) {
		color = clr;
	};

//	/**
//	 * Set the value of pips
//	 * 
//	 * @param newVar
//	 *            the new value of pips
//	 */
//	public void setPips(int newVar) {
//		pips = newVar;
//	}
//
//	/**
//	 * Get the value of pips
//	 * 
//	 * @return the value of pips
//	 */
//	public int getPips() {
//		return pips;
//	}

	/* (non-Javadoc)
	 * @see de.htwg.upfaz.backgammon.entities.IPlayer#setColor(int)
	 */
	@Override
	public void setColor(int newVar) {
		color = newVar;
	}

	/* (non-Javadoc)
	 * @see de.htwg.upfaz.backgammon.entities.IPlayer#getColor()
	 */
	@Override
	public int getColor() {
		return color;
	}
	
	/* (non-Javadoc)
	 * @see de.htwg.upfaz.backgammon.entities.IPlayer#toString()
	 */
	@Override
	public String toString(){
		if (color == 0){
			return  "White";
		} else {
			return "Black";
		}
	}

}
