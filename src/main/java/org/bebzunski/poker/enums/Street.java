package org.bebzunski.poker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Street {

	PRE_FLOP(0),
	FLOP(3),
	TURN(1),
	RIVER(1);

	private final int nrCardsToDeal;

	public boolean isLaterThan(Street street) {
		return this.compareTo(street) > 0;
	}
}
