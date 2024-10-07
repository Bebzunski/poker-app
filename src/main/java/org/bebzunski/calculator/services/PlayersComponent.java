package org.bebzunski.calculator.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.PlayerCards;

import lombok.Getter;

@Getter
public class PlayersComponent {

	private final Deck deck;
	private final List<PlayerCards> playersCards = new ArrayList<>();

	public PlayersComponent(Deck deck) {
		this.deck = deck;
	}

	public void selectPlayers(List<List<Card>> selectedPlayersCards) {
		playersCards.clear();
		selectedPlayersCards.stream().map(HashSet::new).forEach(twoCards -> {
			playersCards.add(new PlayerCards(twoCards));
			deck.excludeCards(twoCards);
		});
	}

	public PlayerCards getFirst() {
		return playersCards.getFirst();
	}
}
