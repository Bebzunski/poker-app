package org.bebzunski.calculator.it;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionHelper {

	public static void assertOdds(BigDecimal valueToAssert, String expected) {
		assertOdds(valueToAssert, expected, "1.00");
	}

	public static void assertOdds(BigDecimal odds, String expected, String deviation) {
		BigDecimal exactResult = new BigDecimal(expected);
		BigDecimal deviationBD = new BigDecimal(deviation);
		assertThat(odds).isBetween(exactResult.subtract(deviationBD), exactResult.add(deviationBD));
	}
}
