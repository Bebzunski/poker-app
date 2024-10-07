package org.bebzunski.calculator.output;

import java.math.BigDecimal;

import org.bebzunski.calculator.hand.output.HandType;

public record HandOdds(HandType handType, BigDecimal odds) {

	@Override
	public String toString() {
		return "%s=%s".formatted(handType, odds.toString()) + "%";
	}
}
