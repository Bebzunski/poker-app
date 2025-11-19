package org.bebzunski.poker.pojo;

import java.util.Set;
import java.util.stream.Collectors;

public record PlayerCards(Set<Card> cards) {

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PlayerCards(Set<Card> cards1))) {
			return false;
		}
		return cards.equals(cards1);
	}

	@Override
	public String toString() {
		return cards.stream().sorted().map(Card::toString).collect(Collectors.joining());
	}
}
