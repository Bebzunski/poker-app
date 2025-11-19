package org.bebzunski.poker.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record BoardRunout(List<Card> flop, Card turn, Card river) {

	public List<Card> fetchTableCards() {
		List<Card> communityCards = new ArrayList<>(flop);
		Optional.ofNullable(turn).ifPresent(communityCards::add);
		Optional.ofNullable(river).ifPresent(communityCards::add);
		return communityCards;
	}
}
