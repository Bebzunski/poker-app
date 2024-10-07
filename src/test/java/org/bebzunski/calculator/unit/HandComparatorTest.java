package org.bebzunski.calculator.unit;

import java.util.Comparator;
import java.util.List;

import org.bebzunski.calculator.hand.HandComparator;
import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.hand.output.Hand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bebzunski.calculator.hand.output.HandType.FLUSH;
import static org.bebzunski.calculator.hand.output.HandType.FULL_HOUSE;
import static org.bebzunski.calculator.hand.output.HandType.HIGH_CARD;
import static org.bebzunski.calculator.hand.output.HandType.ONE_PAIR;
import static org.bebzunski.calculator.hand.output.HandType.QUADS;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT_FLUSH;
import static org.bebzunski.calculator.hand.output.HandType.THREE_OF_KIND;
import static org.bebzunski.calculator.hand.output.HandType.TWO_PAIR;

class HandComparatorTest {

	private final Comparator<Hand> comparator = HandComparator.fullDeck();

	@Test
	void compare_test1() {
		// given
		Hand hand1 = new Hand(ONE_PAIR, List.of());
		Hand hand2 = new Hand(HIGH_CARD, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test2() {
		// given
		Hand hand1 = new Hand(TWO_PAIR, List.of());
		Hand hand2 = new Hand(ONE_PAIR, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test3() {
		// given
		Hand hand1 = new Hand(THREE_OF_KIND, List.of());
		Hand hand2 = new Hand(TWO_PAIR, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test4() {
		// given
		Hand hand1 = new Hand(STRAIGHT, List.of());
		Hand hand2 = new Hand(THREE_OF_KIND, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test6() {
		// given
		Hand hand1 = new Hand(FLUSH, List.of());
		Hand hand2 = new Hand(STRAIGHT, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test7() {
		// given
		Hand hand1 = new Hand(FULL_HOUSE, List.of());
		Hand hand2 = new Hand(FLUSH, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test8() {
		// given
		Hand hand1 = new Hand(QUADS, List.of());
		Hand hand2 = new Hand(FULL_HOUSE, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test9() {
		// given
		Hand hand1 = new Hand(STRAIGHT_FLUSH, List.of());
		Hand hand2 = new Hand(QUADS, List.of());

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test10() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.ACE, Face.KING, Face.TEN, Face.SEVEN, Face.TWO));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.ACE, Face.KING, Face.TEN, Face.SEVEN, Face.TWO));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isZero();
	}

	@Test
	void compare_test11() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.ACE));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test12() {
		// given
		Hand hand1 = new Hand(QUADS, List.of(Face.JACK, Face.TWO));
		Hand hand2 = new Hand(QUADS, List.of(Face.THREE, Face.ACE));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test13() {
		// given
		Hand hand1 = new Hand(TWO_PAIR, List.of(Face.KING, Face.JACK, Face.TEN));
		Hand hand2 = new Hand(TWO_PAIR, List.of(Face.KING, Face.JACK, Face.NINE));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test14() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SEVEN));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isLessThan(0);
	}

	@Test
	void compare_test15() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX, Face.THREE));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX, Face.TWO));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test16() {
		// given
		Hand hand1 = new Hand(STRAIGHT, List.of(Face.KING, Face.QUEEN, Face.JACK, Face.TEN, Face.NINE));
		Hand hand2 = new Hand(STRAIGHT, List.of(Face.QUEEN, Face.JACK, Face.TEN, Face.NINE, Face.EIGHT));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test17() {
		// given
		Hand hand1 = new Hand(STRAIGHT, List.of(Face.KING, Face.QUEEN, Face.JACK, Face.TEN, Face.NINE));
		Hand hand2 = new Hand(STRAIGHT, List.of(Face.FIVE, Face.FOUR, Face.THREE, Face.TWO, Face.ACE));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compare_test18() {
		// given
		Hand hand1 = new Hand(TWO_PAIR, List.of(Face.TEN, Face.TWO, Face.ACE));
		Hand hand2 = new Hand(TWO_PAIR, List.of(Face.TEN, Face.NINE, Face.THREE));

		// when
		int result = comparator.compare(hand1, hand2);

		// then
		assertThat(result).isLessThan(0);
	}

	@Test
	void compare_test19() {
		// given
		Hand hand1 = new Hand(FLUSH, List.of());
		Hand hand2 = new Hand(FULL_HOUSE, List.of());
		
		Comparator<Hand> shortDeckComparator = HandComparator.shortDeck();

		// when
		int result = shortDeckComparator.compare(hand1, hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}
}