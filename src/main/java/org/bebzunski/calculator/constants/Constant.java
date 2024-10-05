package org.bebzunski.calculator.constants;

import java.math.BigDecimal;
import java.util.Set;

import org.bebzunski.calculator.enums.Face;

public class Constant {

	public static final int FIVE_CARDS = 5;
	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
	public static final Set<Face> LOW_STRAIGHT = Set.of(Face.ACE, Face.TWO, Face.THREE, Face.FOUR, Face.FIVE);
}
