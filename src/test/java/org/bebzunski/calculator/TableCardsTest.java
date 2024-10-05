package org.bebzunski.calculator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.enums.Street;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.TableCardsOutput;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableCardsTest {

	@Test
	void dealTimes_whenDidNotSelectStreets_expectedFiveDifferentCards() {
		// given
		TableCards tableCards = createTableCards();

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenLoadNulls_expectedFiveCards() {
		// given
		TableCards tableCards = createTableCards();
		tableCards.selectStreets(null, null, null, null);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenLoadTurnAsLastStreet_expectedFourCards() {
		// given
		TableCards tableCards = createTableCards();
		tableCards.selectStreets(null, null, null, Street.TURN);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenLoadFlopAsLastStreet_expectedThreeCards() {
		// given
		TableCards tableCards = createTableCards();
		tableCards.selectStreets(null, null, null, Street.FLOP);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenSelectedFlop_expectedOnlyTurnAndRiverToBeVariable() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		tableCards.selectStreets(selectedFlop, null, null, null);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenSelectedFlopAndTurn_expectedOnlyRiverToBeVariable() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		tableCards.selectStreets(selectedFlop, selectedTurn, null, null);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelected_expectedNoVariable() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCards.selectStreets(selectedFlop, selectedTurn, selectedRiver, null);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isEqualTo(selectedRiver.getFirst());
	}

	@Test
	void dealTimes_whenAllStreetsSelectedButFlopIsLastStreetToDeal_expectedOnlyFlopCards() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCards.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.FLOP);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelectedButTurnIsLastStreetToDeal_expectedOnlyFlopAndTurnCards() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCards.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.TURN);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenSelectedFlopIsMoreThanThreeCards_expectedOnlyThreeFirstCardsOnFlop() {
		// given
		TableCards tableCards = createTableCards();
		Card card1 = Card.diamondOf(Face.ACE);
		Card card2 = Card.spadeOf(Face.ACE);
		Card card3 = Card.clubOf(Face.ACE);
		Card card4 = Card.heartOf(Face.KING);
		List<Card> selectedFlop = List.of(card1, card2, card3, card4);
		tableCards.selectStreets(selectedFlop, null, null, Street.FLOP);

		// when
		List<TableCardsOutput> outputs = dealOneTime(tableCards);

		// then
		assertThat(outputs).hasSize(1);
		TableCardsOutput output = outputs.getFirst();
		assertThat(output.flop()).containsExactly(card1, card2, card3);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelected_thenOnlyOneOutput() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCards.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.FLOP);

		// when
		List<TableCardsOutput> outputs = tableCards.dealTimes(100);

		// then
		assertThat(outputs).hasSize(1);
	}

	@Test
	void dealTimes_whenNotAllStreetsSelected_thenNrOfOutputsAsGiven() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		tableCards.selectStreets(selectedFlop, selectedTurn, null, null);
		int times = 7;

		// when
		List<TableCardsOutput> outputs = tableCards.dealTimes(times);

		// then
		assertThat(outputs).hasSize(times);
	}

	@Test
	void dealTimes_whenSelectedAllFourAcesOnFlopAndTurn_thenNoAcesOnRiver() {
		// given
		TableCards tableCards = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.ACE));
		tableCards.selectStreets(selectedFlop, selectedTurn, null, null);
		int times = 1000;

		// when
		List<TableCardsOutput> outputs = tableCards.dealTimes(times);

		// then
		assertThat(outputs).hasSize(times);
		Set<Face> faces = outputs.stream().map(TableCardsOutput::river).map(Card::face).collect(Collectors.toSet());
		assertThat(faces).doesNotContain(Face.ACE);
	}

	private TableCards createTableCards() {
		return new TableCards(Deck.fullDeck());
	}

	private List<TableCardsOutput> dealOneTime(TableCards tableCards) {
		return tableCards.dealTimes(1);
	}
}