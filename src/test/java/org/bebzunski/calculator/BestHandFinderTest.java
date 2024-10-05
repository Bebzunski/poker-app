package org.bebzunski.calculator;

import java.util.ArrayList;
import java.util.List;

import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.Hand;
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
import static org.bebzunski.calculator.enums.HandType.FLUSH;
import static org.bebzunski.calculator.enums.HandType.HIGH_CARD;
import static org.bebzunski.calculator.enums.HandType.ONE_PAIR;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT_FLUSH;

class BestHandFinderTest {

	@Test
	void findBestHandFrom_test1() {
		// expected
		assertThatThrownBy(() -> invokeFindBestHandFrom(null)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findBestHandFrom_test2() {
		// expected
		assertThatThrownBy(() -> invokeFindBestHandFrom(List.of())).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findBestHandFrom_test3() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));

		// expected
		assertThatThrownBy(() -> invokeFindBestHandFrom(cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void findBestHandFrom_test4() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));

		// when
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT_FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, KING, QUEEN, JACK, TEN));
	}

	@Test
	void findBestHandFrom_test5() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, SPADE));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(ONE_PAIR);
		assertThat(hand.faces()).isEqualTo(List.of(KING, ACE, QUEEN, JACK));
	}

	@Test
	void findBestHandFrom_test6() {
		// given
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, KING, QUEEN, JACK, SEVEN));
	}

	@Test
	void findBestHandFrom_test7() {
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
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, TEN, NINE, EIGHT, SEVEN));
	}

	@Test
	void findBestHandFrom_test8() {
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
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(JACK, TEN, NINE, EIGHT, SEVEN));
	}

	@Test
	void findBestHandFrom_test9() {
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
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(HIGH_CARD);
		assertThat(hand.faces()).isEqualTo(List.of(KING, TEN, NINE, EIGHT, SEVEN));
	}

	@Test
	void findBestHandFrom_test10() {
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
		Hand hand = invokeFindBestHandFrom(cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(NINE, EIGHT, SEVEN, FOUR, THREE));
	}

	private static Hand invokeFindBestHandFrom(List<Card> cards) {
		return BestHandFinder.findBestHandFrom(List.of(), cards);
	}
}