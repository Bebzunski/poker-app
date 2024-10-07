package org.bebzunski.calculator.services;

import org.bebzunski.calculator.hand.HandComparator;
import org.bebzunski.calculator.hand.HandReader;
import org.bebzunski.calculator.hand.fivecard.FiveCardsService;
import org.bebzunski.calculator.hand.fivecard.HoldemFiveCardsService;
import org.bebzunski.calculator.hand.fivecard.OmahaFiveCardsService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class PokerConfiguration {

	private static final int DEFAULT_ITERATIONS = 10_000;

	private final Deck deck;
	private final HandReader handReader;
	private final HandComparator handComparator;
	private final FiveCardsService fiveCardsService;
	private final int cardsOnPlayersHand;
	private final int maxPlayersOnTable;
	private final int numberOfSimulations;

	public static PokerConfiguration fullDeckHoldem() {
		Deck fullDeck = Deck.fullDeck();
		HandReader handReader = HandReader.fullDeck();
		HandComparator handComparator = HandComparator.fullDeck();
		FiveCardsService fiveCardsService = HoldemFiveCardsService.getInstance();
		return new PokerConfiguration(fullDeck, handReader, handComparator, fiveCardsService, 2, 10, DEFAULT_ITERATIONS);
	}

	public static PokerConfiguration shortDeckHoldem() {
		Deck shortDeck = Deck.sixToAceDeck();
		HandReader handReader = HandReader.sixLowDeck();
		HandComparator handComparator = HandComparator.shortDeck();
		FiveCardsService fiveCardsService = HoldemFiveCardsService.getInstance();
		return new PokerConfiguration(shortDeck, handReader, handComparator, fiveCardsService, 2, 10, DEFAULT_ITERATIONS);
	}

	public static PokerConfiguration omaha() {
		Deck fullDeck = Deck.fullDeck();
		HandReader handReader = HandReader.fullDeck();
		HandComparator handComparator = HandComparator.fullDeck();
		FiveCardsService fiveCardsService = OmahaFiveCardsService.getInstance();
		return new PokerConfiguration(fullDeck, handReader, handComparator, fiveCardsService, 4, 6, DEFAULT_ITERATIONS);
	}
}
