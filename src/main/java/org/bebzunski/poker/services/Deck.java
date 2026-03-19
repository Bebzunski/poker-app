package org.bebzunski.poker.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

import org.bebzunski.poker.enums.Face;
import org.bebzunski.poker.pojo.Card;

public class Deck {

	@Getter
	private final List<Card> cardsScope;
	private List<Card> cardsToDeal = new ArrayList<>();
	private final Random random = new Random();
	private final Set<Card> excludedCards = new HashSet<>();

	public static Deck fullDeck() {
		List<Card> allCards = CardService.allCards();
		return new Deck(allCards);
	}

	public static Deck sixToAceDeck() {
		List<Card> shortDeck = CardService.getCards(Face.SIX);
		return new Deck(shortDeck);
	}

	public void loadFresh() {
		cardsToDeal = cardsScope.stream().filter(c -> !excludedCards.contains(c)).collect(Collectors.toList());
	}

	public void excludeCards(Collection<Card> cards) {
		excludedCards.addAll(cards);
	}

	public List<Card> dealCards(int number) {
		return IntStream.range(0, number).mapToObj(i -> dealCard()).toList();
	}

	private Deck(List<Card> cardsScope) {
		this.cardsScope = Optional.ofNullable(cardsScope).orElse(List.of());
		loadFresh();
	}

	private Card dealCard() {
		if (cardsToDeal.isEmpty()) {
			throw new IllegalStateException("There are no cards to deal.");
		}
		int index = random.nextInt(0, cardsToDeal.size());
		return cardsToDeal.remove(index);
	}
}
