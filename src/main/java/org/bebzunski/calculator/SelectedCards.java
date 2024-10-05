package org.bebzunski.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bebzunski.calculator.pojo.Card;

import static java.util.stream.Collectors.toList;
import static org.bebzunski.calculator.enums.Street.FLOP;
import static org.bebzunski.calculator.enums.Street.RIVER;
import static org.bebzunski.calculator.enums.Street.TURN;

public class SelectedCards {

	private final List<List<Card>> selectedPlayersCards = new ArrayList<>();
	private final List<Card> selectedFlop = new ArrayList<>(FLOP.getNrCardsToDeal());
	private final List<Card> selectedTurn = new ArrayList<>(TURN.getNrCardsToDeal());
	private final List<Card> selectedRiver = new ArrayList<>(RIVER.getNrCardsToDeal());
	private final Set<Card> selectedDeadCards = new HashSet<>();

	public void addPlayerCards(List<Card> cards) {
		selectedPlayersCards.add(cards);
	}

	public void addDeadCards(Card... cards) {
		selectedDeadCards.addAll(Arrays.asList(cards));
	}

	public void addFlop(Card card1, Card card2, Card card3) {
		selectedFlop.clear();
		selectedFlop.add(card1);
		selectedFlop.add(card2);
		selectedFlop.add(card3);
	}

	public void addTurn(Card turnCard) {
		selectedTurn.clear();
		selectedTurn.add(turnCard);
	}

	public void addRiver(Card riverCard) {
		selectedRiver.clear();
		selectedRiver.add(riverCard);
	}

	public Set<Card> getDeadCards() {
		return selectedDeadCards;
	}

	public List<List<Card>> getPlayersCards() {
		return selectedPlayersCards;
	}

	public List<Card> getFlop() {
		return selectedFlop;
	}

	public List<Card> getTurn() {
		return selectedTurn;
	}

	public List<Card> getRiver() {
		return selectedRiver;
	}

	public List<Card> getAll() {
		List<Card> selectedCards = selectedPlayersCards.stream().flatMap(Collection::stream).collect(toList());
		selectedCards.addAll(selectedFlop);
		selectedCards.addAll(selectedTurn);
		selectedCards.addAll(selectedRiver);
		selectedCards.addAll(selectedDeadCards);
		return selectedCards;
	}
}
