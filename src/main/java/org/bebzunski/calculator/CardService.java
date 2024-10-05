package org.bebzunski.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
