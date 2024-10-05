package org.bebzunski.calculator.pojo;

import java.math.BigDecimal;

import org.bebzunski.calculator.enums.HandType;

public record HandOdds(HandType handType, BigDecimal odds) {

	@Override
	public String toString() {
		return "%s=%s".formatted(handType, odds.toString()) + "%";
	}
}
