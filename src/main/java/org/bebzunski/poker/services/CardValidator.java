package org.bebzunski.poker.services;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.bebzunski.poker.pojo.Card;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.CollectionUtils.isEmpty;

public class CardValidator {

	public static void throwWhenCardsAreDuplicated(List<Card> cards) {
		List<Card> duplicatedCards = findDuplicates(cards);
		if (!duplicatedCards.isEmpty()) {
			throw new IllegalArgumentException("Some cards are selected more than once: %s".formatted(duplicatedCards));
		}
	}

	private static List<Card> findDuplicates(List<Card> cards) {
		if (isEmpty(cards)) {
			return List.of();
		}
		Map<Card, Long> occurrenceByCard = cards.stream().collect(groupingBy(Function.identity(), counting()));
		return occurrenceByCard.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).sorted().toList();
	}

	public static void throwWhenSelectedCardsAreOutsideCardsScope(List<Card> cards, List<Card> cardsScope) {
		List<Card> cardsOutsideScope = cards.stream().filter(card -> !cardsScope.contains(card)).sorted().toList();
		if (!cardsOutsideScope.isEmpty()) {
			throw new IllegalArgumentException("Some selected cards are not from game deck: %s".formatted(cardsOutsideScope));
		}
	}
}
