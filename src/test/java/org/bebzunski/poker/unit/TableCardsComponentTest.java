package org.bebzunski.poker.unit;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bebzunski.poker.services.Deck;
import org.bebzunski.poker.services.TableCardsComponent;
import org.bebzunski.poker.enums.Face;
import org.bebzunski.poker.enums.Street;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.pojo.BoardRunout;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableCardsComponentTest {

	@Test
	void dealTimes_whenDidNotSelectStreets_expectedFiveDifferentCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenLoadNulls_expectedFiveCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		tableCardsComponent.selectStreets(null, null, null, null);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenLoadTurnAsLastStreet_expectedFourCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		tableCardsComponent.selectStreets(null, null, null, Street.TURN);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenLoadFlopAsLastStreet_expectedThreeCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		tableCardsComponent.selectStreets(null, null, null, Street.FLOP);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).hasSize(3);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenSelectedFlop_expectedOnlyTurnAndRiverToBeVariable() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		tableCardsComponent.selectStreets(selectedFlop, null, null, null);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isNotNull();
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenSelectedFlopAndTurn_expectedOnlyRiverToBeVariable() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, null, null);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isNotNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelected_expectedNoVariable() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, selectedRiver, null);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isEqualTo(selectedRiver.getFirst());
	}

	@Test
	void dealTimes_whenAllStreetsSelectedButFlopIsLastStreetToDeal_expectedOnlyFlopCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.FLOP);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelectedButTurnIsLastStreetToDeal_expectedOnlyFlopAndTurnCards() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.TURN);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsAll(selectedFlop);
		assertThat(output.turn()).isEqualTo(selectedTurn.getFirst());
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenSelectedFlopIsMoreThanThreeCards_expectedOnlyThreeFirstCardsOnFlop() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		Card card1 = Card.diamondOf(Face.ACE);
		Card card2 = Card.spadeOf(Face.ACE);
		Card card3 = Card.clubOf(Face.ACE);
		Card card4 = Card.heartOf(Face.KING);
		List<Card> selectedFlop = List.of(card1, card2, card3, card4);
		tableCardsComponent.selectStreets(selectedFlop, null, null, Street.FLOP);

		// when
		List<BoardRunout> outputs = dealOneTime(tableCardsComponent);

		// then
		assertThat(outputs).hasSize(1);
		BoardRunout output = outputs.getFirst();
		assertThat(output.flop()).containsExactly(card1, card2, card3);
		assertThat(output.turn()).isNull();
		assertThat(output.river()).isNull();
	}

	@Test
	void dealTimes_whenAllStreetsSelected_thenOnlyOneOutput() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		List<Card> selectedRiver = List.of(Card.heartOf(Face.QUEEN));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, selectedRiver, Street.FLOP);

		// when
		List<BoardRunout> outputs = tableCardsComponent.dealTimes(100);

		// then
		assertThat(outputs).hasSize(1);
	}

	@Test
	void dealTimes_whenNotAllStreetsSelected_thenNrOfOutputsAsGiven() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.KING));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, null, null);
		int times = 7;

		// when
		List<BoardRunout> outputs = tableCardsComponent.dealTimes(times);

		// then
		assertThat(outputs).hasSize(times);
	}

	@Test
	void dealTimes_whenSelectedAllFourAcesOnFlopAndTurn_thenNoAcesOnRiver() {
		// given
		TableCardsComponent tableCardsComponent = createTableCards();
		List<Card> selectedFlop = List.of(Card.diamondOf(Face.ACE), Card.spadeOf(Face.ACE), Card.clubOf(Face.ACE));
		List<Card> selectedTurn = List.of(Card.heartOf(Face.ACE));
		tableCardsComponent.selectStreets(selectedFlop, selectedTurn, null, null);
		int times = 1000;

		// when
		List<BoardRunout> outputs = tableCardsComponent.dealTimes(times);

		// then
		assertThat(outputs).hasSize(times);
		Set<Face> faces = outputs.stream().map(BoardRunout::river).map(Card::face).collect(Collectors.toSet());
		assertThat(faces).doesNotContain(Face.ACE);
	}

	private TableCardsComponent createTableCards() {
		return new TableCardsComponent(Deck.fullDeck());
	}

	private List<BoardRunout> dealOneTime(TableCardsComponent tableCardsComponent) {
		return tableCardsComponent.dealTimes(1);
	}
}