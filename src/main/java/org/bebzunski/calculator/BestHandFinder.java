package org.bebzunski.calculator;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.Hand;

import static java.util.Comparator.naturalOrder;

public class BestHandFinder {

	public static Hand findBestHandFrom(Collection<Card> playerCards, Collection<Card> tableCards) {
		if (playerCards.size() == 4) {
			List<List<Card>> twoCardCombinations = CardCombinations.fetchTwoFromFour(playerCards);
		}
		List<Card> givenCards = concat(playerCards, tableCards);
		List<List<Card>> fiveCardCombinations = CardCombinations.fetchFiveOf(givenCards);
		return fiveCardCombinations.stream().map(HandRecognizer::findHand).max(naturalOrder()).orElseThrow();
	}

	private static List<Card> concat(Collection<Card> playerCards, Collection<Card> tableCards) {
		return Stream.concat(playerCards.stream(), tableCards.stream()).distinct().toList();
	}

	public static Hand findBestHand(Collection<Hand> hands) {
		return hands.stream().max(Comparator.naturalOrder()).orElseThrow(() -> new IllegalStateException("Couldn't find best hand"));
	}
}
