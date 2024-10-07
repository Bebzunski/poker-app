package org.bebzunski.calculator.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Range;
import org.bebzunski.calculator.pojo.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Face {

	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("T"),
	JACK("J"),
	QUEEN("Q"),
	KING("K"),
	ACE("A");

	private final String symbol;

	public Card s() {
		return new Card(this, Color.SPADE);
	}

	public Card d() {
		return new Card(this, Color.DIAMOND);
	}

	public Card c() {
		return new Card(this, Color.CLUB);
	}

	public Card h() {
		return new Card(this, Color.HEART);
	}

	public static Set<Face> getRange(int min, int max) {
		return Arrays.stream(values()).filter(v -> Range.of(min, max).contains(v.ordinal())).collect(Collectors.toSet());
	}
}
