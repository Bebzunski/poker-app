package org.bebzunski.calculator.pojo;

import java.util.Set;
import java.util.stream.Collectors;

public record PlayerCards(Set<Card> cards) {

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PlayerCards playerCards)) {
			return false;
		}
		return cards.equals(playerCards.cards);
	}

	@Override
	public String toString() {
		return cards.stream().sorted().map(Card::toString).collect(Collectors.joining());
	}
}
