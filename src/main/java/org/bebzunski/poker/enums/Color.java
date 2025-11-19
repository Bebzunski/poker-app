package org.bebzunski.poker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {

	HEART("h"),
	DIAMOND("d"),
	SPADE("s"),
	CLUB("c");

	private final String symbol;
}
