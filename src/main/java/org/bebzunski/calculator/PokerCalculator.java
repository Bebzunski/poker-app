package org.bebzunski.calculator;

import java.util.Arrays;
import java.util.List;

import org.bebzunski.calculator.enums.Street;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.HandOddsOutput;
import org.bebzunski.calculator.pojo.TableCardsOutput;

import static org.bebzunski.calculator.enums.Street.FLOP;
import static org.bebzunski.calculator.enums.Street.RIVER;
import static org.bebzunski.calculator.enums.Street.TURN;
import static org.springframework.util.CollectionUtils.isEmpty;

public class PokerCalculator {

	private static final int NUMBER_OF_SIMULATIONS = 10_000;

	private final Deck deck;
	private final Players players;
	private final TableCards tableCards;
	private final SelectedCards selectedCards;
	private final int cardsOnHand;
	private final int maxPlayersOnTable;
	private Street calculateTo;

	public static PokerCalculator holdem() {
		Deck fullDeck = Deck.fullDeck();
		return new PokerCalculator(fullDeck, 2, 10);
	}

	public static PokerCalculator omaha() {
		Deck fullDeck = Deck.fullDeck();
		return new PokerCalculator(fullDeck, 4, 6);
	}

	public static PokerCalculator shortDeckHoldem() {
		Deck shortDeck = Deck.shortDeck();
		return new PokerCalculator(shortDeck, 2, 10);
	}

	private PokerCalculator(Deck deck, int cardsOnHand, int maxPlayersOnTable) {
		this.deck = deck;
		this.players = new Players(deck);
		this.tableCards = new TableCards(deck);
		this.selectedCards = new SelectedCards();
		this.cardsOnHand = cardsOnHand;
		this.maxPlayersOnTable = maxPlayersOnTable;
	}

	public void addPlayerCards(Card... cards) {
		List<Card> cardList = Arrays.stream(cards).distinct().toList();
		if (cardList.size() != cardsOnHand) {
			throw new IllegalStateException("Cards should be %d, but is: %d".formatted(cardsOnHand, cardList.size()));
		}
		if (selectedCards.getPlayersCards().size() == maxPlayersOnTable) {
			throw new IllegalStateException("Too many players. Max is %d".formatted(maxPlayersOnTable));
		}
		selectedCards.addPlayerCards(cardList);
	}

	public void addDeadCards(Card... cards) {
		selectedCards.addDeadCards(cards);
	}

	public void addFlop(Card card1, Card card2, Card card3) {
		selectedCards.addFlop(card1, card2, card3);
	}

	public void addTurn(Card turnCard) {
		selectedCards.addTurn(turnCard);
	}

	public void addRiver(Card riverCard) {
		selectedCards.addRiver(riverCard);
	}

	public void calculateToFlop() {
		calculateTo = FLOP;
	}

	public void calculateToTurn() {
		calculateTo = TURN;
	}

	public void calculateToRiver() {
		calculateTo = RIVER;
	}

	public WinningOddsOutput checkWinningOdds() {
		insertSelectedCards();
		List<TableCardsOutput> outputs = tableCards.dealTimes(NUMBER_OF_SIMULATIONS);
		return WinningOddsBuilder.build(players, outputs);
	}

	public HandOddsOutput checkHandOdds() {
		insertSelectedCards();
		List<TableCardsOutput> outputs = tableCards.dealTimes(NUMBER_OF_SIMULATIONS);
		return HandOddsBuilder.build(players, outputs);
	}

	private void insertSelectedCards() {
		validateSelectedCards();
		deck.excludeCards(selectedCards.getDeadCards());
		players.selectPlayers(selectedCards.getPlayersCards());
		tableCards.selectStreets(selectedCards.getFlop(), selectedCards.getTurn(), selectedCards.getRiver(), calculateTo);
	}

	private void validateSelectedCards() {
		if (isEmpty(selectedCards.getPlayersCards())) {
			throw new IllegalArgumentException("No players selected");
		}
		CardValidator.throwWhenCardsAreDuplicated(selectedCards.getAll());
	}
}
