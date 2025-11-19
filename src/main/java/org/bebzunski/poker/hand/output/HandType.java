package org.bebzunski.poker.hand.output;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum HandType {

	HIGH_CARD(false, 1, 1, 1, 1, 1),
	ONE_PAIR(true, 2, 1, 1, 1),
	TWO_PAIR(true, 2, 2, 1),
	THREE_OF_KIND(true, 3, 1, 1),
	STRAIGHT(false, 1),
	FLUSH(false, 1, 1, 1, 1, 1),
	FULL_HOUSE(true, 3, 2),
	QUADS(true, 4, 1),
	STRAIGHT_FLUSH(false, 1);

	private final boolean pairType;
	private final List<Integer> facesStructure;

	HandType(boolean pairType, Integer... facesStructure) {
		this.pairType = pairType;
		this.facesStructure = Arrays.asList(facesStructure);
	}
}
