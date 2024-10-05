package org.bebzunski.calculator;

import java.util.ArrayList;
import java.util.List;

import org.bebzunski.calculator.pojo.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.bebzunski.calculator.enums.Color.DIAMOND;
import static org.bebzunski.calculator.enums.Face.ACE;
import static org.bebzunski.calculator.enums.Face.EIGHT;
import static org.bebzunski.calculator.enums.Face.JACK;
import static org.bebzunski.calculator.enums.Face.KING;
import static org.bebzunski.calculator.enums.Face.QUEEN;
import static org.bebzunski.calculator.enums.Face.SEVEN;
import static org.bebzunski.calculator.enums.Face.TEN;

class CardCombinationsTest {

	@Test
	void fetchFiveOf_test1() {
		// expected
		assertThatThrownBy(() -> invokeFetchFiveOf(null)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void fetchFiveOf_test2() {
		// expected
		assertThatThrownBy(() -> invokeFetchFiveOf(List.of())).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void fetchFiveOf_test3() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));

		// expected
		assertThatThrownBy(() -> invokeFetchFiveOf(cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void fetchFiveOf_test4() {
		// given
		Card card1 = Card.of(TEN, DIAMOND);
		Card card2 = Card.of(ACE, DIAMOND);
		Card card3 = Card.of(QUEEN, DIAMOND);
		Card card4 = Card.of(JACK, DIAMOND);
		Card card5 = Card.of(KING, DIAMOND);
		List<Card> cards = List.of(card1, card2, card3, card4, card5);

		// when
		List<List<Card>> cardsCombinations = invokeFetchFiveOf(cards);

		// then
		assertThat(cardsCombinations).hasSize(1);
		assertThat(cardsCombinations.getFirst()).isEqualTo(cards);
	}

	@Test
	void fetchFiveOf_test5() {
		// given
		Card card1 = Card.of(TEN, DIAMOND);
		Card card2 = Card.of(ACE, DIAMOND);
		Card card3 = Card.of(QUEEN, DIAMOND);
		Card card4 = Card.of(JACK, DIAMOND);
		Card card5 = Card.of(KING, DIAMOND);
		Card card6 = Card.of(SEVEN, DIAMOND);
		List<Card> cards = List.of(card1, card2, card3, card4, card5, card6);

		// when
		List<List<Card>> cardsCombinations = invokeFetchFiveOf(cards);

		// then
		assertThat(cardsCombinations).hasSize(6);
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card4, card5));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card4, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card3, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card2, card3, card4, card5, card6));
	}

	@Test
	void fetchFiveOf_test6() {
		// given
		Card card0 = Card.of(TEN, DIAMOND);
		Card card1 = Card.of(ACE, DIAMOND);
		Card card2 = Card.of(QUEEN, DIAMOND);
		Card card3 = Card.of(JACK, DIAMOND);
		Card card4 = Card.of(KING, DIAMOND);
		Card card5 = Card.of(SEVEN, DIAMOND);
		Card card6 = Card.of(EIGHT, DIAMOND);
		List<Card> cards = List.of(card0, card1, card2, card3, card4, card5, card6);

		// when
		List<List<Card>> cardsCombinations = invokeFetchFiveOf(cards);

		// then
		assertThat(cardsCombinations).hasSize(21);

		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card3, card4));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card3, card5));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card3, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card4, card5));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card4, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card2, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card3, card4, card5));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card3, card4, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card3, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card2, card3, card4, card5));
		assertThat(cardsCombinations).contains(List.of(card0, card2, card3, card4, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card2, card3, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card4, card5));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card4, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card3, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card1, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card2, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card2, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card0, card3, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card1, card3, card4, card5, card6));
		assertThat(cardsCombinations).contains(List.of(card2, card3, card4, card5, card6));
	}

	private static List<List<Card>> invokeFetchFiveOf(List<Card> cards) {
		return CardCombinations.fetchFiveOf(cards);
	}
}