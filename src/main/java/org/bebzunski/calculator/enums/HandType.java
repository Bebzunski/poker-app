package org.bebzunski.calculator.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum HandType {

	HIGH_CARD(1, 1, 1, 1, 1),
	ONE_PAIR(2, 1, 1, 1),
	TWO_PAIR(2, 2, 1),
	THREE_OF_KIND(3, 1, 1),
	STRAIGHT(1, 1, 1, 1, 1),
	FLUSH(1, 1, 1, 1, 1),
	FULL_HOUSE(3, 2),
	QUADS(4, 1),
	STRAIGHT_FLUSH(1, 1, 1, 1, 1);

	private final List<Integer> facesStructure;

	HandType(Integer... facesStructure) {
		this.facesStructure = Arrays.asList(facesStructure);
	}
}
