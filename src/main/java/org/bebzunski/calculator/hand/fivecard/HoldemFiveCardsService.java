package org.bebzunski.calculator.hand.fivecard;

import java.util.Collection;
import java.util.List;

import org.bebzunski.calculator.constants.Constant;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.services.CardService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HoldemFiveCardsService extends FiveCardsService {

	public static HoldemFiveCardsService getInstance() {
		return new HoldemFiveCardsService();
	}

	@Override
	public List<FiveCards> mapFrom(Collection<Card> playerCards, Collection<Card> tableCards) {
		List<Card> cards = CardService.merge(playerCards, tableCards);
		if (cards.size() == FiveCards.FIVE_CARDS) {
			return List.of(new FiveCards(cards));
		}
		List<List<Integer>> combinations = fetchFiveIndexesOutOfSixOrSeven(cards.size());
		return combinations.stream().map(fiveIndexes -> getCardsBy(fiveIndexes, cards)).map(FiveCards::new).toList();
	}

	private List<List<Integer>> fetchFiveIndexesOutOfSixOrSeven(int size) {
		return switch (size) {
			case 6 -> Constant.FIVE_OUT_OF_SIX;
			case 7 -> Constant.FIVE_OUT_OF_SEVEN;
			default -> throw new IllegalArgumentException("Cards must be between %d and %d, but was %d.".formatted(5, 7, size));
		};
	}
}
