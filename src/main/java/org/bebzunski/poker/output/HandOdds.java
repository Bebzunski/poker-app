package org.bebzunski.poker.output;

import java.math.BigDecimal;

import org.bebzunski.poker.hand.output.HandType;

public record HandOdds(HandType handType, BigDecimal odds) {

	@Override
	public String toString() {
		return "%s=%s".formatted(handType, odds.toString()) + "%";
	}
}
