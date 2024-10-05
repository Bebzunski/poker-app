package org.bebzunski.calculator;

import java.util.List;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.pojo.Hand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bebzunski.calculator.enums.HandType.FLUSH;
import static org.bebzunski.calculator.enums.HandType.FULL_HOUSE;
import static org.bebzunski.calculator.enums.HandType.HIGH_CARD;
import static org.bebzunski.calculator.enums.HandType.ONE_PAIR;
import static org.bebzunski.calculator.enums.HandType.QUADS;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT_FLUSH;
import static org.bebzunski.calculator.enums.HandType.THREE_OF_KIND;
import static org.bebzunski.calculator.enums.HandType.TWO_PAIR;

class HandTest {

	@Test
	void compareTo_test1() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of());
		Hand hand2 = new Hand(ONE_PAIR, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isLessThan(0);
	}

	@Test
	void compareTo_test2() {
		// given
		Hand hand1 = new Hand(TWO_PAIR, List.of());
		Hand hand2 = new Hand(ONE_PAIR, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test3() {
		// given
		Hand hand1 = new Hand(THREE_OF_KIND, List.of());
		Hand hand2 = new Hand(TWO_PAIR, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test4() {
		// given
		Hand hand1 = new Hand(STRAIGHT, List.of());
		Hand hand2 = new Hand(THREE_OF_KIND, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test6() {
		// given
		Hand hand1 = new Hand(FLUSH, List.of());
		Hand hand2 = new Hand(STRAIGHT, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test7() {
		// given
		Hand hand1 = new Hand(FULL_HOUSE, List.of());
		Hand hand2 = new Hand(FLUSH, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test8() {
		// given
		Hand hand1 = new Hand(QUADS, List.of());
		Hand hand2 = new Hand(FULL_HOUSE, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test9() {
		// given
		Hand hand1 = new Hand(STRAIGHT_FLUSH, List.of());
		Hand hand2 = new Hand(QUADS, List.of());

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test10() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.ACE, Face.KING, Face.TEN, Face.SEVEN, Face.TWO));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.ACE, Face.KING, Face.TEN, Face.SEVEN, Face.TWO));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isZero();
	}

	@Test
	void compareTo_test11() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.ACE));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test12() {
		// given
		Hand hand1 = new Hand(QUADS, List.of(Face.KING, Face.JACK));
		Hand hand2 = new Hand(QUADS, List.of(Face.KING, Face.QUEEN));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isLessThan(0);
	}

	@Test
	void compareTo_test13() {
		// given
		Hand hand1 = new Hand(TWO_PAIR, List.of(Face.KING, Face.JACK, Face.TEN));
		Hand hand2 = new Hand(TWO_PAIR, List.of(Face.KING, Face.JACK, Face.NINE));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test14() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SEVEN));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isLessThan(0);
	}

	@Test
	void compareTo_test15() {
		// given
		Hand hand1 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX, Face.THREE));
		Hand hand2 = new Hand(HIGH_CARD, List.of(Face.KING, Face.JACK, Face.TEN, Face.SIX, Face.TWO));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}

	@Test
	void compareTo_test16() {
		// given
		Hand hand1 = new Hand(STRAIGHT, List.of(Face.KING, Face.QUEEN, Face.JACK, Face.TEN, Face.NINE));
		Hand hand2 = new Hand(STRAIGHT, List.of(Face.QUEEN, Face.JACK, Face.TEN, Face.NINE, Face.EIGHT));

		// when
		int result = hand1.compareTo(hand2);

		// then
		assertThat(result).isGreaterThan(0);
	}
}