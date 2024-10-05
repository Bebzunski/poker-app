package org.bebzunski.calculator;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bebzunski.calculator.pojo.Card;

import static org.bebzunski.calculator.constants.Constant.FIVE_CARDS;

public class CardCombinations {

	public static List<List<Card>> fetchTwoFromFour(Collection<Card> givenCards) {
		List<Card> cards = createDictinctList(givenCards);
		if (cards.size() != 4) {
			throw new IllegalArgumentException("Cards must be %d, but was %d.".formatted(4, cards.size()));
		}
		return TWO_FROM_FOUR.stream().map(twoIndexes -> twoIndexes.stream().map(cards::get).toList()).toList();
	}

	public static List<List<Card>> fetchFiveOf(Collection<Card> givenCards) {
		List<Card> cards = createDictinctList(givenCards);
		List<List<Integer>> combinations = fetchIndexesCombinations(cards.size());
		return combinations.stream().map(fiveIndexes -> fiveIndexes.stream().map(cards::get).toList()).toList();
	}

	private static List<Card> createDictinctList(Collection<Card> givenCards) {
		return Optional.ofNullable(givenCards).orElse(List.of()).stream().distinct().toList();
	}

	private static List<List<Integer>> fetchIndexesCombinations(int size) {
		return switch (size) {
			case FIVE_CARDS -> FIVE_FROM_FIVE;
			case FIVE_CARDS + 1 -> FIVE_FROM_SIX;
			case FIVE_CARDS + 2 -> FIVE_FROM_SEVEN;
			default -> throw new IllegalArgumentException(
					"Cards must be between %d and %d, but was %d.".formatted(FIVE_CARDS, FIVE_CARDS + 2, size));
		};
	}

	private static final List<List<Integer>> FIVE_FROM_FIVE = List.of(List.of(0, 1, 2, 3, 4));

	private static final List<List<Integer>> FIVE_FROM_SIX = List.of(//@formatter:off
			List.of(0, 1, 2, 3, 4),
			List.of(0, 1, 2, 3, 5),
			List.of(0, 1, 2, 4, 5),
			List.of(0, 1, 3, 4, 5),
			List.of(0, 2, 3, 4, 5),
			List.of(1, 2, 3, 4, 5));

	private static final List<List<Integer>> FIVE_FROM_SEVEN = List.of(
			List.of(0, 1, 2, 3, 4),
			List.of(0, 1, 2, 3, 5),
			List.of(0, 1, 2, 3, 6),
			List.of(0, 1, 2, 4, 5),
			List.of(0, 1, 2, 4, 6),
			List.of(0, 1, 2, 5, 6),
			List.of(0, 1, 3, 4, 5),
			List.of(0, 1, 3, 4, 6),
			List.of(0, 1, 3, 5, 6),
			List.of(0, 2, 3, 4, 5),
			List.of(0, 2, 3, 4, 6),
			List.of(0, 2, 3, 5, 6),
			List.of(1, 2, 3, 4, 5),
			List.of(1, 2, 3, 4, 6),
			List.of(1, 2, 3, 5, 6),
			List.of(0, 1, 4, 5, 6),
			List.of(0, 2, 4, 5, 6),
			List.of(1, 2, 4, 5, 6),
			List.of(0, 3, 4, 5, 6),
			List.of(1, 3, 4, 5, 6),
			List.of(2, 3, 4, 5, 6));

	private static final List<List<Integer>> TWO_FROM_FOUR = List.of(
			List.of(0, 1),
			List.of(0, 2),
			List.of(0, 3),
			List.of(1, 2),
			List.of(1, 3),
			List.of(2, 3));
	//@formatter:on
}
