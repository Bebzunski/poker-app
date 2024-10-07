package org.bebzunski.calculator.unit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bebzunski.calculator.services.Deck;
import org.bebzunski.calculator.enums.Color;
import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.pojo.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

	private static final int FULL_DECK = 52;
	private static final int SHORT_DECK = 36;

	@Test
	void load_fullDeck_test1() {
		// given
		Deck deck = Deck.fullDeck();

		// when
		deck.loadFresh();
		Set<Card> cards = new HashSet<>(deck.dealCards(FULL_DECK));

		// then
		assertThat(cards).hasSize(FULL_DECK);

		assertThat(cards).contains(Card.of(Face.ACE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.KING, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.JACK, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.NINE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SIX, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.THREE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TWO, Color.SPADE));

		assertThat(cards).contains(Card.of(Face.ACE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.KING, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.JACK, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.NINE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SIX, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.THREE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TWO, Color.CLUB));

		assertThat(cards).contains(Card.of(Face.ACE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.KING, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.JACK, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.NINE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SIX, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.THREE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TWO, Color.DIAMOND));

		assertThat(cards).contains(Card.of(Face.ACE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.KING, Color.HEART));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.JACK, Color.HEART));
		assertThat(cards).contains(Card.of(Face.TEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.NINE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SIX, Color.HEART));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.HEART));
		assertThat(cards).contains(Card.of(Face.THREE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.TWO, Color.HEART));
	}

	@Test
	void load_fullDeck_test2() {
		// given
		Deck deck = Deck.fullDeck();
		deck.excludeCards(List.of(Card.of(Face.ACE, Color.SPADE)));
		deck.excludeCards(List.of(Card.of(Face.FIVE, Color.CLUB)));
		deck.excludeCards(List.of(Card.of(Face.TWO, Color.HEART)));

		// when
		deck.loadFresh();
		Set<Card> cards = new HashSet<>(deck.dealCards(FULL_DECK - 3));

		// then
		assertThat(cards).hasSize(FULL_DECK - 3);

		assertThat(cards).contains(Card.of(Face.KING, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.JACK, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.NINE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SIX, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.THREE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TWO, Color.SPADE));

		assertThat(cards).contains(Card.of(Face.ACE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.KING, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.JACK, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.NINE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SIX, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.THREE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TWO, Color.CLUB));

		assertThat(cards).contains(Card.of(Face.ACE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.KING, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.JACK, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.NINE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SIX, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.THREE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TWO, Color.DIAMOND));

		assertThat(cards).contains(Card.of(Face.ACE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.KING, Color.HEART));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.JACK, Color.HEART));
		assertThat(cards).contains(Card.of(Face.TEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.NINE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SIX, Color.HEART));
		assertThat(cards).contains(Card.of(Face.FIVE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.FOUR, Color.HEART));
		assertThat(cards).contains(Card.of(Face.THREE, Color.HEART));
	}

	@Test
	void load_fullDeck_test3() {
		// given
		Deck deck = Deck.fullDeck();
		deck.loadFresh();
		deck.dealCards(FULL_DECK);

		// expected
		assertThatThrownBy(() -> deck.dealCards(1)).isExactlyInstanceOf(IllegalStateException.class);
	}

	@Test
	void load_sixToAceDeck_test4() {
		// given
		Deck deck = Deck.sixToAceDeck();

		// when
		deck.loadFresh();
		Set<Card> cards = new HashSet<>(deck.dealCards(SHORT_DECK));

		// then
		assertThat(cards).hasSize(SHORT_DECK);

		assertThat(cards).contains(Card.of(Face.ACE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.KING, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.JACK, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.NINE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SIX, Color.SPADE));

		assertThat(cards).contains(Card.of(Face.ACE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.KING, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.JACK, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.NINE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SIX, Color.CLUB));

		assertThat(cards).contains(Card.of(Face.ACE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.KING, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.JACK, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.NINE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SIX, Color.DIAMOND));

		assertThat(cards).contains(Card.of(Face.ACE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.KING, Color.HEART));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.JACK, Color.HEART));
		assertThat(cards).contains(Card.of(Face.TEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.NINE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SIX, Color.HEART));
	}

	@Test
	void load_sixToAceDeck_test5() {
		// given
		Deck deck = Deck.sixToAceDeck();
		deck.loadFresh();
		deck.dealCards(1);

		// when
		deck.loadFresh();
		Set<Card> cards = new HashSet<>(deck.dealCards(SHORT_DECK));

		// then
		assertThat(cards).hasSize(SHORT_DECK);

		assertThat(cards).contains(Card.of(Face.ACE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.KING, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.JACK, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.TEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.NINE, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.SPADE));
		assertThat(cards).contains(Card.of(Face.SIX, Color.SPADE));

		assertThat(cards).contains(Card.of(Face.ACE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.KING, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.JACK, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.TEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.NINE, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.CLUB));
		assertThat(cards).contains(Card.of(Face.SIX, Color.CLUB));

		assertThat(cards).contains(Card.of(Face.ACE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.KING, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.JACK, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.TEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.NINE, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.DIAMOND));
		assertThat(cards).contains(Card.of(Face.SIX, Color.DIAMOND));

		assertThat(cards).contains(Card.of(Face.ACE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.KING, Color.HEART));
		assertThat(cards).contains(Card.of(Face.QUEEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.JACK, Color.HEART));
		assertThat(cards).contains(Card.of(Face.TEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.NINE, Color.HEART));
		assertThat(cards).contains(Card.of(Face.EIGHT, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SEVEN, Color.HEART));
		assertThat(cards).contains(Card.of(Face.SIX, Color.HEART));
	}
}