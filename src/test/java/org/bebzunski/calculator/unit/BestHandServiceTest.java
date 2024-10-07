package org.bebzunski.calculator.unit;

import java.util.ArrayList;
import java.util.List;

import org.bebzunski.calculator.hand.BestHandService;
import org.bebzunski.calculator.services.PokerConfiguration;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.hand.output.Hand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.bebzunski.calculator.enums.Color.CLUB;
import static org.bebzunski.calculator.enums.Color.DIAMOND;
import static org.bebzunski.calculator.enums.Color.SPADE;
import static org.bebzunski.calculator.enums.Face.ACE;
import static org.bebzunski.calculator.enums.Face.EIGHT;
import static org.bebzunski.calculator.enums.Face.FOUR;
import static org.bebzunski.calculator.enums.Face.JACK;
import static org.bebzunski.calculator.enums.Face.KING;
import static org.bebzunski.calculator.enums.Face.NINE;
import static org.bebzunski.calculator.enums.Face.QUEEN;
import static org.bebzunski.calculator.enums.Face.SEVEN;
import static org.bebzunski.calculator.enums.Face.TEN;
import static org.bebzunski.calculator.enums.Face.THREE;
import static org.bebzunski.calculator.enums.Face.TWO;
import static org.bebzunski.calculator.hand.output.HandType.FLUSH;
import static org.bebzunski.calculator.hand.output.HandType.HIGH_CARD;
import static org.bebzunski.calculator.hand.output.HandType.ONE_PAIR;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT_FLUSH;

class BestHandServiceTest {

	private final BestHandService bestHandService = new BestHandService(PokerConfiguration.fullDeckHoldem());

	@Test
	void findFromFrom_test1() {
		// expected
		assertThatThrownBy(() -> invokeFindFromFrom(null)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findFromFrom_test2() {
		// expected
		assertThatThrownBy(() -> invokeFindFromFrom(List.of())).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findFromFrom_test3() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));

		// expected
		assertThatThrownBy(() -> invokeFindFromFrom(cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findFromFrom_test4() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT_FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE));
	}

	@Test
	void findFromFrom_test5() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, SPADE));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(ONE_PAIR);
		assertThat(hand.faces()).isEqualTo(List.of(KING, ACE, QUEEN, JACK));
	}

	@Test
	void findFromFrom_test6() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, KING, QUEEN, JACK, SEVEN));
	}

	@Test
	void findFromFrom_test7() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(SEVEN, SPADE));
		cards.add(Card.of(SEVEN, CLUB));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, DIAMOND));
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, TEN, NINE, EIGHT, SEVEN));
	}

	@Test
	void findFromFrom_test8() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(SEVEN, SPADE));
		cards.add(Card.of(TWO, CLUB));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(JACK));
	}

	@Test
	void findFromFrom_test9() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(THREE, SPADE));
		cards.add(Card.of(TWO, CLUB));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(TEN, SPADE));
		cards.add(Card.of(KING, DIAMOND));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(HIGH_CARD);
		assertThat(hand.faces()).isEqualTo(List.of(KING, TEN, NINE, EIGHT, SEVEN));
	}

	@Test
	void findFromFrom_test10() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, DIAMOND));
		cards.add(Card.of(TEN, SPADE));
		cards.add(Card.of(JACK, SPADE));
		cards.add(Card.of(THREE, DIAMOND));
		cards.add(Card.of(FOUR, DIAMOND));

		// when
		Hand hand = invokeFindFromFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(NINE, EIGHT, SEVEN, FOUR, THREE));
	}

	private Hand invokeFindFromFrom(List<Card> cards) {
		return bestHandService.findFrom(List.of(), cards);
	}
}