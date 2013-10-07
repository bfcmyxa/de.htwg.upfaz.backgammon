package de.htwg.upfaz.backgammon.entities;

import de.htwg.upfaz.backgammon.entities.Field;
import de.htwg.upfaz.backgammon.entities.Player;
import junit.framework.TestCase;

/**
 * Class Player
 */
public class PlayerTest extends TestCase {

	IPlayer player;
	IField f = new Field(5);

	public void setUp() {
		player = new Player(0);
	}

//	public void testgetPips() {
//		player.setPips(100);
//		assertEquals(100, player.getPips());
//		player.setPips(0);
//		assertEquals(0, player.getPips());
//	}

	public void testgetColor() {
		player.setColor(0);
		assertEquals(0, player.getColor());
		player.setColor(1);
		assertEquals(1, player.getColor());
	}


}
