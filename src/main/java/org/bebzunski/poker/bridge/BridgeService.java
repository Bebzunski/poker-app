package org.bebzunski.poker.bridge;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bebzunski.poker.enums.Color;
import org.bebzunski.poker.enums.Face;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.services.Deck;

public class BridgeService {

	public static String dealCardsInPbnFormat() {
		Deck fullDeck = Deck.fullDeck();
		List<Card> randomDeck = fullDeck.dealCards(52);
		String N = toBridgeHand(randomDeck.subList(0, 13));
		String E = toBridgeHand(randomDeck.subList(13, 26));
		String S = toBridgeHand(randomDeck.subList(26, 39));
		String W = toBridgeHand(randomDeck.subList(39, 52));
		return """
				[Event ""]
				[Site ""]
				[Date ""]
				[Board "1"]
				[West ""]
				[North ""]
				[East ""]
				[South ""]
				[Dealer "S"]
				[Vulnerable "None"]
				[Deal "N:%s %s %s %s"]
				""".formatted(N, E, S, W);
	}

	private static String toBridgeHand(List<Card> cards) {
		Map<Color, List<Card>> cardsByColor = cards.stream().collect(Collectors.groupingBy(Card::color));
		List<Card> spades = cardsByColor.getOrDefault(Color.SPADE, List.of());
		List<Card> hearts = cardsByColor.getOrDefault(Color.HEART, List.of());
		List<Card> diamonds = cardsByColor.getOrDefault(Color.DIAMOND, List.of());
		List<Card> clubs = cardsByColor.getOrDefault(Color.CLUB, List.of());
		return "%s.%s.%s.%s".formatted(join(spades), join(hearts), join(diamonds), join(clubs));
	}

	private static String join(List<Card> cardsInOneColor) {
		return cardsInOneColor.stream()
				.map(Card::face)
				.sorted(Comparator.reverseOrder())
				.map(Face::getSymbol)
				.collect(Collectors.joining());
	}
}
