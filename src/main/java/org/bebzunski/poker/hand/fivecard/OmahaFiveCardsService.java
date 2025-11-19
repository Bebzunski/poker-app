package org.bebzunski.poker.hand.fivecard;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bebzunski.poker.constants.Constant;
import org.bebzunski.poker.pojo.Card;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OmahaFiveCardsService extends FiveCardsService {

	public static OmahaFiveCardsService getInstance() {
		return new OmahaFiveCardsService();
	}

	@Override
	public List<FiveCards> mapFrom(Collection<Card> playerCards, Collection<Card> tableCards) {
		List<List<Card>> twoCardsOutOfFour = fetchTwoOutOfFour(playerCards);
		List<List<Card>> threeCardsOutOf = fetchThreeOutOf(tableCards);
		return twoCardsOutOfFour.stream()
				.flatMap(two -> threeCardsOutOf.stream().map(three -> new FiveCards(two, three)))
				.toList();
	}

	private List<List<Card>> fetchTwoOutOfFour(Collection<Card> inputCards) {
		List<Card> cards = toDictinctList(inputCards);
		if (cards.size() != 4) {
			throw new IllegalArgumentException("Cards must be %d, but was %d.".formatted(4, cards.size()));
		}
		return Constant.TWO_OUT_OF_FOUR.stream().map(twoIndexes -> getCardsBy(twoIndexes, cards)).toList();
	}

	private List<List<Card>> fetchThreeOutOf(Collection<Card> inputCards) {
		List<Card> cards = toDictinctList(inputCards);
		if (cards.size() == 3) {
			return List.of(cards);
		}
		List<List<Integer>> combinations = fetchThreeIndexesOutOfFourOrFive(cards.size());
		return combinations.stream().map(threeIndexes -> getCardsBy(threeIndexes, cards)).toList();
	}

	private List<Card> toDictinctList(Collection<Card> givenCards) {
		return Optional.ofNullable(givenCards).orElse(List.of()).stream().distinct().toList();
	}

	private List<List<Integer>> fetchThreeIndexesOutOfFourOrFive(int size) {
		return switch (size) {
			case 4 -> Constant.THREE_OUT_OF_FOUR;
			case 5 -> Constant.THREE_OUT_OF_FIVE;
			default -> throw new IllegalArgumentException("Cards must be between %d and %d, but was %d.".formatted(3, 5, size));
		};
	}
}
