package org.bebzunski.calculator.hand;

import java.util.Collection;
import java.util.List;

import org.bebzunski.calculator.hand.fivecard.FiveCards;
import org.bebzunski.calculator.hand.fivecard.FiveCardsService;
import org.bebzunski.calculator.hand.output.Hand;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.services.PokerConfiguration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BestHandService {

	private final HandReader handReader;
	private final FiveCardsService fiveCardsService;
	private final HandComparator handComparator;

	public BestHandService(PokerConfiguration configuration) {
		this.handReader = configuration.getHandReader();
		this.handComparator = configuration.getHandComparator();
		this.fiveCardsService = configuration.getFiveCardsService();
	}

	public Hand findFrom(Collection<Card> playerCards, Collection<Card> tableCards) {
		List<FiveCards> fiveCardsList = fiveCardsService.mapFrom(playerCards, tableCards);
		List<Hand> hands = fiveCardsList.stream().map(handReader::readFrom).toList();
		return findFrom(hands);
	}

	public Hand findFrom(Collection<Hand> hands) {
		return hands.stream().max(handComparator).orElseThrow(() -> new IllegalStateException("Couldn't find best hand"));
	}
}
