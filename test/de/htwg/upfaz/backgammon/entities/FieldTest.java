package de.htwg.upfaz.backgammon.entities;

import de.htwg.upfaz.backgammon.entities.Field;
import junit.framework.TestCase;


public class FieldTest extends TestCase {

	IField field;

	public void setUp() {
		field = new Field(0);
	}

	public void testgetFieldNr() {
		field.setFieldNr(1);
		assertEquals(1, field.getFieldNr());
		field.setFieldNr(0);
		assertEquals(0, field.getFieldNr());
	}

	public void testgetNumberStones() {
		field.setNumberStones(1);
		assertEquals(1, field.getNumberStones());
		field.setNumberStones(0);
		assertEquals(0, field.getNumberStones());
	}



}
