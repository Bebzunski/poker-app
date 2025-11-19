package org.bebzunski.poker.unit;

import java.util.ArrayList;
import java.util.List;

import org.bebzunski.poker.hand.HandReader;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.hand.fivecard.FiveCards;
import org.bebzunski.poker.hand.output.Hand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.bebzunski.poker.enums.Color.CLUB;
import static org.bebzunski.poker.enums.Color.DIAMOND;
import static org.bebzunski.poker.enums.Color.HEART;
import static org.bebzunski.poker.enums.Color.SPADE;
import static org.bebzunski.poker.enums.Face.ACE;
import static org.bebzunski.poker.enums.Face.EIGHT;
import static org.bebzunski.poker.enums.Face.FIVE;
import static org.bebzunski.poker.enums.Face.FOUR;
import static org.bebzunski.poker.enums.Face.JACK;
import static org.bebzunski.poker.enums.Face.KING;
import static org.bebzunski.poker.enums.Face.NINE;
import static org.bebzunski.poker.enums.Face.QUEEN;
import static org.bebzunski.poker.enums.Face.SEVEN;
import static org.bebzunski.poker.enums.Face.SIX;
import static org.bebzunski.poker.enums.Face.TEN;
import static org.bebzunski.poker.enums.Face.THREE;
import static org.bebzunski.poker.enums.Face.TWO;
import static org.bebzunski.poker.hand.output.HandType.FLUSH;
import static org.bebzunski.poker.hand.output.HandType.FULL_HOUSE;
import static org.bebzunski.poker.hand.output.HandType.HIGH_CARD;
import static org.bebzunski.poker.hand.output.HandType.ONE_PAIR;
import static org.bebzunski.poker.hand.output.HandType.QUADS;
import static org.bebzunski.poker.hand.output.HandType.STRAIGHT;
import static org.bebzunski.poker.hand.output.HandType.STRAIGHT_FLUSH;
import static org.bebzunski.poker.hand.output.HandType.THREE_OF_KIND;
import static org.bebzunski.poker.hand.output.HandType.TWO_PAIR;

class HandReaderTest {

