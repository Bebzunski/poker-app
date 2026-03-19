package org.bebzunski.poker.pojo;

import java.util.Comparator;

import org.bebzunski.poker.enums.Color;
import org.bebzunski.poker.enums.Face;

import static java.util.Comparator.reverseOrder;

public record Card(Face face, Color color) implements Comparable<Card> {

	private static final Comparator<Card> FROM_HIGHEST_FACE = Comparator.comparing(Card::face, reverseOrder()).thenComparing(Card::color);

	public static Card of(Face face, Color color) {
		return new Card(face, color);
	}

	public static Card diamondOf(Face face) {
		return new Card(face, Color.DIAMOND);
	}

	public static Card spadeOf(Face face) {
		return new Card(face, Color.SPADE);
	}

	public static Card clubOf(Face face) {
		return new Card(face, Color.CLUB);
	}

	public static Card heartOf(Face face) {
		return new Card(face, Color.HEART);
	}

	@Override
	public String toString() {
		return "|%s|".formatted(getId());
	}

	public String getId() {
		return "%s%s".formatted(face.getSymbol(), color.getSymbol());
	}

	@Override
	public int compareTo(Card o) {
		return FROM_HIGHEST_FACE.compare(this, o);
	}
}
