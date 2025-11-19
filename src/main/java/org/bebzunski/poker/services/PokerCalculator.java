package org.bebzunski.poker.services;

import java.util.Arrays;
import java.util.List;

import org.bebzunski.poker.enums.Street;
import org.bebzunski.poker.hand.BestHandService;
import org.bebzunski.poker.output.HandMadeOddsService;
import org.bebzunski.poker.output.HandOddsOutput;
import org.bebzunski.poker.output.WinningHandOddsService;
import org.bebzunski.poker.output.WinningOddsOutput;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.pojo.BoardRunout;

import static org.bebzunski.poker.enums.Street.FLOP;
import static org.bebzunski.poker.enums.Street.RIVER;
import static org.bebzunski.poker.enums.Street.TURN;
import static org.springframework.util.CollectionUtils.isEmpty;

public class PokerCalculator {

	private final Deck deck;
	private final SelectedCardsComponent selectedCards;
	private final PlayersComponent playersComponent;
	private final TableCardsComponent tableCardsComponent;
	private final WinningHandOddsService winningHandOddsService;
	private final HandMadeOddsService handMadeOddsService;
	private final int maxPlayersOnTable;
	private final int cardsOnHand;
	private final int numberOfSimulations;
	private Street calculateTo;

	public static PokerCalculator holdem() {
		return new PokerCalculator(PokerConfiguration.fullDeckHoldem());
	}

	public static PokerCalculator omaha() {
		return new PokerCalculator(PokerConfiguration.omaha());
	}

	public static PokerCalculator shortDeckHoldem() {
		return new PokerCalculator(PokerConfiguration.shortDeckHoldem());
	}

	private PokerCalculator(PokerConfiguration configuration) {
		this.deck = configuration.getDeck();
		this.cardsOnHand = configuration.getCardsOnPlayersHand();
		this.maxPlayersOnTable = configuration.getMaxPlayersOnTable();
		this.numberOfSimulations = configuration.getNumberOfSimulations();
		BestHandService bestHandService = new BestHandService(configuration);
		this.playersComponent = new PlayersComponent(deck);
		this.tableCardsComponent = new TableCardsComponent(deck);
		this.winningHandOddsService = new WinningHandOddsService(bestHandService);
		this.handMadeOddsService = new HandMadeOddsService(bestHandService);
		this.selectedCards = new SelectedCardsComponent();
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

	public void flop(Card card1, Card card2, Card card3) {
		selectedCards.addFlop(card1, card2, card3);
	}

	public void turn(Card turnCard) {
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
		List<BoardRunout> outputs = tableCardsComponent.dealTimes(numberOfSimulations);
		return winningHandOddsService.build(playersComponent.getPlayersCards(), outputs);
	}

	public HandOddsOutput checkHandOdds() {
		insertSelectedCards();
		List<BoardRunout> outputs = tableCardsComponent.dealTimes(numberOfSimulations);
		return handMadeOddsService.build(playersComponent, outputs);
	}

	private void insertSelectedCards() {
		validateSelectedCards();
		deck.excludeCards(selectedCards.getDeadCards());
		playersComponent.selectPlayers(selectedCards.getPlayersCards());
		tableCardsComponent.selectStreets(selectedCards.getFlop(), selectedCards.getTurn(), selectedCards.getRiver(), calculateTo);
	}

	private void validateSelectedCards() {
		if (isEmpty(selectedCards.getPlayersCards())) {
			throw new IllegalArgumentException("No players selected");
		}
		CardValidator.throwWhenCardsAreDuplicated(selectedCards.getAll());
		CardValidator.throwWhenSelectedCardsAreOutsideCardsScope(selectedCards.getAll(), deck.getCardsScope());
	}
}
