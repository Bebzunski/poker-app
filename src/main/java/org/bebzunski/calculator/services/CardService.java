package org.bebzunski.calculator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bebzunski.calculator.enums.Color;
import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.pojo.Card;

public class CardService {

	public static List<Card> allCards() {
		List<Card> allCards = new ArrayList<>();
		for (Color color : Color.values()) {
			Arrays.stream(Face.values()).forEach(face -> allCards.add(new Card(face, color)));
		}
		return allCards;
	}

	public static List<Card> getCards(Face minFace) {
		List<Card> allCards = new ArrayList<>();
		for (Color color : Color.values()) {
			Arrays.stream(Face.values()).filter(face -> face.compareTo(minFace) >= 0).forEach(face -> allCards.add(new Card(face, color)));
		}
		return allCards;
	}

	public static List<Card> merge(Collection<Card> cards1, Collection<Card> cards2) {
		Collection<Card> c1 = Optional.ofNullable(cards1).orElse(List.of());
		Collection<Card> c2 = Optional.ofNullable(cards2).orElse(List.of());
		return Stream.concat(c1.stream(), c2.stream()).distinct().toList();
	}
}
