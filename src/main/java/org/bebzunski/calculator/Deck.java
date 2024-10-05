package org.bebzunski.calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.pojo.Card;

public class Deck {

	private final List<Card> cardsScope;
	private final List<Card> cardsToDeal = new ArrayList<>();
	private final Random random = new Random();
	private final Set<Card> excludedCards = new HashSet<>();

	public static Deck fullDeck() {
		return new Deck(CardService.allCards());
	}

	public static Deck shortDeck() {
		return new Deck(CardService.getCards(Face.SIX));
	}

	private Deck(List<Card> cardsScope) {
		this.cardsScope = Optional.ofNullable(cardsScope).orElse(List.of());
	}

	public void loadFresh() {
		cardsToDeal.clear();
		cardsToDeal.addAll(cardsScope);
		cardsToDeal.removeAll(excludedCards);
	}

	public void excludeCards(Collection<Card> cards) {
		excludedCards.addAll(cards);
	}

	public void excludeCard(Card card) {
		excludedCards.add(card);
	}

	public List<Card> dealCards(int number) {
		return IntStream.range(0, number).mapToObj(i -> dealCard()).toList();
	}

	private Card dealCard() {
		if (cardsToDeal.isEmpty()) {
			throw new IllegalStateException("There are no cards to deal.");
		}
		int index = random.nextInt(0, cardsToDeal.size());
		return cardsToDeal.remove(index);
	}
}
