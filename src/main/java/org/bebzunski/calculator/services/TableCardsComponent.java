package org.bebzunski.calculator.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.bebzunski.calculator.enums.Street;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.TableCardsIteration;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

import static org.bebzunski.calculator.enums.Street.FLOP;
import static org.bebzunski.calculator.enums.Street.PRE_FLOP;
import static org.bebzunski.calculator.enums.Street.RIVER;
import static org.bebzunski.calculator.enums.Street.TURN;

public class TableCardsComponent {

	private final Deck deck;
	private List<StreetCards> streetCards;
	private Street lastStreetToDeal = RIVER;

	public TableCardsComponent(@NonNull Deck deck) {
		this.deck = deck;
		this.streetCards = loadStreetCards(List.of(), List.of(), List.of());
	}

	public void selectStreets(List<Card> selectedFlop, List<Card> selectedTurn, List<Card> selectedRiver, Street selectedLastStreetToDeal) {
		List<Card> flop = normalize(selectedFlop, FLOP);
		List<Card> turn = normalize(selectedTurn, TURN);
		List<Card> river = normalize(selectedRiver, RIVER);
		lastStreetToDeal = Optional.ofNullable(selectedLastStreetToDeal).orElse(RIVER);

		validateInputs(flop, turn, river);
		streetCards = loadStreetCards(flop, turn, river);
		streetCards.forEach(sc -> deck.excludeCards(sc.getCards()));
	}

	public List<TableCardsIteration> dealTimes(int times) {
		int actualNrOfSimulations = streetCards.stream().allMatch(StreetCards::isSelected) ? 1 : times;
		return IntStream.range(0, actualNrOfSimulations).mapToObj(s -> deal()).toList();
	}

	private TableCardsIteration deal() {
		deck.loadFresh();
		dealVariableCards();
		return new TableCardsIteration(getFlop(), getCard(TURN), getCard(RIVER));
	}

	private Card getCard(Street street) {
		return streetCards.stream().filter(sc -> sc.getStreet() == street).map(StreetCards::getCards).flatMap(Collection::stream)
				.findFirst().orElse(null);
	}

	private List<Card> getFlop() {
		return streetCards.stream().filter(sc -> sc.getStreet() == FLOP).map(StreetCards::getCards).findFirst().orElse(List.of());
	}

	private void dealVariableCards() {
		streetCards.stream().filter(StreetCards::isVariable).forEach(sc -> {
			int cardsToDeal = sc.getStreet().getNrCardsToDeal();
			List<Card> dealtCards = deck.dealCards(cardsToDeal);
			sc.setCards(dealtCards);
		});
	}

	private List<StreetCards> loadStreetCards(List<Card> flop, List<Card> turn, List<Card> river) {
		List<StreetCards> streetCards = new ArrayList<>();
		createStreetCardIfMakesSense(FLOP, flop).ifPresent(streetCards::add);
		createStreetCardIfMakesSense(TURN, turn).ifPresent(streetCards::add);
		createStreetCardIfMakesSense(RIVER, river).ifPresent(streetCards::add);
		return streetCards;
	}

	private Optional<StreetCards> createStreetCardIfMakesSense(Street street, List<Card> cards) {
		return street.isLaterThan(lastStreetToDeal) ? Optional.empty() : Optional.of(new StreetCards(street, cards));
	}

	private List<Card> normalize(List<Card> selectedCards, Street street) {
		return Optional.ofNullable(selectedCards).orElse(List.of()).stream().limit(street.getNrCardsToDeal()).toList();
	}

	private void validateInputs(List<Card> flop, List<Card> turn, List<Card> river) {
		if (lastStreetToDeal == PRE_FLOP) {
			throw new IllegalArgumentException("Cannot calculate to pre flop. At least has to be flop.");
		}
		if (Set.of(1, 2).contains(flop.size())) {
			throw new IllegalArgumentException("Flop has selected %s card(s), should be 0 or 3".formatted(flop.size()));
		}
		if (flop.isEmpty() && (!turn.isEmpty() || !river.isEmpty())) {
			throw new IllegalArgumentException("Missing flop cards");
		}
		if (turn.isEmpty() && !river.isEmpty() && Set.of(TURN, RIVER).contains(lastStreetToDeal)) {
			throw new IllegalArgumentException("Missing turn card");
		}
	}

	@Setter
	@Getter
	private static class StreetCards {

		private final Street street;
		private List<Card> cards;
		private final boolean selected;

		public StreetCards(Street street, List<Card> cards) {
			this.street = street;
			this.cards = cards;
			this.selected = !cards.isEmpty();
		}

		public boolean isVariable() {
			return !isSelected();
		}
	}
}