	@Test
	void readFrom_test01() {
		// given
		HandReader handReader = HandReader.fullDeck();

		// expected
		assertThatThrownBy(() -> read(handReader, null)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void readFrom_test02() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();

		// expected
		assertThatThrownBy(() -> read(handReader, cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void readFrom_test03() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));

		// expected
		assertThatThrownBy(() -> read(handReader, cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void readFrom_test04() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = List.of(TEN.d(), QUEEN.d(), JACK.d(), KING.d(), KING.d());

		// expected
		assertThatThrownBy(() -> read(handReader, cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void readFrom_test05() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(SEVEN, DIAMOND));

		// expected
		assertThatThrownBy(() -> read(handReader, cards)).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void readFrom_test1() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT_FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE));
	}

	@Test
	void readFrom_test2() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SEVEN, CLUB));
		cards.add(Card.of(SIX, CLUB));
		cards.add(Card.of(THREE, CLUB));
		cards.add(Card.of(FOUR, CLUB));
		cards.add(Card.of(FIVE, CLUB));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT_FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(SEVEN));
	}

	@Test
	void readFrom_test3() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TWO, HEART));
		cards.add(Card.of(ACE, HEART));
		cards.add(Card.of(THREE, HEART));
		cards.add(Card.of(FOUR, HEART));
		cards.add(Card.of(FIVE, HEART));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT_FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(FIVE));
	}

	@Test
	void readFrom_test4() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TWO, HEART));
		cards.add(Card.of(ACE, HEART));
		cards.add(Card.of(THREE, HEART));
		cards.add(Card.of(FOUR, HEART));
		cards.add(Card.of(SEVEN, HEART));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, SEVEN, FOUR, THREE, TWO));
	}

	@Test
	void readFrom_test5() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(ACE, SPADE));
		cards.add(Card.of(QUEEN, SPADE));
		cards.add(Card.of(JACK, SPADE));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, KING, QUEEN, JACK, NINE));
	}

	@Test
	void readFrom_test6() {
		// given
		HandReader handReader = HandReader.sixLowDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(FOUR, SPADE));
		cards.add(Card.of(EIGHT, SPADE));
		cards.add(Card.of(TWO, SPADE));
		cards.add(Card.of(JACK, SPADE));
		cards.add(Card.of(SEVEN, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(FLUSH);
		assertThat(hand.faces()).isEqualTo(List.of(JACK, EIGHT, SEVEN, FOUR, TWO));
	}

	@Test
	void readFrom_test7() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(ACE, SPADE));
		cards.add(Card.of(TEN, SPADE));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(QUEEN, SPADE));
		cards.add(Card.of(JACK, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(ACE));
	}

	@Test
	void readFrom_test8() {
		// given
		HandReader handReader = HandReader.sixLowDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(TEN, SPADE));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(QUEEN, SPADE));
		cards.add(Card.of(JACK, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(KING));
	}

	@Test
	void readFrom_test9() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(TEN, SPADE));
		cards.add(Card.of(SIX, DIAMOND));
		cards.add(Card.of(EIGHT, SPADE));
		cards.add(Card.of(SEVEN, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(TEN));
	}

	@Test
	void readFrom_test10() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(FIVE, SPADE));
		cards.add(Card.of(THREE, SPADE));
		cards.add(Card.of(FOUR, DIAMOND));
		cards.add(Card.of(TWO, SPADE));
		cards.add(Card.of(ACE, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(FIVE));
	}

	@Test
	void readFrom_test11() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(SIX, SPADE));
		cards.add(Card.of(THREE, SPADE));
		cards.add(Card.of(FOUR, DIAMOND));
		cards.add(Card.of(TWO, SPADE));
		cards.add(Card.of(ACE, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(HIGH_CARD);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, SIX, FOUR, THREE, TWO));
	}

	@Test
	void readFrom_test12() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(ACE, SPADE));
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(QUEEN, SPADE));
		cards.add(Card.of(JACK, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(HIGH_CARD);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, KING, QUEEN, JACK, NINE));
	}

	@Test
	void readFrom_test13() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(NINE, CLUB));
		cards.add(Card.of(NINE, HEART));
		cards.add(Card.of(NINE, DIAMOND));
		cards.add(Card.of(NINE, SPADE));
		cards.add(Card.of(JACK, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(QUADS);
		assertThat(hand.faces()).isEqualTo(List.of(NINE, JACK));
	}

	@Test
	void readFrom_test14() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(FIVE, CLUB));
		cards.add(Card.of(FIVE, HEART));
		cards.add(Card.of(FIVE, DIAMOND));
		cards.add(Card.of(QUEEN, DIAMOND));
		cards.add(Card.of(QUEEN, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(FULL_HOUSE);
		assertThat(hand.faces()).isEqualTo(List.of(FIVE, QUEEN));
	}

	@Test
	void readFrom_test15() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TEN, CLUB));
		cards.add(Card.of(FIVE, HEART));
		cards.add(Card.of(ACE, DIAMOND));
		cards.add(Card.of(TEN, DIAMOND));
		cards.add(Card.of(TEN, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(THREE_OF_KIND);
		assertThat(hand.faces()).isEqualTo(List.of(TEN, ACE, FIVE));
	}

	@Test
	void readFrom_test16() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(TWO, CLUB));
		cards.add(Card.of(TWO, HEART));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(KING, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(TWO_PAIR);
		assertThat(hand.faces()).isEqualTo(List.of(KING, TWO, JACK));
	}

	@Test
	void readFrom_test17() {
		// given
		HandReader handReader = HandReader.fullDeck();
		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(QUEEN, CLUB));
		cards.add(Card.of(TWO, HEART));
		cards.add(Card.of(JACK, DIAMOND));
		cards.add(Card.of(KING, DIAMOND));
		cards.add(Card.of(QUEEN, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(ONE_PAIR);
		assertThat(hand.faces()).isEqualTo(List.of(QUEEN, KING, JACK, TWO));
	}

	@Test
	void readFrom_test18() {
		// given
		HandReader handReader = HandReader.fullDeck();

		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(ACE, CLUB));
		cards.add(Card.of(SIX, HEART));
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(HIGH_CARD);
		assertThat(hand.faces()).isEqualTo(List.of(ACE, NINE, EIGHT, SEVEN, SIX));
	}

	@Test
	void readFrom_test19() {
		// given
		HandReader handReader = HandReader.sixLowDeck();

		List<Card> cards = new ArrayList<>();
		cards.add(Card.of(ACE, CLUB));
		cards.add(Card.of(SIX, HEART));
		cards.add(Card.of(SEVEN, DIAMOND));
		cards.add(Card.of(EIGHT, DIAMOND));
		cards.add(Card.of(NINE, SPADE));

		// when
		Hand hand = read(handReader, cards);

		// then
		assertThat(hand.type()).isEqualTo(STRAIGHT);
		assertThat(hand.faces()).isEqualTo(List.of(NINE));
	}

	private Hand read(HandReader handReader, List<Card> cards) {
		return handReader.readFrom(new FiveCards(cards));
	}

}